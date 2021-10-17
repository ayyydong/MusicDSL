package ast;

import jm.music.data.Score;
import jm.music.data.Phrase;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;

public class Evaluator implements Visitor<Void> {
    private static final String[] types = {"whole", "half", "quarter", "eighth", "16th", "32nd", "64th", "128th", "256"};
    private static final int REST = jm.music.data.Note.REST;
    private static final double DEFAULT_RHYTHM_VALUE = jm.music.data.Note.DEFAULT_RHYTHM_VALUE;
    private Score score;
    private jm.music.data.Part tempPart;
    private Phrase tempPhrase;
    private HashMap<String, Integer> pitchmap = new HashMap<>();
    private HashMap<String, Integer> instmap = new HashMap<>();

    private String musicXMLPre;
    private String musicXMLPartList;
    private String musicXMLPost;
    private String measureAttributes;
    private int partCounter;
    private int measureCounter;
    private int baseOctave;

    public Evaluator(Score score) {
        this.score = score;
        this.partCounter = 0;
        this.tempPart = null;
        this.tempPhrase = null;
        musicXMLPre = "";
        musicXMLPartList = "";
        musicXMLPost = "";
        measureAttributes = "";
        partCounter = 0;
        measureCounter = 1;
        baseOctave = 5;
    }

    public Score getScore() {
        return this.score;
    }

    @Override
    public Void visit(Clef c) {
        String clefSymbol = "G";
        int staffLine = 2;
        if (c == Clef.bass) {
            clefSymbol = "F";
            staffLine = 4;
            baseOctave = 3;
        }
        measureAttributes += "<clef><sign>" + clefSymbol + "</sign><line>" + staffLine + "</line></clef>";
        return null;
    }

