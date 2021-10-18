package ast;

import exceptions.FailedStaticCheckException;
import jm.music.data.Score;
import jm.music.data.Phrase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Collections;

import static jm.music.tools.Mod.append;
import static jm.music.tools.Mod.repeat;

public class Evaluator implements Visitor<Void> {
    private static final String[] types = {"whole", "half", "quarter", "eighth", "16th", "32nd", "64th", "128th", "256"};
    private static final int REST = jm.music.data.Note.REST;
    private Score score;
    private int loopCounter;
    private int listPhraseSize;
    private int measureCounter;
    private jm.music.data.Part tempPart;
    private Phrase tempPhrase;
    private Phrase previousPhrases;
    private jm.music.data.Note tempNote;
    private HashMap<String, Integer> pitchMap = new HashMap<>();
    private HashMap<String, Integer> instMap = new HashMap<>();

    private String musicXMLPre;
    private String musicXMLPartList;
    private ArrayList<String> musicXMLParts;
    private String musicXMLPost;
    private String measureAttributes;
    private String measureXML;
    private String loopXML;
    private String noteXML;
    private String partXML;
    private int partCounter;
    private int measureCounterXML;
    private ArrayList<Integer> measureCounts;
    private int currMeasures;
    private boolean noLoop;
    private boolean loop;
    private boolean loopFirstMeasureDone;

    public Evaluator(Score score) {
        this.score = score;
        this.tempPart = null;
        this.tempPhrase = null;
        musicXMLPre = "";
        musicXMLPartList = "";
        musicXMLParts = new ArrayList<>();
        musicXMLPost = "";
        measureAttributes = "";
        measureXML = "";
        noteXML = "";
        loopXML = "";
        partXML = "";
        partCounter = 0;
        measureCounter = 0;
        measureCounts = new ArrayList<>();
        currMeasures = 0;
        noLoop = false;
        loop = false;
        loopFirstMeasureDone = false;
    }

    // JMusic structure
    // Score (Contains any number of Parts)
    //   |
    //   +---- Part (Contains any number of Phrases)
    //           |
    //           +---- Phrase (Contains any number of Notes.)
    //                    |
    //                    +---- Note (Holds information about a single musical event.)

    public Score getScore() {
        return this.score;
    }

    @Override
    public Void visit(Clef c) {
        String clefSymbol = "G";
        int staffLine = 2;
        if (c == Clef.bass) {
            clefSymbol = "F";
            staffLine = 4;
        }
        measureAttributes += "<clef><sign>" + clefSymbol + "</sign><line>" + staffLine + "</line></clef>";
        return null;
    }

