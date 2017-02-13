package UvA.Gamma.Antlr.QL;// Generated from Gamma/src/UvA/Gamma/Antlr/QL//QL.g4 by ANTLR 4.6
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
		BOOL=18, STRING=19, INT=20, DATE=21, DEC=22, MONEY=23, ADD=24, SUB=25, 
		NUMBER=26, QUESTION=27, ID=28, BRACKET_OPEN=29, BRACKET_CLOSE=30, WHITESPACE=31;
	public static final int
		RULE_form = 0, RULE_formItem = 1, RULE_input = 2, RULE_type = 3, RULE_condition = 4, 
		RULE_boolExpr = 5, RULE_intExpr = 6;
	public static final String[] ruleNames = {
		"form", "formItem", "input", "type", "condition", "boolExpr", "intExpr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "'('", "')'", "'if'", "'&&'", "'||'", "'<'", "'>'", 
		"'<='", "'>='", "'!='", "'=='", "'true'", "'false'", "'*'", "'/'", "'boolean'", 
		"'string'", "'integer'", "'date'", "'decimal'", "'money'", "'+'", "'-'", 
		null, null, null, "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, "BOOL", "STRING", "INT", "DATE", "DEC", 
		"MONEY", "ADD", "SUB", "NUMBER", "QUESTION", "ID", "BRACKET_OPEN", "BRACKET_CLOSE", 
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
		public TerminalNode BRACKET_OPEN() { return getToken(QLParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(QLParser.BRACKET_CLOSE, 0); }
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
			setState(14);
			match(T__0);
			setState(15);
			match(ID);
			setState(16);
			match(BRACKET_OPEN);
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==ID) {
				{
				{
				setState(17);
				formItem();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(BRACKET_CLOSE);
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
		public FormItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formItem; }
	 
		public FormItemContext() { }
		public void copyFrom(FormItemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InContext extends FormItemContext {
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public InContext(FormItemContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CondContext extends FormItemContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CondContext(FormItemContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormItemContext formItem() throws RecognitionException {
		FormItemContext _localctx = new FormItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formItem);
		try {
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new InContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				input();
				}
				break;
			case T__4:
				_localctx = new CondContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				condition();
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

	public static class InputContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode QUESTION() { return getToken(QLParser.QUESTION, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_input);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(ID);
			setState(30);
			match(T__1);
			setState(31);
			match(QUESTION);
			setState(32);
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
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public TerminalNode DATE() { return getToken(QLParser.DATE, 0); }
		public TerminalNode DEC() { return getToken(QLParser.DEC, 0); }
		public TerminalNode MONEY() { return getToken(QLParser.MONEY, 0); }
		public IntExprContext intExpr() {
			return getRuleContext(IntExprContext.class,0);
		}
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
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				match(BOOL);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				match(STRING);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				match(INT);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(37);
				match(DATE);
				}
				break;
			case DEC:
				enterOuterAlt(_localctx, 5);
				{
				setState(38);
				match(DEC);
				}
				break;
			case MONEY:
				enterOuterAlt(_localctx, 6);
				{
				setState(39);
				match(MONEY);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 7);
				{
				setState(40);
				match(T__2);
				setState(41);
				intExpr(0);
				setState(42);
				match(T__3);
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
		public TerminalNode BRACKET_OPEN() { return getToken(QLParser.BRACKET_OPEN, 0); }
		public FormItemContext formItem() {
			return getRuleContext(FormItemContext.class,0);
		}
		public TerminalNode BRACKET_CLOSE() { return getToken(QLParser.BRACKET_CLOSE, 0); }
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
		enterRule(_localctx, 8, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__4);
			setState(47);
			match(T__2);
			setState(48);
			boolExpr(0);
			setState(49);
			match(T__3);
			setState(50);
			match(BRACKET_OPEN);
			setState(51);
			formItem();
			setState(52);
			match(BRACKET_CLOSE);
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
		public List<IntExprContext> intExpr() {
			return getRuleContexts(IntExprContext.class);
		}
		public IntExprContext intExpr(int i) {
			return getRuleContext(IntExprContext.class,i);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_boolExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				_localctx = new ComparisonContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(55);
				intExpr(0);
				setState(56);
				((ComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
					((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(57);
				intExpr(0);
				}
				break;
			case 2:
				{
				_localctx = new BoolIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				match(ID);
				}
				break;
			case 3:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
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
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndorContext(new BoolExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
					setState(63);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(64);
					((AndorContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__5 || _la==T__6) ) {
						((AndorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(65);
					boolExpr(5);
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class IntExprContext extends ParserRuleContext {
		public IntExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intExpr; }
	 
		public IntExprContext() { }
		public void copyFrom(IntExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DivContext extends IntExprContext {
		public Token op;
		public List<IntExprContext> intExpr() {
			return getRuleContexts(IntExprContext.class);
		}
		public IntExprContext intExpr(int i) {
			return getRuleContext(IntExprContext.class,i);
		}
		public DivContext(IntExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddContext extends IntExprContext {
		public Token op;
		public List<IntExprContext> intExpr() {
			return getRuleContexts(IntExprContext.class);
		}
		public IntExprContext intExpr(int i) {
			return getRuleContext(IntExprContext.class,i);
		}
		public AddContext(IntExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntIdContext extends IntExprContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public IntIdContext(IntExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends IntExprContext {
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public IntContext(IntExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntExprContext intExpr() throws RecognitionException {
		return intExpr(0);
	}

	private IntExprContext intExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IntExprContext _localctx = new IntExprContext(_ctx, _parentState);
		IntExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_intExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(72);
				match(NUMBER);
				}
				break;
			case ID:
				{
				_localctx = new IntIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(82);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new DivContext(new IntExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_intExpr);
						setState(76);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(77);
						((DivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__15 || _la==T__16) ) {
							((DivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(78);
						intExpr(5);
						}
						break;
					case 2:
						{
						_localctx = new AddContext(new IntExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_intExpr);
						setState(79);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(80);
						((AddContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(81);
						intExpr(4);
						}
						break;
					}
					} 
				}
				setState(86);
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
		case 5:
			return boolExpr_sempred((BoolExprContext)_localctx, predIndex);
		case 6:
			return intExpr_sempred((IntExprContext)_localctx, predIndex);
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
	private boolean intExpr_sempred(IntExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!Z\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\2\7\2\25\n\2\f"+
		"\2\16\2\30\13\2\3\2\3\2\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5/\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7@\n\7\3\7\3\7\3\7\7\7E\n\7\f\7\16"+
		"\7H\13\7\3\b\3\b\3\b\5\bM\n\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bU\n\b\f\b\16"+
		"\bX\13\b\3\b\2\4\f\16\t\2\4\6\b\n\f\16\2\7\3\2\n\17\3\2\20\21\3\2\b\t"+
		"\3\2\22\23\3\2\32\33`\2\20\3\2\2\2\4\35\3\2\2\2\6\37\3\2\2\2\b.\3\2\2"+
		"\2\n\60\3\2\2\2\f?\3\2\2\2\16L\3\2\2\2\20\21\7\3\2\2\21\22\7\36\2\2\22"+
		"\26\7\37\2\2\23\25\5\4\3\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26"+
		"\27\3\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7 \2\2\32\3\3\2\2\2\33\36"+
		"\5\6\4\2\34\36\5\n\6\2\35\33\3\2\2\2\35\34\3\2\2\2\36\5\3\2\2\2\37 \7"+
		"\36\2\2 !\7\4\2\2!\"\7\35\2\2\"#\5\b\5\2#\7\3\2\2\2$/\7\24\2\2%/\7\25"+
		"\2\2&/\7\26\2\2\'/\7\27\2\2(/\7\30\2\2)/\7\31\2\2*+\7\5\2\2+,\5\16\b\2"+
		",-\7\6\2\2-/\3\2\2\2.$\3\2\2\2.%\3\2\2\2.&\3\2\2\2.\'\3\2\2\2.(\3\2\2"+
		"\2.)\3\2\2\2.*\3\2\2\2/\t\3\2\2\2\60\61\7\7\2\2\61\62\7\5\2\2\62\63\5"+
		"\f\7\2\63\64\7\6\2\2\64\65\7\37\2\2\65\66\5\4\3\2\66\67\7 \2\2\67\13\3"+
		"\2\2\289\b\7\1\29:\5\16\b\2:;\t\2\2\2;<\5\16\b\2<@\3\2\2\2=@\7\36\2\2"+
		">@\t\3\2\2?8\3\2\2\2?=\3\2\2\2?>\3\2\2\2@F\3\2\2\2AB\f\6\2\2BC\t\4\2\2"+
		"CE\5\f\7\7DA\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\r\3\2\2\2HF\3\2\2"+
		"\2IJ\b\b\1\2JM\7\34\2\2KM\7\36\2\2LI\3\2\2\2LK\3\2\2\2MV\3\2\2\2NO\f\6"+
		"\2\2OP\t\5\2\2PU\5\16\b\7QR\f\5\2\2RS\t\6\2\2SU\5\16\b\6TN\3\2\2\2TQ\3"+
		"\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\17\3\2\2\2XV\3\2\2\2\n\26\35.?F"+
		"LTV";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
