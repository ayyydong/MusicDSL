package ast;

public class Key extends Node {
    private final KeyType keyType;
    private final String note;

    public Key(KeyType keyType, String note) {
        this.keyType = keyType;
        this.note = note;
    }

    @Override
    public void evaluate() {

    }
}
