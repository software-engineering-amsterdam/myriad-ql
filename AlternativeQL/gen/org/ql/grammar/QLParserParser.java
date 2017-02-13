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
		T__0=1, ASSIGN=2, IF=3, ELSE=4, COLON=5, FORM=6, OPEN_BRACKET=7, CLOSE_BRACKET=8, 
		OPEN_PARENT=9, CLOSE_PARENT=10, SEMICOLON=11, BANG=12, DIV=13, MUL=14, 
		ADD=15, SUB=16, GT=17, LT=18, EQUAL=19, LE=20, GE=21, NOTEQUAL=22, AND=23, 
		OR=24, BOOLEAN=25, FLOAT=26, INTEGER=27, STRING=28, MONEY=29, DATE=30, 
		LINE_COMMENT=31, STRING_LITERAL=32, BOOLEAN_LITERAL=33, FLOAT_LITERAL=34, 
		INTEGER_LITERAL=35, ID=36, WS=37;
	public static final int
		RULE_form = 0, RULE_statement = 1, RULE_defaultValue = 2, RULE_expression = 3, 
		RULE_parameter = 4, RULE_identifier = 5, RULE_literal = 6, RULE_type = 7;
	public static final String[] ruleNames = {
		"form", "statement", "defaultValue", "expression", "parameter", "identifier", 
		"literal", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'boolean'", "'='", "'if'", "'else'", "':'", "'form'", "'{'", "'}'", 
		"'('", "')'", "';'", "'!'", "'/'", "'*'", "'+'", "'-'", "'>'", "'<'", 
		"'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", null, "'float'", "'integer'", 
		"'string'", "'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "ASSIGN", "IF", "ELSE", "COLON", "FORM", "OPEN_BRACKET", "CLOSE_BRACKET", 
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
		public IdentifierContext id;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(FORM);
			setState(17);
			((FormContext)_localctx).id = identifier();
			setState(18);
			match(OPEN_BRACKET);
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << IF) | (1L << FLOAT) | (1L << INTEGER) | (1L << STRING) | (1L << MONEY) | (1L << DATE))) != 0)) {
				{
				{
				setState(19);
				statement();
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25);
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

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionContext extends StatementContext {
		public IdentifierContext id;
		public Token questionMsg;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(QLParserParser.STRING_LITERAL, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public QuestionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case FLOAT:
			case INTEGER:
			case STRING:
			case MONEY:
			case DATE:
				_localctx = new QuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				type();
				setState(28);
				((QuestionContext)_localctx).id = identifier();
				setState(29);
				match(COLON);
				setState(30);
				((QuestionContext)_localctx).questionMsg = match(STRING_LITERAL);
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(31);
					defaultValue();
					}
				}

				setState(34);
				match(SEMICOLON);
				}
				break;
			case IF:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				match(IF);
				setState(37);
				match(OPEN_PARENT);
				setState(38);
				expression(0);
				setState(39);
				match(CLOSE_PARENT);
				setState(40);
				match(OPEN_BRACKET);
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << IF) | (1L << FLOAT) | (1L << INTEGER) | (1L << STRING) | (1L << MONEY) | (1L << DATE))) != 0)) {
					{
					{
					setState(41);
					statement();
					}
					}
					setState(46);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(47);
				match(CLOSE_BRACKET);
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(48);
					match(ELSE);
					setState(49);
					statement();
					}
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

	public static class DefaultValueContext extends ParserRuleContext {
		public ExpressionContext value;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitDefaultValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(ASSIGN);
			setState(55);
			((DefaultValueContext)_localctx).value = expression(0);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case BOOLEAN_LITERAL:
			case FLOAT_LITERAL:
			case INTEGER_LITERAL:
				{
				setState(58);
				literal();
				}
				break;
			case ID:
				{
				setState(59);
				parameter();
				}
				break;
			case OPEN_PARENT:
				{
				setState(60);
				match(OPEN_PARENT);
				setState(61);
				expression(0);
				setState(62);
				match(CLOSE_PARENT);
				}
				break;
			case BANG:
				{
				setState(64);
				match(BANG);
				setState(65);
				expression(13);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(104);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(68);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(69);
						match(DIV);
						setState(70);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(71);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(72);
						match(MUL);
						setState(73);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(74);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(75);
						match(SUB);
						setState(76);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(77);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(78);
						match(ADD);
						setState(79);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(80);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(81);
						match(GT);
						setState(82);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(83);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(84);
						match(LT);
						setState(85);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(86);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(87);
						match(EQUAL);
						setState(88);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(89);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(90);
						match(NOTEQUAL);
						setState(91);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(92);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(93);
						match(LE);
						setState(94);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(95);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(96);
						match(GE);
						setState(97);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(98);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(99);
						match(AND);
						setState(100);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(101);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(102);
						match(OR);
						setState(103);
						expression(2);
						}
						break;
					}
					} 
				}
				setState(108);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParserParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
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
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringLiteralContext extends LiteralContext {
		public TerminalNode STRING_LITERAL() { return getToken(QLParserParser.STRING_LITERAL, 0); }
		public StringLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatLiteralContext extends LiteralContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(QLParserParser.FLOAT_LITERAL, 0); }
		public FloatLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitFloatLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerLiteralContext extends LiteralContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(QLParserParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends LiteralContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(QLParserParser.BOOLEAN_LITERAL, 0); }
		public BooleanLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitBooleanLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_literal);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_LITERAL:
				_localctx = new BooleanLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				match(BOOLEAN_LITERAL);
				}
				break;
			case STRING_LITERAL:
				_localctx = new StringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(STRING_LITERAL);
				}
				break;
			case FLOAT_LITERAL:
				_localctx = new FloatLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(FLOAT_LITERAL);
				}
				break;
			case INTEGER_LITERAL:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				match(INTEGER_LITERAL);
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
	public static class TypeDateContext extends TypeContext {
		public TypeDateContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterTypeDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitTypeDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitTypeDate(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeFloatContext extends TypeContext {
		public TypeFloatContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterTypeFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitTypeFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitTypeFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeMoneyContext extends TypeContext {
		public TypeMoneyContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterTypeMoney(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitTypeMoney(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitTypeMoney(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeIntegerContext extends TypeContext {
		public TypeIntegerContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterTypeInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitTypeInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitTypeInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeStringContext extends TypeContext {
		public TypeStringContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterTypeString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitTypeString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitTypeString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeBooleanContext extends TypeContext {
		public TypeBooleanContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterTypeBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitTypeBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitTypeBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				match(T__0);
				}
				break;
			case FLOAT:
				_localctx = new TypeFloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				match(FLOAT);
				}
				break;
			case INTEGER:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(121);
				match(INTEGER);
				}
				break;
			case STRING:
				_localctx = new TypeStringContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
				match(STRING);
				}
				break;
			case MONEY:
				_localctx = new TypeMoneyContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(123);
				match(MONEY);
				}
				break;
			case DATE:
				_localctx = new TypeDateContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
				match(DATE);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u0082\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\7\2\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3#\n\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\3\3\3\3\3\5\3"+
		"\65\n\3\5\3\67\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5"+
		"E\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\5k\n\5\f\5\16\5n\13\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\5\b"+
		"x\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\t\2\3\b\n\2\4\6\b\n\f\16"+
		"\20\2\2\u0095\2\22\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\bD\3\2\2\2\no\3\2\2"+
		"\2\fq\3\2\2\2\16w\3\2\2\2\20\177\3\2\2\2\22\23\7\b\2\2\23\24\5\f\7\2\24"+
		"\30\7\t\2\2\25\27\5\4\3\2\26\25\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30"+
		"\31\3\2\2\2\31\33\3\2\2\2\32\30\3\2\2\2\33\34\7\n\2\2\34\3\3\2\2\2\35"+
		"\36\5\20\t\2\36\37\5\f\7\2\37 \7\7\2\2 \"\7\"\2\2!#\5\6\4\2\"!\3\2\2\2"+
		"\"#\3\2\2\2#$\3\2\2\2$%\7\r\2\2%\67\3\2\2\2&\'\7\5\2\2\'(\7\13\2\2()\5"+
		"\b\5\2)*\7\f\2\2*.\7\t\2\2+-\5\4\3\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2."+
		"/\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\64\7\n\2\2\62\63\7\6\2\2\63\65\5"+
		"\4\3\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\35\3\2\2\2\66&\3\2"+
		"\2\2\67\5\3\2\2\289\7\4\2\29:\5\b\5\2:\7\3\2\2\2;<\b\5\1\2<E\5\16\b\2"+
		"=E\5\n\6\2>?\7\13\2\2?@\5\b\5\2@A\7\f\2\2AE\3\2\2\2BC\7\16\2\2CE\5\b\5"+
		"\17D;\3\2\2\2D=\3\2\2\2D>\3\2\2\2DB\3\2\2\2El\3\2\2\2FG\f\16\2\2GH\7\17"+
		"\2\2Hk\5\b\5\17IJ\f\r\2\2JK\7\20\2\2Kk\5\b\5\16LM\f\f\2\2MN\7\22\2\2N"+
		"k\5\b\5\rOP\f\13\2\2PQ\7\21\2\2Qk\5\b\5\fRS\f\n\2\2ST\7\23\2\2Tk\5\b\5"+
		"\13UV\f\t\2\2VW\7\24\2\2Wk\5\b\5\nXY\f\b\2\2YZ\7\25\2\2Zk\5\b\5\t[\\\f"+
		"\7\2\2\\]\7\30\2\2]k\5\b\5\b^_\f\6\2\2_`\7\26\2\2`k\5\b\5\7ab\f\5\2\2"+
		"bc\7\27\2\2ck\5\b\5\6de\f\4\2\2ef\7\31\2\2fk\5\b\5\5gh\f\3\2\2hi\7\32"+
		"\2\2ik\5\b\5\4jF\3\2\2\2jI\3\2\2\2jL\3\2\2\2jO\3\2\2\2jR\3\2\2\2jU\3\2"+
		"\2\2jX\3\2\2\2j[\3\2\2\2j^\3\2\2\2ja\3\2\2\2jd\3\2\2\2jg\3\2\2\2kn\3\2"+
		"\2\2lj\3\2\2\2lm\3\2\2\2m\t\3\2\2\2nl\3\2\2\2op\7&\2\2p\13\3\2\2\2qr\7"+
		"&\2\2r\r\3\2\2\2sx\7#\2\2tx\7\"\2\2ux\7$\2\2vx\7%\2\2ws\3\2\2\2wt\3\2"+
		"\2\2wu\3\2\2\2wv\3\2\2\2x\17\3\2\2\2y\u0080\7\3\2\2z\u0080\7\34\2\2{\u0080"+
		"\7\35\2\2|\u0080\7\36\2\2}\u0080\7\37\2\2~\u0080\7 \2\2\177y\3\2\2\2\177"+
		"z\3\2\2\2\177{\3\2\2\2\177|\3\2\2\2\177}\3\2\2\2\177~\3\2\2\2\u0080\21"+
		"\3\2\2\2\f\30\".\64\66Djlw\177";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}