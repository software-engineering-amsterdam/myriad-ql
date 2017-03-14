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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, BOOLEAN_LITERAL=26, INTEGER_LITERAL=27, ID_LITERAL=28, STRING_LITERAL=29, 
		WS=30, COMMENT=31, LINE_COMMENT=32;
	public static final int
		RULE_form = 0, RULE_formElement = 1, RULE_expression = 2, RULE_type = 3;
	public static final String[] ruleNames = {
		"form", "formElement", "expression", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'endform'", "'='", "'if'", "'('", "')'", "'else'", "'endif'", 
		"'!'", "'*'", "'/'", "'+'", "'-'", "'<'", "'<='", "'>'", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", "'BOOLEAN'", "'INTEGER'", "'MONEY'", "'STRING'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BOOLEAN_LITERAL", "INTEGER_LITERAL", "ID_LITERAL", "STRING_LITERAL", 
		"WS", "COMMENT", "LINE_COMMENT"
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
		public List<FormElementContext> formElement() {
			return getRuleContexts(FormElementContext.class);
		}
		public FormElementContext formElement(int i) {
			return getRuleContext(FormElementContext.class,i);
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
				formElement();
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==STRING_LITERAL );
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

	public static class FormElementContext extends ParserRuleContext {
		public FormElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formElement; }
	 
		public FormElementContext() { }
		public void copyFrom(FormElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class QuestionContext extends FormElementContext {
		public Token label;
		public Token id;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public TerminalNode ID_LITERAL() { return getToken(QLParser.ID_LITERAL, 0); }
		public QuestionContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CalculatedQuestionContext extends FormElementContext {
		public Token label;
		public Token id;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public TerminalNode ID_LITERAL() { return getToken(QLParser.ID_LITERAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CalculatedQuestionContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCalculatedQuestion(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStatementContext extends FormElementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<FormElementContext> formElement() {
			return getRuleContexts(FormElementContext.class);
		}
		public FormElementContext formElement(int i) {
			return getRuleContext(FormElementContext.class,i);
		}
		public IfStatementContext(FormElementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormElementContext formElement() throws RecognitionException {
		FormElementContext _localctx = new FormElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formElement);
		int _la;
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new CalculatedQuestionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(17);
				((CalculatedQuestionContext)_localctx).label = match(STRING_LITERAL);
				setState(18);
				((CalculatedQuestionContext)_localctx).id = match(ID_LITERAL);
				setState(19);
				type();
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
			case 2:
				_localctx = new QuestionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				((QuestionContext)_localctx).label = match(STRING_LITERAL);
				setState(25);
				((QuestionContext)_localctx).id = match(ID_LITERAL);
				setState(26);
				type();
				}
				break;
			case 3:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(27);
				match(T__3);
				setState(28);
				match(T__4);
				setState(29);
				expression(0);
				setState(30);
				match(T__5);
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(31);
					formElement();
					}
					}
					setState(34); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__3 || _la==STRING_LITERAL );
				setState(42);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(36);
					match(T__6);
					setState(38); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(37);
						formElement();
						}
						}
						setState(40); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__3 || _la==STRING_LITERAL );
					}
				}

				setState(44);
				match(T__7);
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
	public static class AddContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThenEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GreaterThenEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitGreaterThenEqual(this);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdLiteralContext extends ExpressionContext {
		public Token atom;
		public TerminalNode ID_LITERAL() { return getToken(QLParser.ID_LITERAL, 0); }
		public IdLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIdLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThenEqualContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LessThenEqualContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLessThenEqual(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterThenContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GreaterThenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitGreaterThen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends ExpressionContext {
		public Token atom;
		public TerminalNode STRING_LITERAL() { return getToken(QLParser.STRING_LITERAL, 0); }
		public StringLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringLiteral(this);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerLiteralContext extends ExpressionContext {
		public Token atom;
		public TerminalNode INTEGER_LITERAL() { return getToken(QLParser.INTEGER_LITERAL, 0); }
		public IntegerLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DivideContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DivideContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitDivide(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessThenContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LessThenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLessThen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiplyContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultiplyContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMultiply(this);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends ExpressionContext {
		public Token atom;
		public TerminalNode BOOLEAN_LITERAL() { return getToken(QLParser.BOOLEAN_LITERAL, 0); }
		public BooleanLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubstractContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SubstractContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitSubstract(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualsNotContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EqualsNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitEqualsNot(this);
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			switch (_input.LA(1)) {
			case T__4:
				{
				_localctx = new ParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(49);
				match(T__4);
				setState(50);
				expression(0);
				setState(51);
				match(T__5);
				}
				break;
			case T__8:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(53);
				match(T__8);
				setState(54);
				expression(17);
				}
				break;
			case BOOLEAN_LITERAL:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(55);
				((BooleanLiteralContext)_localctx).atom = match(BOOLEAN_LITERAL);
				}
				break;
			case INTEGER_LITERAL:
				{
				_localctx = new IntegerLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				((IntegerLiteralContext)_localctx).atom = match(INTEGER_LITERAL);
				}
				break;
			case ID_LITERAL:
				{
				_localctx = new IdLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(57);
				((IdLiteralContext)_localctx).atom = match(ID_LITERAL);
				}
				break;
			case STRING_LITERAL:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(58);
				((StringLiteralContext)_localctx).atom = match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(97);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplyContext(new ExpressionContext(_parentctx, _parentState));
						((MultiplyContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(61);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(62);
						match(T__9);
						setState(63);
						((MultiplyContext)_localctx).right = expression(17);
						}
						break;
					case 2:
						{
						_localctx = new DivideContext(new ExpressionContext(_parentctx, _parentState));
						((DivideContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(64);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(65);
						match(T__10);
						setState(66);
						((DivideContext)_localctx).right = expression(16);
						}
						break;
					case 3:
						{
						_localctx = new AddContext(new ExpressionContext(_parentctx, _parentState));
						((AddContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(67);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(68);
						match(T__11);
						setState(69);
						((AddContext)_localctx).right = expression(15);
						}
						break;
					case 4:
						{
						_localctx = new SubstractContext(new ExpressionContext(_parentctx, _parentState));
						((SubstractContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(70);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(71);
						match(T__12);
						setState(72);
						((SubstractContext)_localctx).right = expression(14);
						}
						break;
					case 5:
						{
						_localctx = new LessThenContext(new ExpressionContext(_parentctx, _parentState));
						((LessThenContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(73);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(74);
						match(T__13);
						setState(75);
						((LessThenContext)_localctx).right = expression(13);
						}
						break;
					case 6:
						{
						_localctx = new LessThenEqualContext(new ExpressionContext(_parentctx, _parentState));
						((LessThenEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(76);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(77);
						match(T__14);
						setState(78);
						((LessThenEqualContext)_localctx).right = expression(12);
						}
						break;
					case 7:
						{
						_localctx = new GreaterThenContext(new ExpressionContext(_parentctx, _parentState));
						((GreaterThenContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(79);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(80);
						match(T__15);
						setState(81);
						((GreaterThenContext)_localctx).right = expression(11);
						}
						break;
					case 8:
						{
						_localctx = new GreaterThenEqualContext(new ExpressionContext(_parentctx, _parentState));
						((GreaterThenEqualContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(82);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(83);
						match(T__16);
						setState(84);
						((GreaterThenEqualContext)_localctx).right = expression(10);
						}
						break;
					case 9:
						{
						_localctx = new EqualsContext(new ExpressionContext(_parentctx, _parentState));
						((EqualsContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(85);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(86);
						match(T__17);
						setState(87);
						((EqualsContext)_localctx).right = expression(9);
						}
						break;
					case 10:
						{
						_localctx = new EqualsNotContext(new ExpressionContext(_parentctx, _parentState));
						((EqualsNotContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(88);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(89);
						match(T__18);
						setState(90);
						((EqualsNotContext)_localctx).right = expression(8);
						}
						break;
					case 11:
						{
						_localctx = new LogicalAndContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalAndContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(91);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(92);
						match(T__19);
						setState(93);
						((LogicalAndContext)_localctx).right = expression(7);
						}
						break;
					case 12:
						{
						_localctx = new LogicalOrContext(new ExpressionContext(_parentctx, _parentState));
						((LogicalOrContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(94);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(95);
						match(T__20);
						setState(96);
						((LogicalOrContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(101);
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
	public static class BooleanTypeContext extends TypeContext {
		public BooleanTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitBooleanType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerTypeContext extends TypeContext {
		public IntegerTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIntegerType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoneyTypeContext extends TypeContext {
		public MoneyTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMoneyType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(106);
			switch (_input.LA(1)) {
			case T__21:
				_localctx = new BooleanTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(T__21);
				}
				break;
			case T__22:
				_localctx = new IntegerTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(T__22);
				}
				break;
			case T__23:
				_localctx = new MoneyTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(T__23);
				}
				break;
			case T__24:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				match(T__24);
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
		case 2:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"o\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\5\3\31\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3#\n\3\r\3\16"+
		"\3$\3\3\3\3\6\3)\n\3\r\3\16\3*\5\3-\n\3\3\3\3\3\5\3\61\n\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4>\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4d\n\4\f\4\16\4g\13\4"+
		"\3\5\3\5\3\5\3\5\5\5m\n\5\3\5\2\3\6\6\2\4\6\b\2\2\u0085\2\n\3\2\2\2\4"+
		"\60\3\2\2\2\6=\3\2\2\2\bl\3\2\2\2\n\f\7\3\2\2\13\r\5\4\3\2\f\13\3\2\2"+
		"\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\20\3\2\2\2\20\21\7\4\2\2"+
		"\21\22\7\2\2\3\22\3\3\2\2\2\23\24\7\37\2\2\24\25\7\36\2\2\25\30\5\b\5"+
		"\2\26\27\7\5\2\2\27\31\5\6\4\2\30\26\3\2\2\2\30\31\3\2\2\2\31\61\3\2\2"+
		"\2\32\33\7\37\2\2\33\34\7\36\2\2\34\61\5\b\5\2\35\36\7\6\2\2\36\37\7\7"+
		"\2\2\37 \5\6\4\2 \"\7\b\2\2!#\5\4\3\2\"!\3\2\2\2#$\3\2\2\2$\"\3\2\2\2"+
		"$%\3\2\2\2%,\3\2\2\2&(\7\t\2\2\')\5\4\3\2(\'\3\2\2\2)*\3\2\2\2*(\3\2\2"+
		"\2*+\3\2\2\2+-\3\2\2\2,&\3\2\2\2,-\3\2\2\2-.\3\2\2\2./\7\n\2\2/\61\3\2"+
		"\2\2\60\23\3\2\2\2\60\32\3\2\2\2\60\35\3\2\2\2\61\5\3\2\2\2\62\63\b\4"+
		"\1\2\63\64\7\7\2\2\64\65\5\6\4\2\65\66\7\b\2\2\66>\3\2\2\2\678\7\13\2"+
		"\28>\5\6\4\239>\7\34\2\2:>\7\35\2\2;>\7\36\2\2<>\7\37\2\2=\62\3\2\2\2"+
		"=\67\3\2\2\2=9\3\2\2\2=:\3\2\2\2=;\3\2\2\2=<\3\2\2\2>e\3\2\2\2?@\f\22"+
		"\2\2@A\7\f\2\2Ad\5\6\4\23BC\f\21\2\2CD\7\r\2\2Dd\5\6\4\22EF\f\20\2\2F"+
		"G\7\16\2\2Gd\5\6\4\21HI\f\17\2\2IJ\7\17\2\2Jd\5\6\4\20KL\f\16\2\2LM\7"+
		"\20\2\2Md\5\6\4\17NO\f\r\2\2OP\7\21\2\2Pd\5\6\4\16QR\f\f\2\2RS\7\22\2"+
		"\2Sd\5\6\4\rTU\f\13\2\2UV\7\23\2\2Vd\5\6\4\fWX\f\n\2\2XY\7\24\2\2Yd\5"+
		"\6\4\13Z[\f\t\2\2[\\\7\25\2\2\\d\5\6\4\n]^\f\b\2\2^_\7\26\2\2_d\5\6\4"+
		"\t`a\f\7\2\2ab\7\27\2\2bd\5\6\4\bc?\3\2\2\2cB\3\2\2\2cE\3\2\2\2cH\3\2"+
		"\2\2cK\3\2\2\2cN\3\2\2\2cQ\3\2\2\2cT\3\2\2\2cW\3\2\2\2cZ\3\2\2\2c]\3\2"+
		"\2\2c`\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\7\3\2\2\2ge\3\2\2\2hm\7"+
		"\30\2\2im\7\31\2\2jm\7\32\2\2km\7\33\2\2lh\3\2\2\2li\3\2\2\2lj\3\2\2\2"+
		"lk\3\2\2\2m\t\3\2\2\2\f\16\30$*,\60=cel";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}