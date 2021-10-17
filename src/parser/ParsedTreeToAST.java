package parser;

import ast.*;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayList;
import java.util.List;

public class ParsedTreeToAST extends AbstractParseTreeVisitor<Node> implements MusicSheetParserVisitor<Node> {

    @Override
    public Program visitProgram(MusicSheetParser.ProgramContext ctx) {
        Title title = this.visitTitle(ctx.title());
        List<Part> parts = new ArrayList<>();

        for (MusicSheetParser.PartContext partContext: ctx.part()) {
            parts.add(this.visitPart(partContext));
        }

        return new Program(title, parts);
    }

    @Override
    public Title visitTitle(MusicSheetParser.TitleContext ctx) {
        return new Title(ctx.TEXT().getText());
    }

    @Override
    public Part visitPart(MusicSheetParser.PartContext ctx) {
        Name name = this.visitName(ctx.name());
        Sheet sheet = this.visitSheet(ctx.sheet());
        return new Part(name, sheet);
    }

    @Override
    public Name visitName(MusicSheetParser.NameContext ctx) {
        return new Name(ctx.TEXT().getText());
    }

    @Override
    public Sheet visitSheet(MusicSheetParser.SheetContext ctx) {
        Clef clef;
        if (ctx.CLEF().getText().equals("treble")) {
            clef = Clef.treble;
        } else if (ctx.CLEF().getText().equals("bass")) {
            clef = Clef.bass;
        } else {
            throw new RuntimeException("Lexer error: tokenized clef that is not treble or bass");
        }

        String[] timeValues = ctx.TIME().toString().split("/");
//        double time = (double) Integer.parseInt(timeValues[0]) / Integer.parseInt(timeValues[1]);
        int timenum = Integer.parseInt(timeValues[0]);
        int timedem = Integer.parseInt(timeValues[1]);

        Key key = this.visitKey(ctx.key());

        List<Measure> measures = new ArrayList<>();
        for(MusicSheetParser.MeasureContext measureContext: ctx.measure()) {
            measures.add(this.visitMeasure(measureContext));
        }

        return new Sheet(clef, key, timenum, timedem, measures);
    }


    @Override
    public Key visitKey(MusicSheetParser.KeyContext ctx) {
        Note note = visitNote(ctx.note());
        KeyType keyType;
        if (ctx.KEYTYPE().getText().equals("major")) {
            keyType = KeyType.MAJOR;
        } else if (ctx.KEYTYPE().getText().equals("minor")) {
            keyType = KeyType.MINOR;
        } else {
            throw new RuntimeException("Lexer error: tokenized key type that is not major or minor");
        }

        return new Key(keyType, note);
    }

    @Override
    public Note visitNote(MusicSheetParser.NoteContext ctx) {
        int dots = 0;
        String division = null;
        AccidentalType accidental = null;
        SubMeasureType subMeasureType;
        if (ctx.NOTE_LETTER().getText().equals("R")){
            subMeasureType = SubMeasureType.rest;
        } else if (ctx.NOTE_LETTER().getText().matches("[A-G]")) {
            subMeasureType = SubMeasureType.note;
        } else {
            throw new RuntimeException("Lexer error: tokenized submeasure type that is not note or rest");
        }
        String letter = ctx.NOTE_LETTER().getText();

        if (ctx.DOTS() != null) {
            dots = ctx.DOTS().getText().length();
        }

        if (ctx.DIVISION() != null) {
            division = ctx.DIVISION().getText();
        }

        if (ctx.ACCIDENTAL() != null) {
            if (ctx.ACCIDENTAL().getText().equals("#")) {
                accidental = AccidentalType.SHARP;
            } else if (ctx.ACCIDENTAL().getText().equals("b")) {
                accidental = AccidentalType.FLAT;
            } else {
                throw new RuntimeException("Lexer error: tokenized accidental type that is not # or b");
            }
        }

        return new Note(subMeasureType, letter, accidental, dots, division);
    }

    @Override
    public Measure visitMeasure(MusicSheetParser.MeasureContext ctx) {
        List<Note> notes = new ArrayList<>();

        for (MusicSheetParser.NoteContext noteContext: ctx.note()) {
            notes.add(this.visitNote(noteContext));
        }

        return new Measure(notes);
    }

}