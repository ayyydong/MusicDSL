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
//    private static final double DEFAULT_RHYTHM_VALUE = jm.music.data.Note.DEFAULT_RHYTHM_VALUE;
    private Score score;
    private int partCounter;
    private int loopCounter;
    private int listPhraseSize;
    private int measureCounter;
    private jm.music.data.Part tempPart;
    private Phrase tempPhrase;
    private HashMap<String, Integer> pitchmap = new HashMap<>();
    private HashMap<String, Integer> instmap = new HashMap<>();
    private Phrase previousPhrases;

    public Evaluator(Score score) {
        this.score = score;
        this.partCounter = 0;
        this.tempPart = null;
        this.tempPhrase = null;
    }

    public Score getScore() {
        return this.score;
    }

    @Override
    public Void visit(Clef c) {
        // Nothing to be done here?
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

        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        tempPhrase = new Phrase();
        for(Note note : m.getNotes()) {
            note.accept(this);
        }
        // if know that it is loop use repeat
        if (loopCounter != 0) {
            if (measureCounter < listPhraseSize){
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
        SubMeasureType noteType = n.getType();
        jm.music.data.Note temp;
        String division = n.getDivision();
        // Check whether or not note is letter or rest
        if (noteType == SubMeasureType.rest) {
            temp = new jm.music.data.Note(REST, tempPart.getNumerator()/(Integer.parseInt(division)));
//            temp = new jm.music.data.Note(REST, 1.5);
            tempPhrase.addNote(temp);
            return null;
        }
        if (pitchmap.isEmpty()) {
            pitchmap = getPitchMap();
        }
        // pitchmap now contains all string keys of the constants originally defined for ints
        // Note is a letter
        String noteString = n.getLetter();
        AccidentalType accidental = n.getAccidental();
        // Need to account for nonexistent sharps/flats
        // Change to pitch instead of noteString
        if (accidental != null && accidental == AccidentalType.SHARP) {
            noteString += "S";
        } else if (accidental != null && accidental == AccidentalType.FLAT) {
            noteString += "F";
        }
        noteString += division;
        if (pitchmap.containsKey(noteString)) {
            temp = new jm.music.data.Note(pitchmap.get(noteString), tempPart.getNumerator()/(Integer.parseInt(division)));
//            System.out.println(Integer.toString(tempPhrase.getNumerator()));
//            temp = new jm.music.data.Note(pitchmap.get(noteString), 1.5);
            tempPhrase.addNote(temp);
        } else {
            if (accidental == null) {
                System.out.println("Note: " + n.getLetter() + n.getDivision() + " was not found");
            } else {
                System.out.println("Note: " + n.getLetter() + n.getAccidental().name() + n.getDivision() + " was not found");
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
        tempPart = new jm.music.data.Part();
        // name can be searched in instrument library
        p.getName().accept(this);
        p.getSheet().accept(this);
        // 2 instruments cannot play at the same time?
        score.add(tempPart);
        return null;
    }

    @Override
    public Void visit(Program p) throws IllegalAccessException {
        p.getTitle().accept(this);
        for(Part part : p.getParts()) {
            part.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Sheet s) throws IllegalAccessException {
//        score.createPart(); // we called createPart in visit(Part p)
        tempPart.setNumerator(s.getTimeNum());
        tempPart.setDenominator(s.getTimeDem());
        s.getClef().accept(this);
        s.getKey().accept(this);
        //Not touching time cause double is not the way to go
        // score.setTimeSignature(s.getTimeNum(),s.getTimeDem());
        // Will need this when we do error checking
        for(Object measures : s.getMeasures()) {
            if (measures instanceof Loop) {
                ((Loop) measures).accept(this);
            } else {
                loopCounter = 0;
                ((Measure) measures).accept(this);
            }
        }
//        temp.setKeySignature();
        return null;
    }

    @Override
    public Void visit(Title t) {
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
        measureCounter = 0;
        return null;
    }
}
