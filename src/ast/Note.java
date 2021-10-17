package ast;

public class Note extends Node {
    private final SubMeasureType subMeasureType;
    private final String letter;
    private final AccidentalType accidental;
    private final String octave;
    private final String dots;
    private final String division;

    public Note(SubMeasureType subMeasureType, String letter, AccidentalType accidental, String octave, String dots, String division) {
        this.subMeasureType = subMeasureType;
        this.letter = letter;
        this.accidental = accidental;
        this.octave = octave;
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