    @Override
    public Void visit(Key k) {
        KeyType kt = k.getKeyType();
        int quality = 0;
        if (kt == KeyType.MINOR) {
            quality = 1;
        }
        //score.setKeyQuality(quality); // this sets score, should be setting part instead
        tempPart.setKeyQuality(quality);
        // Process number of sharps and flats based on quality/note

        int keySig = 0;
        Note sigNote = k.getNote();
        AccidentalType keyAcc = sigNote.getAccidental();
        if (kt == KeyType.MAJOR) {
            if (sigNote.getLetter().equals("A")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -4;
                } else {
                    keySig = 3;
                }
            } else if (sigNote.getLetter().equals("B")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -2;
                } else {
                    keySig = 5;
                }
            } else if (sigNote.getLetter().equals("C")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                } else {
                    keySig = 0;
                }
            } else if (sigNote.getLetter().equals("D")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.getLetter().equals("E")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -3;
                } else {
                    keySig = 4;
                }
            } else if (sigNote.getLetter().equals("F")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.getLetter().equals("G")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            }
        } else if (kt == KeyType.MINOR) {
            if (sigNote.getLetter().equals("A")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 7;
                } else if (keyAcc == AccidentalType.FLAT) {
                    keySig = -7;
                }
            } else if (sigNote.getLetter().equals("B")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -5;
                } else {
                    keySig = 2;
                }
            } else if (sigNote.getLetter().equals("C")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 4;
                } else {
                    keySig = -3;
                }
            } else if (sigNote.getLetter().equals("D")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 6;
                } else {
                    keySig = -1;
                }
            } else if (sigNote.getLetter().equals("E")) {
                if (keyAcc == AccidentalType.FLAT) {
                    keySig = -6;
                } else {
                    keySig = 1;
                }
            } else if (sigNote.getLetter().equals("F")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 3;
                } else {
                    keySig = -4;
                }
            } else if (sigNote.getLetter().equals("G")) {
                if (keyAcc == AccidentalType.SHARP) {
                    keySig = 5;
                } else {
                    keySig = -2;
                }
            }
        }
        tempPart.setKeySignature(keySig);

        measureAttributes += "<key><fifths>" + keySig + "</fifths></key>";

        return null;
    }

    @Override
    public Void visit(Measure m) throws IllegalAccessException {
        musicXMLPost += "<measure number=\"" + measureCounter + "\">" + measureAttributes;
        for(Note note : m.getNotes()) {
            note.accept(this);
        }
        musicXMLPost += "</measure>";
        return null;
    }

    @Override
    public Void visit(Name n) throws IllegalAccessException {
        musicXMLPartList += "<part-name>" + n.getName() + "</part-name>";
        tempPart.setTitle(n.getName());
        if (instmap.isEmpty()) {
            instmap = getInstrumentMap();
        }
        String instName = n.getName().replaceAll(" ", "_").toUpperCase(Locale.ROOT);
        if (instmap.containsKey(instName)) {
            tempPart.setInstrument(instmap.get(instName));
        } else {
            System.out.println("Instrument: " + n.getName() + "was not found");
        }
        return null;
    }

    private HashMap<String, Integer> getInstrumentMap() throws IllegalAccessException {
        // name can be searched in instrument library
        // https://stackoverflow.com/questions/22230787/string-values-of-field-constants-in-java
        // https://stackoverflow.com/questions/9700081/in-java-how-to-iterate-on-the-constants-of-an-interface
        HashMap<String,Integer> map = new HashMap<>();
        for (Field f : jm.constants.ProgramChanges.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) { //check if the field is public
                map.put(f.getName(), (Integer) f.get(null));
            }
        }
        return map;
    }

    @Override
    public Void visit(Note n) throws IllegalAccessException {
        musicXMLPost += "<note>";
        SubMeasureType noteType = n.getType();
        jm.music.data.Note temp;
        String division = n.getDivision();
        // Check whether or not note is letter or rest

        int div = Integer.parseInt(division);
        int typeCount = 0;
        for (int i = div; i > 1; i/=2) {
            typeCount++;
        }
        int duration = 256/div;
        for (int i = 0; i < n.getDots(); i++) {
            div *= 2;
            duration += 256/div;
        }
        String durationAndType = "<duration>" + duration + "</duration><type>" + types[typeCount] + "</type>";
        for (int i = 0; i < n.getDots(); i++) {
            durationAndType += "<dot/>";
        }

        if (noteType == SubMeasureType.rest) {
//            temp = new jm.music.data.Note(REST, tempPart.getNumerator()/(Integer.parseInt(division)));
            temp = new jm.music.data.Note(REST, 4.0/Integer.parseInt(division));
            tempPhrase.addNote(temp);
            musicXMLPost += "<rest/>" + durationAndType + "</note>";
            return null;
        }
        if (pitchmap.isEmpty()) {
            pitchmap = getPitchMap();
        }
        // pitchmap now contains all string keys of the constants originally defined for ints
        // Note is a letter
        String noteString = n.getLetter();
        musicXMLPost += "<pitch><step>" + noteString + "</step>";
        AccidentalType accidental = n.getAccidental();
        if (accidental == AccidentalType.FLAT) {
            musicXMLPost += "<alter>-1</alter>";
        } else if (accidental == AccidentalType.SHARP) {
            musicXMLPost += "<alter>1</alter>";
        }

        // Figuring this out on the fly
        if (baseOctave == 5) {
            if (noteString.compareTo("E") > 0 || noteString.compareTo("C") < 0) {
                musicXMLPost += "<octave>4</octave>";
            } else {
                musicXMLPost += "<octave>5</octave>";
            }
        } else if(baseOctave == 3) {
            if (noteString.compareTo("D") < 0) {
                musicXMLPost += "<octave>2</octave>";
            } else {
                musicXMLPost += "<octave>3</octave>";
            }
        }
        musicXMLPost += "</pitch>" + durationAndType + "</note>";

        // Need to account for nonexistent sharps/flats
        // Change to pitch instead of noteString
        if (accidental != null && accidental == AccidentalType.SHARP) {
            noteString += "S";
        } else if (accidental != null && accidental == AccidentalType.FLAT) {
            noteString += "F";
        }
        noteString += division;
        if (pitchmap.containsKey(noteString)) {
//            temp = new jm.music.data.Note(pitchmap.get(noteString), tempPart.getNumerator()/(Integer.parseInt(division)));
            temp = new jm.music.data.Note(pitchmap.get(noteString), 4.0/Integer.parseInt(division));
            tempPhrase.addNote(temp);
        } else {
            if (accidental == null) {
                System.out.println("Note: " + n.getLetter() + n.getDivision() + " was not found");
            } else {
                System.out.println("Note: " + n.getLetter() + n.getAccidental().name() + n.getDivision() + " was not found");
            }
        }
        return null;
    }

    private HashMap<String, Integer> getPitchMap() throws IllegalAccessException {
        HashMap<String,Integer> pitchmap = new HashMap<>();
        for (Field f : jm.constants.Pitches.class.getFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) { //check if the field is public
                pitchmap.put(f.getName(), (Integer) f.get(null));
            }
        }
        return pitchmap;
    }

    @Override
    public Void visit(Part p) throws IllegalAccessException {
        musicXMLPartList += "<score-part id=\"" + partCounter + "\">";
        musicXMLPost += "<part id=\"" + partCounter + "\">";
        tempPart = new jm.music.data.Part();
        // name can be searched in instrument library
        p.getName().accept(this);
        p.getSheet().accept(this);
        // 2 instruments cannot play at the same time?
        score.add(tempPart);
        musicXMLPartList += "</score-part>";
        musicXMLPost += "</part>";
        partCounter++;
        return null;
    }

    @Override
    public Void visit(Program p) throws IllegalAccessException {
        musicXMLPre = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 4.0 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\"><score-partwise version=\"4.0\">";
        musicXMLPartList = "<part-list>";
        musicXMLPost = "";
        p.getTitle().accept(this);
        for(Part part : p.getParts()) {
            part.accept(this);
        }
        musicXMLPartList += "</part-list>";
        musicXMLPost += "</score-partwise>";
        return null;
    }

    @Override
    public Void visit(Sheet s) throws IllegalAccessException {
        measureAttributes = "<attributes><divisions>64</divisions>";
        measureCounter = 1;
//        score.createPart(); // we called createPart in visit(Part p)
        tempPhrase = new Phrase();
        s.getKey().accept(this);
        measureAttributes += "<time><beats>" + s.getTimeNum() + "</beats><beat-type>" + s.getTimeDem()+ "</beat-type></time>";
        s.getClef().accept(this);
        measureAttributes += "</attributes>";
        // Will need this when we do error checking
        for(Measure measure : s.getMeasures()) {
            measure.accept(this);
        }
        tempPart.add(tempPhrase);
        tempPart.setNumerator(s.getTimeNum());
        tempPart.setDenominator(s.getTimeDem());
//        temp.setKeySignature();
        return null;
    }

    @Override
    public Void visit(Title t) {
        musicXMLPre += "<work><work-title>" + t.getTitle() + "</work-title></work>";
        score.setTitle(t.getTitle());
        return null;
    }

    public String getMusicXML() {
        return musicXMLPre + musicXMLPartList + musicXMLPost;
    }
}
