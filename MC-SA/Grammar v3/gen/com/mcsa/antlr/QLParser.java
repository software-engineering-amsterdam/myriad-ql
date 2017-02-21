// Generated from /Users/matt/Repos/myriad-ql/MC-SA/Grammar v3/src/com/mcsa/grammars/QL.g4 by ANTLR 4.6
package com.mcsa.antlr;
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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, ID=23, STRING=24, NUMBER=25, 
		WHITESPACE=26, COMMENT=27, OPEN_BRACKET=28, CLOSE_BRACKET=29, OPEN_PARENTH=30, 
		CLOSE_PARENTH=31;
	public static final int
		RULE_formDeclaration = 0, RULE_statement = 1, RULE_ifStatement = 2, RULE_question = 3, 
		RULE_ifCase = 4, RULE_ifCaseArgs = 5, RULE_questionParameters = 6, RULE_type = 7, 
		RULE_mathaction = 8;
	public static final String[] ruleNames = {
		"formDeclaration", "statement", "ifStatement", "question", "ifCase", "ifCaseArgs", 
		"questionParameters", "type", "mathaction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", "';'", "'>'", "'<'", "'<='", "'>='", "'=='", "'AND'", 
		"'OR'", "':'", "'boolean'", "'integer'", "'double'", "'float'", "'string'", 
		"'money'", "'='", "'*'", "'/'", "'+'", "'-'", null, null, null, null, 
		null, "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "ID", 
		"STRING", "NUMBER", "WHITESPACE", "COMMENT", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_PARENTH", "CLOSE_PARENTH"
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
	public static class FormDeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(QLParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLParser.CLOSE_BRACKET, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FormDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitFormDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormDeclarationContext formDeclaration() throws RecognitionException {
		FormDeclarationContext _localctx = new FormDeclarationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(T__0);
			setState(19);
			match(ID);
			setState(20);
			match(OPEN_BRACKET);
			setState(22); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(21);
				statement();
				}
				}
				setState(24); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==STRING );
			setState(26);
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
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				question();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				ifStatement();
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

	public static class IfStatementContext extends ParserRuleContext {
		public IfCaseContext ifCase() {
			return getRuleContext(IfCaseContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(QLParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(QLParser.CLOSE_BRACKET, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__1);
			setState(33);
			ifCase();
			setState(34);
			match(OPEN_BRACKET);
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				statement();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==STRING );
			setState(40);
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public QuestionParametersContext questionParameters() {
			return getRuleContext(QuestionParametersContext.class,0);
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
		enterRule(_localctx, 6, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(STRING);
			setState(43);
			questionParameters();
			setState(44);
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

	public static class IfCaseContext extends ParserRuleContext {
		public List<IfCaseArgsContext> ifCaseArgs() {
			return getRuleContexts(IfCaseArgsContext.class);
		}
		public IfCaseArgsContext ifCaseArgs(int i) {
			return getRuleContext(IfCaseArgsContext.class,i);
		}
		public TerminalNode OPEN_PARENTH() { return getToken(QLParser.OPEN_PARENTH, 0); }
		public TerminalNode CLOSE_PARENTH() { return getToken(QLParser.CLOSE_PARENTH, 0); }
		public List<IfCaseContext> ifCase() {
			return getRuleContexts(IfCaseContext.class);
		}
		public IfCaseContext ifCase(int i) {
			return getRuleContext(IfCaseContext.class,i);
		}
		public IfCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCase; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfCaseContext ifCase() throws RecognitionException {
		IfCaseContext _localctx = new IfCaseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifCase);
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				ifCaseArgs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(OPEN_PARENTH);
				setState(48);
				ifCaseArgs();
				setState(49);
				match(T__3);
				setState(50);
				ifCaseArgs();
				setState(51);
				match(CLOSE_PARENTH);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				match(OPEN_PARENTH);
				setState(54);
				ifCaseArgs();
				setState(55);
				match(T__4);
				setState(56);
				ifCaseArgs();
				setState(57);
				match(CLOSE_PARENTH);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				match(OPEN_PARENTH);
				setState(60);
				ifCaseArgs();
				setState(61);
				match(T__5);
				setState(62);
				ifCaseArgs();
				setState(63);
				match(CLOSE_PARENTH);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(65);
				match(OPEN_PARENTH);
				setState(66);
				ifCaseArgs();
				setState(67);
				match(T__6);
				setState(68);
				ifCaseArgs();
				setState(69);
				match(CLOSE_PARENTH);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				match(OPEN_PARENTH);
				setState(72);
				ifCaseArgs();
				setState(73);
				match(T__7);
				setState(74);
				ifCaseArgs();
				setState(75);
				match(CLOSE_PARENTH);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(77);
				match(OPEN_PARENTH);
				setState(78);
				ifCase();
				setState(79);
				match(T__8);
				setState(80);
				ifCase();
				setState(81);
				match(CLOSE_PARENTH);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(83);
				match(OPEN_PARENTH);
				setState(84);
				ifCase();
				setState(85);
				match(T__9);
				setState(86);
				ifCase();
				setState(87);
				match(CLOSE_PARENTH);
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

	public static class IfCaseArgsContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public IfCaseArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCaseArgs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitIfCaseArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfCaseArgsContext ifCaseArgs() throws RecognitionException {
		IfCaseArgsContext _localctx = new IfCaseArgsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifCaseArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
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

	public static class QuestionParametersContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public QuestionParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionParameters; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitQuestionParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionParametersContext questionParameters() throws RecognitionException {
		QuestionParametersContext _localctx = new QuestionParametersContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_questionParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(ID);
			setState(94);
			match(T__10);
			setState(95);
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
		public List<MathactionContext> mathaction() {
			return getRuleContexts(MathactionContext.class);
		}
		public MathactionContext mathaction(int i) {
			return getRuleContext(MathactionContext.class,i);
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
		enterRule(_localctx, 14, RULE_type);
		int _la;
		try {
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				match(T__13);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				match(T__14);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				match(T__15);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 6);
				{
				setState(102);
				match(T__16);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__17) {
					{
					{
					setState(103);
					match(T__17);
					setState(104);
					mathaction(0);
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class MathactionContext extends ParserRuleContext {
		public Token op;
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public List<MathactionContext> mathaction() {
			return getRuleContexts(MathactionContext.class);
		}
		public MathactionContext mathaction(int i) {
			return getRuleContext(MathactionContext.class,i);
		}
		public MathactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathaction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitMathaction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathactionContext mathaction() throws RecognitionException {
		return mathaction(0);
	}

	private MathactionContext mathaction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MathactionContext _localctx = new MathactionContext(_ctx, _parentState);
		MathactionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_mathaction, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(113);
				match(NUMBER);
				}
				break;
			case ID:
				{
				setState(114);
				match(ID);
				}
				break;
			case OPEN_PARENTH:
				{
				setState(115);
				match(OPEN_PARENTH);
				setState(116);
				mathaction(0);
				setState(117);
				match(CLOSE_PARENTH);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(127);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(121);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(122);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__19) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(123);
						mathaction(6);
						}
						break;
					case 2:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(124);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(125);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						mathaction(5);
						}
						break;
					}
					} 
				}
				setState(131);
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
		case 8:
			return mathaction_sempred((MathactionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean mathaction_sempred(MathactionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u0087\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\6\2\31\n\2\r\2\16\2\32\3\2\3\2\3\3\3\3\5\3!\n\3\3\4\3\4\3\4\3"+
		"\4\6\4\'\n\4\r\4\16\4(\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\5\6\\\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\7\tl\n\t\f\t\16\to\13\t\5\tq\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"z\n\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0082\n\n\f\n\16\n\u0085\13\n\3\n\2"+
		"\3\22\13\2\4\6\b\n\f\16\20\22\2\5\4\2\31\31\33\33\3\2\25\26\3\2\27\30"+
		"\u0091\2\24\3\2\2\2\4 \3\2\2\2\6\"\3\2\2\2\b,\3\2\2\2\n[\3\2\2\2\f]\3"+
		"\2\2\2\16_\3\2\2\2\20p\3\2\2\2\22y\3\2\2\2\24\25\7\3\2\2\25\26\7\31\2"+
		"\2\26\30\7\36\2\2\27\31\5\4\3\2\30\27\3\2\2\2\31\32\3\2\2\2\32\30\3\2"+
		"\2\2\32\33\3\2\2\2\33\34\3\2\2\2\34\35\7\37\2\2\35\3\3\2\2\2\36!\5\b\5"+
		"\2\37!\5\6\4\2 \36\3\2\2\2 \37\3\2\2\2!\5\3\2\2\2\"#\7\4\2\2#$\5\n\6\2"+
		"$&\7\36\2\2%\'\5\4\3\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2"+
		"\2\2*+\7\37\2\2+\7\3\2\2\2,-\7\32\2\2-.\5\16\b\2./\7\5\2\2/\t\3\2\2\2"+
		"\60\\\5\f\7\2\61\62\7 \2\2\62\63\5\f\7\2\63\64\7\6\2\2\64\65\5\f\7\2\65"+
		"\66\7!\2\2\66\\\3\2\2\2\678\7 \2\289\5\f\7\29:\7\7\2\2:;\5\f\7\2;<\7!"+
		"\2\2<\\\3\2\2\2=>\7 \2\2>?\5\f\7\2?@\7\b\2\2@A\5\f\7\2AB\7!\2\2B\\\3\2"+
		"\2\2CD\7 \2\2DE\5\f\7\2EF\7\t\2\2FG\5\f\7\2GH\7!\2\2H\\\3\2\2\2IJ\7 \2"+
		"\2JK\5\f\7\2KL\7\n\2\2LM\5\f\7\2MN\7!\2\2N\\\3\2\2\2OP\7 \2\2PQ\5\n\6"+
		"\2QR\7\13\2\2RS\5\n\6\2ST\7!\2\2T\\\3\2\2\2UV\7 \2\2VW\5\n\6\2WX\7\f\2"+
		"\2XY\5\n\6\2YZ\7!\2\2Z\\\3\2\2\2[\60\3\2\2\2[\61\3\2\2\2[\67\3\2\2\2["+
		"=\3\2\2\2[C\3\2\2\2[I\3\2\2\2[O\3\2\2\2[U\3\2\2\2\\\13\3\2\2\2]^\t\2\2"+
		"\2^\r\3\2\2\2_`\7\31\2\2`a\7\r\2\2ab\5\20\t\2b\17\3\2\2\2cq\7\16\2\2d"+
		"q\7\17\2\2eq\7\20\2\2fq\7\21\2\2gq\7\22\2\2hm\7\23\2\2ij\7\24\2\2jl\5"+
		"\22\n\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2nq\3\2\2\2om\3\2\2\2pc"+
		"\3\2\2\2pd\3\2\2\2pe\3\2\2\2pf\3\2\2\2pg\3\2\2\2ph\3\2\2\2q\21\3\2\2\2"+
		"rs\b\n\1\2sz\7\33\2\2tz\7\31\2\2uv\7 \2\2vw\5\22\n\2wx\7!\2\2xz\3\2\2"+
		"\2yr\3\2\2\2yt\3\2\2\2yu\3\2\2\2z\u0083\3\2\2\2{|\f\7\2\2|}\t\3\2\2}\u0082"+
		"\5\22\n\b~\177\f\6\2\2\177\u0080\t\4\2\2\u0080\u0082\5\22\n\7\u0081{\3"+
		"\2\2\2\u0081~\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\23\3\2\2\2\u0085\u0083\3\2\2\2\13\32 ([mpy\u0081\u0083";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}