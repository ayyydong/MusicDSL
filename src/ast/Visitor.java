package ast;

public interface Visitor<T> {
  T visit(Clef c);
  T visit(Key k);
  T visit(Measure m) throws IllegalAccessException;
  T visit(Name n) throws IllegalAccessException;
  T visit(Note n) throws IllegalAccessException;
  T visit(Part p) throws IllegalAccessException;
  T visit(Program p) throws IllegalAccessException;
  T visit(Sheet s) throws IllegalAccessException;
  T visit(Title t);
}