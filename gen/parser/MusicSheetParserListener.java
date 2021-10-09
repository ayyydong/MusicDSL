// Generated from C:/Users/danie/OneDrive/Documents/School/2021W1/CPSC410/MusicSheetGenerator/src/parser\MusicSheetParser.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MusicSheetParser}.
 */
public interface MusicSheetParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MusicSheetParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MusicSheetParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#title}.
	 * @param ctx the parse tree
	 */
	void enterTitle(MusicSheetParser.TitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#title}.
	 * @param ctx the parse tree
	 */
	void exitTitle(MusicSheetParser.TitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#part}.
	 * @param ctx the parse tree
	 */
	void enterPart(MusicSheetParser.PartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#part}.
	 * @param ctx the parse tree
	 */
	void exitPart(MusicSheetParser.PartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(MusicSheetParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(MusicSheetParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#sheet}.
	 * @param ctx the parse tree
	 */
	void enterSheet(MusicSheetParser.SheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#sheet}.
	 * @param ctx the parse tree
	 */
	void exitSheet(MusicSheetParser.SheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(MusicSheetParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(MusicSheetParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#measure}.
	 * @param ctx the parse tree
	 */
	void enterMeasure(MusicSheetParser.MeasureContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#measure}.
	 * @param ctx the parse tree
	 */
	void exitMeasure(MusicSheetParser.MeasureContext ctx);
	/**
	 * Enter a parse tree produced by {@link MusicSheetParser#note}.
	 * @param ctx the parse tree
	 */
	void enterNote(MusicSheetParser.NoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MusicSheetParser#note}.
	 * @param ctx the parse tree
	 */
	void exitNote(MusicSheetParser.NoteContext ctx);
}