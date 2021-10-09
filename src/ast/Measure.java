package ast;

import java.util.List;

public class Measure extends Node {
    private final List<Note> notes;

    public Measure(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void evaluate() {

    }
}
