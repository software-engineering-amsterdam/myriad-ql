// Generated from Questionnaires\QLS\Grammar\QLS.g4 by ANTLR 4.6
#pragma warning disable 3021
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
		T__9=10, Whitespace=11, MultiLineComment=12, SingleLineComment=13, HexDigit=14, 
		LiteralValue=15, Type=16, Identifier=17, Property=18, StringLiteral=19, 
		NumberLiteral=20, ColorLiteral=21;
	public static final int
		RULE_stylesheet = 0, RULE_page = 1, RULE_section = 2, RULE_widget = 3, 
		RULE_question = 4, RULE_defaultStyle = 5, RULE_cssItem = 6;
	public static final String[] ruleNames = {
		"stylesheet", "page", "section", "widget", "question", "defaultStyle", 
		"cssItem"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'\"'", "'widget'", 
		"'question'", "'default'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "Whitespace", 
		"MultiLineComment", "SingleLineComment", "HexDigit", "LiteralValue", "Type", 
		"Identifier", "Property", "StringLiteral", "NumberLiteral", "ColorLiteral"
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
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(T__0);
			setState(15);
			match(Identifier);
			setState(16);
			match(T__1);
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(17);
				page();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
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
		public TerminalNode Identifier() { return getToken(QLSParser.Identifier, 0); }
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
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
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__3);
			setState(26);
			match(Identifier);
			setState(27);
			match(T__1);
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(28);
				section();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
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

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(QLSParser.Identifier, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<DefaultStyleContext> defaultStyle() {
			return getRuleContexts(DefaultStyleContext.class);
		}
		public DefaultStyleContext defaultStyle(int i) {
			return getRuleContext(DefaultStyleContext.class,i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
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
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(T__4);
			setState(37);
			match(T__5);
			setState(38);
			match(Identifier);
			setState(39);
			match(T__5);
			setState(40);
			match(T__1);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__8))) != 0)) {
				{
				setState(44);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__7:
					{
					setState(41);
					question();
					}
					break;
				case T__8:
					{
					setState(42);
					defaultStyle();
					}
					break;
				case T__4:
					{
					setState(43);
					section();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
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

	public static class WidgetContext extends ParserRuleContext {
		public TerminalNode Type() { return getToken(QLSParser.Type, 0); }
		public WidgetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterWidget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitWidget(this);
		}
	}

	public final WidgetContext widget() throws RecognitionException {
		WidgetContext _localctx = new WidgetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_widget);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(T__6);
			setState(52);
			match(Type);
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
		public TerminalNode Identifier() { return getToken(QLSParser.Identifier, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__7);
			setState(55);
			match(Identifier);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(56);
				match(T__1);
				setState(57);
				widget();
				setState(58);
				match(T__2);
				}
			}

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
		public TerminalNode LiteralValue() { return getToken(QLSParser.LiteralValue, 0); }
		public WidgetContext widget() {
			return getRuleContext(WidgetContext.class,0);
		}
		public List<CssItemContext> cssItem() {
			return getRuleContexts(CssItemContext.class);
		}
		public CssItemContext cssItem(int i) {
			return getRuleContext(CssItemContext.class,i);
		}
		public DefaultStyleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultStyle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterDefaultStyle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitDefaultStyle(this);
		}
	}

	public final DefaultStyleContext defaultStyle() throws RecognitionException {
		DefaultStyleContext _localctx = new DefaultStyleContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defaultStyle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__8);
			setState(63);
			match(LiteralValue);
			setState(64);
			match(T__1);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Property) {
				{
				{
				setState(65);
				cssItem();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			widget();
			setState(72);
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

	public static class CssItemContext extends ParserRuleContext {
		public TerminalNode Property() { return getToken(QLSParser.Property, 0); }
		public TerminalNode StringLiteral() { return getToken(QLSParser.StringLiteral, 0); }
		public TerminalNode NumberLiteral() { return getToken(QLSParser.NumberLiteral, 0); }
		public TerminalNode ColorLiteral() { return getToken(QLSParser.ColorLiteral, 0); }
		public CssItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cssItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).enterCssItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLSListener ) ((QLSListener)listener).exitCssItem(this);
		}
	}

	public final CssItemContext cssItem() throws RecognitionException {
		CssItemContext _localctx = new CssItemContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cssItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(Property);
			setState(75);
			match(T__9);
			setState(76);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << StringLiteral) | (1L << NumberLiteral) | (1L << ColorLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\27Q\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3 \n\3\f\3\16\3#\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6?\n\6\3\7\3\7\3\7\3\7\7\7E\n\7"+
		"\f\7\16\7H\13\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2"+
		"\3\3\2\25\27P\2\20\3\2\2\2\4\33\3\2\2\2\6&\3\2\2\2\b\65\3\2\2\2\n8\3\2"+
		"\2\2\f@\3\2\2\2\16L\3\2\2\2\20\21\7\3\2\2\21\22\7\23\2\2\22\26\7\4\2\2"+
		"\23\25\5\4\3\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2"+
		"\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\5\2\2\32\3\3\2\2\2\33\34\7\6\2\2"+
		"\34\35\7\23\2\2\35!\7\4\2\2\36 \5\6\4\2\37\36\3\2\2\2 #\3\2\2\2!\37\3"+
		"\2\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\7\5\2\2%\5\3\2\2\2&\'\7\7\2\2"+
		"\'(\7\b\2\2()\7\23\2\2)*\7\b\2\2*\60\7\4\2\2+/\5\n\6\2,/\5\f\7\2-/\5\6"+
		"\4\2.+\3\2\2\2.,\3\2\2\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2"+
		"\2\61\63\3\2\2\2\62\60\3\2\2\2\63\64\7\5\2\2\64\7\3\2\2\2\65\66\7\t\2"+
		"\2\66\67\7\22\2\2\67\t\3\2\2\289\7\n\2\29>\7\23\2\2:;\7\4\2\2;<\5\b\5"+
		"\2<=\7\5\2\2=?\3\2\2\2>:\3\2\2\2>?\3\2\2\2?\13\3\2\2\2@A\7\13\2\2AB\7"+
		"\21\2\2BF\7\4\2\2CE\5\16\b\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G"+
		"I\3\2\2\2HF\3\2\2\2IJ\5\b\5\2JK\7\5\2\2K\r\3\2\2\2LM\7\24\2\2MN\7\f\2"+
		"\2NO\t\2\2\2O\17\3\2\2\2\b\26!.\60>F";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}