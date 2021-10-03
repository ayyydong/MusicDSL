package ast;

import java.util.List;

public class Measure extends Node {
    private final List<SubMeasure> subMeasures;

    public Measure(List<SubMeasure> subMeasures) {
        this.subMeasures = subMeasures;
    }

    @Override
    public void evaluate() {

    }
}
