package ast;

import jm.music.data.Score;
import jm.music.data.Phrase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static jm.music.tools.Mod.append;
import static jm.music.tools.Mod.repeat;

public class Evaluator implements Visitor<Void> {
    private static final String[] types = {"whole", "half", "quarter", "eighth", "16th", "32nd", "64th", "128th", "256"};
    private static final int REST = jm.music.data.Note.REST;
//    private static final double DEFAULT_RHYTHM_VALUE = jm.music.data.Note.DEFAULT_RHYTHM_VALUE;
    private Score score;
    private int loopCounter;
    private int listPhraseSize;
    private int measureCounter;
    private jm.music.data.Part tempPart;
    private Phrase tempPhrase;
    private HashMap<String, Integer> pitchmap = new HashMap<>();
    private HashMap<String, Integer> instmap = new HashMap<>();
    private Phrase previousPhrases;

    private String musicXMLPre;
    private String musicXMLPartList;
    private ArrayList<String> musicXMLParts;
    private String musicXMLPost;
    private String measureAttributes;
    private String measureXML;
    private String partXML;
    private int partCounter;
    private int measureCounterXML;
    private int baseOctave;
    private ArrayList<Integer> measureCounts;
    private int currMeasures;

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
        partXML = "";
        partCounter = 0;
        measureCounter = 0;
        baseOctave = 5;
        measureCounts = new ArrayList<>();
        currMeasures = 0;
    }

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
            baseOctave = 3;
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
        //score.setKeyQuality(quality); // this sets score, should be setting part instead
        tempPart.setKeyQuality(quality);
        // Process number of sharps and flats based on quality/note

        int keySig = 0;
        Note sigNote = k.getNote();
        AccidentalType keyAcc = sigNote.getAccidental();
        if (kt == KeyType.MAJOR) {
            if (sigNote.getLetter().equals("A")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -4;
                } else {
                    keySig = 3;
                }
            } else if (sigNote.getLetter().equals("B")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -2;
                } else {
                    keySig = 5;
                }
            } else if (sigNote.getLetter().equals("C")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                } else {
                    keySig = 0;
                }
            } else if (sigNote.getLetter().equals("D")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.getLetter().equals("E")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -3;
                } else {
                    keySig = 4;
                }
            } else if (sigNote.getLetter().equals("F")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.getLetter().equals("G")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            }
        } else if (kt == KeyType.MINOR) {
            if (sigNote.getLetter().equals("A")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                } else {
                    keySig = 0;
                }
            } else if (sigNote.getLetter().equals("B")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.getLetter().equals("C")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 4;
                } else {
                    keySig = -3;
                }
            } else if (sigNote.getLetter().equals("D")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.getLetter().equals("E")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            } else if (sigNote.getLetter().equals("F")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 3;
                } else {
                    keySig = -4;
                }
            } else if (sigNote.getLetter().equals("G")) {
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
        measureXML += "<measure number=\"" + measureCounterXML + "\">" + measureAttributes;
        tempPhrase = new Phrase();
        for(Note note : m.getNotes()) {
            note.accept(this);
        }
        measureXML += "</measure>";
        // if know that it is loop use repeat
        if (loopCounter != 0) {
            if (measureCounter < listPhraseSize){
                append(previousPhrases, tempPhrase);
                return null; //break before tempPart adding
            } else if (measureCounter == listPhraseSize) {
                append(previousPhrases, tempPhrase);
                repeat(previousPhrases, loopCounter);
                measureXML = measureXML.repeat(loopCounter);
                tempPhrase = previousPhrases;
            }
        }
        partXML += measureXML;
        tempPart.add(tempPhrase);
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        musicXMLPartList += "<part-name>" + n.getName() + "</part-name>";
        tempPart.setTitle(n.getName());
        if (instmap.isEmpty()) {
            instmap = getInstrumentMap();
        }
        String instName = n.getName().replaceAll(" ", "_").toUpperCase(Locale.ROOT);
        if (instmap.containsKey(instName)) {
            tempPart.setInstrument(instmap.get(instName));
        } else {
            System.out.println("Instrument: " + n.getName() + "was not found");
        }
        return null;
    }

    private HashMap<String, Integer> getInstrumentMap() throws IllegalAccessException {
        // name can be searched in instrument library
        // https://stackoverflow.com/questions/22230787/string-values-of-field-constants-in-java
        // https://stackoverflow.com/questions/9700081/in-java-how-to-iterate-on-the-constants-of-an-interface
        HashMap<String,Integer> map = new HashMap<>();
        for (Field f : jm.constants.ProgramChanges.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) { //check if the field is public
                map.put(f.getName(), (Integer) f.get(null));
            }
        }
        return map;
    }

    @Override
    public Void visit(Note n) throws IllegalAccessException {
        measureXML += "<note>";
        SubMeasureType noteType = n.getType();
        jm.music.data.Note temp;
        String octave = n.getOctave();
        String division = n.getDivision().replace("$","");
        int numDiv = Integer.parseInt(division);
        double rhythmValue = tempPart.getNumerator() / numDiv;
        int dotCount = 0;
        if (n.getDots() != null) {
            dotCount = n.getDots().length();
        }
        double extraRhythm = 0;
        // Check whether or not note is letter or rest

        int div = Integer.parseInt(division);
        int typeCount = 0;
        for (int i = div; i > 1; i/=2) {
            typeCount++;
        }
        int duration = 256/div;
        for (int i = 0; i < dotCount; i++) {
            div *= 2;
            duration += 256/div;
        }
        String durationAndType = "<duration>" + duration + "</duration><type>" + types[typeCount] + "</type>";
        for (int i = 0; i < dotCount; i++) {
            durationAndType += "<dot/>";
        }

        if (noteType == SubMeasureType.rest) {
            if (dotCount == 0) {
                temp = new jm.music.data.Note(REST, rhythmValue);
            } else {
                for (int i = 0; i <= dotCount; i++) {
                    extraRhythm += rhythmValue * Math.pow(0.5, i); // added length for dots
                }
                temp = new jm.music.data.Note(REST, extraRhythm);
            }
            tempPhrase.addNote(temp);
            measureXML += "<rest/>" + durationAndType + "</note>";
            return null;
        }
        if (pitchmap.isEmpty()) {
            pitchmap = getPitchMap();
        }
        // pitchmap now contains all string keys of the constants originally defined for ints
        // Note is a letter
        String noteString = n.getLetter();
        measureXML += "<pitch><step>" + noteString + "</step>";
        AccidentalType accidental = n.getAccidental();
        if (accidental == AccidentalType.FLAT) {
            measureXML += "<alter>-1</alter>";
        } else if (accidental == AccidentalType.SHARP) {
            measureXML += "<alter>1</alter>";
        }

        // Figuring this out on the fly
        if (baseOctave == 5) {
            if (noteString.compareTo("E") > 0 || noteString.compareTo("C") < 0) {
                measureXML += "<octave>4</octave>";
            } else {
                measureXML += "<octave>5</octave>";
            }
        } else if(baseOctave == 3) {
            if (noteString.compareTo("D") < 0) {
                measureXML += "<octave>2</octave>";
            } else {
                measureXML += "<octave>3</octave>";
            }
        }
        measureXML += "</pitch>" + durationAndType + "</note>";

        // Need to account for nonexistent sharps/flats
        // Change to pitch instead of noteString
        if (accidental != null && accidental == AccidentalType.SHARP) {
            noteString += "S";
        } else if (accidental != null && accidental == AccidentalType.FLAT) {
            noteString += "F";
        }
        noteString += octave;
        if (pitchmap.containsKey(noteString)) {
            if (dotCount == 0) {
                temp = new jm.music.data.Note(pitchmap.get(noteString), rhythmValue);
            } else {
                for (int i = 0; i <= dotCount; i++) {
                    extraRhythm += rhythmValue * Math.pow(0.5, i); // added length for dots
                }
//                System.out.println(extraRhythm);
                temp = new jm.music.data.Note(pitchmap.get(noteString), extraRhythm);
            }
            tempPhrase.addNote(temp);
        } else {
            if (accidental == null) {
                System.out.println("Note: " + n.getLetter() + division + " was not found");
            } else {
                System.out.println("Note: " + n.getLetter() + n.getAccidental().name() + division + " was not found");
            }
        }
        return null;
    }

    private HashMap<String, Integer> getPitchMap() throws IllegalAccessException {
        HashMap<String,Integer> pitchmap = new HashMap<>();
        for (Field f : jm.constants.Pitches.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) { //check if the field is public
                pitchmap.put(f.getName(), (Integer) f.get(null));
            }
        }
        return pitchmap;
    }

    @Override
    public Void visit(Part p) throws IllegalAccessException {
        musicXMLPartList += "<score-part id=\"" + partCounter + "\">";
        tempPart = new jm.music.data.Part();
        // name can be searched in instrument library
        p.getName().accept(this);
        p.getSheet().accept(this);
        // 2 instruments cannot play at the same time?
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
        for(Part part : p.getParts()) {
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
            musicXMLPost += thisPartXML + "</part>";;
        }
        musicXMLPartList += "</part-list>";
        musicXMLPost += "</score-partwise>";
        return null;
    }

    @Override
    public Void visit(Sheet s) throws IllegalAccessException {
        currMeasures = 0;
        partXML = "";

        measureAttributes = "<attributes><divisions>64</divisions>";
        measureCounterXML = 1;
//        score.createPart(); // we called createPart in visit(Part p)
        tempPart.setNumerator(s.getTimeNum());
        tempPart.setDenominator(s.getTimeDem());
        s.getKey().accept(this);
        measureAttributes += "<time><beats>" + s.getTimeNum() + "</beats><beat-type>" + s.getTimeDem()+ "</beat-type></time>";
        s.getClef().accept(this);
        measureAttributes += "</attributes>";
        // Will need this when we do error checking
        for(Object measures : s.getMeasures()) {
            measureXML = "";
            if (measures instanceof Loop) {
                ((Loop) measures).accept(this);
            } else {
                currMeasures++;
                loopCounter = 0;
                ((Measure) measures).accept(this);
            }
        }
        musicXMLParts.add(partXML);
        measureCounts.add(currMeasures);
//        temp.setKeySignature();
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
        // Note: Phrase = measure
        previousPhrases = new Phrase();
        loopCounter = l.getCount(); //how many times the user specified the looping
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
