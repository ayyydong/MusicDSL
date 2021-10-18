package ast;

import jm.music.data.Score;
import jm.music.data.Phrase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static jm.music.tools.Mod.append;
import static jm.music.tools.Mod.repeat;

public class Evaluator implements Visitor<Void> {
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

    public Evaluator(Score score) {
        this.score = score;
        this.tempPart = null;
        this.tempPhrase = null;
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
        // JMusic does not support clefs, so nothing to be done here
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
        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        tempPhrase = new Phrase();
        for (Note note : m.getNotes()) {
            note.accept(this);
        }
        // if know that it is loop use repeat
        if (loopCounter != 0) {
            if (measureCounter < listPhraseSize) {
                append(previousPhrases, tempPhrase);
                return null; //break before tempPart adding
            } else if (measureCounter == listPhraseSize) {
                append(previousPhrases, tempPhrase);
                repeat(previousPhrases, loopCounter);
                tempPhrase = previousPhrases;
            }
        }
        tempPart.add(tempPhrase);
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        tempPart.setTitle(n.getName());
        if (instMap.isEmpty()) {
            instMap = getInstrumentMap();
        }
        String instName = n.getName().replaceAll(" ", "_").toUpperCase(Locale.ROOT);
        if (instMap.containsKey(instName)) {
            tempPart.setInstrument(instMap.get(instName));
        } else {
            System.out.println("Instrument: " + n.getName() + "was not found");
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
        NoteType noteType = n.getType();
        String octave = n.getOctave();
        String division = n.getDivision().replace("$", "");
        int numDiv = Integer.parseInt(division);
        double rhythmValue = tempPart.getNumerator() / numDiv;
        int dotCount = 0;
        if (n.getDots() != null) {
            dotCount = n.getDots().length();
        }
        // Check whether note is letter or rest
        if (noteType == NoteType.rest) {
            noteHelper(REST, rhythmValue, dotCount);
            return null;
        }
        if (pitchMap.isEmpty()) {
            pitchMap = getPitchMap();
        }
        // pitchMap now contains all constant keys in String from Pitch interface
        String noteString = n.getLetter().toUpperCase(Locale.ROOT);
        AccidentalType accidental = n.getAccidental();
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
                System.out.println("Note: " + n.getLetter() + division + " was not found");
            } else {
                System.out.println("Note: " + n.getLetter() + n.getAccidental().name() + division + " was not found");
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
        tempPart = new jm.music.data.Part();
        p.getName().accept(this);
        p.getSheet().accept(this);
        score.add(tempPart);
        return null;
    }

    @Override
    public Void visit(Program p) throws IllegalAccessException {
        p.getTitle().accept(this);
        for (Part part : p.getParts()) {
            part.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Sheet s) throws IllegalAccessException {
        tempPart.setNumerator(s.getTimeNum());
        tempPart.setDenominator(s.getTimeDem());
        s.getClef().accept(this);
        s.getKey().accept(this);
        for (Object measures : s.getMeasures()) {
            if (measures instanceof Loop) {
                ((Loop) measures).accept(this);
            } else {
                loopCounter = 0;
                ((Measure) measures).accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(Title t) {
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
        measureCounter = 0;
        return null;
    }
}
