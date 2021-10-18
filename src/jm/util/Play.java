/*
 
 <This Java Class is part of the jMusic API version 1.65, March 2017.>
 
 Copyright (C) 2000 Andrew Sorensen & Andrew Brown
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or any
 later version.
 
 This program is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

 */ 

package jm.util;

import jm.midi.MidiSynth;
import jm.music.data.*;
import jm.JMC;
import jm.audio.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Vector;

/*  Enhanced by the Derryn McMaster 2003 */
/*  Further updated by Andrew R Brown 2011 and 2012*/

public class Play implements JMC {
	/**
     * True at the index of a midiCycle that is currently playing
     */
	private static boolean cyclePlaying = false;
    /** A thread started to time the duration of playbnack */
    private static Thread pauseThread;
	/** A instance of the jMusic's JavaSound MIDI playback class */
	//private static Vector ms = new Vector();
	// for reading audio files for playback
	private static AudioInputStream audioInputStream;
	// mixer for real time audio playback
	private static Vector mixerList = new Vector();
	/** A flag for real time audio currently playing */
	private static boolean audioPlaying = false;
	/** A flag to indicate audio playback has been initiated but halted */
	private static boolean audioPaused = false;
	//
	private static RTMixer mixer;
	//
	private static MidiSynth currentMidiSynth;
	
	/**
     * Constructor
     */
	public Play() {	}

	/**
     * Used by infinite cycle player threads to check
     * cyclePlaying status.
     */
	public static boolean cycleIsPlaying(){
		return cyclePlaying;
	}
	
    /**
     * Thread.sleeps for a period of 1 score play length
     * (i.e. the time it would take for the specified 
     * score to play).
     * Can be used in conjunction with midiCycle() if the 
     * score requires a re-compute just before being
     * replayed.  (i.e. sleeps for one loop)  Should 
     * be placed immediately after the Play.midiCycle() 
     * command to ensure that the time waited is for 
     * the currently playing score, and not subject 
     * to any changes made to the score since.
     * @param score The score used for timing the sleep.
     */

	/**
     * Playback a MIDI file from disk.
     * @param fileName The name of the file to play back.
     */
    public static void mid(String fileName) {
        Score score = new Score();
        Read.midi(score, fileName);
        Play.midi(score);
    }
	
    /**
     * Playback the jMusic note using JavaSound MIDI
     * @param n The note to be played
     */
	public static void midi(Note n) {
        midi(new Phrase(n));
	}	
    
	/**
     * Playback the jMusic phrase using JavaSound MIDI
     * @param phr The Phrase to be played
     */
	public static void midi(Phrase phr) {
        Part p = new Part(phr);
        if (phr.getTempo() == Phrase.DEFAULT_TEMPO) p.setTempo(120);
        midi(p);
	}
    
	/**
     * Playback the jMusic part using JavaSound MIDI
     * @param p The Part to be played
     */
	public static void midi(Part p) {
        midi(new Score(p));
	}
		
 	/**
     * Playback the jMusic score JavaSound MIDI.
     * @param score The score to be played.
     */ 
	public static void midi(Score score) {
		currentMidiSynth = new MidiSynth(); 
		if (currentMidiSynth.isPlaying()) currentMidiSynth.stop();
		try {
			currentMidiSynth.play(score);
		} catch (Exception e) {
			System.err.println("jMusic Play: MIDI Playback Error:" + e);
			return;
		}
	}
	
	/**
	* Refresh the JavaSound MIDI playback with a new score.
	* Only works when midiCycle() is operating and 
	* updates take effect at the start of the next cycle.
    * @param s The score to be used as the update.
	* @param index The id of the MidiSynth to update.
	*/	
	public static void updateScore(Score s){
		try {
			currentMidiSynth.updateSeq(s);
		} catch (Exception e) {
			System.err.println("jMusic Play class can't update MIDI sequence:" + e);
			return;
		}
	}
	
	/**
	* Halt real time audio playback.
	*/
	public static void pauseAudio() {
		for(int i=0; i<mixerList.size(); i++) {
			((RTMixer)mixerList.elementAt(i)).pause();
		}
		audioPaused = true;
	}
	
	/**
	* Restart real time audio playback after being paused.
	*/
	public static void unPauseAudio() {
		if (audioPaused) {
			for(int i=0; i<mixerList.size(); i++) {
				((RTMixer)mixerList.elementAt(i)).unPause();
			}
			audioPaused = false;
		} else {
			System.err.println("Play.unPauseAudio error: audio playback was not previously paused.");
		}
	}
	
	/**
	* End real time audio playback immediatly.
	*/
	public static void stopAudio() {
		for(int i=0; i<mixerList.size(); i++) {
			((RTMixer)mixerList.elementAt(i)).stop();
		}
		audioPaused = false;
		audioPlaying = false;
	}
	
