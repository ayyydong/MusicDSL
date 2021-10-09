package ast;

public class Key extends Node {
    private final KeyType keyType;
    private final Note note;

    public Key(KeyType keyType, Note note) {
        this.keyType = keyType;
        this.note = note;
    }

    @Override
    public void evaluate() {

    }
}
