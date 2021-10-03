package ast;

public class Part extends Node {
    private final Name name;
    private final Sheet sheet;

    public Part(Name name, Sheet sheet) {
        this.name = name;
        this.sheet = sheet;
    }

    @Override
    public void evaluate() {

    }
}
