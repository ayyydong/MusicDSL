package ast;

import java.util.List;

public class Sheet extends Node {
    private final Clef clef;
    private final Key key;
    private final int timenum;
    private final int timedem;
    private final List<Measure> measures;

    public Sheet(Clef clef, Key key, int timenum, int timedem, List<Measure> measures) {
        this.clef = clef;
        this.key = key;
        this.timenum = timenum;
        this.timedem = timedem;
        this.measures = measures;
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

    public List<Measure> getMeasures() {
        return measures;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
