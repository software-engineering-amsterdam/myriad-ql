// Generated from /Users/matt/Repos/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/grammars/QL.g4 by ANTLR 4.6
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, ID=19, STRING=20, NUMBER=21, WHITESPACE=22, COMMENT=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "ID", "STRING", "NUMBER", "WHITESPACE", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u00a4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\6\24y\n\24\r\24\16\24z\3\25\3\25\7\25\177\n"+
		"\25\f\25\16\25\u0082\13\25\3\25\3\25\3\26\6\26\u0087\n\26\r\26\16\26\u0088"+
		"\3\26\3\26\6\26\u008d\n\26\r\26\16\26\u008e\5\26\u0091\n\26\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u009b\n\30\f\30\16\30\u009e\13\30"+
		"\3\30\3\30\3\30\3\30\3\30\4\u0080\u009c\2\31\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\3\2\4\6\2&&C\\aac|\5\2\13\f\17\17\"\"\u00a9\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2"+
		"\5\66\3\2\2\2\78\3\2\2\2\t:\3\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2"+
		"\21C\3\2\2\2\23K\3\2\2\2\25S\3\2\2\2\27Z\3\2\2\2\31`\3\2\2\2\33g\3\2\2"+
		"\2\35m\3\2\2\2\37o\3\2\2\2!q\3\2\2\2#s\3\2\2\2%u\3\2\2\2\'x\3\2\2\2)|"+
		"\3\2\2\2+\u0086\3\2\2\2-\u0092\3\2\2\2/\u0096\3\2\2\2\61\62\7h\2\2\62"+
		"\63\7q\2\2\63\64\7t\2\2\64\65\7o\2\2\65\4\3\2\2\2\66\67\7}\2\2\67\6\3"+
		"\2\2\289\7\177\2\29\b\3\2\2\2:;\7k\2\2;<\7h\2\2<\n\3\2\2\2=>\7*\2\2>\f"+
		"\3\2\2\2?@\7+\2\2@\16\3\2\2\2AB\7<\2\2B\20\3\2\2\2CD\7d\2\2DE\7q\2\2E"+
		"F\7q\2\2FG\7n\2\2GH\7g\2\2HI\7c\2\2IJ\7p\2\2J\22\3\2\2\2KL\7k\2\2LM\7"+
		"p\2\2MN\7v\2\2NO\7g\2\2OP\7i\2\2PQ\7g\2\2QR\7t\2\2R\24\3\2\2\2ST\7f\2"+
		"\2TU\7q\2\2UV\7w\2\2VW\7d\2\2WX\7n\2\2XY\7g\2\2Y\26\3\2\2\2Z[\7h\2\2["+
		"\\\7n\2\2\\]\7q\2\2]^\7c\2\2^_\7v\2\2_\30\3\2\2\2`a\7u\2\2ab\7v\2\2bc"+
		"\7t\2\2cd\7k\2\2de\7p\2\2ef\7i\2\2f\32\3\2\2\2gh\7o\2\2hi\7q\2\2ij\7p"+
		"\2\2jk\7g\2\2kl\7{\2\2l\34\3\2\2\2mn\7?\2\2n\36\3\2\2\2op\7,\2\2p \3\2"+
		"\2\2qr\7\61\2\2r\"\3\2\2\2st\7-\2\2t$\3\2\2\2uv\7/\2\2v&\3\2\2\2wy\t\2"+
		"\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{(\3\2\2\2|\u0080\7$\2\2}"+
		"\177\13\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080\u0081\3\2\2\2\u0080~\3"+
		"\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7$\2\2\u0084"+
		"*\3\2\2\2\u0085\u0087\4\62;\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0090\3\2\2\2\u008a\u008c"+
		"\7\60\2\2\u008b\u008d\4\62;\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008a"+
		"\3\2\2\2\u0090\u0091\3\2\2\2\u0091,\3\2\2\2\u0092\u0093\t\3\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\b\27\2\2\u0095.\3\2\2\2\u0096\u0097\7\61\2"+
		"\2\u0097\u0098\7,\2\2\u0098\u009c\3\2\2\2\u0099\u009b\13\2\2\2\u009a\u0099"+
		"\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d"+
		"\u009f\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a0\7,\2\2\u00a0\u00a1\7\61"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\30\2\2\u00a3\60\3\2\2\2\t\2z\u0080"+
		"\u0088\u008e\u0090\u009c\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}