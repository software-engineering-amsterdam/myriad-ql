// Generated from C:/Users/LGGX/Documents/SC2017/ConstructionWorkers/src/qls\QLS.g4 by ANTLR 4.6
package qls.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLSParser}.
 */
public interface QLSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultWithoutStyleDeclaration}
	 * labeled alternative in {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 */
	void enterDefaultWithoutStyleDeclaration(QLSParser.DefaultWithoutStyleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultWithoutStyleDeclaration}
	 * labeled alternative in {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 */
	void exitDefaultWithoutStyleDeclaration(QLSParser.DefaultWithoutStyleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultWithStyleDeclaration}
	 * labeled alternative in {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 */
	void enterDefaultWithStyleDeclaration(QLSParser.DefaultWithStyleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultWithStyleDeclaration}
	 * labeled alternative in {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 */
	void exitDefaultWithStyleDeclaration(QLSParser.DefaultWithStyleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(QLSParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(QLSParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code widgetQuestion}
	 * labeled alternative in {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void enterWidgetQuestion(QLSParser.WidgetQuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code widgetQuestion}
	 * labeled alternative in {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void exitWidgetQuestion(QLSParser.WidgetQuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalQuestion}
	 * labeled alternative in {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void enterNormalQuestion(QLSParser.NormalQuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalQuestion}
	 * labeled alternative in {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void exitNormalQuestion(QLSParser.NormalQuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterCheckbox(QLSParser.CheckboxContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitCheckbox(QLSParser.CheckboxContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spinbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterSpinbox(QLSParser.SpinboxContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spinbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitSpinbox(QLSParser.SpinboxContext ctx);
	/**
	 * Enter a parse tree produced by the {@code slider}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterSlider(QLSParser.SliderContext ctx);
	/**
	 * Exit a parse tree produced by the {@code slider}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitSlider(QLSParser.SliderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterTextbox(QLSParser.TextboxContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textbox}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitTextbox(QLSParser.TextboxContext ctx);
	/**
	 * Enter a parse tree produced by the {@code radio}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterRadio(QLSParser.RadioContext ctx);
	/**
	 * Exit a parse tree produced by the {@code radio}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitRadio(QLSParser.RadioContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropdown}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterDropdown(QLSParser.DropdownContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropdown}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitDropdown(QLSParser.DropdownContext ctx);
	/**
	 * Enter a parse tree produced by the {@code widthStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterWidthStyle(QLSParser.WidthStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code widthStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitWidthStyle(QLSParser.WidthStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterFontStyle(QLSParser.FontStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitFontStyle(QLSParser.FontStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontsizeStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterFontsizeStyle(QLSParser.FontsizeStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontsizeStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitFontsizeStyle(QLSParser.FontsizeStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterColorStyle(QLSParser.ColorStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitColorStyle(QLSParser.ColorStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(QLSParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(QLSParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(QLSParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(QLSParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(QLSParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(QLSParser.StringTypeContext ctx);
}