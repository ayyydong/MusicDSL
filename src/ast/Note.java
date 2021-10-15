package ast;

public class Note extends Node {
    private final String letter;
    private final AccidentalType accidental;
    private final Integer dots;
    private final Integer division;

    public Note(String letter, AccidentalType accidental, Integer dots, Integer division) {
        this.letter = letter;
        this.accidental = accidental;
        this.dots = dots;
        this.division = division;
    }

    @Override
    public void evaluate() {

    }
}
