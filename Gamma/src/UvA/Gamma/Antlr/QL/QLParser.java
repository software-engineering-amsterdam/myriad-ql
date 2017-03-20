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
		RULE_type = 4, RULE_condition = 5, RULE_elseblock = 6, RULE_expression = 7, 
		RULE_boolExpr = 8, RULE_numExpr = 9;
	public static final String[] ruleNames = {
		"form", "formItem", "question", "computed", "type", "condition", "elseblock", 
		"expression", "boolExpr", "numExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'if'", "'('", "')'", "'else'", "'='", 
		"'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'!'", "'true'", 
		"'false'", "'*'", "'/'", "'+'", "'-'", "'boolean'", "'string'", "'integer'", 
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
			setState(20);
			match(T__0);
			setState(21);
			match(ID);
			setState(22);
			match(T__1);
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==STRING_LITERAL) {
				{
				{
				setState(23);
				formItem();
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
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
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				question();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				computed();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
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
			setState(36);
			match(STRING_LITERAL);
			setState(37);
			match(ID);
			setState(38);
			match(T__3);
			setState(39);
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
			setState(41);
			match(STRING_LITERAL);
			setState(42);
			match(ID);
			setState(43);
			match(T__3);
			setState(44);
			expression();
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
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
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
			match(T__4);
			setState(55);
			match(T__5);
			setState(56);
			boolExpr(0);
			setState(57);
			match(T__6);
			setState(58);
			match(T__1);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==STRING_LITERAL) {
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
			if (_la==T__7) {
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
			match(T__7);
			setState(70);
			match(T__1);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==STRING_LITERAL) {
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
	public static class NumberExpressionContext extends ExpressionContext {
		public NumExprContext numExpr() {
			return getRuleContext(NumExprContext.class,0);
		}
		public TerminalNode DEC() { return getToken(QLParser.DEC, 0); }
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public NumberExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNumberExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyExpressionContext extends ExpressionContext {
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public NumExprContext numExpr() {
			return getRuleContext(NumExprContext.class,0);
		}
		public MoneyExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMoneyExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanExpressionContext extends ExpressionContext {
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public BooleanExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		int _la;
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				_localctx = new BooleanExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(BOOL);
				setState(80);
				match(T__8);
				setState(81);
				match(T__5);
				setState(82);
				boolExpr(0);
				setState(83);
				match(T__6);
				}
				break;
			case INT:
			case DEC:
				_localctx = new NumberExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==DEC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(86);
				match(T__8);
				setState(87);
				match(T__5);
				setState(88);
				numExpr(0);
				setState(89);
				match(T__6);
				}
				break;
			case MONEY:
				_localctx = new MoneyExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				match(MONEY);
				setState(92);
				match(T__8);
				setState(93);
				match(T__5);
				setState(94);
				numExpr(0);
				setState(95);
				match(T__6);
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

	public static class BoolExprContext extends ParserRuleContext {
		public Token op;
		public List<NumExprContext> numExpr() {
			return getRuleContexts(NumExprContext.class);
		}
		public NumExprContext numExpr(int i) {
			return getRuleContext(NumExprContext.class,i);
		}
		public List<BoolExprContext> boolExpr() {
			return getRuleContexts(BoolExprContext.class);
		}
		public BoolExprContext boolExpr(int i) {
			return getRuleContext(BoolExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolExprContext boolExpr() throws RecognitionException {
		return boolExpr(0);
	}

	private BoolExprContext boolExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BoolExprContext _localctx = new BoolExprContext(_ctx, _parentState);
		BoolExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_boolExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(100);
				numExpr(0);
				setState(101);
				((BoolExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
					((BoolExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(102);
				numExpr(0);
				}
				break;
			case 2:
				{
				setState(104);
				match(T__5);
				setState(105);
				numExpr(0);
				setState(106);
				match(T__6);
				}
				break;
			case 3:
				{
				setState(108);
				match(T__17);
				setState(109);
				boolExpr(3);
				}
				break;
			case 4:
				{
				setState(110);
				match(ID);
				}
				break;
			case 5:
				{
				setState(111);
				_la = _input.LA(1);
				if ( !(_la==T__18 || _la==T__19) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BoolExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
					setState(114);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(115);
					((BoolExprContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
						((BoolExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(116);
					boolExpr(7);
					}
					} 
				}
				setState(121);
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

	public static class NumExprContext extends ParserRuleContext {
		public Token op;
		public List<NumExprContext> numExpr() {
			return getRuleContexts(NumExprContext.class);
		}
		public NumExprContext numExpr(int i) {
			return getRuleContext(NumExprContext.class,i);
		}
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public NumExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNumExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumExprContext numExpr() throws RecognitionException {
		return numExpr(0);
	}

	private NumExprContext numExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NumExprContext _localctx = new NumExprContext(_ctx, _parentState);
		NumExprContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_numExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(123);
				match(T__5);
				setState(124);
				numExpr(0);
				setState(125);
				match(T__6);
				}
				break;
			case NUMBER:
				{
				setState(127);
				match(NUMBER);
				}
				break;
			case ID:
				{
				setState(128);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(139);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(137);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new NumExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numExpr);
						setState(131);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(132);
						((NumExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((NumExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(133);
						numExpr(6);
						}
						break;
					case 2:
						{
						_localctx = new NumExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numExpr);
						setState(134);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(135);
						((NumExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__22 || _la==T__23) ) {
							((NumExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(136);
						numExpr(5);
						}
						break;
					}
					} 
				}
				setState(141);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		case 8:
			return boolExpr_sempred((BoolExprContext)_localctx, predIndex);
		case 9:
			return numExpr_sempred((NumExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean numExpr_sempred(NumExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u0091\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\7\2\33\n\2\f\2\16\2\36\13\2\3\2\3\2\3\3\3\3\3\3\5"+
		"\3%\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6\67\n\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7?\n\7\f\7\16\7B\13\7\3\7\3\7"+
		"\5\7F\n\7\3\b\3\b\3\b\7\bK\n\b\f\b\16\bN\13\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\td\n\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\ns\n\n\3\n\3\n\3\n"+
		"\7\nx\n\n\f\n\16\n{\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0084"+
		"\n\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u008c\n\13\f\13\16\13\u008f\13"+
		"\13\3\13\2\4\22\24\f\2\4\6\b\n\f\16\20\22\24\2\b\4\2\35\35\37\37\3\2\16"+
		"\23\3\2\25\26\3\2\f\17\3\2\27\30\3\2\31\32\u009c\2\26\3\2\2\2\4$\3\2\2"+
		"\2\6&\3\2\2\2\b+\3\2\2\2\n\66\3\2\2\2\f8\3\2\2\2\16G\3\2\2\2\20c\3\2\2"+
		"\2\22r\3\2\2\2\24\u0083\3\2\2\2\26\27\7\3\2\2\27\30\7#\2\2\30\34\7\4\2"+
		"\2\31\33\5\4\3\2\32\31\3\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2"+
		"\2\35\37\3\2\2\2\36\34\3\2\2\2\37 \7\5\2\2 \3\3\2\2\2!%\5\6\4\2\"%\5\b"+
		"\5\2#%\5\f\7\2$!\3\2\2\2$\"\3\2\2\2$#\3\2\2\2%\5\3\2\2\2&\'\7\"\2\2\'"+
		"(\7#\2\2()\7\6\2\2)*\5\n\6\2*\7\3\2\2\2+,\7\"\2\2,-\7#\2\2-.\7\6\2\2."+
		"/\5\20\t\2/\t\3\2\2\2\60\67\7\33\2\2\61\67\7\34\2\2\62\67\7\35\2\2\63"+
		"\67\7\36\2\2\64\67\7\37\2\2\65\67\7 \2\2\66\60\3\2\2\2\66\61\3\2\2\2\66"+
		"\62\3\2\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\13\3\2\2\289"+
		"\7\7\2\29:\7\b\2\2:;\5\22\n\2;<\7\t\2\2<@\7\4\2\2=?\5\4\3\2>=\3\2\2\2"+
		"?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CE\7\5\2\2DF\5\16\b"+
		"\2ED\3\2\2\2EF\3\2\2\2F\r\3\2\2\2GH\7\n\2\2HL\7\4\2\2IK\5\4\3\2JI\3\2"+
		"\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\5\2\2P\17\3"+
		"\2\2\2QR\7\33\2\2RS\7\13\2\2ST\7\b\2\2TU\5\22\n\2UV\7\t\2\2Vd\3\2\2\2"+
		"WX\t\2\2\2XY\7\13\2\2YZ\7\b\2\2Z[\5\24\13\2[\\\7\t\2\2\\d\3\2\2\2]^\7"+
		" \2\2^_\7\13\2\2_`\7\b\2\2`a\5\24\13\2ab\7\t\2\2bd\3\2\2\2cQ\3\2\2\2c"+
		"W\3\2\2\2c]\3\2\2\2d\21\3\2\2\2ef\b\n\1\2fg\5\24\13\2gh\t\3\2\2hi\5\24"+
		"\13\2is\3\2\2\2jk\7\b\2\2kl\5\24\13\2lm\7\t\2\2ms\3\2\2\2no\7\24\2\2o"+
		"s\5\22\n\5ps\7#\2\2qs\t\4\2\2re\3\2\2\2rj\3\2\2\2rn\3\2\2\2rp\3\2\2\2"+
		"rq\3\2\2\2sy\3\2\2\2tu\f\b\2\2uv\t\5\2\2vx\5\22\n\twt\3\2\2\2x{\3\2\2"+
		"\2yw\3\2\2\2yz\3\2\2\2z\23\3\2\2\2{y\3\2\2\2|}\b\13\1\2}~\7\b\2\2~\177"+
		"\5\24\13\2\177\u0080\7\t\2\2\u0080\u0084\3\2\2\2\u0081\u0084\7!\2\2\u0082"+
		"\u0084\7#\2\2\u0083|\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\u008d\3\2\2\2\u0085\u0086\f\7\2\2\u0086\u0087\t\6\2\2\u0087\u008c\5\24"+
		"\13\b\u0088\u0089\f\6\2\2\u0089\u008a\t\7\2\2\u008a\u008c\5\24\13\7\u008b"+
		"\u0085\3\2\2\2\u008b\u0088\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\25\3\2\2\2\u008f\u008d\3\2\2\2\16\34$\66"+
		"@ELcry\u0083\u008b\u008d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
