// Generated from C:/Users/sotos/Documents/GitHub/myriad-ql/SA/Grammar v3/src/com/Qlmain/grammars\QL.g4 by ANTLR 4.6
package com.Qlmain.antlr;
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
		T__17=18, T__18=19, BOOLEAN=20, ID=21, STRING=22, NUMBER=23, WHITESPACE=24, 
		COMMENT=25, OPEN_BRACKET=26, CLOSE_BRACKET=27, OPEN_PARENTH=28, CLOSE_PARENTH=29;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "BOOLEAN", "ID", "STRING", "NUMBER", "WHITESPACE", "COMMENT", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", "CLOSE_PARENTH"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'if'", "':'", "'boolean'", "'integer'", "'string'", "'money'", 
		"'='", "'*'", "'/'", "'+'", "'-'", "'AND'", "'OR'", "'<'", "'>'", "'>='", 
		"'<='", "'=='", null, null, null, null, null, null, "'{'", "'}'", "'('", 
		"')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "BOOLEAN", "ID", "STRING", 
		"NUMBER", "WHITESPACE", "COMMENT", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", 
		"CLOSE_PARENTH"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\37\u00c2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u008c\n\25\3\26"+
		"\6\26\u008f\n\26\r\26\16\26\u0090\3\27\3\27\7\27\u0095\n\27\f\27\16\27"+
		"\u0098\13\27\3\27\3\27\3\30\6\30\u009d\n\30\r\30\16\30\u009e\3\30\3\30"+
		"\6\30\u00a3\n\30\r\30\16\30\u00a4\5\30\u00a7\n\30\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\7\32\u00b1\n\32\f\32\16\32\u00b4\13\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\4\u0096\u00b2"+
		"\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37\3\2\4\6\2&&C\\aac|\5\2\13\f\17\17\"\"\u00c8\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5B\3"+
		"\2\2\2\7E\3\2\2\2\tG\3\2\2\2\13O\3\2\2\2\rW\3\2\2\2\17^\3\2\2\2\21d\3"+
		"\2\2\2\23f\3\2\2\2\25h\3\2\2\2\27j\3\2\2\2\31l\3\2\2\2\33n\3\2\2\2\35"+
		"r\3\2\2\2\37u\3\2\2\2!w\3\2\2\2#y\3\2\2\2%|\3\2\2\2\'\177\3\2\2\2)\u008b"+
		"\3\2\2\2+\u008e\3\2\2\2-\u0092\3\2\2\2/\u009c\3\2\2\2\61\u00a8\3\2\2\2"+
		"\63\u00ac\3\2\2\2\65\u00ba\3\2\2\2\67\u00bc\3\2\2\29\u00be\3\2\2\2;\u00c0"+
		"\3\2\2\2=>\7h\2\2>?\7q\2\2?@\7t\2\2@A\7o\2\2A\4\3\2\2\2BC\7k\2\2CD\7h"+
		"\2\2D\6\3\2\2\2EF\7<\2\2F\b\3\2\2\2GH\7d\2\2HI\7q\2\2IJ\7q\2\2JK\7n\2"+
		"\2KL\7g\2\2LM\7c\2\2MN\7p\2\2N\n\3\2\2\2OP\7k\2\2PQ\7p\2\2QR\7v\2\2RS"+
		"\7g\2\2ST\7i\2\2TU\7g\2\2UV\7t\2\2V\f\3\2\2\2WX\7u\2\2XY\7v\2\2YZ\7t\2"+
		"\2Z[\7k\2\2[\\\7p\2\2\\]\7i\2\2]\16\3\2\2\2^_\7o\2\2_`\7q\2\2`a\7p\2\2"+
		"ab\7g\2\2bc\7{\2\2c\20\3\2\2\2de\7?\2\2e\22\3\2\2\2fg\7,\2\2g\24\3\2\2"+
		"\2hi\7\61\2\2i\26\3\2\2\2jk\7-\2\2k\30\3\2\2\2lm\7/\2\2m\32\3\2\2\2no"+
		"\7C\2\2op\7P\2\2pq\7F\2\2q\34\3\2\2\2rs\7Q\2\2st\7T\2\2t\36\3\2\2\2uv"+
		"\7>\2\2v \3\2\2\2wx\7@\2\2x\"\3\2\2\2yz\7@\2\2z{\7?\2\2{$\3\2\2\2|}\7"+
		">\2\2}~\7?\2\2~&\3\2\2\2\177\u0080\7?\2\2\u0080\u0081\7?\2\2\u0081(\3"+
		"\2\2\2\u0082\u0083\7v\2\2\u0083\u0084\7t\2\2\u0084\u0085\7w\2\2\u0085"+
		"\u008c\7g\2\2\u0086\u0087\7h\2\2\u0087\u0088\7c\2\2\u0088\u0089\7n\2\2"+
		"\u0089\u008a\7u\2\2\u008a\u008c\7g\2\2\u008b\u0082\3\2\2\2\u008b\u0086"+
		"\3\2\2\2\u008c*\3\2\2\2\u008d\u008f\t\2\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091,\3\2\2\2"+
		"\u0092\u0096\7$\2\2\u0093\u0095\13\2\2\2\u0094\u0093\3\2\2\2\u0095\u0098"+
		"\3\2\2\2\u0096\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0099\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0099\u009a\7$\2\2\u009a.\3\2\2\2\u009b\u009d\4\62;\2\u009c"+
		"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a6\3\2\2\2\u00a0\u00a2\7\60\2\2\u00a1\u00a3\4\62;\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a0\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\60\3\2\2\2\u00a8\u00a9\t\3\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\b\31\2"+
		"\2\u00ab\62\3\2\2\2\u00ac\u00ad\7\61\2\2\u00ad\u00ae\7,\2\2\u00ae\u00b2"+
		"\3\2\2\2\u00af\u00b1\13\2\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b4\3\2\2\2"+
		"\u00b2\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00b2"+
		"\3\2\2\2\u00b5\u00b6\7,\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00b9\b\32\2\2\u00b9\64\3\2\2\2\u00ba\u00bb\7}\2\2\u00bb\66\3\2\2\2\u00bc"+
		"\u00bd\7\177\2\2\u00bd8\3\2\2\2\u00be\u00bf\7*\2\2\u00bf:\3\2\2\2\u00c0"+
		"\u00c1\7+\2\2\u00c1<\3\2\2\2\n\2\u008b\u0090\u0096\u009e\u00a4\u00a6\u00b2"+
		"\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}