	/**
	* End JavaSound MIDI playback immediatly.
	* For Play.stopMidi() to be able to take effect you need to add a flag 
	* when calling Play.midi to tell it not to create a Thread that holds open 
	* the program for the duration of playback - so this assumes you have some  
	* other persistent activity in your program (such as a GUI). 
	* e.g., Play.midi(myScore, false); later... Play.midiStop();
	* Call closeAll() after stopping if ready to exit application.
	*/
    public static void stopMidi() {
		if (currentMidiSynth != null) {
			currentMidiSynth.stop();
			currentMidiSynth.finalize();
		}
	}


	/**
     * Halt the infinite midiCycle() at the end of the next cycle.
	 * Call closeAll() after stopping if ready to exit application.
     */
	public static void stopMidiCycle() {
		if (currentMidiSynth != null) {
			currentMidiSynth.setCycle(false);
			//stopMidi();
		}
		cyclePlaying = false;
	}
	
	/**
    * Repeated playback the jMusic note via the JavaSound MIDI synthesizer
    * @param n The note to be played. See midiCycle(Score s)
	* @param index The midiCycle id to be used - default is 0.
    */
	public static void midiCycle(Note n) {
		Phrase phr = new Phrase(n);
		phr.setTempo(120);
        midiCycle(phr);
	}	
    
	/**
     * Repeated playback the jMusic phrase via the JavaSound MIDI synthesizer
     * @param phr The Phrase to be played. See midiCycle(Score s)
     */
	public static void midiCycle(Phrase phr) {
		Part p = new Part(phr);
        midiCycle(p);
	}
    
	/**
     * Repeated playback the jMusic part via the JavaSound MIDI synthesizer
     * @param part The Part to be played. See midiCycle(Score s)
     */
	public static void midiCycle(Part part) {
		Score s = new Score(part);
        midiCycle(s);
	}
    
	
	/**
     * Continually repeat playback of a Score object (i.e., loop playback).
	* @param score The score to played back repeatedly.
     */
	public static void midiCycle(Score score){
		currentMidiSynth = new MidiSynth(); 
		if (currentMidiSynth.isPlaying()) currentMidiSynth.stop();
        cyclePlaying = true;
		System.out.println("jMusic Play: Starting cycle playback");
		try {
			currentMidiSynth.setCycle(true);
			currentMidiSynth.play(score);
		}
		catch (Exception e) {
			System.err.println("MIDI Playback Error:" + e);
			return;
		}
	}
	
    /**
     * Playback an audio file via javaSound.
     * This method requires the javax.sound packages in Java 1.3 or higher.
     * @param fileName The name of the file to be played.
     */ 
    public static void au(String fileName) {
        au(fileName, true);
    }
	
    /**
     * Playback an audio file via javaSound.
     * jMusic currently supports playback of .au, .wav and .aif file formats.
     * This method requires the javax.sound packages in Java 1.3 or higher.
     * By default this method, when complete, will continue the application. 
	* Careful that the application does not end, preventing the file from playing.
	* To keep the application open during playback pass 'false' as the autoClose argument.
     * @param filename The name of the file to be played.
     * @param autoClose A flag for exiting java after the file has played.
     */ 
    public static void au(String fileName, boolean autoClose) {
        jm.gui.wave.WaveFileReader afr = new jm.gui.wave.WaveFileReader(fileName);
        jm.music.rt.RTLine[] lineArray = {new jm.util.AudioRTLine(fileName)};	
        jm.audio.RTMixer mixer = new jm.audio.RTMixer(lineArray) ;//, 4096, si.getSampleRate(), si.getChannels(), 0.01);	
            mixer.begin();
            System.out.println("---------- Playing '" + fileName + "'... Sample rate = "
                               + afr.getSampleRate() + " Channels = " + afr.getChannels() + " ----------");
            if (autoClose) {
                java.io.File audioFile = new java.io.File(fileName);
                try {
                    int byteSize = afr.getBits() - 1;
                    // bytes, sample rate, channels, milliseconds, cautious buffer
                    Thread.sleep((int)((double)audioFile.length() / byteSize / 
                                       afr.getSampleRate() / afr.getChannels() * 1000.0));
                } catch (InterruptedException e) {
                    System.err.println("jMusic play.au error: Thread sleeping interupted");
                }
                System.out.println("-------------------- Completed Audio Playback ----------------------");
                System.exit(0); // horrid, but less confusing for beginners
            }
    }
	
	// audio file playback classes adapted from the SoundCipher library; http:soundcipher.com

