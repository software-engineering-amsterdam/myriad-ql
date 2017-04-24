// Generated from C:/Users/LGGX/Documents/SC2017/ConstructionWorkers/src/qls/grammar\QLS.g4 by ANTLR 4.6
package qls.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		STRING=25, Identifier=26, INTEGER=27, HEX=28, WS=29;
	public static final int
		RULE_stylesheet = 0, RULE_page = 1, RULE_defaultStyle = 2, RULE_section = 3, 
		RULE_question = 4, RULE_widget = 5, RULE_style = 6, RULE_type = 7;
	public static final String[] ruleNames = {
		"stylesheet", "page", "defaultStyle", "section", "question", "widget", 
		"style", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'default'", "'section'", 
		"'question'", "'widget checkbox'", "'widget spinbox'", "'widget slider'", 
		"'widget textbox'", "'widget radio'", "'('", "','", "')'", "'widget dropdown'", 
		"'width:'", "'font:'", "'fontsize:'", "'color:'", "'boolean'", "'integer'", 
		"'string'", "'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "STRING", "Identifier", "INTEGER", "HEX", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StylesheetContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(QLSParser.Identifier, 0); }
		public List<PageContext> page() {
			return getRuleContexts(PageContext.class);
		}
		public PageContext page(int i) {
			return getRuleContext(PageContext.class,i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStylesheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStylesheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStylesheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(17);
			match(Identifier);
			setState(18);
			match(T__1);
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(19);
				page();
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PageContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public List<DefaultStyleContext> defaultStyle() {
			return getRuleContexts(DefaultStyleContext.class);
		}
		public DefaultStyleContext defaultStyle(int i) {
			return getRuleContext(DefaultStyleContext.class,i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterPage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitPage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitPage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(T__3);
			setState(28);
			match(STRING);
			setState(29);
			match(T__1);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==T__5) {
				{
				setState(32);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(30);
					section();
					}
					break;
				case T__4:
					{
					setState(31);
					defaultStyle();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultStyleContext extends ParserRuleContext {
		public DefaultStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultStyle; }
	 
		public DefaultStyleContext() { }
		public void copyFrom(DefaultStyleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DefaultWithoutStyleDeclarationContext extends DefaultStyleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public DefaultWithoutStyleDeclarationContext(DefaultStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultWithoutStyleDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultWithoutStyleDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultWithoutStyleDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DefaultWithStyleDeclarationContext extends DefaultStyleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public List<StyleContext> style() {
			return getRuleContexts(StyleContext.class);
		}
		public StyleContext style(int i) {
			return getRuleContext(StyleContext.class,i);
		}
		public DefaultWithStyleDeclarationContext(DefaultStyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultWithStyleDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultWithStyleDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDefaultWithStyleDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultStyleContext defaultStyle() throws RecognitionException {
		DefaultStyleContext _localctx = new DefaultStyleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defaultStyle);
		int _la;
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new DefaultWithoutStyleDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				match(T__4);
				setState(40);
				type();
				setState(41);
				widget();
				}
				break;
			case 2:
				_localctx = new DefaultWithStyleDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				match(T__4);
				setState(44);
				type();
				setState(45);
				match(T__1);
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(46);
					style();
					}
					}
					setState(49); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0) );
				setState(51);
				widget();
				setState(52);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public List<DefaultStyleContext> defaultStyle() {
			return getRuleContexts(DefaultStyleContext.class);
		}
		public DefaultStyleContext defaultStyle(int i) {
			return getRuleContext(DefaultStyleContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__5);
			setState(57);
			match(STRING);
			setState(58);
			match(T__1);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6))) != 0)) {
				{
				setState(62);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__6:
					{
					setState(59);
					question();
					}
					break;
				case T__5:
					{
					setState(60);
					section();
					}
					break;
				case T__4:
					{
					setState(61);
					defaultStyle();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	 
		public QuestionContext() { }
		public void copyFrom(QuestionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NormalQuestionContext extends QuestionContext {
		public TerminalNode Identifier() { return getToken(QLSParser.Identifier, 0); }
		public NormalQuestionContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterNormalQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitNormalQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitNormalQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WidgetQuestionContext extends QuestionContext {
		public TerminalNode Identifier() { return getToken(QLSParser.Identifier, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public WidgetQuestionContext(QuestionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidgetQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidgetQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidgetQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new WidgetQuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(T__6);
				setState(70);
				match(Identifier);
				setState(71);
				widget();
				}
				break;
			case 2:
				_localctx = new NormalQuestionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(T__6);
				setState(73);
				match(Identifier);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WidgetContext extends ParserRuleContext {
		public WidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widget; }
	 
		public WidgetContext() { }
		public void copyFrom(WidgetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SliderContext extends WidgetContext {
		public SliderContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSlider(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSlider(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSlider(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextboxContext extends WidgetContext {
		public TextboxContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterTextbox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitTextbox(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitTextbox(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CheckboxContext extends WidgetContext {
		public CheckboxContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterCheckbox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitCheckbox(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitCheckbox(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DropdownContext extends WidgetContext {
		public Token yes;
		public Token no;
		public List<TerminalNode> STRING() { return getTokens(QLSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QLSParser.STRING, i);
		}
		public DropdownContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDropdown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDropdown(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitDropdown(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SpinboxContext extends WidgetContext {
		public SpinboxContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterSpinbox(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitSpinbox(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitSpinbox(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RadioContext extends WidgetContext {
		public Token yes;
		public Token no;
		public List<TerminalNode> STRING() { return getTokens(QLSParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QLSParser.STRING, i);
		}
		public RadioContext(WidgetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterRadio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitRadio(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitRadio(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WidgetContext widget() throws RecognitionException {
		WidgetContext _localctx = new WidgetContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_widget);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				_localctx = new CheckboxContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				match(T__7);
				}
				break;
			case T__8:
				_localctx = new SpinboxContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(T__8);
				}
				break;
			case T__9:
				_localctx = new SliderContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				match(T__9);
				}
				break;
			case T__10:
				_localctx = new TextboxContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				match(T__10);
				}
				break;
			case T__11:
				_localctx = new RadioContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				match(T__11);
				setState(81);
				match(T__12);
				setState(82);
				((RadioContext)_localctx).yes = match(STRING);
				setState(83);
				match(T__13);
				setState(84);
				((RadioContext)_localctx).no = match(STRING);
				setState(85);
				match(T__14);
				}
				break;
			case T__15:
				_localctx = new DropdownContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(86);
				match(T__15);
				setState(87);
				match(T__12);
				setState(88);
				((DropdownContext)_localctx).yes = match(STRING);
				setState(89);
				match(T__13);
				setState(90);
				((DropdownContext)_localctx).no = match(STRING);
				setState(91);
				match(T__14);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StyleContext extends ParserRuleContext {
		public StyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_style; }
	 
		public StyleContext() { }
		public void copyFrom(StyleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WidthStyleContext extends StyleContext {
		public TerminalNode INTEGER() { return getToken(QLSParser.INTEGER, 0); }
		public WidthStyleContext(StyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidthStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidthStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitWidthStyle(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColorStyleContext extends StyleContext {
		public TerminalNode HEX() { return getToken(QLSParser.HEX, 0); }
		public ColorStyleContext(StyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterColorStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitColorStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitColorStyle(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FontsizeStyleContext extends StyleContext {
		public TerminalNode INTEGER() { return getToken(QLSParser.INTEGER, 0); }
		public FontsizeStyleContext(StyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterFontsizeStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitFontsizeStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitFontsizeStyle(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FontStyleContext extends StyleContext {
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public FontStyleContext(StyleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterFontStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitFontStyle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitFontStyle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StyleContext style() throws RecognitionException {
		StyleContext _localctx = new StyleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_style);
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				_localctx = new WidthStyleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				match(T__16);
				setState(95);
				match(INTEGER);
				}
				break;
			case T__17:
				_localctx = new FontStyleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(T__17);
				setState(97);
				match(STRING);
				}
				break;
			case T__18:
				_localctx = new FontsizeStyleContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				match(T__18);
				setState(99);
				match(INTEGER);
				}
				break;
			case T__19:
				_localctx = new ColorStyleContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				match(T__19);
				setState(101);
				match(HEX);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanTypeContext extends TypeContext {
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterBooleanType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitBooleanType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerTypeContext extends TypeContext {
		public IntegerTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterIntegerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitIntegerType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitIntegerType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitStringType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyTypeContext extends TypeContext {
		public MoneyTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterMoneyType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitMoneyType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLSVisitor ) return ((QLSVisitor<? extends T>)visitor).visitMoneyType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(T__20);
				}
				break;
			case T__21:
				_localctx = new IntegerTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(T__21);
				}
				break;
			case T__22:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				match(T__22);
				}
				break;
			case T__23:
				_localctx = new MoneyTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
				match(T__23);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37q\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\7\2"+
		"\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3#\n\3\f\3\16\3"+
		"&\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\6\4\62\n\4\r\4\16\4\63"+
		"\3\4\3\4\3\4\5\49\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5A\n\5\f\5\16\5D\13\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7_\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\bi\n\b\3\t\3\t\3\t\3\t\5\to\n\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2"+
		"|\2\22\3\2\2\2\4\35\3\2\2\2\68\3\2\2\2\b:\3\2\2\2\nL\3\2\2\2\f^\3\2\2"+
		"\2\16h\3\2\2\2\20n\3\2\2\2\22\23\7\3\2\2\23\24\7\34\2\2\24\30\7\4\2\2"+
		"\25\27\5\4\3\2\26\25\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2"+
		"\31\33\3\2\2\2\32\30\3\2\2\2\33\34\7\5\2\2\34\3\3\2\2\2\35\36\7\6\2\2"+
		"\36\37\7\33\2\2\37$\7\4\2\2 #\5\b\5\2!#\5\6\4\2\" \3\2\2\2\"!\3\2\2\2"+
		"#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'(\7\5\2\2(\5\3\2"+
		"\2\2)*\7\7\2\2*+\5\20\t\2+,\5\f\7\2,9\3\2\2\2-.\7\7\2\2./\5\20\t\2/\61"+
		"\7\4\2\2\60\62\5\16\b\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64"+
		"\3\2\2\2\64\65\3\2\2\2\65\66\5\f\7\2\66\67\7\5\2\2\679\3\2\2\28)\3\2\2"+
		"\28-\3\2\2\29\7\3\2\2\2:;\7\b\2\2;<\7\33\2\2<B\7\4\2\2=A\5\n\6\2>A\5\b"+
		"\5\2?A\5\6\4\2@=\3\2\2\2@>\3\2\2\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2"+
		"\2\2CE\3\2\2\2DB\3\2\2\2EF\7\5\2\2F\t\3\2\2\2GH\7\t\2\2HI\7\34\2\2IM\5"+
		"\f\7\2JK\7\t\2\2KM\7\34\2\2LG\3\2\2\2LJ\3\2\2\2M\13\3\2\2\2N_\7\n\2\2"+
		"O_\7\13\2\2P_\7\f\2\2Q_\7\r\2\2RS\7\16\2\2ST\7\17\2\2TU\7\33\2\2UV\7\20"+
		"\2\2VW\7\33\2\2W_\7\21\2\2XY\7\22\2\2YZ\7\17\2\2Z[\7\33\2\2[\\\7\20\2"+
		"\2\\]\7\33\2\2]_\7\21\2\2^N\3\2\2\2^O\3\2\2\2^P\3\2\2\2^Q\3\2\2\2^R\3"+
		"\2\2\2^X\3\2\2\2_\r\3\2\2\2`a\7\23\2\2ai\7\35\2\2bc\7\24\2\2ci\7\33\2"+
		"\2de\7\25\2\2ei\7\35\2\2fg\7\26\2\2gi\7\36\2\2h`\3\2\2\2hb\3\2\2\2hd\3"+
		"\2\2\2hf\3\2\2\2i\17\3\2\2\2jo\7\27\2\2ko\7\30\2\2lo\7\31\2\2mo\7\32\2"+
		"\2nj\3\2\2\2nk\3\2\2\2nl\3\2\2\2nm\3\2\2\2o\21\3\2\2\2\r\30\"$\638@BL"+
		"^hn";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}