// Generated from QL.g4 by ANTLR 4.5.3
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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, BOOL=23, TYPE=24, INT=25, 
		ID=26, STR=27, WS=28, COMMENT=29, LINE_COMMENT=30;
	public static final int
		RULE_form = 0, RULE_form_element = 1, RULE_conditional_block = 2, RULE_expression = 3;
	public static final String[] ruleNames = {
		"form", "form_element", "conditional_block", "expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'endform'", "'='", "'if'", "'else if'", "'else'", "'endif'", 
		"'('", "')'", "'!'", "'*'", "'/'", "'+'", "'-'", "'<'", "'<='", "'>'", 
		"'>='", "'=='", "'!='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "BOOL", 
		"TYPE", "INT", "ID", "STR", "WS", "COMMENT", "LINE_COMMENT"
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
		public TerminalNode EOF() { return getToken(QLParser.EOF, 0); }
		public List<Form_elementContext> form_element() {
			return getRuleContexts(Form_elementContext.class);
		}
		public Form_elementContext form_element(int i) {
			return getRuleContext(Form_elementContext.class,i);
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
			setState(8);
			match(T__0);
			setState(10); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(9);
				form_element();
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==STR );
			setState(14);
			match(T__1);
			setState(15);
			match(EOF);
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
		public List<Conditional_blockContext> conditional_block() {
			return getRuleContexts(Conditional_blockContext.class);
		}
		public Conditional_blockContext conditional_block(int i) {
			return getRuleContext(Conditional_blockContext.class,i);
		}
		public List<Form_elementContext> form_element() {
			return getRuleContexts(Form_elementContext.class);
		}
		public Form_elementContext form_element(int i) {
			return getRuleContext(Form_elementContext.class,i);
		}
		public If_statementContext(Form_elementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QuestionContext extends Form_elementContext {
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(QLParser.TYPE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public QuestionContext(Form_elementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
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
				setState(17);
				match(STR);
				setState(18);
				match(ID);
				setState(19);
				match(TYPE);
				setState(22);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(20);
					match(T__2);
					setState(21);
					expression(0);
					}
				}

				}
				break;
			case T__3:
				_localctx = new If_statementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				match(T__3);
				setState(25);
				conditional_block();
				setState(30);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(26);
					match(T__4);
					setState(27);
					conditional_block();
					}
					}
					setState(32);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(39);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(33);
					match(T__5);
					setState(35); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(34);
						form_element();
						}
						}
						setState(37); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__3 || _la==STR );
					}
				}

				setState(41);
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

	public static class Conditional_blockContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<Form_elementContext> form_element() {
			return getRuleContexts(Form_elementContext.class);
		}
		public Form_elementContext form_element(int i) {
			return getRuleContext(Form_elementContext.class,i);
		}
		public Conditional_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitConditional_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Conditional_blockContext conditional_block() throws RecognitionException {
		Conditional_blockContext _localctx = new Conditional_blockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_conditional_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__7);
			setState(46);
			expression(0);
			setState(47);
			match(T__8);
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				form_element();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==STR );
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIdAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode INT() { return getToken(QLParser.INT, 0); }
		public IntAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StrAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode STR() { return getToken(QLParser.STR, 0); }
		public StrAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStrAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolAtomContext extends ExpressionContext {
		public Token atom;
		public TerminalNode BOOL() { return getToken(QLParser.BOOL, 0); }
		public BoolAtomContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBoolAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitParenExpr(this);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			switch (_input.LA(1)) {
			case T__7:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(54);
				match(T__7);
				setState(55);
				expression(0);
				setState(56);
				match(T__8);
				}
				break;
			case T__9:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(58);
				match(T__9);
				setState(59);
				expression(9);
				}
				break;
			case BOOL:
				{
				_localctx = new BoolAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(60);
				((BoolAtomContext)_localctx).atom = match(BOOL);
				}
				break;
			case INT:
				{
				_localctx = new IntAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(61);
				((IntAtomContext)_localctx).atom = match(INT);
				}
				break;
			case ID:
				{
				_localctx = new IdAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(62);
				((IdAtomContext)_localctx).atom = match(ID);
				}
				break;
			case STR:
				{
				_localctx = new StrAtomContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				((StrAtomContext)_localctx).atom = match(STR);
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
					_errHandler.sync(this);
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
						if ( !(_la==T__10 || _la==T__11) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(68);
						((OpExprContext)_localctx).right = expression(9);
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
						if ( !(_la==T__12 || _la==T__13) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(71);
						((OpExprContext)_localctx).right = expression(8);
						}
						break;
					case 3:
						{
						_localctx = new OpExprContext(new ExpressionContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(72);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(73);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(74);
						((OpExprContext)_localctx).right = expression(7);
						}
						break;
					case 4:
						{
						_localctx = new OpExprContext(new ExpressionContext(_parentctx, _parentState));
						((OpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(75);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(76);
						((OpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((OpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(77);
						((OpExprContext)_localctx).right = expression(6);
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
		case 3:
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 V\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\5\3\31\n\3\3\3\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\3\3\3"+
		"\6\3&\n\3\r\3\16\3\'\5\3*\n\3\3\3\3\3\5\3.\n\3\3\4\3\4\3\4\3\4\6\4\64"+
		"\n\4\r\4\16\4\65\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5Q\n\5\f\5\16\5T\13"+
		"\5\3\5\2\3\b\6\2\4\6\b\2\6\3\2\r\16\3\2\17\20\3\2\21\26\3\2\27\30a\2\n"+
		"\3\2\2\2\4-\3\2\2\2\6/\3\2\2\2\bB\3\2\2\2\n\f\7\3\2\2\13\r\5\4\3\2\f\13"+
		"\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\20\3\2\2\2\20\21\7"+
		"\4\2\2\21\22\7\2\2\3\22\3\3\2\2\2\23\24\7\35\2\2\24\25\7\34\2\2\25\30"+
		"\7\32\2\2\26\27\7\5\2\2\27\31\5\b\5\2\30\26\3\2\2\2\30\31\3\2\2\2\31."+
		"\3\2\2\2\32\33\7\6\2\2\33 \5\6\4\2\34\35\7\7\2\2\35\37\5\6\4\2\36\34\3"+
		"\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!)\3\2\2\2\" \3\2\2\2#%\7\b\2"+
		"\2$&\5\4\3\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)#\3"+
		"\2\2\2)*\3\2\2\2*+\3\2\2\2+,\7\t\2\2,.\3\2\2\2-\23\3\2\2\2-\32\3\2\2\2"+
		".\5\3\2\2\2/\60\7\n\2\2\60\61\5\b\5\2\61\63\7\13\2\2\62\64\5\4\3\2\63"+
		"\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\7\3\2\2\2\67"+
		"8\b\5\1\289\7\n\2\29:\5\b\5\2:;\7\13\2\2;C\3\2\2\2<=\7\f\2\2=C\5\b\5\13"+
		">C\7\31\2\2?C\7\33\2\2@C\7\34\2\2AC\7\35\2\2B\67\3\2\2\2B<\3\2\2\2B>\3"+
		"\2\2\2B?\3\2\2\2B@\3\2\2\2BA\3\2\2\2CR\3\2\2\2DE\f\n\2\2EF\t\2\2\2FQ\5"+
		"\b\5\13GH\f\t\2\2HI\t\3\2\2IQ\5\b\5\nJK\f\b\2\2KL\t\4\2\2LQ\5\b\5\tMN"+
		"\f\7\2\2NO\t\5\2\2OQ\5\b\5\bPD\3\2\2\2PG\3\2\2\2PJ\3\2\2\2PM\3\2\2\2Q"+
		"T\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\t\3\2\2\2TR\3\2\2\2\f\16\30 \')-\65BPR";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}