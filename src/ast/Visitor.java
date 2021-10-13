package ast;

public interface Visitor<T> {
  T visit(Clef c);
  T visit(Key k);
  T visit(Measure m);
  T visit(Name n);
  T visit(Note n);
  T visit(Part p);
  T visit(Program p);
  T visit(Sheet s);
  T visit(Title t);
}