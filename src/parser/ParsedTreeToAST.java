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
        double time = (double) Integer.parseInt(timeValues[0]) / Integer.parseInt(timeValues[1]);

        Key key = this.visitKey(ctx.key());

        List<Measure> measures = new ArrayList<>();
        for(MusicSheetParser.MeasureContext measureContext: ctx.measure()) {
            measures.add(this.visitMeasure(measureContext));
        }

        return new Sheet(clef, key, time, measures);
    }

    @Override
    public Key visitKey(MusicSheetParser.KeyContext ctx) {
        KeyType keyType;
        if (ctx.KEYTYPE().getText().equals("major")) {
            keyType = KeyType.major;
        } else if (ctx.KEYTYPE().getText().equals("minor")) {
            keyType = KeyType.minor;
        } else {
            throw new RuntimeException("Lexer error: tokenized key type that is not major or minor");
        }

        String note = ctx.NOTE().getText();

        return new Key(keyType, note);
    }

    @Override
    public Measure visitMeasure(MusicSheetParser.MeasureContext ctx) {
        List<SubMeasure> subMeasures = new ArrayList<>();

        for (MusicSheetParser.SubmeasureContext submeasureContext: ctx.submeasure()) {
            subMeasures.add(this.visitSubmeasure(submeasureContext));
        }

        return new Measure(subMeasures);
    }

    @Override
    public SubMeasure visitSubmeasure(MusicSheetParser.SubmeasureContext ctx) {
        SubMeasureType subMeasureType;
        String content = ctx.getText();
        if (ctx.NOTE() != null) {
            subMeasureType = SubMeasureType.note;
        } else if (ctx.REST() != null) {
            subMeasureType = SubMeasureType.rest;
        } else {
            throw new RuntimeException("Lexer error: submeasure is not of type note or rest");
        }
        return new SubMeasure(subMeasureType, content);
    }
}
