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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, BOOL=23, STRING=24, 
		INT=25, DATE=26, DEC=27, MONEY=28, NUMBER=29, STRING_LITERAL=30, ID=31, 
		WHITESPACE=32;
	public static final int
		RULE_form = 0, RULE_formItem = 1, RULE_question = 2, RULE_computed = 3, 
		RULE_type = 4, RULE_condition = 5, RULE_expression = 6, RULE_boolExpr = 7, 
		RULE_numExpr = 8;
	public static final String[] ruleNames = {
		"form", "formItem", "question", "computed", "type", "condition", "expression", 
		"boolExpr", "numExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'='", "'('", "')'", "'if'", "'&&'", 
		"'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'true'", "'false'", 
		"'*'", "'/'", "'+'", "'-'", "'boolean'", "'string'", "'integer'", "'date'", 
		"'decimal'", "'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "BOOL", 
		"STRING", "INT", "DATE", "DEC", "MONEY", "NUMBER", "STRING_LITERAL", "ID", 
		"WHITESPACE"
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
			setState(18);
			match(T__0);
			setState(19);
			match(ID);
			setState(20);
			match(T__1);
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==STRING_LITERAL) {
				{
				{
				setState(21);
				formItem();
				}
				}
				setState(26);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(27);
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
			setState(32);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				question();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				computed();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
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
			setState(34);
			match(STRING_LITERAL);
			setState(35);
			match(ID);
			setState(36);
			match(T__3);
			setState(37);
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
			setState(39);
			match(STRING_LITERAL);
			setState(40);
			match(ID);
			setState(41);
			match(T__3);
			setState(42);
			type();
			setState(43);
			match(T__4);
			setState(44);
			match(T__5);
			setState(45);
			expression();
			setState(46);
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
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public TerminalNode DATE() { return getToken(QLParser.DATE, 0); }
		public TerminalNode DEC() { return getToken(QLParser.DEC, 0); }
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << STRING) | (1L << INT) | (1L << DATE) | (1L << DEC) | (1L << MONEY))) != 0)) ) {
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
			setState(50);
			match(T__7);
			setState(51);
			match(T__5);
			setState(52);
			boolExpr(0);
			setState(53);
			match(T__6);
			setState(54);
			match(T__1);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==STRING_LITERAL) {
				{
				{
				setState(55);
				formItem();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
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
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public NumExprContext numExpr() {
			return getRuleContext(NumExprContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expression);
		try {
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				boolExpr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				numExpr(0);
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

	public static class BoolExprContext extends ParserRuleContext {
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
	 
		public BoolExprContext() { }
		public void copyFrom(BoolExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AndorContext extends BoolExprContext {
		public Token op;
		public List<BoolExprContext> boolExpr() {
			return getRuleContexts(BoolExprContext.class);
		}
		public BoolExprContext boolExpr(int i) {
			return getRuleContext(BoolExprContext.class,i);
		}
		public AndorContext(BoolExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAndor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparisonContext extends BoolExprContext {
		public Token op;
		public List<NumExprContext> numExpr() {
			return getRuleContexts(NumExprContext.class);
		}
		public NumExprContext numExpr(int i) {
			return getRuleContext(NumExprContext.class,i);
		}
		public ComparisonContext(BoolExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolContext extends BoolExprContext {
		public BoolContext(BoolExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolIdContext extends BoolExprContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public BoolIdContext(BoolExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBoolId(this);
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_boolExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new ComparisonContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(68);
				numExpr(0);
				setState(69);
				((ComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) ) {
					((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(70);
				numExpr(0);
				}
				break;
			case 2:
				{
				_localctx = new BoolIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(72);
				match(ID);
				}
				break;
			case 3:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				_la = _input.LA(1);
				if ( !(_la==T__16 || _la==T__17) ) {
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
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndorContext(new BoolExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
					setState(76);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(77);
					((AndorContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
						((AndorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(78);
					boolExpr(5);
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public List<NumExprContext> numExpr() {
			return getRuleContexts(NumExprContext.class);
		}
		public NumExprContext numExpr(int i) {
			return getRuleContext(NumExprContext.class,i);
		}
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_numExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(85);
				match(NUMBER);
				}
				break;
			case ID:
				{
				setState(86);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(95);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new NumExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numExpr);
						setState(89);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(90);
						((NumExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__19) ) {
							((NumExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(91);
						numExpr(5);
						}
						break;
					case 2:
						{
						_localctx = new NumExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_numExpr);
						setState(92);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(93);
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
						setState(94);
						numExpr(4);
						}
						break;
					}
					} 
				}
				setState(99);
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
			return boolExpr_sempred((BoolExprContext)_localctx, predIndex);
		case 8:
			return numExpr_sempred((NumExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean numExpr_sempred(NumExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"g\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\3\3\3\3\3\3\5\3#\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\7\7;\n\7\f\7\16\7>\13\7\3\7\3\7\3\b\3\b\5\bD\n\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\tM\n\t\3\t\3\t\3\t\7\tR\n\t\f\t\16\tU\13\t\3\n\3"+
		"\n\3\n\5\nZ\n\n\3\n\3\n\3\n\3\n\3\n\3\n\7\nb\n\n\f\n\16\ne\13\n\3\n\2"+
		"\4\20\22\13\2\4\6\b\n\f\16\20\22\2\b\3\2\31\36\3\2\r\22\3\2\23\24\3\2"+
		"\13\16\3\2\25\26\3\2\27\30h\2\24\3\2\2\2\4\"\3\2\2\2\6$\3\2\2\2\b)\3\2"+
		"\2\2\n\62\3\2\2\2\f\64\3\2\2\2\16C\3\2\2\2\20L\3\2\2\2\22Y\3\2\2\2\24"+
		"\25\7\3\2\2\25\26\7!\2\2\26\32\7\4\2\2\27\31\5\4\3\2\30\27\3\2\2\2\31"+
		"\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2\2\2\35"+
		"\36\7\5\2\2\36\3\3\2\2\2\37#\5\6\4\2 #\5\b\5\2!#\5\f\7\2\"\37\3\2\2\2"+
		"\" \3\2\2\2\"!\3\2\2\2#\5\3\2\2\2$%\7 \2\2%&\7!\2\2&\'\7\6\2\2\'(\5\n"+
		"\6\2(\7\3\2\2\2)*\7 \2\2*+\7!\2\2+,\7\6\2\2,-\5\n\6\2-.\7\7\2\2./\7\b"+
		"\2\2/\60\5\16\b\2\60\61\7\t\2\2\61\t\3\2\2\2\62\63\t\2\2\2\63\13\3\2\2"+
		"\2\64\65\7\n\2\2\65\66\7\b\2\2\66\67\5\20\t\2\678\7\t\2\28<\7\4\2\29;"+
		"\5\4\3\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?"+
		"@\7\5\2\2@\r\3\2\2\2AD\5\20\t\2BD\5\22\n\2CA\3\2\2\2CB\3\2\2\2D\17\3\2"+
		"\2\2EF\b\t\1\2FG\5\22\n\2GH\t\3\2\2HI\5\22\n\2IM\3\2\2\2JM\7!\2\2KM\t"+
		"\4\2\2LE\3\2\2\2LJ\3\2\2\2LK\3\2\2\2MS\3\2\2\2NO\f\6\2\2OP\t\5\2\2PR\5"+
		"\20\t\7QN\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\21\3\2\2\2US\3\2\2\2"+
		"VW\b\n\1\2WZ\7\37\2\2XZ\7!\2\2YV\3\2\2\2YX\3\2\2\2Zc\3\2\2\2[\\\f\6\2"+
		"\2\\]\t\6\2\2]b\5\22\n\7^_\f\5\2\2_`\t\7\2\2`b\5\22\n\6a[\3\2\2\2a^\3"+
		"\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\23\3\2\2\2ec\3\2\2\2\13\32\"<CL"+
		"SYac";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
