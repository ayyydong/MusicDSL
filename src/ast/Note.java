package ast;

public class Note extends Node {
    private final String letter;
    private final AccidentalType accidental;
    private final String dots;
    private final String division;

    public Note(String letter, AccidentalType accidental, String dots, String division) {
        this.letter = letter;
        this.accidental = accidental;
        this.dots = dots;
        this.division = division;
    }

    public String getLetter() {
        return letter;
    }

    public AccidentalType getAccidental() {
        return accidental;
    }

    public String getDots() {
        return dots;
    }

    public String getDivision() {
        return division;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
