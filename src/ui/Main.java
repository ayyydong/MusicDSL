package ui;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import parser.MusicSheetLexer;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {

    public static void main(String[] args) throws IOException {
        MusicSheetLexer lexer;
        try {
            lexer = new MusicSheetLexer(CharStreams.fromFileName("test_sheet.txt"));

        } catch (NoSuchFileException e) {
            System.out.println("ERROR: File not found\n" + e.toString());
            return;
        }

        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        System.out.println("Done tokenizing");


    }
}
