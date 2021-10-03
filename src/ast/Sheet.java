package ast;

import java.util.List;

public class Sheet extends Node {
    private final Clef clef;
    private final Key key;
    private final double time;
    private final List<Measure> measures;

    public Sheet(Clef clef, Key key, double time, List<Measure> measures) {
        this.clef = clef;
        this.key = key;
        this.time = time;
        this.measures = measures;
    }

    @Override
    public void evaluate() {

    }
}
