package ast;

import java.util.List;

public class Loop extends Node{
    private final List<Measure> measures;
    private final int count;

    public Loop(List<Measure> measures, int count) {
        this.measures = measures;
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    public List<Measure> getMeasures() {
        return measures;
    }

    @Override
    public <T> T accept(Visitor<T> v) throws IllegalAccessException {
        return v.visit(this);
    }
}
