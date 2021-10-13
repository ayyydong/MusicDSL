package ast;

public class Key extends Node {
    private final KeyType keyType;
    private final Note note;

    public Key(KeyType keyType, Note note) {
        this.keyType = keyType;
        this.note = note;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public Note getNote() {
        return note;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
