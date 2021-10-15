package ast;

public interface Visitor<T> {
  T visit(Clef c);
  T visit(Key k);
  T visit(Measure m);
  T visit(Name n) throws IllegalAccessException;
  T visit(Note n);
  T visit(Part p) throws IllegalAccessException;
  T visit(Program p) throws IllegalAccessException;
  T visit(Sheet s);
  T visit(Title t);
}