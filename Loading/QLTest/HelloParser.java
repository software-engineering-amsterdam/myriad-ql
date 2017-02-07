// Generated from Hello.g4 by ANTLR 4.6
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, BOOL=23, IF=24, ELSE=25, 
		WHILE=26, ID=27, INT=28, STRING=29, WS=30, COMMENT=31;
	public static final int
		RULE_root = 0, RULE_block = 1, RULE_question = 2, RULE_type = 3, RULE_computed_question = 4, 
		RULE_statement = 5, RULE_expr = 6, RULE_atom = 7;
	public static final String[] ruleNames = {
		"root", "block", "question", "type", "computed_question", "statement", 
		"expr", "atom"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "'boolean'", "'date'", "'decimal'", 
		"'integer'", "'money'", "'string'", "'('", "'-'", "'+'", "')'", "'=='", 
		"'<='", "'>='", "'<'", "'>'", "'&&'", "'||'", "'!'", null, "'if'", "'else'", 
		"'while'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "BOOL", 
		"IF", "ELSE", "WHILE", "ID", "INT", "STRING", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HelloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HelloParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(17);
			match(ID);
			setState(18);
			block();
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

	public static class BlockContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(T__1);
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << ID))) != 0)) {
				{
				{
				setState(21);
				question();
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HelloParser.ID, 0); }
		public TerminalNode STRING() { return getToken(HelloParser.STRING, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<Computed_questionContext> computed_question() {
			return getRuleContexts(Computed_questionContext.class);
		}
		public Computed_questionContext computed_question(int i) {
			return getRuleContext(Computed_questionContext.class,i);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(29);
				match(ID);
				setState(30);
				match(T__3);
				setState(31);
				match(STRING);
				setState(32);
				type();
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) {
					{
					{
					setState(33);
					computed_question();
					}
					}
					setState(38);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case IF:
			case WHILE:
				{
				setState(39);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9))) != 0)) ) {
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

	public static class Computed_questionContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public Computed_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_computed_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterComputed_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitComputed_question(this);
		}
	}

	public final Computed_questionContext computed_question() throws RecognitionException {
		Computed_questionContext _localctx = new Computed_questionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_computed_question);
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(T__10);
				setState(45);
				type();
				setState(46);
				match(T__11);
				setState(47);
				type();
				}
				break;
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				type();
				setState(50);
				match(T__12);
				setState(51);
				type();
				setState(52);
				match(T__13);
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

	public static class StatementContext extends ParserRuleContext {
		public List<TerminalNode> IF() { return getTokens(HelloParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(HelloParser.IF, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(HelloParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(HelloParser.ELSE, i);
		}
		public TerminalNode WHILE() { return getToken(HelloParser.WHILE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(IF);
				setState(57);
				expr();
				setState(58);
				block();
				setState(66);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(59);
						match(ELSE);
						setState(60);
						match(IF);
						setState(61);
						expr();
						setState(62);
						block();
						}
						} 
					}
					setState(68);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(69);
					match(ELSE);
					setState(70);
					block();
					}
				}

				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(WHILE);
				setState(74);
				expr();
				setState(75);
				block();
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

	public static class ExprContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(T__10);
				setState(80);
				atom();
				setState(81);
				match(T__14);
				setState(82);
				atom();
				setState(83);
				match(T__13);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(T__10);
				setState(86);
				atom();
				setState(87);
				match(T__15);
				setState(88);
				atom();
				setState(89);
				match(T__13);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				match(T__10);
				setState(92);
				atom();
				setState(93);
				match(T__16);
				setState(94);
				atom();
				setState(95);
				match(T__13);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				match(T__10);
				setState(98);
				atom();
				setState(99);
				match(T__17);
				setState(100);
				atom();
				setState(101);
				match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(103);
				match(T__10);
				setState(104);
				atom();
				setState(105);
				match(T__18);
				setState(106);
				atom();
				setState(107);
				match(T__13);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(109);
				match(T__10);
				setState(110);
				atom();
				setState(111);
				match(T__19);
				setState(112);
				atom();
				setState(113);
				match(T__13);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(115);
				match(T__10);
				setState(116);
				atom();
				setState(117);
				match(T__20);
				setState(118);
				atom();
				setState(119);
				match(T__13);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(121);
				match(T__10);
				setState(122);
				match(T__21);
				setState(123);
				atom();
				setState(124);
				match(T__13);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(126);
				match(T__10);
				setState(127);
				atom();
				setState(128);
				match(T__13);
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

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HelloParser.ID, 0); }
		public TerminalNode INT() { return getToken(HelloParser.INT, 0); }
		public TerminalNode STRING() { return getToken(HelloParser.STRING, 0); }
		public TerminalNode BOOL() { return getToken(HelloParser.BOOL, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << ID) | (1L << INT) | (1L << STRING))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u0089\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\7\3\31\n\3\f\3\16\3\34\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\7\4%"+
		"\n\4\f\4\16\4(\13\4\3\4\5\4+\n\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\5\69\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7C\n\7\f\7\16\7"+
		"F\13\7\3\7\3\7\5\7J\n\7\3\7\3\7\3\7\3\7\5\7P\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0085\n\b\3\t\3\t\3\t\2"+
		"\2\n\2\4\6\b\n\f\16\20\2\4\3\2\7\f\4\2\31\31\35\37\u008f\2\22\3\2\2\2"+
		"\4\26\3\2\2\2\6*\3\2\2\2\b,\3\2\2\2\n8\3\2\2\2\fO\3\2\2\2\16\u0084\3\2"+
		"\2\2\20\u0086\3\2\2\2\22\23\7\3\2\2\23\24\7\35\2\2\24\25\5\4\3\2\25\3"+
		"\3\2\2\2\26\32\7\4\2\2\27\31\5\6\4\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30"+
		"\3\2\2\2\32\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2\2\2\35\36\7\5\2\2\36\5"+
		"\3\2\2\2\37 \7\35\2\2 !\7\6\2\2!\"\7\37\2\2\"&\5\b\5\2#%\5\n\6\2$#\3\2"+
		"\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'+\3\2\2\2(&\3\2\2\2)+\5\f\7\2*\37"+
		"\3\2\2\2*)\3\2\2\2+\7\3\2\2\2,-\t\2\2\2-\t\3\2\2\2./\7\r\2\2/\60\5\b\5"+
		"\2\60\61\7\16\2\2\61\62\5\b\5\2\629\3\2\2\2\63\64\5\b\5\2\64\65\7\17\2"+
		"\2\65\66\5\b\5\2\66\67\7\20\2\2\679\3\2\2\28.\3\2\2\28\63\3\2\2\29\13"+
		"\3\2\2\2:;\7\32\2\2;<\5\16\b\2<D\5\4\3\2=>\7\33\2\2>?\7\32\2\2?@\5\16"+
		"\b\2@A\5\4\3\2AC\3\2\2\2B=\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EI\3\2"+
		"\2\2FD\3\2\2\2GH\7\33\2\2HJ\5\4\3\2IG\3\2\2\2IJ\3\2\2\2JP\3\2\2\2KL\7"+
		"\34\2\2LM\5\16\b\2MN\5\4\3\2NP\3\2\2\2O:\3\2\2\2OK\3\2\2\2P\r\3\2\2\2"+
		"QR\7\r\2\2RS\5\20\t\2ST\7\21\2\2TU\5\20\t\2UV\7\20\2\2V\u0085\3\2\2\2"+
		"WX\7\r\2\2XY\5\20\t\2YZ\7\22\2\2Z[\5\20\t\2[\\\7\20\2\2\\\u0085\3\2\2"+
		"\2]^\7\r\2\2^_\5\20\t\2_`\7\23\2\2`a\5\20\t\2ab\7\20\2\2b\u0085\3\2\2"+
		"\2cd\7\r\2\2de\5\20\t\2ef\7\24\2\2fg\5\20\t\2gh\7\20\2\2h\u0085\3\2\2"+
		"\2ij\7\r\2\2jk\5\20\t\2kl\7\25\2\2lm\5\20\t\2mn\7\20\2\2n\u0085\3\2\2"+
		"\2op\7\r\2\2pq\5\20\t\2qr\7\26\2\2rs\5\20\t\2st\7\20\2\2t\u0085\3\2\2"+
		"\2uv\7\r\2\2vw\5\20\t\2wx\7\27\2\2xy\5\20\t\2yz\7\20\2\2z\u0085\3\2\2"+
		"\2{|\7\r\2\2|}\7\30\2\2}~\5\20\t\2~\177\7\20\2\2\177\u0085\3\2\2\2\u0080"+
		"\u0081\7\r\2\2\u0081\u0082\5\20\t\2\u0082\u0083\7\20\2\2\u0083\u0085\3"+
		"\2\2\2\u0084Q\3\2\2\2\u0084W\3\2\2\2\u0084]\3\2\2\2\u0084c\3\2\2\2\u0084"+
		"i\3\2\2\2\u0084o\3\2\2\2\u0084u\3\2\2\2\u0084{\3\2\2\2\u0084\u0080\3\2"+
		"\2\2\u0085\17\3\2\2\2\u0086\u0087\t\3\2\2\u0087\21\3\2\2\2\n\32&*8DIO"+
		"\u0084";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}