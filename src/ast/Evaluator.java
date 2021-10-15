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
    private HashMap<String, Integer> pitchmap = new HashMap<>();
    private HashMap<String, Integer> instmap = new HashMap<>();

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
        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        for(Note note : m.getNotes()) {
            note.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        jm.music.data.Part temp = score.getPart(partCounter);
        temp.setTitle(n.getName());
        if (instmap.isEmpty()) {
            instmap = getInstrumentMap();
        }
        String instName = n.getName().replaceAll(" ", "_").toUpperCase(Locale.ROOT);
        if (instmap.containsKey(instName)) {
            temp.setInstrument(instmap.get(instName));
        } else {
            System.out.println("Instrument: " + n.getName() + "was not found");
        }
        score.removeLastPart();
        score.add(temp);
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
    public Void visit(Sheet s) throws IllegalAccessException {
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