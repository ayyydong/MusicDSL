package ui;

import ast.Program;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.MusicSheetLexer;
import parser.MusicSheetParser;
import parser.ParsedTreeToAST;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {

    public static void main(String[] args) throws IOException {
        MusicSheetLexer lexer;
        try {
            lexer = new MusicSheetLexer(CharStreams.fromFileName("test_sheet.txt"));

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
    }

//        parsedProgram.evaluate();

//        Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
//        Write.midi(score, "expected_output");
//        Instrument inst = new SawtoothInst(44100);
//        Write.au(score, "expected_output_au", inst);
//        Write.xml(score,"expected_output_xml");
//        View.notate(score);
//    }
}

/**

 * This is the simplest jMusic program of all.

 * The eqivalent to a programming language's 'Hello World'

 */
//// Check common practice notation
//// https://explodingart.com/jmusic/jmtutorial/CPN.html
// public final class Main implements JMC {
//
//    public static void main(String[] args) {
//        Phrase phr = new Phrase();
//        for(int i = 0; i< 50; i++) {
//            Note n = new Note((int)(Math.random()*60 + 30), SQ * (int)(Math.random()*8 + 1));
//            phr.addNote(n);
//        }
////        View.print(phr);
////        View.pianoRoll(phr);
//        View.notate(phr);
//    }
//}