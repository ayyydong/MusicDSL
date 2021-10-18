package ast;

import exceptions.FailedStaticCheckException;

import java.util.List;

public class StaticChecker implements Visitor<Void>{
    private String currPart;
    private int measureCounter;
    private int currMeasureDuration;
    private int tempDuration;

    public StaticChecker() {
        currPart = "";
        measureCounter = 0;
        currMeasureDuration = 0;
    }

    @Override
    public Void visit(Clef c) {
        // Nothing to check for Clef
        return null;
    }

    @Override
    public Void visit(Key k) {
        KeyType kt = k.getKeyType();
        String sigNote = k.getNote();
        AccidentalType keyAcc = k.getAccidentalType();
        if (kt == KeyType.MAJOR) {
            if (sigNote.equals("A")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("A# major is not a valid key");
                }
            } else if (sigNote.equals("B")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("B# major is not a valid key");
                }
            } else if (sigNote.equals("D")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("D# major is not a valid key");
                }
            } else if (sigNote.equals("E")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("E# major is not a valid key");
                }
            } else if (sigNote.equals("F")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Fb major is not a valid key");
                }
            } else if (sigNote.equals("G")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("G# major is not a valid key");
                }
            }
        } else if (kt == KeyType.MINOR) {
            if (sigNote.equals("B")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("B# minor is not a valid key");
                }
            } else if (sigNote.equals("C")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Cb minor is not a valid key");
                }
            } else if (sigNote.equals("D")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Db minor is not a valid key");
                }
            } else if (sigNote.equals("E")) {
                if (keyAcc == AccidentalType.SHARP) {
                    throw new FailedStaticCheckException("E# minor is not a valid key");
                }
            } else if (sigNote.equals("F")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Fb minor is not a valid key");
                }
            } else if (sigNote.equals("G")) {
                if (keyAcc == AccidentalType.FLAT) {
                    throw new FailedStaticCheckException("Gb minor is not a valid key");
                }
            }
        }
        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        tempDuration = 0;
        measureCounter++;
        for(Note note : m.getNotes()) {
            note.accept(this);
        }
        if (tempDuration < currMeasureDuration) {
            throw new FailedStaticCheckException("Duration of notes in " + currPart + ": Measure " + measureCounter + " is less than the indicated time signature");
        } else if (tempDuration > currMeasureDuration) {
            throw new FailedStaticCheckException("Duration of notes in " + currPart + ": Measure " + measureCounter + " is greater than the indicated time signature");
        }
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        currPart = n.getName();
        return null;
    }

    @Override
    public Void visit(Note n) throws IllegalAccessException {
        NoteType noteType = n.getType();
        AccidentalType accidental = n.getAccidental();
        int octave = Integer.parseInt(n.getOctave());
        int div = Integer.parseInt(n.getDivision().replace("$",""));

        if (noteType == NoteType.rest && accidental != null) {
            throw new FailedStaticCheckException("Error in " + currPart + ": Measure " + measureCounter + ", rest does not have accidentals");
        }

        if (octave < 0 || octave > 9) {
            throw new FailedStaticCheckException("Error in " + currPart + ": Measure " + measureCounter + ", octave must be between 0 and 9");
        }

        int dotCount = 0;
        if (n.getDots() != null) {
            dotCount = n.getDots().length();
        }
        
        int duration = 256/div;
        for (int i = 0; i < dotCount; i++) {
            div *= 2;
            duration += 256/div;
        }
        tempDuration += duration;


        return null;
    }

    @Override
    public Void visit(Part p) throws IllegalAccessException {
        measureCounter = 0;
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
        currMeasureDuration = (256/s.getTimeDem()) * s.getTimeNum();
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
        List<Measure> measures = l.getMeasures();
        for (Measure measure : measures) {
            measure.accept(this);
        }
        return null;
    }
    
}
