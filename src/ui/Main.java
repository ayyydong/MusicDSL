package ui;

import ast.Evaluator;
import ast.Program;
import ast.StaticChecker;
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
import java.util.ArrayList;
import java.util.List;

import static ast.SubMeasureType.note;
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

        StaticChecker checker = new StaticChecker();
        parsedProgram.accept(checker);

        Evaluator e = new Evaluator(new Score());
        parsedProgram.accept(e);
//       Play.midi(e.getScore());
        System.out.println("completed successfully");
        // Score (Contains any number of Parts)
        //   |
        //   +---- Part (Contains any number of Phrases)
        //           |
        //           +---- Phrase (Contains any number of Notes.)
        //                    |
        //                    +---- Note (Holds information about a single musical event.)
        // Looked at: update key signature // JMusic does not support key signatures and clefs! (terrible)
        // Looked at: checkout treble/bass stave
        // TODO: include octaves
        // Repeat barlines dont exist in JMusic? will look into it: https://www.studybass.com/lessons/reading-music/repeats-and-endings/
        // public static void repeat(Phrase phrase, int n)
//        Phrase[] phrases = {new Phrase(new Note(G4, MINIM)),new Phrase(new Note(C4, MINIM)),new Phrase(new Note(G4, MINIM)),new Phrase(new Note(G9, MINIM))};
//        Score score = new Score(new Part(phrases,"piano",40));
//        Play.midi(score);
//        Play.midi(e.getScore());
//        View.notate(e.getScore());
//Score outScore = e.getScore();
        //Write.xml(outScore);
        //Play.midi(e.getScore());
        PrintWriter out = new PrintWriter("out.xml");
        out.println(e.getMusicXML());
        out.close();
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