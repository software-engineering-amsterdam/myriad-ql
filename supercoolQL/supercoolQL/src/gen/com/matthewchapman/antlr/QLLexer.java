// Generated from /Users/matt/Repos/myriad-ql/supercoolQL/supercoolQL/grammar/QL.g4 by ANTLR 4.6
package com.matthewchapman.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, ID=23, STRING=24, NUMBER=25, 
		WHITESPACE=26, MULTI_LINE_COMMENT=27, SINGLE_LINE_COMMENT=28, TRUE=29, 
		FALSE=30, OPEN_BRACKET=31, CLOSE_BRACKET=32, OPEN_PARENTH=33, CLOSE_PARENTH=34;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "ID", "STRING", "NUMBER", 
		"WHITESPACE", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", "TRUE", "FALSE", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", "CLOSE_PARENTH"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "';'", "'if'", "'else'", "'!'", "'/'", "'*'", "'-'", 
		"'+'", "'>'", "'<'", "'=='", "'!='", "'<='", "'>='", "'&&'", "'||'", "'='", 
		"'boolean'", "'integer'", "'string'", null, null, null, null, null, null, 
		"'TRUE'", "'FALSE'", "'{'", "'}'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "ID", 
		"STRING", "NUMBER", "WHITESPACE", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", 
		"TRUE", "FALSE", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", "CLOSE_PARENTH"
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


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u00e2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\6\30\u0093\n\30\r\30\16\30\u0094\3\30\7\30\u0098\n\30"+
		"\f\30\16\30\u009b\13\30\3\31\3\31\7\31\u009f\n\31\f\31\16\31\u00a2\13"+
		"\31\3\31\3\31\3\32\6\32\u00a7\n\32\r\32\16\32\u00a8\3\32\3\32\6\32\u00ad"+
		"\n\32\r\32\16\32\u00ae\5\32\u00b1\n\32\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\7\34\u00bb\n\34\f\34\16\34\u00be\13\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\7\35\u00c9\n\35\f\35\16\35\u00cc\13\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3!\3"+
		"!\3\"\3\"\3#\3#\4\u00a0\u00bc\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$\3\2\6\5\2C\\aac|\6\2\62;C"+
		"\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u00e9\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5L\3\2\2\2\7N\3\2\2\2"+
		"\tP\3\2\2\2\13S\3\2\2\2\rX\3\2\2\2\17Z\3\2\2\2\21\\\3\2\2\2\23^\3\2\2"+
		"\2\25`\3\2\2\2\27b\3\2\2\2\31d\3\2\2\2\33f\3\2\2\2\35i\3\2\2\2\37l\3\2"+
		"\2\2!o\3\2\2\2#r\3\2\2\2%u\3\2\2\2\'x\3\2\2\2)z\3\2\2\2+\u0082\3\2\2\2"+
		"-\u008a\3\2\2\2/\u0092\3\2\2\2\61\u009c\3\2\2\2\63\u00a6\3\2\2\2\65\u00b2"+
		"\3\2\2\2\67\u00b6\3\2\2\29\u00c4\3\2\2\2;\u00cf\3\2\2\2=\u00d4\3\2\2\2"+
		"?\u00da\3\2\2\2A\u00dc\3\2\2\2C\u00de\3\2\2\2E\u00e0\3\2\2\2GH\7h\2\2"+
		"HI\7q\2\2IJ\7t\2\2JK\7o\2\2K\4\3\2\2\2LM\7<\2\2M\6\3\2\2\2NO\7=\2\2O\b"+
		"\3\2\2\2PQ\7k\2\2QR\7h\2\2R\n\3\2\2\2ST\7g\2\2TU\7n\2\2UV\7u\2\2VW\7g"+
		"\2\2W\f\3\2\2\2XY\7#\2\2Y\16\3\2\2\2Z[\7\61\2\2[\20\3\2\2\2\\]\7,\2\2"+
		"]\22\3\2\2\2^_\7/\2\2_\24\3\2\2\2`a\7-\2\2a\26\3\2\2\2bc\7@\2\2c\30\3"+
		"\2\2\2de\7>\2\2e\32\3\2\2\2fg\7?\2\2gh\7?\2\2h\34\3\2\2\2ij\7#\2\2jk\7"+
		"?\2\2k\36\3\2\2\2lm\7>\2\2mn\7?\2\2n \3\2\2\2op\7@\2\2pq\7?\2\2q\"\3\2"+
		"\2\2rs\7(\2\2st\7(\2\2t$\3\2\2\2uv\7~\2\2vw\7~\2\2w&\3\2\2\2xy\7?\2\2"+
		"y(\3\2\2\2z{\7d\2\2{|\7q\2\2|}\7q\2\2}~\7n\2\2~\177\7g\2\2\177\u0080\7"+
		"c\2\2\u0080\u0081\7p\2\2\u0081*\3\2\2\2\u0082\u0083\7k\2\2\u0083\u0084"+
		"\7p\2\2\u0084\u0085\7v\2\2\u0085\u0086\7g\2\2\u0086\u0087\7i\2\2\u0087"+
		"\u0088\7g\2\2\u0088\u0089\7t\2\2\u0089,\3\2\2\2\u008a\u008b\7u\2\2\u008b"+
		"\u008c\7v\2\2\u008c\u008d\7t\2\2\u008d\u008e\7k\2\2\u008e\u008f\7p\2\2"+
		"\u008f\u0090\7i\2\2\u0090.\3\2\2\2\u0091\u0093\t\2\2\2\u0092\u0091\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0099\3\2\2\2\u0096\u0098\t\3\2\2\u0097\u0096\3\2\2\2\u0098\u009b\3\2"+
		"\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\60\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009c\u00a0\7$\2\2\u009d\u009f\13\2\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a3\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7$\2\2\u00a4\62\3\2\2\2\u00a5\u00a7"+
		"\4\62;\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00b0\3\2\2\2\u00aa\u00ac\7\60\2\2\u00ab\u00ad\4"+
		"\62;\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b0\u00b1\3\2"+
		"\2\2\u00b1\64\3\2\2\2\u00b2\u00b3\t\4\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5"+
		"\b\33\2\2\u00b5\66\3\2\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00b8\7,\2\2\u00b8"+
		"\u00bc\3\2\2\2\u00b9\u00bb\13\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00be\3"+
		"\2\2\2\u00bc\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00bf\u00c0\7,\2\2\u00c0\u00c1\7\61\2\2\u00c1\u00c2\3\2"+
		"\2\2\u00c2\u00c3\b\34\2\2\u00c38\3\2\2\2\u00c4\u00c5\7\61\2\2\u00c5\u00c6"+
		"\7\61\2\2\u00c6\u00ca\3\2\2\2\u00c7\u00c9\n\5\2\2\u00c8\u00c7\3\2\2\2"+
		"\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd"+
		"\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\b\35\2\2\u00ce:\3\2\2\2\u00cf"+
		"\u00d0\7V\2\2\u00d0\u00d1\7T\2\2\u00d1\u00d2\7W\2\2\u00d2\u00d3\7G\2\2"+
		"\u00d3<\3\2\2\2\u00d4\u00d5\7H\2\2\u00d5\u00d6\7C\2\2\u00d6\u00d7\7N\2"+
		"\2\u00d7\u00d8\7U\2\2\u00d8\u00d9\7G\2\2\u00d9>\3\2\2\2\u00da\u00db\7"+
		"}\2\2\u00db@\3\2\2\2\u00dc\u00dd\7\177\2\2\u00ddB\3\2\2\2\u00de\u00df"+
		"\7*\2\2\u00dfD\3\2\2\2\u00e0\u00e1\7+\2\2\u00e1F\3\2\2\2\13\2\u0094\u0099"+
		"\u00a0\u00a8\u00ae\u00b0\u00bc\u00ca\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}