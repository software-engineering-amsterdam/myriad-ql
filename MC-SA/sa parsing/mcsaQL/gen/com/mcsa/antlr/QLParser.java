// Generated from C:/Users/sotos/Documents/GitHub/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/grammars\QL.g4 by ANTLR 4.6
package com.mcsa.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, ID=22, STRING=23, NUMBER=24, WHITESPACE=25, 
		COMMENT=26, OPEN_BRACKET=27, CLOSE_BRACKET=28, OPEN_PARENTH=29, CLOSE_PARENTH=30;
	public static final int
		RULE_formDeclaration = 0, RULE_content = 1, RULE_categories = 2, RULE_ifStatement = 3, 
		RULE_question = 4, RULE_ifCase = 5, RULE_ifCaseArgs = 6, RULE_questionParameters = 7, 
		RULE_type = 8, RULE_mathaction = 9;
	public static final String[] ruleNames = {
		"formDeclaration", "content", "categories", "ifStatement", "question", 
		"ifCase", "ifCaseArgs", "questionParameters", "type", "mathaction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", "'>'", "'<'", "'<='", "'>='", "'=='", "'AND'", 
		"'OR'", "':'", "'boolean'", "'integer'", "'double'", "'float'", "'string'", 
		"'money'", "'='", "'*'", "'/'", "'+'", "'-'", null, null, null, null, 
		null, "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "ID", "STRING", 
		"NUMBER", "WHITESPACE", "COMMENT", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", 
		"CLOSE_PARENTH"
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
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormDeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(QLParser.OPEN_BRACKET, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(QLParser.CLOSE_BRACKET, 0); }
		public FormDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterFormDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitFormDeclaration(this);
		}
	}

	public final FormDeclarationContext formDeclaration() throws RecognitionException {
		FormDeclarationContext _localctx = new FormDeclarationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(T__0);
			setState(21);
			match(ID);
			setState(22);
			match(OPEN_BRACKET);
			setState(23);
			content();
			setState(24);
			match(CLOSE_BRACKET);
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

	public static class ContentContext extends ParserRuleContext {
		public List<CategoriesContext> categories() {
			return getRuleContexts(CategoriesContext.class);
		}
		public CategoriesContext categories(int i) {
			return getRuleContext(CategoriesContext.class,i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				categories();
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << STRING) | (1L << NUMBER) | (1L << WHITESPACE) | (1L << COMMENT))) != 0) );
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

	public static class CategoriesContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode WHITESPACE() { return getToken(QLParser.WHITESPACE, 0); }
		public TerminalNode COMMENT() { return getToken(QLParser.COMMENT, 0); }
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public CategoriesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_categories; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterCategories(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitCategories(this);
		}
	}

	public final CategoriesContext categories() throws RecognitionException {
		CategoriesContext _localctx = new CategoriesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_categories);
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				question();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				match(NUMBER);
				}
				break;
			case WHITESPACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				match(WHITESPACE);
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(34);
				match(COMMENT);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 5);
				{
				setState(35);
				ifStatement();
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

	public static class IfStatementContext extends ParserRuleContext {
		public IfCaseContext ifCase() {
			return getRuleContext(IfCaseContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(QLParser.OPEN_BRACKET, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(QLParser.CLOSE_BRACKET, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__1);
			setState(39);
			ifCase();
			setState(40);
			match(OPEN_BRACKET);
			setState(41);
			content();
			setState(42);
			match(CLOSE_BRACKET);
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
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public QuestionParametersContext questionParameters() {
			return getRuleContext(QuestionParametersContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(STRING);
			setState(45);
			questionParameters();
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

	public static class IfCaseContext extends ParserRuleContext {
		public List<IfCaseArgsContext> ifCaseArgs() {
			return getRuleContexts(IfCaseArgsContext.class);
		}
		public IfCaseArgsContext ifCaseArgs(int i) {
			return getRuleContext(IfCaseArgsContext.class,i);
		}
		public TerminalNode OPEN_PARENTH() { return getToken(QLParser.OPEN_PARENTH, 0); }
		public TerminalNode CLOSE_PARENTH() { return getToken(QLParser.CLOSE_PARENTH, 0); }
		public List<IfCaseContext> ifCase() {
			return getRuleContexts(IfCaseContext.class);
		}
		public IfCaseContext ifCase(int i) {
			return getRuleContext(IfCaseContext.class,i);
		}
		public IfCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfCase(this);
		}
	}

	public final IfCaseContext ifCase() throws RecognitionException {
		IfCaseContext _localctx = new IfCaseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifCase);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				ifCaseArgs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				match(OPEN_PARENTH);
				setState(49);
				ifCaseArgs();
				setState(50);
				match(T__2);
				setState(51);
				ifCaseArgs();
				setState(52);
				match(CLOSE_PARENTH);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				match(OPEN_PARENTH);
				setState(55);
				ifCaseArgs();
				setState(56);
				match(T__3);
				setState(57);
				ifCaseArgs();
				setState(58);
				match(CLOSE_PARENTH);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				match(OPEN_PARENTH);
				setState(61);
				ifCaseArgs();
				setState(62);
				match(T__4);
				setState(63);
				ifCaseArgs();
				setState(64);
				match(CLOSE_PARENTH);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				match(OPEN_PARENTH);
				setState(67);
				ifCaseArgs();
				setState(68);
				match(T__5);
				setState(69);
				ifCaseArgs();
				setState(70);
				match(CLOSE_PARENTH);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(72);
				match(OPEN_PARENTH);
				setState(73);
				ifCaseArgs();
				setState(74);
				match(T__6);
				setState(75);
				ifCaseArgs();
				setState(76);
				match(CLOSE_PARENTH);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(78);
				match(OPEN_PARENTH);
				setState(79);
				ifCase();
				setState(80);
				match(T__7);
				setState(81);
				ifCase();
				setState(82);
				match(CLOSE_PARENTH);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(84);
				match(OPEN_PARENTH);
				setState(85);
				ifCase();
				setState(86);
				match(T__8);
				setState(87);
				ifCase();
				setState(88);
				match(CLOSE_PARENTH);
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

	public static class IfCaseArgsContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public IfCaseArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCaseArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIfCaseArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIfCaseArgs(this);
		}
	}

	public final IfCaseArgsContext ifCaseArgs() throws RecognitionException {
		IfCaseArgsContext _localctx = new IfCaseArgsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifCaseArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
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

	public static class QuestionParametersContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public QuestionParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestionParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestionParameters(this);
		}
	}

	public final QuestionParametersContext questionParameters() throws RecognitionException {
		QuestionParametersContext _localctx = new QuestionParametersContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(ID);
			setState(95);
			match(T__9);
			setState(96);
			type();
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
		public List<MathactionContext> mathaction() {
			return getRuleContexts(MathactionContext.class);
		}
		public MathactionContext mathaction(int i) {
			return getRuleContext(MathactionContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(T__10);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(100);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 4);
				{
				setState(101);
				match(T__13);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 5);
				{
				setState(102);
				match(T__14);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 6);
				{
				setState(103);
				match(T__15);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(104);
					match(T__16);
					setState(105);
					mathaction(0);
					}
					}
					setState(110);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class MathactionContext extends ParserRuleContext {
		public Token op;
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public List<MathactionContext> mathaction() {
			return getRuleContexts(MathactionContext.class);
		}
		public MathactionContext mathaction(int i) {
			return getRuleContext(MathactionContext.class,i);
		}
		public MathactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterMathaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitMathaction(this);
		}
	}

	public final MathactionContext mathaction() throws RecognitionException {
		return mathaction(0);
	}

	private MathactionContext mathaction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MathactionContext _localctx = new MathactionContext(_ctx, _parentState);
		MathactionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_mathaction, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(114);
				match(NUMBER);
				}
				break;
			case ID:
				{
				setState(115);
				match(ID);
				}
				break;
			case OPEN_PARENTH:
				{
				setState(116);
				match(OPEN_PARENTH);
				setState(117);
				mathaction(0);
				setState(118);
				match(CLOSE_PARENTH);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(128);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(122);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(123);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(124);
						mathaction(6);
						}
						break;
					case 2:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(125);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(126);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(127);
						mathaction(5);
						}
						break;
					}
					} 
				}
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return mathaction_sempred((MathactionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean mathaction_sempred(MathactionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 \u0088\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3\36\n\3\r\3\16\3\37\3\4\3\4\3\4\3\4"+
		"\3\4\5\4\'\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7]\n\7\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\nm\n\n\f\n\16\np\13\n\5\nr\n\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13{\n\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0083\n\13\f\13\16"+
		"\13\u0086\13\13\3\13\2\3\24\f\2\4\6\b\n\f\16\20\22\24\2\5\4\2\30\30\32"+
		"\32\3\2\24\25\3\2\26\27\u0093\2\26\3\2\2\2\4\35\3\2\2\2\6&\3\2\2\2\b("+
		"\3\2\2\2\n.\3\2\2\2\f\\\3\2\2\2\16^\3\2\2\2\20`\3\2\2\2\22q\3\2\2\2\24"+
		"z\3\2\2\2\26\27\7\3\2\2\27\30\7\30\2\2\30\31\7\35\2\2\31\32\5\4\3\2\32"+
		"\33\7\36\2\2\33\3\3\2\2\2\34\36\5\6\4\2\35\34\3\2\2\2\36\37\3\2\2\2\37"+
		"\35\3\2\2\2\37 \3\2\2\2 \5\3\2\2\2!\'\5\n\6\2\"\'\7\32\2\2#\'\7\33\2\2"+
		"$\'\7\34\2\2%\'\5\b\5\2&!\3\2\2\2&\"\3\2\2\2&#\3\2\2\2&$\3\2\2\2&%\3\2"+
		"\2\2\'\7\3\2\2\2()\7\4\2\2)*\5\f\7\2*+\7\35\2\2+,\5\4\3\2,-\7\36\2\2-"+
		"\t\3\2\2\2./\7\31\2\2/\60\5\20\t\2\60\13\3\2\2\2\61]\5\16\b\2\62\63\7"+
		"\37\2\2\63\64\5\16\b\2\64\65\7\5\2\2\65\66\5\16\b\2\66\67\7 \2\2\67]\3"+
		"\2\2\289\7\37\2\29:\5\16\b\2:;\7\6\2\2;<\5\16\b\2<=\7 \2\2=]\3\2\2\2>"+
		"?\7\37\2\2?@\5\16\b\2@A\7\7\2\2AB\5\16\b\2BC\7 \2\2C]\3\2\2\2DE\7\37\2"+
		"\2EF\5\16\b\2FG\7\b\2\2GH\5\16\b\2HI\7 \2\2I]\3\2\2\2JK\7\37\2\2KL\5\16"+
		"\b\2LM\7\t\2\2MN\5\16\b\2NO\7 \2\2O]\3\2\2\2PQ\7\37\2\2QR\5\f\7\2RS\7"+
		"\n\2\2ST\5\f\7\2TU\7 \2\2U]\3\2\2\2VW\7\37\2\2WX\5\f\7\2XY\7\13\2\2YZ"+
		"\5\f\7\2Z[\7 \2\2[]\3\2\2\2\\\61\3\2\2\2\\\62\3\2\2\2\\8\3\2\2\2\\>\3"+
		"\2\2\2\\D\3\2\2\2\\J\3\2\2\2\\P\3\2\2\2\\V\3\2\2\2]\r\3\2\2\2^_\t\2\2"+
		"\2_\17\3\2\2\2`a\7\30\2\2ab\7\f\2\2bc\5\22\n\2c\21\3\2\2\2dr\7\r\2\2e"+
		"r\7\16\2\2fr\7\17\2\2gr\7\20\2\2hr\7\21\2\2in\7\22\2\2jk\7\23\2\2km\5"+
		"\24\13\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2or\3\2\2\2pn\3\2\2\2q"+
		"d\3\2\2\2qe\3\2\2\2qf\3\2\2\2qg\3\2\2\2qh\3\2\2\2qi\3\2\2\2r\23\3\2\2"+
		"\2st\b\13\1\2t{\7\32\2\2u{\7\30\2\2vw\7\37\2\2wx\5\24\13\2xy\7 \2\2y{"+
		"\3\2\2\2zs\3\2\2\2zu\3\2\2\2zv\3\2\2\2{\u0084\3\2\2\2|}\f\7\2\2}~\t\3"+
		"\2\2~\u0083\5\24\13\b\177\u0080\f\6\2\2\u0080\u0081\t\4\2\2\u0081\u0083"+
		"\5\24\13\7\u0082|\3\2\2\2\u0082\177\3\2\2\2\u0083\u0086\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\25\3\2\2\2\u0086\u0084\3\2\2"+
		"\2\n\37&\\nqz\u0082\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}