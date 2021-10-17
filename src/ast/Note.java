package ast;

public class Note extends Node {
    private final NoteType noteType;
    private final String letter;
    private final AccidentalType accidental;
//    private final Type type;
    private final String octave;
    private final String dots;
    private final String division;

    public Note(NoteType noteType, String letter, AccidentalType accidental, String octave, String dots, String division) {
        this.noteType = noteType;
        this.letter = letter;
        this.accidental = accidental;
//        this.type = type;
        this.octave = octave;
        this.dots = dots;
        this.division = division;
    }

    public NoteType getType() {
        return noteType;
    }

    public String getLetter() {
        return letter;
    }

    public AccidentalType getAccidental() {
        return accidental;
    }
//    public Type getType() {
//        return type;
//    }

    public String getOctave() {
        return octave;
    }

    public String getDots() {
        return dots;
    }

    public String getDivision() {
        return division;
    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}