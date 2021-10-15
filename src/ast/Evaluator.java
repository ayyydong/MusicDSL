package ast;

import jm.music.data.Score;
import jm.music.data.Phrase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;

public class Evaluator implements Visitor<Void> {
    private static final int REST = jm.music.data.Note.REST;
    private static final double DEFAULT_RHYTHM_VALUE = jm.music.data.Note.DEFAULT_RHYTHM_VALUE;
    private Score score;
    private int partCounter;
    private jm.music.data.Part tempPart;
    private Phrase tempPhrase;

    public Evaluator(Score score) {
        this.score = score;
        this.partCounter = 0;
        this.tempPart = null;
        this.tempPhrase = null;
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
            if (sigNote.getLetter() == "A") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -4;
                } else {
                    keySig = 3;
                }
            } else if (sigNote.getLetter() == "B") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -2;
                } else {
                    keySig = 5;
                }
            } else if (sigNote.getLetter() == "C") {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                } else {
                    keySig = 0;
                }
            } else if (sigNote.getLetter() == "D") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.getLetter() == "E") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -3;
                } else {
                    keySig = 4;
                }
            } else if (sigNote.getLetter() == "F") {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.getLetter() == "G") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            } 
        } else if (kt == KeyType.MINOR) {
            if (sigNote.getLetter() == "A") {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                }
            } else if (sigNote.getLetter() == "B") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.getLetter() == "C") {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 4;
                } else {
                    keySig = -3;
                }
            } else if (sigNote.getLetter() == "D") {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.getLetter() == "E") {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            } else if (sigNote.getLetter() == "F") {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 3;
                } else {
                    keySig = -4;
                }
            } else if (sigNote.getLetter() == "G") {
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
    public Void visit(Measure m) {
        for(Note note : m.getNotes()) {
            note.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        tempPart.setTitle(n.getName());
        // name can be searched in instrument library
        // https://stackoverflow.com/questions/22230787/string-values-of-field-constants-in-java
        // https://stackoverflow.com/questions/9700081/in-java-how-to-iterate-on-the-constants-of-an-interface
        HashMap<String,Integer> map = new HashMap<>();
        for (Field f : jm.constants.ProgramChanges.class.getFields()) {
                int modifiers = f.getModifiers();//check if the field is public and static
                if (Modifier.isPublic(modifiers)) {
                    map.put(f.getName(), (Integer) f.get(null));
                }
        }
        String instName = n.getName().replaceAll(" ", "_").toUpperCase(Locale.ROOT);
        if (map.containsKey(instName)) {
            tempPart.setInstrument(map.get(instName));
        } else {
            System.out.println("Instrument: " + n.getName() + "was not found");
        }
        return null;
    }

    @Override
    public Void visit(Note n) {
        // NOTES NEED A DURATION
        SubMeasureType noteType = n.getType();
        jm.music.data.Note temp;
        // Check whether or not note is letter or rest
        if (noteType == SubMeasureType.rest) {
            temp = new jm.music.data.Note(REST, DEFAULT_RHYTHM_VALUE);
            tempPhrase.addNote(temp);
            return null;
        }
        // Note is a letter
        AccidentalType accidental = n.getAccidental();
        String noteString = n.getLetter();
        if (accidental != null && accidental == AccidentalType.SHARP) {
            noteString += "_SHARP";
            if (noteString == "B_SHARP") {
                noteString = "C";
            } else if (noteString == "E_SHARP") {
                noteString = "F";
            }
        } else if (accidental != null && accidental == AccidentalType.FLAT) {
            noteString += "_FLAT";
            if (noteString == "C_FLAT") {
                noteString = "B";
            } else if (noteString == "D_FLAT") {
                noteString = "C_SHARP";
            } else if (noteString == "F_FLAT") {
                noteString = "E";
            } else if (noteString == "G_FLAT") {
                noteString = "F_SHARP";
            }
        }
        temp = new jm.music.data.Note(noteString);
        tempPhrase.addNote(temp);
        return null;
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
    public Void visit(Sheet s) {
//        score.createPart(); // we called createPart in visit(Part p)
        tempPhrase = new Phrase();
        s.getClef().accept(this);
        s.getKey().accept(this);
        //Not touching time cause double is not the way to go
        // score.setTimeSignature(s.getTimeNum(),s.getTimeDem());
        // Will need this when we do error checking
        for(Measure measure : s.getMeasures()) {
            measure.accept(this);
        }
        tempPart.add(tempPhrase);
        tempPart.setNumerator(s.getTimeNum());
        tempPart.setDenominator(s.getTimeDem());
//        temp.setKeySignature();
        return null;
    }

    @Override
    public Void visit(Title t) {
        score.setTitle(t.getTitle());
        return null;
    }
}