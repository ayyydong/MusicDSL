package ast;

public enum Clef {
    treble,
    bass;

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}