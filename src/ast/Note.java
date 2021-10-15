package ast;

public class Note extends Node {
    private final SubMeasureType subMeasureType;
    private final String letter;
    private final AccidentalType accidental;
    private final Integer dots;
    private final Integer division;

    public Note(SubMeasureType subMeasureType, String letter, AccidentalType accidental, String dots, String division) {
        this.subMeasureType = subMeasureType;
        this.letter = letter;
        this.accidental = accidental;
        this.dots = dots;
        this.division = division;
    }

    public SubMeasureType getType() {
        return subMeasureType;
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
