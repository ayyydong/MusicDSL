// Generated from /home/ammanz/Projects/CPSC-410/Project1Group22/src/parser/MusicSheetParser.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MusicSheetParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MusicSheetParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MusicSheetParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle(MusicSheetParser.TitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPart(MusicSheetParser.PartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(MusicSheetParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#sheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSheet(MusicSheetParser.SheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(MusicSheetParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MusicSheetParser#measure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeasure(MusicSheetParser.MeasureContext ctx);
}