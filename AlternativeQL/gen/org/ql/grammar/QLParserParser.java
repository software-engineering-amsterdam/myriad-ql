// Generated from /home/yoan/github/myriad-ql/AlternativeQL/src/org/ql/grammar/QLParser.g4 by ANTLR 4.6
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		LINE_COMMENT=32, STRING_LITERAL=33, BOOLEAN_LITERAL=34, DECIMAL_LITERAL=35, 
		INTEGER_LITERAL=36, ID=37, WS=38;
	public static final int
		RULE_form = 0, RULE_statement = 1, RULE_questionText = 2, RULE_defaultValue = 3, 
		RULE_expression = 4, RULE_identifier = 5, RULE_type = 6;
	public static final String[] ruleNames = {
		"form", "statement", "questionText", "defaultValue", "expression", "identifier", 
		"type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "';'", "'if'", "'('", "')'", "'else'", 
		"'='", "'!'", "'++'", "'--'", "'/'", "'*'", "'-'", "'+'", "'>'", "'<'", 
		"'=='", "'!='", "'<='", "'>='", "'&&'", "'||'", "'boolean'", "'float'", 
		"'integer'", "'string'", "'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "LINE_COMMENT", "STRING_LITERAL", 
		"BOOLEAN_LITERAL", "DECIMAL_LITERAL", "INTEGER_LITERAL", "ID", "WS"
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
			setState(14);
			match(T__0);
			setState(15);
			((FormContext)_localctx).id = identifier();
			setState(16);
			match(T__1);
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) {
				{
				{
				setState(17);
				statement();
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
		public QuestionTextContext text;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public QuestionTextContext questionText() {
			return getRuleContext(QuestionTextContext.class,0);
		}
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
	public static class IfElseThenContext extends StatementContext {
		public StatementContext statement;
		public List<StatementContext> thenStatements = new ArrayList<StatementContext>();
		public List<StatementContext> elseStatements = new ArrayList<StatementContext>();
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfElseThenContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterIfElseThen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitIfElseThen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitIfElseThen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfThenContext extends StatementContext {
		public StatementContext statement;
		public List<StatementContext> thenStatements = new ArrayList<StatementContext>();
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfThenContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterIfThen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitIfThen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitIfThen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new QuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				type();
				setState(26);
				((QuestionContext)_localctx).id = identifier();
				setState(27);
				match(T__3);
				setState(28);
				((QuestionContext)_localctx).text = questionText();
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__9) {
					{
					setState(29);
					defaultValue();
					}
				}

				setState(32);
				match(T__4);
				}
				break;
			case 2:
				_localctx = new IfThenContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(T__5);
				setState(35);
				match(T__6);
				setState(36);
				expression(0);
				setState(37);
				match(T__7);
				setState(38);
				match(T__1);
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) {
					{
					{
					setState(39);
					((IfThenContext)_localctx).statement = statement();
					((IfThenContext)_localctx).thenStatements.add(((IfThenContext)_localctx).statement);
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(45);
				match(T__2);
				}
				break;
			case 3:
				_localctx = new IfElseThenContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				match(T__5);
				setState(48);
				match(T__6);
				setState(49);
				expression(0);
				setState(50);
				match(T__7);
				setState(51);
				match(T__1);
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) {
					{
					{
					setState(52);
					((IfElseThenContext)_localctx).statement = statement();
					((IfElseThenContext)_localctx).thenStatements.add(((IfElseThenContext)_localctx).statement);
					}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(58);
				match(T__2);
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(59);
					match(T__8);
					setState(60);
					match(T__1);
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30))) != 0)) {
						{
						{
						setState(61);
						((IfElseThenContext)_localctx).statement = statement();
						((IfElseThenContext)_localctx).elseStatements.add(((IfElseThenContext)_localctx).statement);
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

	public static class QuestionTextContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(QLParserParser.STRING_LITERAL, 0); }
		public QuestionTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterQuestionText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitQuestionText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitQuestionText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTextContext questionText() throws RecognitionException {
		QuestionTextContext _localctx = new QuestionTextContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(STRING_LITERAL);
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
		enterRule(_localctx, 6, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__9);
			setState(75);
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DecimalLiteralContext extends ExpressionContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(QLParserParser.DECIMAL_LITERAL, 0); }
		public DecimalLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterDecimalLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitDecimalLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitDecimalLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegationContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProductContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ProductContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitProduct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitProduct(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubtractionContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SubtractionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterSubtraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitSubtraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitSubtraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NotEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterNotEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitNotEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitNotEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalAndContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitLogicalAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IncrementContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IncrementContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterIncrement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitIncrement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitIncrement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LowerThanContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LowerThanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterLowerThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitLowerThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitLowerThan(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThanOrEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GreaterThanOrEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterGreaterThanOrEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitGreaterThanOrEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitGreaterThanOrEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivisionContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DivisionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExpressionContext {
		public TerminalNode STRING_LITERAL() { return getToken(QLParserParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
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
	public static class DecrementContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DecrementContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterDecrement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitDecrement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitDecrement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParameterContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(QLParserParser.ID, 0); }
		public ParameterContext(ExpressionContext ctx) { copyFrom(ctx); }
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
	public static class EqualsContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LowerThanOrEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LowerThanOrEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterLowerThanOrEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitLowerThanOrEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitLowerThanOrEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerLiteralContext extends ExpressionContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(QLParserParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
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
	public static class LogicalOrContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitLogicalOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends ExpressionContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(QLParserParser.BOOLEAN_LITERAL, 0); }
		public BooleanLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
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
	public static class GroupContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GroupContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditionContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AdditionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitAddition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitAddition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThanContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GreaterThanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).enterGreaterThan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLParserListener ) ((QLParserListener)listener).exitGreaterThan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLParserVisitor ) return ((QLParserVisitor<? extends T>)visitor).visitGreaterThan(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_LITERAL:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(78);
				match(BOOLEAN_LITERAL);
				}
				break;
			case STRING_LITERAL:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(79);
				match(STRING_LITERAL);
				}
				break;
			case DECIMAL_LITERAL:
				{
				_localctx = new DecimalLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				match(DECIMAL_LITERAL);
				}
				break;
			case INTEGER_LITERAL:
				{
				_localctx = new IntegerLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(INTEGER_LITERAL);
				}
				break;
			case ID:
				{
				_localctx = new ParameterContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(ID);
				}
				break;
			case T__6:
				{
				_localctx = new GroupContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(T__6);
				setState(84);
				expression(0);
				setState(85);
				match(T__7);
				}
				break;
			case T__10:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				match(T__10);
				setState(88);
				expression(15);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(131);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new DivisionContext(new ExpressionContext(_parentctx, _parentState));
						((DivisionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(91);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(92);
						match(T__13);
						setState(93);
						((DivisionContext)_localctx).right = expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ProductContext(new ExpressionContext(_parentctx, _parentState));
						((ProductContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(95);
						match(T__14);
						setState(96);
						((ProductContext)_localctx).right = expression(12);
						}
						break;
					case 3:
						{
						_localctx = new SubtractionContext(new ExpressionContext(_parentctx, _parentState));
						((SubtractionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(97);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(98);
						match(T__15);
						setState(99);
						((SubtractionContext)_localctx).right = expression(11);
						}
						break;
					case 4:
						{
						_localctx = new AdditionContext(new ExpressionContext(_parentctx, _parentState));
						((AdditionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(100);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(101);
						match(T__16);
						setState(102);
						((AdditionContext)_localctx).right = expression(10);
						}
						break;
					case 5:
						{
						_localctx = new GreaterThanContext(new ExpressionContext(_parentctx, _parentState));
						((GreaterThanContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(103);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(104);
						match(T__17);
						setState(105);
						((GreaterThanContext)_localctx).right = expression(9);
						}
						break;
					case 6:
						{
						_localctx = new LowerThanContext(new ExpressionContext(_parentctx, _parentState));
						((LowerThanContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(106);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(107);
						match(T__18);
						setState(108);
						((LowerThanContext)_localctx).right = expression(8);
						}
						break;
					case 7:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState));
						((EqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(109);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(110);
						match(T__19);
						setState(111);
						((EqualsContext)_localctx).right = expression(7);
						}
						break;
					case 8:
						{
						_localctx = new NotEqualContext(new ExpressionContext(_parentctx, _parentState));
						((NotEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(112);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(113);
						match(T__20);
						setState(114);
						((NotEqualContext)_localctx).right = expression(6);
						}
						break;
					case 9:
						{
						_localctx = new LowerThanOrEqualContext(new ExpressionContext(_parentctx, _parentState));
						((LowerThanOrEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(115);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(116);
						match(T__21);
						setState(117);
						((LowerThanOrEqualContext)_localctx).right = expression(5);
						}
						break;
					case 10:
						{
						_localctx = new GreaterThanOrEqualContext(new ExpressionContext(_parentctx, _parentState));
						((GreaterThanOrEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(118);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(119);
						match(T__22);
						setState(120);
						((GreaterThanOrEqualContext)_localctx).right = expression(4);
						}
						break;
					case 11:
						{
						_localctx = new LogicalAndContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalAndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(121);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(122);
						match(T__23);
						setState(123);
						((LogicalAndContext)_localctx).right = expression(3);
						}
						break;
					case 12:
						{
						_localctx = new LogicalOrContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalOrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(125);
						match(T__24);
						setState(126);
						((LogicalOrContext)_localctx).right = expression(2);
						}
						break;
					case 13:
						{
						_localctx = new IncrementContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(127);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(128);
						match(T__11);
						}
						break;
					case 14:
						{
						_localctx = new DecrementContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(129);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(130);
						match(T__12);
						}
						break;
					}
					} 
				}
				setState(135);
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
			setState(136);
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
		enterRule(_localctx, 12, RULE_type);
		try {
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
				_localctx = new TypeBooleanContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				match(T__25);
				}
				break;
			case T__26:
				_localctx = new TypeFloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(T__26);
				}
				break;
			case T__27:
				_localctx = new TypeIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				match(T__27);
				}
				break;
			case T__28:
				_localctx = new TypeStringContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				match(T__28);
				}
				break;
			case T__29:
				_localctx = new TypeMoneyContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(142);
				match(T__29);
				}
				break;
			case T__30:
				_localctx = new TypeDateContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(143);
				match(T__30);
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
		case 4:
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
		case 12:
			return precpred(_ctx, 14);
		case 13:
			return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3(\u0095\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25"+
		"\n\2\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3!\n\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\3\3\3\3\3\3\3\7\3A\n\3\f\3\16\3D\13\3"+
		"\3\3\5\3G\n\3\5\3I\n\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6\\\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0086\n\6\f\6\16"+
		"\6\u0089\13\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0093\n\b\3\b\2\3\n"+
		"\t\2\4\6\b\n\f\16\2\2\u00ae\2\20\3\2\2\2\4H\3\2\2\2\6J\3\2\2\2\bL\3\2"+
		"\2\2\n[\3\2\2\2\f\u008a\3\2\2\2\16\u0092\3\2\2\2\20\21\7\3\2\2\21\22\5"+
		"\f\7\2\22\26\7\4\2\2\23\25\5\4\3\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3"+
		"\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\5\2\2\32\3\3"+
		"\2\2\2\33\34\5\16\b\2\34\35\5\f\7\2\35\36\7\6\2\2\36 \5\6\4\2\37!\5\b"+
		"\5\2 \37\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\7\7\2\2#I\3\2\2\2$%\7\b\2\2%"+
		"&\7\t\2\2&\'\5\n\6\2\'(\7\n\2\2(,\7\4\2\2)+\5\4\3\2*)\3\2\2\2+.\3\2\2"+
		"\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\5\2\2\60I\3\2\2\2\61"+
		"\62\7\b\2\2\62\63\7\t\2\2\63\64\5\n\6\2\64\65\7\n\2\2\659\7\4\2\2\668"+
		"\5\4\3\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2"+
		"\2\2<F\7\5\2\2=>\7\13\2\2>B\7\4\2\2?A\5\4\3\2@?\3\2\2\2AD\3\2\2\2B@\3"+
		"\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3\2\2\2EG\7\5\2\2F=\3\2\2\2FG\3\2\2\2GI\3"+
		"\2\2\2H\33\3\2\2\2H$\3\2\2\2H\61\3\2\2\2I\5\3\2\2\2JK\7#\2\2K\7\3\2\2"+
		"\2LM\7\f\2\2MN\5\n\6\2N\t\3\2\2\2OP\b\6\1\2P\\\7$\2\2Q\\\7#\2\2R\\\7%"+
		"\2\2S\\\7&\2\2T\\\7\'\2\2UV\7\t\2\2VW\5\n\6\2WX\7\n\2\2X\\\3\2\2\2YZ\7"+
		"\r\2\2Z\\\5\n\6\21[O\3\2\2\2[Q\3\2\2\2[R\3\2\2\2[S\3\2\2\2[T\3\2\2\2["+
		"U\3\2\2\2[Y\3\2\2\2\\\u0087\3\2\2\2]^\f\16\2\2^_\7\20\2\2_\u0086\5\n\6"+
		"\17`a\f\r\2\2ab\7\21\2\2b\u0086\5\n\6\16cd\f\f\2\2de\7\22\2\2e\u0086\5"+
		"\n\6\rfg\f\13\2\2gh\7\23\2\2h\u0086\5\n\6\fij\f\n\2\2jk\7\24\2\2k\u0086"+
		"\5\n\6\13lm\f\t\2\2mn\7\25\2\2n\u0086\5\n\6\nop\f\b\2\2pq\7\26\2\2q\u0086"+
		"\5\n\6\trs\f\7\2\2st\7\27\2\2t\u0086\5\n\6\buv\f\6\2\2vw\7\30\2\2w\u0086"+
		"\5\n\6\7xy\f\5\2\2yz\7\31\2\2z\u0086\5\n\6\6{|\f\4\2\2|}\7\32\2\2}\u0086"+
		"\5\n\6\5~\177\f\3\2\2\177\u0080\7\33\2\2\u0080\u0086\5\n\6\4\u0081\u0082"+
		"\f\20\2\2\u0082\u0086\7\16\2\2\u0083\u0084\f\17\2\2\u0084\u0086\7\17\2"+
		"\2\u0085]\3\2\2\2\u0085`\3\2\2\2\u0085c\3\2\2\2\u0085f\3\2\2\2\u0085i"+
		"\3\2\2\2\u0085l\3\2\2\2\u0085o\3\2\2\2\u0085r\3\2\2\2\u0085u\3\2\2\2\u0085"+
		"x\3\2\2\2\u0085{\3\2\2\2\u0085~\3\2\2\2\u0085\u0081\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\13\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b\7\'\2\2\u008b\r\3\2\2\2\u008c"+
		"\u0093\7\34\2\2\u008d\u0093\7\35\2\2\u008e\u0093\7\36\2\2\u008f\u0093"+
		"\7\37\2\2\u0090\u0093\7 \2\2\u0091\u0093\7!\2\2\u0092\u008c\3\2\2\2\u0092"+
		"\u008d\3\2\2\2\u0092\u008e\3\2\2\2\u0092\u008f\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0092\u0091\3\2\2\2\u0093\17\3\2\2\2\r\26 ,9BFH[\u0085\u0087\u0092";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}