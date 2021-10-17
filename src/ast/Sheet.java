package ast;

import java.util.List;

public class Sheet extends Node {
    private final Clef clef;
    private final Key key;
    private final int timenum;
    private final int timedem;
//    private final List<Measure> measures;
    private final List<Object> measures;
//    private final List<Loop> loopMeasures;

    public Sheet(Clef clef, Key key, int timenum, int timedem, List<Object> measures) {// List<Loop> loopMeasures) {
        this.clef = clef;
        this.key = key;
        this.timenum = timenum;
        this.timedem = timedem;
        this.measures = measures;
//        this.loopMeasures = loopMeasures;
    }

    public Clef getClef() {
        return clef;
    }

    public Key getKey() {
        return key;
    }

    public int getTimeNum() {
        return timenum;
    }

    public int getTimeDem() {
        return timedem;
    }

    public List<Object> getMeasures() {
        return measures;
    }
//
//    public List<Loop> getLoopMeasures() {
//        return loopMeasures;
//    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}
