// Generated from QL.g4 by ANTLR 4.4
package sc.ql.antlr;
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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__21=1, T__20=2, T__19=3, T__18=4, T__17=5, T__16=6, T__15=7, T__14=8, 
		T__13=9, T__12=10, T__11=11, T__10=12, T__9=13, T__8=14, T__7=15, T__6=16, 
		T__5=17, T__4=18, T__3=19, T__2=20, T__1=21, T__0=22, BOOL=23, TYPE=24, 
		INT=25, ID=26, STR=27, WS=28, COMMENT=29, LINE_COMMENT=30;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'endif'", "'!='", "'||'", "'>='", "'&&'", "'=='", 
		"'<'", "'='", "'>'", "'if'", "'<='", "'!'", "'else if'", "'else'", "'endform'", 
		"'('", "')'", "'*'", "'+'", "'form'", "'-'", "BOOL", "TYPE", "INT", "ID", 
		"STR", "WS", "COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_form = 0, RULE_form_element = 1, RULE_conditional_block = 2, RULE_expression = 3;
	public static final String[] ruleNames = {
		"form", "form_element", "conditional_block", "expression"
	};

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
		public Form_elementContext form_element(int i) {
			return getRuleContext(Form_elementContext.class,i);
		}
		public List<Form_elementContext> form_element() {
			return getRuleContexts(Form_elementContext.class);
		}
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitForm(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_form);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8); match(T__1);
			setState(10); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(9); form_element();
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 || _la==STR );
			setState(14); match(T__6);
			setState(15); match(EOF);
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

	public static class Form_elementContext extends ParserRuleContext {
		public Form_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form_element; }
	 
		public Form_elementContext() { }
		public void copyFrom(Form_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class If_statementContext extends Form_elementContext {
		public Form_elementContext form_element(int i) {
			return getRuleContext(Form_elementContext.class,i);
		}
		public Conditional_blockContext conditional_block(int i) {
			return getRuleContext(Conditional_blockContext.class,i);
		}
		public List<Conditional_blockContext> conditional_block() {
			return getRuleContexts(Conditional_blockContext.class);
		}
		public List<Form_elementContext> form_element() {
			return getRuleContexts(Form_elementContext.class);
		}
		public If_statementContext(Form_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIf_statement(this);
		}
	}
	public static class QuestionContext extends Form_elementContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TerminalNode TYPE() { return getToken(QLParser.TYPE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuestionContext(Form_elementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitQuestion(this);
		}
	}

	public final Form_elementContext form_element() throws RecognitionException {
		Form_elementContext _localctx = new Form_elementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_form_element);
		int _la;
		try {
			setState(43);
			switch (_input.LA(1)) {
			case STR:
				_localctx = new QuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(17); match(STR);
				setState(18); match(ID);
				setState(19); match(TYPE);
				setState(22);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(20); match(T__13);
					setState(21); expression(0);
					}
				}

				}
				break;
			case T__11:
				_localctx = new If_statementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(24); match(T__11);
				setState(25); conditional_block();
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(26); match(T__8);
					setState(27); conditional_block();
					}
					}
					setState(32);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(39);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(33); match(T__7);
					setState(35); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(34); form_element();
						}
						}
						setState(37); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__11 || _la==STR );
					}
				}

				setState(41); match(T__20);
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

	public static class Conditional_blockContext extends ParserRuleContext {
		public Form_elementContext form_element(int i) {
			return getRuleContext(Form_elementContext.class,i);
		}
		public List<Form_elementContext> form_element() {
			return getRuleContexts(Form_elementContext.class);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Conditional_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterConditional_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitConditional_block(this);
		}
	}

	public final Conditional_blockContext conditional_block() throws RecognitionException {
		Conditional_blockContext _localctx = new Conditional_blockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_conditional_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); match(T__5);
			setState(46); expression(0);
			setState(47); match(T__4);
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48); form_element();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 || _la==STR );
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
	public static class IdAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public IdAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIdAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIdAtom(this);
		}
	}
	public static class OpExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public OpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitOpExpr(this);
		}
	}
	public static class IntAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public IntAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterIntAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitIntAtom(this);
		}
	}
	public static class StrAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public StrAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStrAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStrAtom(this);
		}
	}
	public static class BoolAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public BoolAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBoolAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBoolAtom(this);
		}
	}
	public static class BoolExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public BoolExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitBoolExpr(this);
		}
	}
	public static class RelExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public RelExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterRelExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitRelExpr(this);
		}
	}
	public static class ParenExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitParenExpr(this);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			switch (_input.LA(1)) {
			case T__9:
				{
				_localctx = new BoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(54); match(T__9);
				setState(55); expression(9);
				}
				break;
			case T__5:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56); match(T__5);
				setState(57); expression(0);
				setState(58); match(T__4);
				}
				break;
			case BOOL:
				{
				_localctx = new BoolAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60); ((BoolAtomContext)_localctx).atom = match(BOOL);
				}
				break;
			case INT:
				{
				_localctx = new IntAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61); ((IntAtomContext)_localctx).atom = match(INT);
				}
				break;
			case ID:
				{
				_localctx = new IdAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(62); ((IdAtomContext)_localctx).atom = match(ID);
				}
				break;
			case STR:
				{
				_localctx = new StrAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63); ((StrAtomContext)_localctx).atom = match(STR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(78);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new OpExprContext(new ExpressionContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(66);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(67);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__3) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(68); ((OpExprContext)_localctx).right = expression(9);
						}
						break;
					case 2:
						{
						_localctx = new OpExprContext(new ExpressionContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(69);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(70);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__0) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(71); ((OpExprContext)_localctx).right = expression(8);
						}
						break;
					case 3:
						{
						_localctx = new RelExprContext(new ExpressionContext(_parentctx, _parentState));
						((RelExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(72);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(73);
						((RelExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__17) | (1L << T__15) | (1L << T__14) | (1L << T__12) | (1L << T__10))) != 0)) ) {
							((RelExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(74); ((RelExprContext)_localctx).right = expression(7);
						}
						break;
					case 4:
						{
						_localctx = new BoolExprContext(new ExpressionContext(_parentctx, _parentState));
						((BoolExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(75);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(76);
						((BoolExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__16) ) {
							((BoolExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						consume();
						setState(77); ((BoolExprContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(82);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 8);
		case 1: return precpred(_ctx, 7);
		case 2: return precpred(_ctx, 6);
		case 3: return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 V\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\5\3\31\n\3\3\3\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\3\3\3"+
		"\6\3&\n\3\r\3\16\3\'\5\3*\n\3\3\3\3\3\5\3.\n\3\3\4\3\4\3\4\3\4\6\4\64"+
		"\n\4\r\4\16\4\65\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5Q\n\5\f\5\16\5T\13"+
		"\5\3\5\2\3\b\6\2\4\6\b\2\6\4\2\3\3\25\25\4\2\26\26\30\30\7\2\5\5\7\7\t"+
		"\n\f\f\16\16\4\2\6\6\b\ba\2\n\3\2\2\2\4-\3\2\2\2\6/\3\2\2\2\bB\3\2\2\2"+
		"\n\f\7\27\2\2\13\r\5\4\3\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17"+
		"\3\2\2\2\17\20\3\2\2\2\20\21\7\22\2\2\21\22\7\2\2\3\22\3\3\2\2\2\23\24"+
		"\7\35\2\2\24\25\7\34\2\2\25\30\7\32\2\2\26\27\7\13\2\2\27\31\5\b\5\2\30"+
		"\26\3\2\2\2\30\31\3\2\2\2\31.\3\2\2\2\32\33\7\r\2\2\33 \5\6\4\2\34\35"+
		"\7\20\2\2\35\37\5\6\4\2\36\34\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2"+
		"\2!)\3\2\2\2\" \3\2\2\2#%\7\21\2\2$&\5\4\3\2%$\3\2\2\2&\'\3\2\2\2\'%\3"+
		"\2\2\2\'(\3\2\2\2(*\3\2\2\2)#\3\2\2\2)*\3\2\2\2*+\3\2\2\2+,\7\4\2\2,."+
		"\3\2\2\2-\23\3\2\2\2-\32\3\2\2\2.\5\3\2\2\2/\60\7\23\2\2\60\61\5\b\5\2"+
		"\61\63\7\24\2\2\62\64\5\4\3\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2"+
		"\2\65\66\3\2\2\2\66\7\3\2\2\2\678\b\5\1\289\7\17\2\29C\5\b\5\13:;\7\23"+
		"\2\2;<\5\b\5\2<=\7\24\2\2=C\3\2\2\2>C\7\31\2\2?C\7\33\2\2@C\7\34\2\2A"+
		"C\7\35\2\2B\67\3\2\2\2B:\3\2\2\2B>\3\2\2\2B?\3\2\2\2B@\3\2\2\2BA\3\2\2"+
		"\2CR\3\2\2\2DE\f\n\2\2EF\t\2\2\2FQ\5\b\5\13GH\f\t\2\2HI\t\3\2\2IQ\5\b"+
		"\5\nJK\f\b\2\2KL\t\4\2\2LQ\5\b\5\tMN\f\7\2\2NO\t\5\2\2OQ\5\b\5\bPD\3\2"+
		"\2\2PG\3\2\2\2PJ\3\2\2\2PM\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\t\3"+
		"\2\2\2TR\3\2\2\2\f\16\30 \')-\65BPR";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}