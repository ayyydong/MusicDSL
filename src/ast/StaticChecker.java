package ast;

import exceptions.FailedStaticCheckException;

public class StaticChecker implements Visitor<Void>{

    public StaticChecker() {
    }

    @Override
    public Void visit(Clef c) {
        // Nothing to check for Clef
        return null;
    }

    @Override
    public Void visit(Key k) {
        KeyType kt = k.getKeyType();
        Note sigNote = k.getNote();
        AccidentalType keyAcc = sigNote.getAccidental();
        String dots = sigNote.getDots();
        String duration = sigNote.getDivision();
        if (dots != null || duration != null) {
            throw new FailedStaticCheckException("Key signature should not have a duration");
        }
        if (kt == KeyType.MAJOR) {
            if (sigNote.getLetter().equals("A")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("A# major is not a valid key");
                }
            } else if (sigNote.getLetter().equals("B")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("B# major is not a valid key");
                }
            } else if (sigNote.getLetter().equals("D")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("D# major is not a valid key");
                }
            } else if (sigNote.getLetter().equals("E")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("E# major is not a valid key");
                }
            } else if (sigNote.getLetter().equals("F")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Fb major is not a valid key");
                }
            } else if (sigNote.getLetter().equals("G")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("G# major is not a valid key");
                }
            }
        } else if (kt == KeyType.MINOR) {
            if (sigNote.getLetter().equals("B")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("B# minor is not a valid key");
                }
            } else if (sigNote.getLetter().equals("C")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Cb minor is not a valid key");
                }
            } else if (sigNote.getLetter().equals("D")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Db minor is not a valid key");
                }
            } else if (sigNote.getLetter().equals("E")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("E# minor is not a valid key");
                }
            } else if (sigNote.getLetter().equals("F")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Fb minor is not a valid key");
                }
            } else if (sigNote.getLetter().equals("G")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Gb minor is not a valid key");
                }
            }
        }
        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        // Nothing to check for Name
        return null;
    }

    @Override
    public Void visit(Note n) throws IllegalAccessException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visit(Part p) throws IllegalAccessException {
        p.getName().accept(this);
        p.getSheet().accept(this);
        return null;
    }

    @Override
    public Void visit(Program p) throws IllegalAccessException {
        p.getTitle().accept(this);
        for(Part part : p.getParts()) {
            part.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Sheet s) throws IllegalAccessException {
        s.getKey().accept(this);
        s.getClef().accept(this);
        for(Object measures : s.getMeasures()) {
            if (measures instanceof Loop) {
                ((Loop) measures).accept(this);
            } else {
                ((Measure) measures).accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visit(Title t) {
        // Nothing to check for Title
        return null;
    }

    @Override
    public Void visit(Loop l) throws IllegalAccessException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
