package ast;

import java.util.List;

public class Program extends Node {
    private final Title title;
    private final List<Part> parts;

    public Program(Title title, List<Part> parts) {
        this.title = title;
        this.parts = parts;
    }

    public Title getTitle() {
        return title;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}
