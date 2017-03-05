// Generated from C:/Users/LGGX/Documents/SC2017/ConstructionWorkers/src/qls\QLS.g4 by ANTLR 4.6
package qls.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultWithoutStyleDeclaration}
	 * labeled alternative in {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultWithoutStyleDeclaration(QLSParser.DefaultWithoutStyleDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultWithStyleDeclaration}
	 * labeled alternative in {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultWithStyleDeclaration(QLSParser.DefaultWithStyleDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(QLSParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code widgetQuestion}
	 * labeled alternative in {@link QLSParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetQuestion(QLSParser.WidgetQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code normalQuestion}
	 * labeled alternative in {@link QLSParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalQuestion(QLSParser.NormalQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckbox(QLSParser.CheckboxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code spinbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpinbox(QLSParser.SpinboxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code slider}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlider(QLSParser.SliderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextbox(QLSParser.TextboxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code radio}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadio(QLSParser.RadioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropdown}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropdown(QLSParser.DropdownContext ctx);
	/**
	 * Visit a parse tree produced by the {@code widthStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidthStyle(QLSParser.WidthStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fontStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontStyle(QLSParser.FontStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fontsizeStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontsizeStyle(QLSParser.FontsizeStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colorStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorStyle(QLSParser.ColorStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(QLSParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(QLSParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(QLSParser.StringTypeContext ctx);
}