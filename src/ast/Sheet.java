package ast;

import java.util.List;

public class Sheet extends Node {
    private final Clef clef;
    private final Key key;
    private final int timeNum;
    private final int timeDen;
    private final List<Object> measures;

    public Sheet(Clef clef, Key key, int timeNum, int timeDen, List<Object> measures) {
        this.clef = clef;
        this.key = key;
        this.timeNum = timeNum;
        this.timeDen = timeDen;
        this.measures = measures;
    }

    public Clef getClef() {
        return clef;
    }

    public Key getKey() {
        return key;
    }

    public int getTimeNum() {
        return timeNum;
    }

    public int getTimeDem() {
        return timeDen;
    }

    public List<Object> getMeasures() {
        return measures;
    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}