    @Override
    public Void visit(Key k) {
        KeyType kt = k.getKeyType();
        int quality = 0;
        if (kt == KeyType.MINOR) {
            quality = 1;
        }
        tempPart.setKeyQuality(quality);
        // Process number of sharps and flats based on quality/note
        int keySig = 0;
        String sigNote = k.getNote();
        AccidentalType keyAcc = k.getAccidentalType();
        if (kt == KeyType.MAJOR) {
            if (sigNote.equals("A")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -4;
                } else {
                    keySig = 3;
                }
            } else if (sigNote.equals("B")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -2;
                } else {
                    keySig = 5;
                }
            } else if (sigNote.equals("C")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                } else {
                    keySig = 0;
                }
            } else if (sigNote.equals("D")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.equals("E")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -3;
                } else {
                    keySig = 4;
                }
            } else if (sigNote.equals("F")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.equals("G")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            }
        } else if (kt == KeyType.MINOR) {
            if (sigNote.equals("A")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                }
            } else if (sigNote.equals("B")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.equals("C")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 4;
                } else {
                    keySig = -3;
                }
            } else if (sigNote.equals("D")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.equals("E")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            } else if (sigNote.equals("F")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 3;
                } else {
                    keySig = -4;
                }
            } else if (sigNote.equals("G")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 5;
                } else {
                    keySig = -2;
                }
            }
        }
        tempPart.setKeySignature(keySig);

        measureAttributes += "<key><fifths>" + keySig + "</fifths></key>";

        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        if (noLoop && currMeasures == 0) {
            measureXML += "<measure number=\"" + measureCounterXML + "\">" + measureAttributes;
        } else if (loop) {
            if (currMeasures == 0 && !loopFirstMeasureDone) {
                loopXML += "<measure number=\"" + measureCounterXML + "\">" + measureAttributes;
                measureXML += "<measure number=\"" + measureCounterXML + "\">" + "<attributes><divisions>64</divisions></attributes>";
                loopFirstMeasureDone = true;
            } else {
                loopXML += "<measure number=\"" + measureCounterXML + "\">" + "<attributes><divisions>64</divisions></attributes>";
                measureXML += "<measure number=\"" + measureCounterXML + "\">" + "<attributes><divisions>64</divisions></attributes>";
            }
        } else {
            measureXML += "<measure number=\"" + measureCounterXML + "\">" + "<attributes><divisions>64</divisions></attributes>";
        }
        tempPhrase = new Phrase();
        for (Note note : m.getNotes()) {
            noteXML = "";
            note.accept(this);
            if (loop) {
                loopXML += noteXML;
            }
            measureXML += noteXML;
        }
        measureXML += "</measure>";
        if (loop) {
            loopXML += "</measure>";
        }
        // if know that it is loop use repeat
        if (loopCounter != 0) {
            if (measureCounter < listPhraseSize) {
                append(previousPhrases, tempPhrase);
                return null; //break before tempPart adding
            } else if (measureCounter == listPhraseSize) {
                append(previousPhrases, tempPhrase);
                repeat(previousPhrases, loopCounter);
                measureXML = measureXML.repeat(loopCounter - 1);
                measureXML = loopXML + measureXML;
                tempPhrase = previousPhrases;
            }
        }
        loopFirstMeasureDone = false;
        measureCounterXML++;
        partXML += measureXML;
        tempPart.add(tempPhrase);
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        musicXMLPartList += "<part-name>" + n.getName() + "</part-name>";
        tempPart.setTitle(n.getName());
        if (instMap.isEmpty()) {
            instMap = getInstrumentMap();
        }
        String instName = n.getName().replaceAll(" ", "_").toUpperCase(Locale.ROOT);
        if (instMap.containsKey(instName)) {
            tempPart.setInstrument(instMap.get(instName));
        } else {
            throw new FailedStaticCheckException("Instrument: " + n.getName() + "was not found");
        }
        return null;
    }

    private HashMap<String, Integer> getInstrumentMap() throws IllegalAccessException {
        // Given instrument name can be searched in JMusic's instrument library
        // https://stackoverflow.com/questions/22230787/string-values-of-field-constants-in-java
        // https://stackoverflow.com/questions/9700081/in-java-how-to-iterate-on-the-constants-of-an-interface
        HashMap<String, Integer> instMap = new HashMap<>();
        for (Field f : jm.constants.ProgramChanges.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) { // Check if the field is public
                instMap.put(f.getName(), (Integer) f.get(null));
            }
        }
        return instMap;
    }

    @Override
    public Void visit(Note n) throws IllegalAccessException {
        noteXML += "<note>";
        NoteType noteType = n.getType();
        String octave = n.getOctave();
        String division = n.getDivision().replace("$", "");
        int numDiv = Integer.parseInt(division);
        double rhythmValue = (double) tempPart.getNumerator() / numDiv;
        int dotCount = 0;
        if (n.getDots() != null) {
            dotCount = n.getDots().length();
        }
      
        int div = Integer.parseInt(division);
        int typeCount = 0;
        for (int i = div; i > 1; i /= 2) {
            typeCount++;
        }
        int duration = 256 / div;
        for (int i = 0; i < dotCount; i++) {
            div *= 2;
            duration += 256 / div;
        }
        String durationAndType = "<duration>" + duration + "</duration><type>" + types[typeCount] + "</type>";
        for (int i = 0; i < dotCount; i++) {
            durationAndType += "<dot/>";
        }
        // Check whether note is letter or rest
        if (noteType == NoteType.rest) {
            noteHelper(REST, rhythmValue, dotCount);
            noteXML += "<rest/>" + durationAndType + "</note>";
            return null;
        }
        if (pitchMap.isEmpty()) {
            pitchMap = getPitchMap();
        }
        // pitchMap now contains all constant keys in String from Pitch interface
        String noteString = n.getLetter().toUpperCase(Locale.ROOT);
        noteXML += "<pitch><step>" + noteString + "</step>";
        AccidentalType accidental = n.getAccidental();
        if (accidental == AccidentalType.FLAT) {
            noteXML += "<alter>-1</alter>";
        } else if (accidental == AccidentalType.SHARP) {
            noteXML += "<alter>1</alter>";
        }

        // Add note Octave
        noteXML += "<octave>" + octave + "</octave></pitch>" + durationAndType + "</note>"

        // Need to account for nonexistent sharps/flats
        if (accidental != null && accidental == AccidentalType.SHARP) {
            noteString += "S";
        } else if (accidental != null && accidental == AccidentalType.FLAT) {
            noteString += "F";
        }
        noteString += octave;
        if (pitchMap.containsKey(noteString)) {
            noteHelper(pitchMap.get(noteString), rhythmValue, dotCount);
        } else {
            if (accidental == null) {
                throw new FailedStaticCheckException("Note: " + n.getLetter() + division + " was not found");
            } else {
                throw new FailedStaticCheckException("Note: " + n.getLetter() + n.getAccidental().name() + division + " was not found");
            }
        }
        return null;
    }

    private void noteHelper(int pitch, double rhythmValue, int dotCount) {
        if (dotCount == 0) {
            tempNote = new jm.music.data.Note(pitch, rhythmValue);
        } else {
            double extraRhythm = 0;
            for (int i = 0; i <= dotCount; i++) {
                extraRhythm += rhythmValue * Math.pow(0.5, i); // Extra length for dotted notes
            }
            tempNote = new jm.music.data.Note(pitch, extraRhythm);
        }
        tempPhrase.addNote(tempNote);
    }

    private HashMap<String, Integer> getPitchMap() throws IllegalAccessException {
        HashMap<String, Integer> pitchMap = new HashMap<>();
        for (Field f : jm.constants.Pitches.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) { //Check if the field is public
                pitchMap.put(f.getName(), (Integer) f.get(null));
            }
        }
        return pitchMap;
    }

    @Override
    public Void visit(Part p) throws IllegalAccessException {
        musicXMLPartList += "<score-part id=\"" + partCounter + "\">";
        tempPart = new jm.music.data.Part();
        p.getName().accept(this);
        p.getSheet().accept(this);
        score.add(tempPart);
        musicXMLPartList += "</score-part>";
        partCounter++;
        return null;
    }

    @Override
    public Void visit(Program p) throws IllegalAccessException {
        musicXMLPre = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 4.0 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\"><score-partwise version=\"4.0\">";
        musicXMLPartList = "<part-list>";
        musicXMLPost = "";
        p.getTitle().accept(this);
        for (Part part : p.getParts()) {
            part.accept(this);
        }

        int maxMeasures = Collections.max(measureCounts);
        for (int i = 0; i < musicXMLParts.size(); i++) {
            musicXMLPost += "<part id=\"" + i + "\">";
            int thisMeasures = measureCounts.get(i);
            String thisPartXML = musicXMLParts.get(i);
            while (thisMeasures < maxMeasures) {
                thisPartXML += "<measure></measure>";
                thisMeasures++;
            }
            musicXMLPost += thisPartXML + "</part>";
            ;
        }
        musicXMLPartList += "</part-list>";
        musicXMLPost += "</score-partwise>";
        return null;
    }

    @Override
    public Void visit(Sheet s) throws IllegalAccessException {
        currMeasures = 0;
        loop = false;
        noLoop = false;
        partXML = "";

        measureAttributes = "<attributes><divisions>64</divisions>";
        measureCounterXML = 1;
        tempPart.setNumerator(s.getTimeNum());
        tempPart.setDenominator(s.getTimeDem());
        s.getKey().accept(this);
        measureAttributes += "<time><beats>" + s.getTimeNum() + "</beats><beat-type>" + s.getTimeDem() + "</beat-type></time>";
        s.getClef().accept(this);
        measureAttributes += "</attributes>";
        // Will need this when we do error checking
        for (Object measures : s.getMeasures()) {
            measureXML = "";
            if (measures instanceof Loop) {
                loop = true;
                ((Loop) measures).accept(this);
                loop = false;
            } else {
                loopCounter = 0;
                noLoop = true;
                ((Measure) measures).accept(this);
                noLoop = false;
                currMeasures++;
            }
        }
        musicXMLParts.add(partXML);
        measureCounts.add(currMeasures);
        return null;
    }

    @Override
    public Void visit(Title t) {
        musicXMLPre += "<work><work-title>" + t.getTitle() + "</work-title></work>";
        score.setTitle(t.getTitle());
        return null;
    }


    @Override
    public Void visit(Loop l) throws IllegalAccessException {
        // Note: Phrase used in JMusic is represented by our Measure class
        previousPhrases = new Phrase();
        loopCounter = l.getCount(); // How many times the user specified the looping
        List<Measure> measures = l.getMeasures();
        listPhraseSize = measures.size(); // # of measures in the whole loop
        for (Measure measure : measures) {
            measureCounter++;
            measure.accept(this);
        }
        currMeasures += loopCounter * listPhraseSize;
        measureCounter = 0;
        return null;
    }

    public String getMusicXML() {
        return musicXMLPre + musicXMLPartList + musicXMLPost;
    }
}
