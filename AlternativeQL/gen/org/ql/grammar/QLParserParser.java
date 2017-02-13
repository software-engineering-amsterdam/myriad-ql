// Generated from QLParser.g4 by ANTLR 4.6
package org.ql.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASSIGN=1, IF=2, ELSE=3, COLON=4, FORM=5, OPEN_BRACKET=6, CLOSE_BRACKET=7, 
		OPEN_PARENT=8, CLOSE_PARENT=9, SEMICOLON=10, BANG=11, DIV=12, MUL=13, 
		ADD=14, SUB=15, GT=16, LT=17, EQUAL=18, LE=19, GE=20, NOTEQUAL=21, AND=22, 
		OR=23, BOOLEAN=24, FLOAT=25, INTEGER=26, STRING=27, MONEY=28, DATE=29, 
		LINE_COMMENT=30, STRING_LITERAL=31, BOOLEAN_LITERAL=32, FLOAT_LITERAL=33, 
		INTEGER_LITERAL=34, ID=35, WS=36;
	public static final int
		RULE_form = 0, RULE_declaration = 1, RULE_question = 2, RULE_statement = 3, 
		RULE_defaulvalue = 4, RULE_expression = 5, RULE_parameter = 6, RULE_literal = 7, 
		RULE_type = 8;
	public static final String[] ruleNames = {
		"form", "declaration", "question", "statement", "defaulvalue", "expression", 
		"parameter", "literal", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'if'", "'else'", "':'", "'form'", "'{'", "'}'", "'('", "')'", 
		"';'", "'!'", "'/'", "'*'", "'+'", "'-'", "'>'", "'<'", "'=='", "'<='", 
		"'>='", "'!='", "'&&'", "'||'", null, "'float'", "'integer'", "'string'", 
		"'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ASSIGN", "IF", "ELSE", "COLON", "FORM", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_PARENT", "CLOSE_PARENT", "SEMICOLON", "BANG", "DIV", "MUL", "ADD", 
		"SUB", "GT", "LT", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "BOOLEAN", 
		"FLOAT", "INTEGER", "STRING", "MONEY", "DATE", "LINE_COMMENT", "STRING_LITERAL", 
		"BOOLEAN_LITERAL", "FLOAT_LITERAL", "INTEGER_LITERAL", "ID", "WS"
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
	public String getGrammarFileName() { return "QLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QLParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormContext extends ParserRuleContext {
		public TerminalNode FORM() { return getToken(QLParserParser.FORM, 0); }
		public TerminalNode ID() { return getToken(QLParserParser.ID, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(QLParserParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLParserParser.CLOSE_BRACKET, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitForm(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(FORM);
			setState(19);
			match(ID);
			setState(20);
			match(OPEN_BRACKET);
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << BOOLEAN) | (1L << FLOAT) | (1L << INTEGER) | (1L << STRING) | (1L << MONEY) | (1L << DATE))) != 0)) {
				{
				{
				setState(21);
				declaration();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
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

	public static class DeclarationContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(QLParserParser.SEMICOLON, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(33);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case FLOAT:
			case INTEGER:
			case STRING:
			case MONEY:
			case DATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				question();
				setState(30);
				match(SEMICOLON);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				statement();
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

	public static class QuestionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(QLParserParser.ID, 0); }
		public TerminalNode COLON() { return getToken(QLParserParser.COLON, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(QLParserParser.STRING_LITERAL, 0); }
		public DefaulvalueContext defaulvalue() {
			return getRuleContext(DefaulvalueContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(QLParserParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question);
		int _la;
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				type();
				setState(36);
				match(ID);
				setState(37);
				match(COLON);
				setState(38);
				match(STRING_LITERAL);
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(39);
					defaulvalue();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				type();
				setState(43);
				match(ASSIGN);
				setState(44);
				expression(0);
				setState(45);
				match(COLON);
				setState(46);
				match(STRING_LITERAL);
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(47);
					defaulvalue();
					}
				}

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

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(QLParserParser.IF, 0); }
		public TerminalNode OPEN_PARENT() { return getToken(QLParserParser.OPEN_PARENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(QLParserParser.CLOSE_PARENT, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(QLParserParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLParserParser.CLOSE_BRACKET, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(QLParserParser.ELSE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(IF);
			setState(53);
			match(OPEN_PARENT);
			setState(54);
			expression(0);
			setState(55);
			match(CLOSE_PARENT);
			setState(56);
			match(OPEN_BRACKET);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << BOOLEAN) | (1L << FLOAT) | (1L << INTEGER) | (1L << STRING) | (1L << MONEY) | (1L << DATE))) != 0)) {
				{
				{
				setState(57);
				declaration();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(CLOSE_BRACKET);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(64);
				match(ELSE);
				setState(65);
				statement();
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

	public static class DefaulvalueContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(QLParserParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefaulvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaulvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterDefaulvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitDefaulvalue(this);
		}
	}

	public final DefaulvalueContext defaulvalue() throws RecognitionException {
		DefaulvalueContext _localctx = new DefaulvalueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_defaulvalue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(ASSIGN);
			setState(69);
			expression(0);
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

	public static class ExpressionContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public TerminalNode OPEN_PARENT() { return getToken(QLParserParser.OPEN_PARENT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CLOSE_PARENT() { return getToken(QLParserParser.CLOSE_PARENT, 0); }
		public TerminalNode BANG() { return getToken(QLParserParser.BANG, 0); }
		public TerminalNode DIV() { return getToken(QLParserParser.DIV, 0); }
		public TerminalNode MUL() { return getToken(QLParserParser.MUL, 0); }
		public TerminalNode SUB() { return getToken(QLParserParser.SUB, 0); }
		public TerminalNode ADD() { return getToken(QLParserParser.ADD, 0); }
		public TerminalNode GT() { return getToken(QLParserParser.GT, 0); }
		public TerminalNode LT() { return getToken(QLParserParser.LT, 0); }
		public TerminalNode EQUAL() { return getToken(QLParserParser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(QLParserParser.NOTEQUAL, 0); }
		public TerminalNode LE() { return getToken(QLParserParser.LE, 0); }
		public TerminalNode GE() { return getToken(QLParserParser.GE, 0); }
		public TerminalNode AND() { return getToken(QLParserParser.AND, 0); }
		public TerminalNode OR() { return getToken(QLParserParser.OR, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case BOOLEAN_LITERAL:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
				{
				setState(72);
				literal();
				}
				break;
			case ID:
				{
				setState(73);
				parameter();
				}
				break;
			case OPEN_PARENT:
				{
				setState(74);
				match(OPEN_PARENT);
				setState(75);
				expression(0);
				setState(76);
				match(CLOSE_PARENT);
				}
				break;
			case BANG:
				{
				setState(78);
				match(BANG);
				setState(79);
				expression(13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(120);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(118);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(82);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(83);
						match(DIV);
						setState(84);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(85);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(86);
						match(MUL);
						setState(87);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(88);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(89);
						match(SUB);
						setState(90);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(91);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(92);
						match(ADD);
						setState(93);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(95);
						match(GT);
						setState(96);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(97);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(98);
						match(LT);
						setState(99);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(100);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(101);
						match(EQUAL);
						setState(102);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(103);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(104);
						match(NOTEQUAL);
						setState(105);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(106);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(107);
						match(LE);
						setState(108);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(110);
						match(GE);
						setState(111);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(112);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(113);
						match(AND);
						setState(114);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(115);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(116);
						match(OR);
						setState(117);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(122);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParserParser.ID, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(ID);
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(QLParserParser.BOOLEAN_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(QLParserParser.STRING_LITERAL, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(QLParserParser.FLOAT_LITERAL, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(QLParserParser.INTEGER_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING_LITERAL) | (1L << BOOLEAN_LITERAL) | (1L << FLOAT_LITERAL) | (1L << INTEGER_LITERAL))) != 0)) ) {
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(QLParserParser.BOOLEAN, 0); }
		public TerminalNode FLOAT() { return getToken(QLParserParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(QLParserParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(QLParserParser.STRING, 0); }
		public TerminalNode MONEY() { return getToken(QLParserParser.MONEY, 0); }
		public TerminalNode DATE() { return getToken(QLParserParser.DATE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << FLOAT) | (1L << INTEGER) | (1L << STRING) | (1L << MONEY) | (1L << DATE))) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u0084\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3$\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\5\4+\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\63\n\4\5\4\65"+
		"\n\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5=\n\5\f\5\16\5@\13\5\3\5\3\5\3\5\5\5E"+
		"\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7S\n\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7"+
		"y\n\7\f\7\16\7|\13\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\2\3\f\13\2\4\6\b\n\f"+
		"\16\20\22\2\4\3\2!$\3\2\32\37\u0090\2\24\3\2\2\2\4#\3\2\2\2\6\64\3\2\2"+
		"\2\b\66\3\2\2\2\nF\3\2\2\2\fR\3\2\2\2\16}\3\2\2\2\20\177\3\2\2\2\22\u0081"+
		"\3\2\2\2\24\25\7\7\2\2\25\26\7%\2\2\26\32\7\b\2\2\27\31\5\4\3\2\30\27"+
		"\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32"+
		"\3\2\2\2\35\36\7\t\2\2\36\3\3\2\2\2\37 \5\6\4\2 !\7\f\2\2!$\3\2\2\2\""+
		"$\5\b\5\2#\37\3\2\2\2#\"\3\2\2\2$\5\3\2\2\2%&\5\22\n\2&\'\7%\2\2\'(\7"+
		"\6\2\2(*\7!\2\2)+\5\n\6\2*)\3\2\2\2*+\3\2\2\2+\65\3\2\2\2,-\5\22\n\2-"+
		".\7\3\2\2./\5\f\7\2/\60\7\6\2\2\60\62\7!\2\2\61\63\5\n\6\2\62\61\3\2\2"+
		"\2\62\63\3\2\2\2\63\65\3\2\2\2\64%\3\2\2\2\64,\3\2\2\2\65\7\3\2\2\2\66"+
		"\67\7\4\2\2\678\7\n\2\289\5\f\7\29:\7\13\2\2:>\7\b\2\2;=\5\4\3\2<;\3\2"+
		"\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AD\7\t\2\2BC\7\5"+
		"\2\2CE\5\b\5\2DB\3\2\2\2DE\3\2\2\2E\t\3\2\2\2FG\7\3\2\2GH\5\f\7\2H\13"+
		"\3\2\2\2IJ\b\7\1\2JS\5\20\t\2KS\5\16\b\2LM\7\n\2\2MN\5\f\7\2NO\7\13\2"+
		"\2OS\3\2\2\2PQ\7\r\2\2QS\5\f\7\17RI\3\2\2\2RK\3\2\2\2RL\3\2\2\2RP\3\2"+
		"\2\2Sz\3\2\2\2TU\f\16\2\2UV\7\16\2\2Vy\5\f\7\17WX\f\r\2\2XY\7\17\2\2Y"+
		"y\5\f\7\16Z[\f\f\2\2[\\\7\21\2\2\\y\5\f\7\r]^\f\13\2\2^_\7\20\2\2_y\5"+
		"\f\7\f`a\f\n\2\2ab\7\22\2\2by\5\f\7\13cd\f\t\2\2de\7\23\2\2ey\5\f\7\n"+
		"fg\f\b\2\2gh\7\24\2\2hy\5\f\7\tij\f\7\2\2jk\7\27\2\2ky\5\f\7\blm\f\6\2"+
		"\2mn\7\25\2\2ny\5\f\7\7op\f\5\2\2pq\7\26\2\2qy\5\f\7\6rs\f\4\2\2st\7\30"+
		"\2\2ty\5\f\7\5uv\f\3\2\2vw\7\31\2\2wy\5\f\7\4xT\3\2\2\2xW\3\2\2\2xZ\3"+
		"\2\2\2x]\3\2\2\2x`\3\2\2\2xc\3\2\2\2xf\3\2\2\2xi\3\2\2\2xl\3\2\2\2xo\3"+
		"\2\2\2xr\3\2\2\2xu\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\r\3\2\2\2|z"+
		"\3\2\2\2}~\7%\2\2~\17\3\2\2\2\177\u0080\t\2\2\2\u0080\21\3\2\2\2\u0081"+
		"\u0082\t\3\2\2\u0082\23\3\2\2\2\f\32#*\62\64>DRxz";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}