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

    @Override
    public void evaluate() {

    }
}
