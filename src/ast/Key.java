package ast;

public class Key extends Node {
    private final KeyType keyType;
    private final String note;
    private final AccidentalType accidentalType;

    public Key(String note, AccidentalType accidentalType, KeyType keyType) {
        this.note = note;
        this.accidentalType = accidentalType;
        this.keyType = keyType;
//        this.note = note;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public AccidentalType getAccidentalType() {
        return accidentalType;
    }

    public String getNote() {
        return note;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
