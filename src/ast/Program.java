package ast;

import java.io.PrintWriter;
import java.util.List;

public class Program extends Node {
    private final Title title;
    private final List<Part> parts;

    public Program(Title title, List<Part> parts) {
        this.title = title;
        this.parts = parts;
    }

    @Override
    public void evaluate() {

    }
}
