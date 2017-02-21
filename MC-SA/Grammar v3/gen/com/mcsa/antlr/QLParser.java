// Generated from C:/Users/sotos/Documents/GitHub/myriad-ql/MC-SA/Grammar v3/src/com/mcsa/grammars\QL.g4 by ANTLR 4.6
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, TOKEN=15, ID=16, STRING=17, 
		NUMBER=18, WHITESPACE=19, COMMENT=20, OPEN_BRACKET=21, CLOSE_BRACKET=22, 
		OPEN_PARENTH=23, CLOSE_PARENTH=24;
	public static final int
		RULE_formDeclaration = 0, RULE_statement = 1, RULE_ifStatement = 2, RULE_question = 3, 
		RULE_ifCase = 4, RULE_ifCaseArgs = 5, RULE_questionParameters = 6, RULE_type = 7, 
		RULE_mathaction = 8;
	public static final String[] ruleNames = {
		"formDeclaration", "statement", "ifStatement", "question", "ifCase", "ifCaseArgs", 
		"questionParameters", "type", "mathaction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", "':'", "'boolean'", "'integer'", "'double'", "'float'", 
		"'string'", "'money'", "'='", "'*'", "'/'", "'+'", "'-'", null, null, 
		null, null, null, null, "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "TOKEN", "ID", "STRING", "NUMBER", "WHITESPACE", "COMMENT", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", "CLOSE_PARENTH"
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
		public TerminalNode TOKEN() { return getToken(QLParser.TOKEN, 0); }
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
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				ifCaseArgs();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				match(OPEN_PARENTH);
				setState(47);
				ifCaseArgs();
				setState(48);
				match(TOKEN);
				setState(49);
				ifCaseArgs();
				setState(50);
				match(CLOSE_PARENTH);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				match(OPEN_PARENTH);
				setState(53);
				ifCaseArgs();
				setState(54);
				match(TOKEN);
				setState(55);
				ifCaseArgs();
				setState(56);
				match(CLOSE_PARENTH);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(OPEN_PARENTH);
				setState(59);
				ifCaseArgs();
				setState(60);
				match(TOKEN);
				setState(61);
				ifCaseArgs();
				setState(62);
				match(CLOSE_PARENTH);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(64);
				match(OPEN_PARENTH);
				setState(65);
				ifCaseArgs();
				setState(66);
				match(TOKEN);
				setState(67);
				ifCaseArgs();
				setState(68);
				match(CLOSE_PARENTH);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(70);
				match(OPEN_PARENTH);
				setState(71);
				ifCaseArgs();
				setState(72);
				match(TOKEN);
				setState(73);
				ifCaseArgs();
				setState(74);
				match(CLOSE_PARENTH);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				match(OPEN_PARENTH);
				setState(77);
				ifCase();
				setState(78);
				match(TOKEN);
				setState(79);
				ifCase();
				setState(80);
				match(CLOSE_PARENTH);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(82);
				match(OPEN_PARENTH);
				setState(83);
				ifCase();
				setState(84);
				match(TOKEN);
				setState(85);
				ifCase();
				setState(86);
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
			setState(90);
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
			setState(92);
			match(ID);
			setState(93);
			match(T__2);
			setState(94);
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
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				match(T__3);
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(T__4);
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				match(T__5);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
				match(T__6);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				match(T__7);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				match(T__8);
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(102);
					match(T__9);
					setState(103);
					mathaction(0);
					}
					}
					setState(108);
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
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(112);
				match(NUMBER);
				}
				break;
			case ID:
				{
				setState(113);
				match(ID);
				}
				break;
			case OPEN_PARENTH:
				{
				setState(114);
				match(OPEN_PARENTH);
				setState(115);
				mathaction(0);
				setState(116);
				match(CLOSE_PARENTH);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(128);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(126);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(120);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(121);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__10 || _la==T__11) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(122);
						mathaction(6);
						}
						break;
					case 2:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(123);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(124);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(125);
						mathaction(5);
						}
						break;
					}
					} 
				}
				setState(130);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\32\u0086\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\2\6\2\31\n\2\r\2\16\2\32\3\2\3\2\3\3\3\3\5\3!\n\3\3\4\3\4\3\4"+
		"\3\4\6\4\'\n\4\r\4\16\4(\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\5\6[\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\tk\n\t\f\t\16\tn\13\t\5\tp\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\ny\n"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0081\n\n\f\n\16\n\u0084\13\n\3\n\2\3\22"+
		"\13\2\4\6\b\n\f\16\20\22\2\5\4\2\22\22\24\24\3\2\r\16\3\2\17\20\u0090"+
		"\2\24\3\2\2\2\4 \3\2\2\2\6\"\3\2\2\2\b,\3\2\2\2\nZ\3\2\2\2\f\\\3\2\2\2"+
		"\16^\3\2\2\2\20o\3\2\2\2\22x\3\2\2\2\24\25\7\3\2\2\25\26\7\22\2\2\26\30"+
		"\7\27\2\2\27\31\5\4\3\2\30\27\3\2\2\2\31\32\3\2\2\2\32\30\3\2\2\2\32\33"+
		"\3\2\2\2\33\34\3\2\2\2\34\35\7\30\2\2\35\3\3\2\2\2\36!\5\b\5\2\37!\5\6"+
		"\4\2 \36\3\2\2\2 \37\3\2\2\2!\5\3\2\2\2\"#\7\4\2\2#$\5\n\6\2$&\7\27\2"+
		"\2%\'\5\4\3\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\7\30"+
		"\2\2+\7\3\2\2\2,-\7\23\2\2-.\5\16\b\2.\t\3\2\2\2/[\5\f\7\2\60\61\7\31"+
		"\2\2\61\62\5\f\7\2\62\63\7\21\2\2\63\64\5\f\7\2\64\65\7\32\2\2\65[\3\2"+
		"\2\2\66\67\7\31\2\2\678\5\f\7\289\7\21\2\29:\5\f\7\2:;\7\32\2\2;[\3\2"+
		"\2\2<=\7\31\2\2=>\5\f\7\2>?\7\21\2\2?@\5\f\7\2@A\7\32\2\2A[\3\2\2\2BC"+
		"\7\31\2\2CD\5\f\7\2DE\7\21\2\2EF\5\f\7\2FG\7\32\2\2G[\3\2\2\2HI\7\31\2"+
		"\2IJ\5\f\7\2JK\7\21\2\2KL\5\f\7\2LM\7\32\2\2M[\3\2\2\2NO\7\31\2\2OP\5"+
		"\n\6\2PQ\7\21\2\2QR\5\n\6\2RS\7\32\2\2S[\3\2\2\2TU\7\31\2\2UV\5\n\6\2"+
		"VW\7\21\2\2WX\5\n\6\2XY\7\32\2\2Y[\3\2\2\2Z/\3\2\2\2Z\60\3\2\2\2Z\66\3"+
		"\2\2\2Z<\3\2\2\2ZB\3\2\2\2ZH\3\2\2\2ZN\3\2\2\2ZT\3\2\2\2[\13\3\2\2\2\\"+
		"]\t\2\2\2]\r\3\2\2\2^_\7\22\2\2_`\7\5\2\2`a\5\20\t\2a\17\3\2\2\2bp\7\6"+
		"\2\2cp\7\7\2\2dp\7\b\2\2ep\7\t\2\2fp\7\n\2\2gl\7\13\2\2hi\7\f\2\2ik\5"+
		"\22\n\2jh\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2nl\3\2\2\2ob"+
		"\3\2\2\2oc\3\2\2\2od\3\2\2\2oe\3\2\2\2of\3\2\2\2og\3\2\2\2p\21\3\2\2\2"+
		"qr\b\n\1\2ry\7\24\2\2sy\7\22\2\2tu\7\31\2\2uv\5\22\n\2vw\7\32\2\2wy\3"+
		"\2\2\2xq\3\2\2\2xs\3\2\2\2xt\3\2\2\2y\u0082\3\2\2\2z{\f\7\2\2{|\t\3\2"+
		"\2|\u0081\5\22\n\b}~\f\6\2\2~\177\t\4\2\2\177\u0081\5\22\n\7\u0080z\3"+
		"\2\2\2\u0080}\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\23\3\2\2\2\u0084\u0082\3\2\2\2\13\32 (Zlox\u0080\u0082";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}