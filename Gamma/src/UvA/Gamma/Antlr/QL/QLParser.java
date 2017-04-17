package UvA.Gamma.Antlr.QL;// Generated from src/UvA/Gamma/Antlr/QL//QL.g4 by ANTLR 4.6
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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		BOOL=25, STRING=26, INT=27, DATE=28, DEC=29, MONEY=30, NUMBER=31, STRING_LITERAL=32, 
		ID=33, WHITESPACE=34, ONE_LINE_COMMENT=35, MULTI_LINE_COMMENT=36;
	public static final int
		RULE_form = 0, RULE_formItem = 1, RULE_question = 2, RULE_computed = 3, 
		RULE_type = 4, RULE_condition = 5, RULE_elseblock = 6, RULE_expression = 7;
	public static final String[] ruleNames = {
		"form", "formItem", "question", "computed", "type", "condition", "elseblock", 
		"expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'='", "'('", "')'", "'if'", "'else'", 
		"'!'", "'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'*'", 
		"'/'", "'+'", "'-'", "'true'", "'false'", "'boolean'", "'string'", "'integer'", 
		"'date'", "'decimal'", "'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "BOOL", "STRING", "INT", "DATE", "DEC", "MONEY", "NUMBER", "STRING_LITERAL", 
		"ID", "WHITESPACE", "ONE_LINE_COMMENT", "MULTI_LINE_COMMENT"
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
	public static class FormContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public List<FormItemContext> formItem() {
			return getRuleContexts(FormItemContext.class);
		}
		public FormItemContext formItem(int i) {
			return getRuleContext(FormItemContext.class,i);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitForm(this);
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
			match(T__0);
			setState(17);
			match(ID);
			setState(18);
			match(T__1);
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==STRING_LITERAL) {
				{
				{
				setState(19);
				formItem();
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

	public static class FormItemContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public ComputedContext computed() {
			return getRuleContext(ComputedContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public FormItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formItem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitFormItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormItemContext formItem() throws RecognitionException {
		FormItemContext _localctx = new FormItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formItem);
		try {
			setState(30);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				question();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				computed();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				condition();
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(STRING_LITERAL);
			setState(33);
			match(ID);
			setState(34);
			match(T__3);
			setState(35);
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

	public static class ComputedContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ComputedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_computed; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitComputed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComputedContext computed() throws RecognitionException {
		ComputedContext _localctx = new ComputedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_computed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(STRING_LITERAL);
			setState(38);
			match(ID);
			setState(39);
			match(T__3);
			setState(40);
			type();
			setState(41);
			match(T__4);
			setState(42);
			match(T__5);
			setState(43);
			expression(0);
			setState(44);
			match(T__6);
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
	public static class DecimalTypeContext extends TypeContext {
		public TerminalNode DEC() { return getToken(QLParser.DEC, 0); }
		public DecimalTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDecimalType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanTypeContext extends TypeContext {
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateTypeContext extends TypeContext {
		public TerminalNode DATE() { return getToken(QLParser.DATE, 0); }
		public DateTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDateType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyTypeContext extends TypeContext {
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public MoneyTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMoneyType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(BOOL);
				}
				break;
			case STRING:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(STRING);
				}
				break;
			case INT:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				match(INT);
				}
				break;
			case DATE:
				_localctx = new DateTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				match(DATE);
				}
				break;
			case DEC:
				_localctx = new DecimalTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(50);
				match(DEC);
				}
				break;
			case MONEY:
				_localctx = new MoneyTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(51);
				match(MONEY);
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

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<FormItemContext> formItem() {
			return getRuleContexts(FormItemContext.class);
		}
		public FormItemContext formItem(int i) {
			return getRuleContext(FormItemContext.class,i);
		}
		public ElseblockContext elseblock() {
			return getRuleContext(ElseblockContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__7);
			setState(55);
			match(T__5);
			setState(56);
			expression(0);
			setState(57);
			match(T__6);
			setState(58);
			match(T__1);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==STRING_LITERAL) {
				{
				{
				setState(59);
				formItem();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(T__2);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(66);
				elseblock();
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

	public static class ElseblockContext extends ParserRuleContext {
		public List<FormItemContext> formItem() {
			return getRuleContexts(FormItemContext.class);
		}
		public FormItemContext formItem(int i) {
			return getRuleContext(FormItemContext.class,i);
		}
		public ElseblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseblock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitElseblock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseblockContext elseblock() throws RecognitionException {
		ElseblockContext _localctx = new ElseblockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_elseblock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__8);
			setState(70);
			match(T__1);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==STRING_LITERAL) {
				{
				{
				setState(71);
				formItem();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
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
	public static class LogicalBooleanExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalBooleanExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLogicalBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegatedBooleanExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegatedBooleanExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNegatedBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAddExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NestedExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNestedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierExpressionContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public IdentifierExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultiExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMultiExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanValueExpressionContext extends ExpressionContext {
		public BooleanValueExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanValueExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberValueExpressionContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public NumberValueExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNumberValueExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalIntegerExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LogicalIntegerExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLogicalIntegerExpression(this);
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				_localctx = new NegatedBooleanExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(80);
				match(T__9);
				setState(81);
				expression(9);
				}
				break;
			case T__5:
				{
				_localctx = new NestedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(T__5);
				setState(83);
				expression(0);
				setState(84);
				match(T__6);
				}
				break;
			case ID:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				match(ID);
				}
				break;
			case T__22:
			case T__23:
				{
				_localctx = new BooleanValueExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				_la = _input.LA(1);
				if ( !(_la==T__22 || _la==T__23) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberValueExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(103);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new LogicalBooleanExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(91);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(92);
						((LogicalBooleanExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
							((LogicalBooleanExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(93);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new LogicalIntegerExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(95);
						((LogicalIntegerExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17))) != 0)) ) {
							((LogicalIntegerExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(96);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new MultiExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(97);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(98);
						((MultiExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__19) ) {
							((MultiExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(99);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new AddExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(100);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(101);
						((AddExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(102);
						expression(6);
						}
						break;
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&o\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\7\2\27"+
		"\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\3\3\5\3!\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6\67\n"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7?\n\7\f\7\16\7B\13\7\3\7\3\7\5\7F\n\7\3"+
		"\b\3\b\3\b\7\bK\n\b\f\b\16\bN\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\\\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\7\tj\n\t\f\t\16\tm\13\t\3\t\2\3\20\n\2\4\6\b\n\f\16\20\2\7\3\2\31\32"+
		"\3\2\r\20\3\2\17\24\3\2\25\26\3\2\27\30y\2\22\3\2\2\2\4 \3\2\2\2\6\"\3"+
		"\2\2\2\b\'\3\2\2\2\n\66\3\2\2\2\f8\3\2\2\2\16G\3\2\2\2\20[\3\2\2\2\22"+
		"\23\7\3\2\2\23\24\7#\2\2\24\30\7\4\2\2\25\27\5\4\3\2\26\25\3\2\2\2\27"+
		"\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\30\3\2\2\2\33"+
		"\34\7\5\2\2\34\3\3\2\2\2\35!\5\6\4\2\36!\5\b\5\2\37!\5\f\7\2 \35\3\2\2"+
		"\2 \36\3\2\2\2 \37\3\2\2\2!\5\3\2\2\2\"#\7\"\2\2#$\7#\2\2$%\7\6\2\2%&"+
		"\5\n\6\2&\7\3\2\2\2\'(\7\"\2\2()\7#\2\2)*\7\6\2\2*+\5\n\6\2+,\7\7\2\2"+
		",-\7\b\2\2-.\5\20\t\2./\7\t\2\2/\t\3\2\2\2\60\67\7\33\2\2\61\67\7\34\2"+
		"\2\62\67\7\35\2\2\63\67\7\36\2\2\64\67\7\37\2\2\65\67\7 \2\2\66\60\3\2"+
		"\2\2\66\61\3\2\2\2\66\62\3\2\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2"+
		"\2\2\67\13\3\2\2\289\7\n\2\29:\7\b\2\2:;\5\20\t\2;<\7\t\2\2<@\7\4\2\2"+
		"=?\5\4\3\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2"+
		"CE\7\5\2\2DF\5\16\b\2ED\3\2\2\2EF\3\2\2\2F\r\3\2\2\2GH\7\13\2\2HL\7\4"+
		"\2\2IK\5\4\3\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2"+
		"\2\2OP\7\5\2\2P\17\3\2\2\2QR\b\t\1\2RS\7\f\2\2S\\\5\20\t\13TU\7\b\2\2"+
		"UV\5\20\t\2VW\7\t\2\2W\\\3\2\2\2X\\\7#\2\2Y\\\t\2\2\2Z\\\7!\2\2[Q\3\2"+
		"\2\2[T\3\2\2\2[X\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\k\3\2\2\2]^\f\n\2\2^_\t"+
		"\3\2\2_j\5\20\t\13`a\f\t\2\2ab\t\4\2\2bj\5\20\t\ncd\f\b\2\2de\t\5\2\2"+
		"ej\5\20\t\tfg\f\7\2\2gh\t\6\2\2hj\5\20\t\bi]\3\2\2\2i`\3\2\2\2ic\3\2\2"+
		"\2if\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\21\3\2\2\2mk\3\2\2\2\13\30"+
		" \66@EL[ik";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
