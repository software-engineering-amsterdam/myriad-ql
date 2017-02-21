// Generated from C:/Users/sotos/Documents/GitHub/myriad-ql/MC-SA/Grammar v3/src/com/mcsa/grammars\QL.g4 by ANTLR 4.6
package com.mcsa.antlr;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, TOKEN=15, ID=16, STRING=17, 
		NUMBER=18, WHITESPACE=19, COMMENT=20, OPEN_BRACKET=21, CLOSE_BRACKET=22, 
		OPEN_PARENTH=23, CLOSE_PARENTH=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "TOKEN", "ID", "STRING", "NUMBER", 
		"WHITESPACE", "COMMENT", "OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_PARENTH", 
		"CLOSE_PARENTH"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u00b4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20~\n\20\3\21\6\21\u0081\n"+
		"\21\r\21\16\21\u0082\3\22\3\22\7\22\u0087\n\22\f\22\16\22\u008a\13\22"+
		"\3\22\3\22\3\23\6\23\u008f\n\23\r\23\16\23\u0090\3\23\3\23\6\23\u0095"+
		"\n\23\r\23\16\23\u0096\5\23\u0099\n\23\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\7\25\u00a3\n\25\f\25\16\25\u00a6\13\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\4\u0088\u00a4\2\32\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\5\4\2>>@@\6\2&&C\\aac|\5\2\13"+
		"\f\17\17\"\"\u00be\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\58\3\2\2\2\7;\3\2\2\2\t=\3"+
		"\2\2\2\13E\3\2\2\2\rM\3\2\2\2\17T\3\2\2\2\21Z\3\2\2\2\23a\3\2\2\2\25g"+
		"\3\2\2\2\27i\3\2\2\2\31k\3\2\2\2\33m\3\2\2\2\35o\3\2\2\2\37}\3\2\2\2!"+
		"\u0080\3\2\2\2#\u0084\3\2\2\2%\u008e\3\2\2\2\'\u009a\3\2\2\2)\u009e\3"+
		"\2\2\2+\u00ac\3\2\2\2-\u00ae\3\2\2\2/\u00b0\3\2\2\2\61\u00b2\3\2\2\2\63"+
		"\64\7h\2\2\64\65\7q\2\2\65\66\7t\2\2\66\67\7o\2\2\67\4\3\2\2\289\7k\2"+
		"\29:\7h\2\2:\6\3\2\2\2;<\7<\2\2<\b\3\2\2\2=>\7d\2\2>?\7q\2\2?@\7q\2\2"+
		"@A\7n\2\2AB\7g\2\2BC\7c\2\2CD\7p\2\2D\n\3\2\2\2EF\7k\2\2FG\7p\2\2GH\7"+
		"v\2\2HI\7g\2\2IJ\7i\2\2JK\7g\2\2KL\7t\2\2L\f\3\2\2\2MN\7f\2\2NO\7q\2\2"+
		"OP\7w\2\2PQ\7d\2\2QR\7n\2\2RS\7g\2\2S\16\3\2\2\2TU\7h\2\2UV\7n\2\2VW\7"+
		"q\2\2WX\7c\2\2XY\7v\2\2Y\20\3\2\2\2Z[\7u\2\2[\\\7v\2\2\\]\7t\2\2]^\7k"+
		"\2\2^_\7p\2\2_`\7i\2\2`\22\3\2\2\2ab\7o\2\2bc\7q\2\2cd\7p\2\2de\7g\2\2"+
		"ef\7{\2\2f\24\3\2\2\2gh\7?\2\2h\26\3\2\2\2ij\7,\2\2j\30\3\2\2\2kl\7\61"+
		"\2\2l\32\3\2\2\2mn\7-\2\2n\34\3\2\2\2op\7/\2\2p\36\3\2\2\2q~\t\2\2\2r"+
		"s\7@\2\2s~\7?\2\2tu\7>\2\2u~\7?\2\2vw\7?\2\2w~\7?\2\2xy\7C\2\2yz\7P\2"+
		"\2z~\7F\2\2{|\7Q\2\2|~\7T\2\2}q\3\2\2\2}r\3\2\2\2}t\3\2\2\2}v\3\2\2\2"+
		"}x\3\2\2\2}{\3\2\2\2~ \3\2\2\2\177\u0081\t\3\2\2\u0080\177\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\"\3\2\2\2"+
		"\u0084\u0088\7$\2\2\u0085\u0087\13\2\2\2\u0086\u0085\3\2\2\2\u0087\u008a"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0088\3\2\2\2\u008b\u008c\7$\2\2\u008c$\3\2\2\2\u008d\u008f\4\62;\2\u008e"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u0098\3\2\2\2\u0092\u0094\7\60\2\2\u0093\u0095\4\62;\2\u0094"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\u0099\3\2\2\2\u0098\u0092\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"&\3\2\2\2\u009a\u009b\t\4\2\2\u009b\u009c\3\2\2\2\u009c\u009d\b\24\2\2"+
		"\u009d(\3\2\2\2\u009e\u009f\7\61\2\2\u009f\u00a0\7,\2\2\u00a0\u00a4\3"+
		"\2\2\2\u00a1\u00a3\13\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a7\u00a8\7,\2\2\u00a8\u00a9\7\61\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\b\25\2\2\u00ab*\3\2\2\2\u00ac\u00ad\7}\2\2\u00ad,\3\2\2\2\u00ae"+
		"\u00af\7\177\2\2\u00af.\3\2\2\2\u00b0\u00b1\7*\2\2\u00b1\60\3\2\2\2\u00b2"+
		"\u00b3\7+\2\2\u00b3\62\3\2\2\2\n\2}\u0082\u0088\u0090\u0096\u0098\u00a4"+
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