   /**
     * Playback a specified audio file using JavaSound.
	* Audio files are presumed tobe in teh same folder as the program source.
     * @param fileName Name of the audio file to play.
     */
	public static void audioFile(String fileName) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));//bis);
			new AudioFilePlayThread(audioInputStream).start();
			System.out.println("Playing audio file " + fileName);
		} catch (IOException ioe) {
			System.err.println("Play audioFile error: in playAudioFile(): " + ioe.getMessage());
		}
		catch (UnsupportedAudioFileException uafe) {
			System.err.println("Unsupported Audio File error: in Play.audioFile():" + uafe.getMessage());
		}
	}
    
	/**
     * Playback a note as real time audio.
     * @param note The phrase to be played.
     * @param inst An instrument to play the note with
     */
    public static void audio(Note note, Instrument inst) {
        audio(new Phrase(note), inst);
    }

    /**
     * Playback a phrase as real time audio.
     * @param phrase The phrase to be played.
     * @param insts An array of instruments to play the phrase with
     */
    public static void audio(Phrase phrase, Instrument[] insts) {
        audio(new Score(new Part(phrase)), insts);
    }
    
    /**
     * Playback a phrase as real time audio.
     * @param phrase The phrase to be played.
     * @param inst An instrument to play the phrase with
     */
    public static void audio(Phrase phrase, Instrument inst) {
        Part part = new Part(phrase);
        if(phrase.getTempo() != Phrase.DEFAULT_TEMPO) part.setTempo(phrase.getTempo());
        audio(part, new Instrument[] {inst});
    }
    
    /**
     * Playback a part as real time audio.
     * @param part The part to be played.
     * @param insts An array of instruments to play the part with
     */
    public static void audio(Part part, Instrument[] insts) {
        Score score = new Score(part);
        if(part.getTempo() != Part.DEFAULT_TEMPO) score.setTempo(part.getTempo());
        audio(score, insts);
    }

    /**
     * Playback a score as real time audio.
     * @param score The score to be played.
     * @param insts An array of instrument to play the score with
     */
    public static void audio(Score score, Instrument[] insts) {
		audioPlaying = true;
        System.out.print("Playing Score as Audio... ");
        // make all instrument real time
        for(int i=0; i<insts.length; i++) {
            insts[i].setOutput(Instrument.REALTIME);
        }
        // get all the phrases in a vector
        java.util.Vector v = new java.util.Vector();
        for(int i=0; i<score.size(); i++) {
            Part p = score.getPart(i);
            for(int j=0; j<p.size(); j++) {
                Phrase phr = p.getPhrase(j);
                if(phr.getInstrument() == Phrase.DEFAULT_INSTRUMENT) 
                    phr.setInstrument(p.getInstrument());
                if(phr.getTempo() == Phrase.DEFAULT_TEMPO) 
                    phr.setTempo(p.getTempo());
                v.addElement(phr);
            }
        }
        // create RTPhrases for each phrase
        jm.music.rt.RTLine[] lines = new jm.music.rt.RTLine[v.size()];
        for(int i=0; i<v.size(); i++) {
            Phrase phr = (Phrase)(v.elementAt(i));
            lines[i] = new jm.music.rt.RTPhrase(phr, insts[phr.getInstrument()]);
        }        
        // create mixer and wait for the end then pause the mixer
		if (mixer == null) {
			mixer = new RTMixer(lines);
			mixer.begin();
		} else {
			mixer.addLines(lines);
		}
    }
    
	// Spawn a thread to keep app alive during playback
    private static void audioWait(final Score score, final RTMixer mixer) {
        pauseThread = new Thread( new Runnable() {
            public void run() {
                try {
                    pauseThread.sleep((int)(score.getEndTime() * 60.0 / score.getTempo() * 1000.0));
                } catch (Exception e) {System.out.println("jMusic Play.audioWait error in pauseThread");}
                System.out.println("Completed audio playback.");
				//mixer.pause();
				audioPaused = true;
				try {
					Thread.sleep(500); // stop abrupt cutoff buzz
				} catch (InterruptedException e) {};
            }});
        pauseThread.start();
    }
    
    /**
     * Playback an audio file using Java Applet audioclip playback.
     * A audioClip limitation is that the file must be small enough to fit into RAM.
     * This method is compatibl with Java 1.1 and higher.
     * @param fileName The name of the file to be played.
     */ 
    public static void audioClip(String fileName) {
        System.out.println("-------- Playing an audio file ----------");
        System.out.println("Loading sound into memory, please wait...");
        java.io.File audioFile = new java.io.File(fileName);
        try {
	    java.net.URI tempURI = audioFile.toURI();
	    java.net.URL tempURL = tempURI.toURL();
            java.applet.AudioClip sound = java.applet.Applet.newAudioClip(tempURL);
            System.out.println("Playing '" + fileName + "' ...");
            sound.play();
        } catch (java.net.MalformedURLException e) {
            System.err.println("jMusic play.au error: malformed URL or filename");
        }
        try {
            // bytes, channels, sample rate, milliseconds, cautious buffer
            Thread.sleep((int)(audioFile.length() / 2.0 / 44100.0 / 2.0 * 1000.0) + 1000);
        } catch (InterruptedException e) {
            System.err.println("jMusic play.au error: Thread sleeping interupted");
        }
        System.out.println("-------------------- Completed Playback ----------------------");
        System.exit(0); // horrid but less confusing for beginners
	}
	
}


