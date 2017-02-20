// Generated from /home/yoan/github/myriad-ql/AlternativeQL/src/org/ql/grammar/QLParser.g4 by ANTLR 4.6
package org.ql.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLParserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		LINE_COMMENT=32, STRING_LITERAL=33, BOOLEAN_LITERAL=34, DECIMAL_LITERAL=35, 
		INTEGER_LITERAL=36, ID=37, WS=38;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "LINE_COMMENT", 
		"STRING_LITERAL", "BOOLEAN_LITERAL", "DECIMAL_LITERAL", "INTEGER_LITERAL", 
		"ID", "WS", "ESCAPE_QUOTE", "UNICODE", "HEX"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "':'", "';'", "'if'", "'('", "')'", "'else'", 
		"'='", "'!'", "'++'", "'--'", "'/'", "'*'", "'-'", "'+'", "'>'", "'<'", 
		"'=='", "'!='", "'<='", "'>='", "'&&'", "'||'", "'boolean'", "'float'", 
		"'integer'", "'string'", "'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "LINE_COMMENT", "STRING_LITERAL", 
		"BOOLEAN_LITERAL", "DECIMAL_LITERAL", "INTEGER_LITERAL", "ID", "WS"
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


	public QLParserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2(\u0108\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3"+
		"!\7!\u00c3\n!\f!\16!\u00c6\13!\3!\3!\3\"\3\"\3\"\7\"\u00cd\n\"\f\"\16"+
		"\"\u00d0\13\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u00dd\n#\3$\6$\u00e0"+
		"\n$\r$\16$\u00e1\3$\3$\6$\u00e6\n$\r$\16$\u00e7\3%\6%\u00eb\n%\r%\16%"+
		"\u00ec\3&\3&\6&\u00f1\n&\r&\16&\u00f2\3\'\6\'\u00f6\n\'\r\'\16\'\u00f7"+
		"\3\'\3\'\3(\3(\3(\5(\u00ff\n(\3)\3)\3)\3)\3)\3)\3*\3*\2\2+\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O\2Q\2S\2\3\2\n\4\2\f\f\17\17\4\2$$^^\3\2\62;\4\2C\\c|\6\2\62"+
		";C\\aac|\5\2\13\f\16\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\u010e"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\3U\3\2\2\2\5Z\3\2\2\2\7\\\3\2\2\2"+
		"\t^\3\2\2\2\13`\3\2\2\2\rb\3\2\2\2\17e\3\2\2\2\21g\3\2\2\2\23i\3\2\2\2"+
		"\25n\3\2\2\2\27p\3\2\2\2\31r\3\2\2\2\33u\3\2\2\2\35x\3\2\2\2\37z\3\2\2"+
		"\2!|\3\2\2\2#~\3\2\2\2%\u0080\3\2\2\2\'\u0082\3\2\2\2)\u0084\3\2\2\2+"+
		"\u0087\3\2\2\2-\u008a\3\2\2\2/\u008d\3\2\2\2\61\u0090\3\2\2\2\63\u0093"+
		"\3\2\2\2\65\u0096\3\2\2\2\67\u009e\3\2\2\29\u00a4\3\2\2\2;\u00ac\3\2\2"+
		"\2=\u00b3\3\2\2\2?\u00b9\3\2\2\2A\u00be\3\2\2\2C\u00c9\3\2\2\2E\u00dc"+
		"\3\2\2\2G\u00df\3\2\2\2I\u00ea\3\2\2\2K\u00ee\3\2\2\2M\u00f5\3\2\2\2O"+
		"\u00fb\3\2\2\2Q\u0100\3\2\2\2S\u0106\3\2\2\2UV\7h\2\2VW\7q\2\2WX\7t\2"+
		"\2XY\7o\2\2Y\4\3\2\2\2Z[\7}\2\2[\6\3\2\2\2\\]\7\177\2\2]\b\3\2\2\2^_\7"+
		"<\2\2_\n\3\2\2\2`a\7=\2\2a\f\3\2\2\2bc\7k\2\2cd\7h\2\2d\16\3\2\2\2ef\7"+
		"*\2\2f\20\3\2\2\2gh\7+\2\2h\22\3\2\2\2ij\7g\2\2jk\7n\2\2kl\7u\2\2lm\7"+
		"g\2\2m\24\3\2\2\2no\7?\2\2o\26\3\2\2\2pq\7#\2\2q\30\3\2\2\2rs\7-\2\2s"+
		"t\7-\2\2t\32\3\2\2\2uv\7/\2\2vw\7/\2\2w\34\3\2\2\2xy\7\61\2\2y\36\3\2"+
		"\2\2z{\7,\2\2{ \3\2\2\2|}\7/\2\2}\"\3\2\2\2~\177\7-\2\2\177$\3\2\2\2\u0080"+
		"\u0081\7@\2\2\u0081&\3\2\2\2\u0082\u0083\7>\2\2\u0083(\3\2\2\2\u0084\u0085"+
		"\7?\2\2\u0085\u0086\7?\2\2\u0086*\3\2\2\2\u0087\u0088\7#\2\2\u0088\u0089"+
		"\7?\2\2\u0089,\3\2\2\2\u008a\u008b\7>\2\2\u008b\u008c\7?\2\2\u008c.\3"+
		"\2\2\2\u008d\u008e\7@\2\2\u008e\u008f\7?\2\2\u008f\60\3\2\2\2\u0090\u0091"+
		"\7(\2\2\u0091\u0092\7(\2\2\u0092\62\3\2\2\2\u0093\u0094\7~\2\2\u0094\u0095"+
		"\7~\2\2\u0095\64\3\2\2\2\u0096\u0097\7d\2\2\u0097\u0098\7q\2\2\u0098\u0099"+
		"\7q\2\2\u0099\u009a\7n\2\2\u009a\u009b\7g\2\2\u009b\u009c\7c\2\2\u009c"+
		"\u009d\7p\2\2\u009d\66\3\2\2\2\u009e\u009f\7h\2\2\u009f\u00a0\7n\2\2\u00a0"+
		"\u00a1\7q\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7v\2\2\u00a38\3\2\2\2\u00a4"+
		"\u00a5\7k\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7v\2\2\u00a7\u00a8\7g\2\2"+
		"\u00a8\u00a9\7i\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7t\2\2\u00ab:\3\2\2"+
		"\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7v\2\2\u00ae\u00af\7t\2\2\u00af\u00b0"+
		"\7k\2\2\u00b0\u00b1\7p\2\2\u00b1\u00b2\7i\2\2\u00b2<\3\2\2\2\u00b3\u00b4"+
		"\7o\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7p\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7{\2\2\u00b8>\3\2\2\2\u00b9\u00ba\7f\2\2\u00ba\u00bb\7c\2\2\u00bb"+
		"\u00bc\7v\2\2\u00bc\u00bd\7g\2\2\u00bd@\3\2\2\2\u00be\u00bf\7\61\2\2\u00bf"+
		"\u00c0\7\61\2\2\u00c0\u00c4\3\2\2\2\u00c1\u00c3\n\2\2\2\u00c2\u00c1\3"+
		"\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\b!\2\2\u00c8B\3\2\2\2\u00c9"+
		"\u00ce\7$\2\2\u00ca\u00cd\5O(\2\u00cb\u00cd\n\3\2\2\u00cc\u00ca\3\2\2"+
		"\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7$\2\2\u00d2"+
		"D\3\2\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7w\2\2\u00d6"+
		"\u00dd\7g\2\2\u00d7\u00d8\7h\2\2\u00d8\u00d9\7c\2\2\u00d9\u00da\7n\2\2"+
		"\u00da\u00db\7u\2\2\u00db\u00dd\7g\2\2\u00dc\u00d3\3\2\2\2\u00dc\u00d7"+
		"\3\2\2\2\u00ddF\3\2\2\2\u00de\u00e0\t\4\2\2\u00df\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e5\7\60\2\2\u00e4\u00e6\t\4\2\2\u00e5\u00e4\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8H\3\2\2\2"+
		"\u00e9\u00eb\t\4\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ea"+
		"\3\2\2\2\u00ec\u00ed\3\2\2\2\u00edJ\3\2\2\2\u00ee\u00f0\t\5\2\2\u00ef"+
		"\u00f1\t\6\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3L\3\2\2\2\u00f4\u00f6\t\7\2\2\u00f5\u00f4"+
		"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"\u00f9\3\2\2\2\u00f9\u00fa\b\'\3\2\u00faN\3\2\2\2\u00fb\u00fe\7^\2\2\u00fc"+
		"\u00ff\t\b\2\2\u00fd\u00ff\5Q)\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2"+
		"\2\u00ffP\3\2\2\2\u0100\u0101\7w\2\2\u0101\u0102\5S*\2\u0102\u0103\5S"+
		"*\2\u0103\u0104\5S*\2\u0104\u0105\5S*\2\u0105R\3\2\2\2\u0106\u0107\t\t"+
		"\2\2\u0107T\3\2\2\2\r\2\u00c4\u00cc\u00ce\u00dc\u00e1\u00e7\u00ec\u00f2"+
		"\u00f7\u00fe\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}