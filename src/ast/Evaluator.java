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
        jm.music.data.Part temp = score.getPart(partCounter);
        temp.setTitle(n.getName());
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
            temp.setInstrument(map.get(instName));
        } else {
            System.out.println("Instrument: " + n.getName() + "was not found");
        }
        score.removeLastPart();
        score.add(temp);
        return null;
    }

    @Override
    public Void visit(Note n) {
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
        // Need to account for nonexistent sharps/flats
        if (accidental != null && accidental == AccidentalType.SHARP) {
            noteString += "_SHARP";
        } else if (accidental != null && accidental == AccidentalType.FLAT) {
            noteString += "_FLAT";
        }
        temp = new jm.music.data.Note(noteString);
        tempPhrase.addNote(temp);
        return null;
    }

    @Override
    public Void visit(Part p) throws IllegalAccessException {
        score.createPart();
        // name can be searched in instrument library
        p.getName().accept(this);
        p.getSheet().accept(this);
        // 2 instruments cannot play at the same time?
        partCounter++;
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
        jm.music.data.Part temp = score.getPart(partCounter);
        tempPart = temp;
        s.getClef().accept(this);
        s.getKey().accept(this);
        //Not touching time cause double is not the way to go
        // score.setTimeSignature(s.getTimeNum(),s.getTimeDem());
        // Will need this when we do error checking
        for(Measure measure : s.getMeasures()) {
            measure.accept(this);
        }
        temp.add(tempPhrase);
        temp.setNumerator(s.getTimeNum());
        temp.setDenominator(s.getTimeDem());
//        temp.setKeySignature();
        score.removeLastPart();
        score.add(temp);
        return null;
    }

    @Override
    public Void visit(Title t) {
        score.setTitle(t.getTitle());
        return null;
    }
}