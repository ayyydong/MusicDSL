package ast;

public class Part extends Node {
    private final Name name;
    private final Sheet sheet;

    public Part(Name name, Sheet sheet) {
        this.name = name;
        this.sheet = sheet;
    }

    public Name getName() {
        return name;
    }

    public Sheet getSheet() {
        return sheet;
    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}
