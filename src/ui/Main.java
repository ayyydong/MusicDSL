package ui;

import ast.Evaluator;
import ast.Program;
import jm.util.Play;
import jm.util.Write;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import parser.MusicSheetLexer;
import parser.MusicSheetParser;
import parser.ParsedTreeToAST;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import inst.SawtoothInst;
import jm.audio.Instrument;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.View;

import java.io.IOException;
import java.io.PrintWriter;

import static jm.constants.Durations.*;
import static jm.constants.Pitches.*;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {
        MusicSheetLexer lexer;
        try {
            lexer = new MusicSheetLexer(CharStreams.fromFileName("test_sheet_proper.txt"));

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
//        Play.midi(e.getScore());
        System.out.println("completed successfully");
        // Score (Contains any number of Parts)
        //   |
        //   +---- Part (Contains any number of Phrases)
        //           |
        //           +---- Phrase (Contains any number of Notes.)
        //                    |
        //                    +---- Note (Holds information about a single musical event.)
        // TODO: update phrase count
        // TODO: should count #notes and update time signature
        // TODO: update key signature // JMusic does not support key signatures and clefs! (terrible)
        // TODO: checkout treble/bass stave
//        Score score = new Score(new Part(new Phrase(new Note(G4, MINIM))));
        //Score outScore = e.getScore();
        //Write.xml(outScore);
        //Play.midi(e.getScore());
        PrintWriter out = new PrintWriter("out.mxl");
        out.println(e.getMusicXML());
        out.close();

//        View.notate(score);
    }


//        Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
//        score.setKeyQuality(1);
//        score.setKeySignature(3);
//       Write.midi(score, "expected_output");
//       Instrument inst = new SawtoothInst(44100);
//       Write.au(score, "expected_output_au", inst);
//       Write.xml(score,"expected_output_xml");
//        View.notate(score);
//    }
}

// https://i.pinimg.com/originals/03/45/5e/03455e5f600c75ab2c22f00d2bce8a3a.gif

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
// a score contains 2 parts
// a part contains 2 phrases
// a phrase contains 4 notes