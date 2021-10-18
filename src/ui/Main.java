package ui;

import ast.Evaluator;
import ast.Program;
import jm.util.Play;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.MusicSheetLexer;
import parser.MusicSheetParser;
import parser.ParsedTreeToAST;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import jm.music.data.Score;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        MusicSheetLexer lexer;
        try {
            lexer = new MusicSheetLexer(CharStreams.fromFileName("test_sheet3.txt"));

        } catch (NoSuchFileException e) {
            System.out.println("ERROR: File not found\n" + e);
            return;
        }

        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Done tokenizing");

        MusicSheetParser parser = new MusicSheetParser(tokens);
        ParsedTreeToAST visitor = new ParsedTreeToAST();
        Program parsedProgram = visitor.visitProgram(parser.program());
        System.out.println("Done parsing");

        Evaluator e = new Evaluator(new Score());
        parsedProgram.accept(e);
        Play.midi(e.getScore());
        System.out.println("completed successfully");
    }
}
