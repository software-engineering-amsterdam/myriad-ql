// Generated from /Users/matt/Repos/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/grammars/QL.g4 by ANTLR 4.6
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
		T__17=18, ID=19, STRING=20, NUMBER=21, WHITESPACE=22, COMMENT=23;
	public static final int
		RULE_start = 0, RULE_content = 1, RULE_categorise = 2, RULE_caseNewInput = 3, 
		RULE_type = 4, RULE_mathaction = 5;
	public static final String[] ruleNames = {
		"start", "content", "categorise", "caseNewInput", "type", "mathaction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'if'", "'('", "')'", "':'", "'boolean'", 
		"'integer'", "'double'", "'float'", "'string'", "'money'", "'='", "'*'", 
		"'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "ID", "STRING", "NUMBER", "WHITESPACE", 
		"COMMENT"
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
	public static class StartContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(T__0);
			setState(13);
			match(ID);
			setState(14);
			match(T__1);
			setState(15);
			content();
			setState(16);
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

	public static class ContentContext extends ParserRuleContext {
		public List<CategoriseContext> categorise() {
			return getRuleContexts(CategoriseContext.class);
		}
		public CategoriseContext categorise(int i) {
			return getRuleContext(CategoriseContext.class,i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << STRING) | (1L << NUMBER) | (1L << WHITESPACE) | (1L << COMMENT))) != 0)) {
				{
				{
				setState(18);
				categorise();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class CategoriseContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(QLParser.STRING, 0); }
		public CaseNewInputContext caseNewInput() {
			return getRuleContext(CaseNewInputContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(QLParser.NUMBER, 0); }
		public TerminalNode WHITESPACE() { return getToken(QLParser.WHITESPACE, 0); }
		public TerminalNode COMMENT() { return getToken(QLParser.COMMENT, 0); }
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public CategoriseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_categorise; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterCategorise(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitCategorise(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCategorise(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CategoriseContext categorise() throws RecognitionException {
		CategoriseContext _localctx = new CategoriseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_categorise);
		try {
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				match(STRING);
				setState(25);
				caseNewInput();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(NUMBER);
				}
				break;
			case WHITESPACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(27);
				match(WHITESPACE);
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(28);
				match(COMMENT);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 5);
				{
				setState(29);
				match(T__3);
				setState(30);
				match(T__4);
				setState(31);
				match(ID);
				setState(32);
				match(T__5);
				setState(33);
				match(T__1);
				setState(34);
				content();
				setState(35);
				match(T__2);
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

	public static class CaseNewInputContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QLParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CaseNewInputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseNewInput; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterCaseNewInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitCaseNewInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QLVisitor ) return ((QLVisitor<? extends T>)visitor).visitCaseNewInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseNewInputContext caseNewInput() throws RecognitionException {
		CaseNewInputContext _localctx = new CaseNewInputContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_caseNewInput);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(ID);
			setState(40);
			match(T__6);
			setState(41);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitType(this);
		}
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
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(T__7);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(T__8);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				match(T__9);
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 4);
				{
				setState(46);
				match(T__10);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 6);
				{
				setState(48);
				match(T__12);
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__13) {
					{
					{
					setState(49);
					match(T__13);
					setState(50);
					mathaction(0);
					}
					}
					setState(55);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).enterMathaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QLListener ) ((QLListener)listener).exitMathaction(this);
		}
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_mathaction, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(59);
				match(NUMBER);
				}
				break;
			case ID:
				{
				setState(60);
				match(ID);
				}
				break;
			case T__4:
				{
				setState(61);
				match(T__4);
				setState(62);
				mathaction(0);
				setState(63);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(75);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(73);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(67);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(68);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==T__15) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(69);
						mathaction(6);
						}
						break;
					case 2:
						{
						_localctx = new MathactionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_mathaction);
						setState(70);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(71);
						((MathactionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((MathactionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(72);
						mathaction(5);
						}
						break;
					}
					} 
				}
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31Q\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\2\3\2\3\3\7\3\26"+
		"\n\3\f\3\16\3\31\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4(\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\66\n"+
		"\6\f\6\16\69\13\6\5\6;\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7D\n\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\7\7L\n\7\f\7\16\7O\13\7\3\7\2\3\f\b\2\4\6\b\n\f\2\4"+
		"\3\2\21\22\3\2\23\24Y\2\16\3\2\2\2\4\27\3\2\2\2\6\'\3\2\2\2\b)\3\2\2\2"+
		"\n:\3\2\2\2\fC\3\2\2\2\16\17\7\3\2\2\17\20\7\25\2\2\20\21\7\4\2\2\21\22"+
		"\5\4\3\2\22\23\7\5\2\2\23\3\3\2\2\2\24\26\5\6\4\2\25\24\3\2\2\2\26\31"+
		"\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30\5\3\2\2\2\31\27\3\2\2\2\32\33"+
		"\7\26\2\2\33(\5\b\5\2\34(\7\27\2\2\35(\7\30\2\2\36(\7\31\2\2\37 \7\6\2"+
		"\2 !\7\7\2\2!\"\7\25\2\2\"#\7\b\2\2#$\7\4\2\2$%\5\4\3\2%&\7\5\2\2&(\3"+
		"\2\2\2\'\32\3\2\2\2\'\34\3\2\2\2\'\35\3\2\2\2\'\36\3\2\2\2\'\37\3\2\2"+
		"\2(\7\3\2\2\2)*\7\25\2\2*+\7\t\2\2+,\5\n\6\2,\t\3\2\2\2-;\7\n\2\2.;\7"+
		"\13\2\2/;\7\f\2\2\60;\7\r\2\2\61;\7\16\2\2\62\67\7\17\2\2\63\64\7\20\2"+
		"\2\64\66\5\f\7\2\65\63\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28"+
		";\3\2\2\29\67\3\2\2\2:-\3\2\2\2:.\3\2\2\2:/\3\2\2\2:\60\3\2\2\2:\61\3"+
		"\2\2\2:\62\3\2\2\2;\13\3\2\2\2<=\b\7\1\2=D\7\27\2\2>D\7\25\2\2?@\7\7\2"+
		"\2@A\5\f\7\2AB\7\b\2\2BD\3\2\2\2C<\3\2\2\2C>\3\2\2\2C?\3\2\2\2DM\3\2\2"+
		"\2EF\f\7\2\2FG\t\2\2\2GL\5\f\7\bHI\f\6\2\2IJ\t\3\2\2JL\5\f\7\7KE\3\2\2"+
		"\2KH\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\r\3\2\2\2OM\3\2\2\2\t\27\'"+
		"\67:CKM";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}