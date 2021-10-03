package ast;

public class SubMeasure extends Node {
    private final SubMeasureType subMeasureType;
    private final String content;

    public SubMeasure(SubMeasureType subMeasureType, String content) {
        this.subMeasureType = subMeasureType;
        this.content = content;
    }

    @Override
    public void evaluate() {

    }
}
