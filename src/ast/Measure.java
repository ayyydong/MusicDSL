package ast;

import java.util.List;

public class Measure extends Node {
    private final List<Note> notes;

    public Measure(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}