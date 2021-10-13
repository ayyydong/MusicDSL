package ast;


public abstract class Node {
    abstract public <T> T accept(Visitor<T> v);
}
