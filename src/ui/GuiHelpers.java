package ui;

import ast.Evaluator;
import ast.Program;
import ast.StaticChecker;
import exceptions.FailedStaticCheckException;
import exceptions.ThrowingErrorListener;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Write;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.MusicSheetLexer;
import parser.MusicSheetParser;
import parser.ParsedTreeToAST;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GuiHelpers {
    TokenStream tokens;
    Program parsedProgram;
    Evaluator evaluator;

    public GuiHelpers() {

    }

    public String init(String input, String outputFile) throws FailedStaticCheckException, FileNotFoundException  {
        tokens = tokenize(input);
        parsedProgram = parse();
        performStaticChecks();
        evaluator = evaluate();
        write(outputFile);

        return "Generated Successfully at: " + outputFile;
    }

    public CommonTokenStream tokenize(String input) {
        MusicSheetLexer lexer = new MusicSheetLexer(CharStreams.fromString(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }

        lexer.reset();
        System.out.println("Done tokenizing");

        return new CommonTokenStream(lexer);
    }

    public Program parse() {
        MusicSheetParser parser = new MusicSheetParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        ParsedTreeToAST visitor = new ParsedTreeToAST();
        System.out.println("Done parsing");
        return visitor.visitProgram(parser.program());
    }

    public void performStaticChecks() throws FailedStaticCheckException{
        StaticChecker checker = new StaticChecker();

        try {
            parsedProgram.accept(checker);
        } catch (IllegalAccessException ignored) {
        }
    }

    public Evaluator evaluate() {
        Evaluator evaluator = new Evaluator(new Score());

        try {
            parsedProgram.accept(evaluator);
        } catch (IllegalAccessException ignored) {
        }

        System.out.println("completed successfully");

        return evaluator;
    }

    public void write(String outputFile) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(outputFile + ".xml");
        out.println(evaluator.getMusicXML());
        out.close();

        Write.midi(evaluator.getScore(), outputFile + ".midi");

        System.out.println("Done Writing");
    }

    public boolean play() {
        if (evaluator != null) {
            Play.midi(evaluator.getScore());
            return true;
        }
        return false;
    }


}
