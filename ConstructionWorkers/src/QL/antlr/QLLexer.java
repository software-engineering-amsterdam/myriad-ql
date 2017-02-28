// Generated from C:/Users/vince/Documents/Documenten/School/Jaar 5 (Master Software Engineering)/Software Construction/myriad-ql/ConstructionWorkers/src\QL.QL.g4 by ANTLR 4.6
package ql.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, BOOL=26, STRING=27, INT=28, MONEY=29, IDENTIFIER=30, LINE_COMMENT=31, 
		MULTI_LINE_COMMENT=32, WS=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"BOOL", "STRING", "INT", "MONEY", "IDENTIFIER", "LINE_COMMENT", "MULTI_LINE_COMMENT", 
		"WS", "ESC", "UNICODE", "HEX"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'{'", "'}'", "'('", "')'", "';'", "':'", "'if'", "'boolean'", 
		"'string'", "'int'", "'money'", "'+'", "'-'", "'!'", "'*'", "'/'", "'>'", 
		"'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BOOL", "STRING", "INT", "MONEY", "IDENTIFIER", "LINE_COMMENT", 
		"MULTI_LINE_COMMENT", "WS"
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
	public String getGrammarFileName() { return "ql/QL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00f2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3"+
		"\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\5\33\u00a2\n\33\3\34\3\34\3\34\7\34\u00a7\n\34\f\34\16\34\u00aa\13"+
		"\34\3\34\3\34\3\35\6\35\u00af\n\35\r\35\16\35\u00b0\3\36\6\36\u00b4\n"+
		"\36\r\36\16\36\u00b5\3\36\3\36\3\36\3\36\3\37\6\37\u00bd\n\37\r\37\16"+
		"\37\u00be\3 \3 \3 \3 \7 \u00c5\n \f \16 \u00c8\13 \3 \5 \u00cb\n \3 \3"+
		" \3 \3 \3!\3!\3!\3!\7!\u00d5\n!\f!\16!\u00d8\13!\3!\3!\3!\3!\3!\3\"\6"+
		"\"\u00e0\n\"\r\"\16\"\u00e1\3\"\3\"\3#\3#\3#\5#\u00e9\n#\3$\3$\3$\3$\3"+
		"$\3$\3%\3%\3\u00d6\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E\2G\2I\2\3\2\t\4\2$$^^\3\2\62;\4\2C\\c|"+
		"\4\2\f\f\17\17\5\2\13\f\16\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch"+
		"\u00f9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3K\3\2\2\2\5"+
		"P\3\2\2\2\7R\3\2\2\2\tT\3\2\2\2\13V\3\2\2\2\rX\3\2\2\2\17Z\3\2\2\2\21"+
		"\\\3\2\2\2\23_\3\2\2\2\25g\3\2\2\2\27n\3\2\2\2\31r\3\2\2\2\33x\3\2\2\2"+
		"\35z\3\2\2\2\37|\3\2\2\2!~\3\2\2\2#\u0080\3\2\2\2%\u0082\3\2\2\2\'\u0084"+
		"\3\2\2\2)\u0087\3\2\2\2+\u0089\3\2\2\2-\u008c\3\2\2\2/\u008f\3\2\2\2\61"+
		"\u0092\3\2\2\2\63\u0095\3\2\2\2\65\u00a1\3\2\2\2\67\u00a3\3\2\2\29\u00ae"+
		"\3\2\2\2;\u00b3\3\2\2\2=\u00bc\3\2\2\2?\u00c0\3\2\2\2A\u00d0\3\2\2\2C"+
		"\u00df\3\2\2\2E\u00e5\3\2\2\2G\u00ea\3\2\2\2I\u00f0\3\2\2\2KL\7h\2\2L"+
		"M\7q\2\2MN\7t\2\2NO\7o\2\2O\4\3\2\2\2PQ\7}\2\2Q\6\3\2\2\2RS\7\177\2\2"+
		"S\b\3\2\2\2TU\7*\2\2U\n\3\2\2\2VW\7+\2\2W\f\3\2\2\2XY\7=\2\2Y\16\3\2\2"+
		"\2Z[\7<\2\2[\20\3\2\2\2\\]\7k\2\2]^\7h\2\2^\22\3\2\2\2_`\7d\2\2`a\7q\2"+
		"\2ab\7q\2\2bc\7n\2\2cd\7g\2\2de\7c\2\2ef\7p\2\2f\24\3\2\2\2gh\7u\2\2h"+
		"i\7v\2\2ij\7t\2\2jk\7k\2\2kl\7p\2\2lm\7i\2\2m\26\3\2\2\2no\7k\2\2op\7"+
		"p\2\2pq\7v\2\2q\30\3\2\2\2rs\7o\2\2st\7q\2\2tu\7p\2\2uv\7g\2\2vw\7{\2"+
		"\2w\32\3\2\2\2xy\7-\2\2y\34\3\2\2\2z{\7/\2\2{\36\3\2\2\2|}\7#\2\2} \3"+
		"\2\2\2~\177\7,\2\2\177\"\3\2\2\2\u0080\u0081\7\61\2\2\u0081$\3\2\2\2\u0082"+
		"\u0083\7@\2\2\u0083&\3\2\2\2\u0084\u0085\7@\2\2\u0085\u0086\7?\2\2\u0086"+
		"(\3\2\2\2\u0087\u0088\7>\2\2\u0088*\3\2\2\2\u0089\u008a\7>\2\2\u008a\u008b"+
		"\7?\2\2\u008b,\3\2\2\2\u008c\u008d\7?\2\2\u008d\u008e\7?\2\2\u008e.\3"+
		"\2\2\2\u008f\u0090\7#\2\2\u0090\u0091\7?\2\2\u0091\60\3\2\2\2\u0092\u0093"+
		"\7(\2\2\u0093\u0094\7(\2\2\u0094\62\3\2\2\2\u0095\u0096\7~\2\2\u0096\u0097"+
		"\7~\2\2\u0097\64\3\2\2\2\u0098\u0099\7v\2\2\u0099\u009a\7t\2\2\u009a\u009b"+
		"\7w\2\2\u009b\u00a2\7g\2\2\u009c\u009d\7h\2\2\u009d\u009e\7c\2\2\u009e"+
		"\u009f\7n\2\2\u009f\u00a0\7u\2\2\u00a0\u00a2\7g\2\2\u00a1\u0098\3\2\2"+
		"\2\u00a1\u009c\3\2\2\2\u00a2\66\3\2\2\2\u00a3\u00a8\7$\2\2\u00a4\u00a7"+
		"\5E#\2\u00a5\u00a7\n\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2"+
		"\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7$\2\2\u00ac8\3\2\2\2\u00ad\u00af"+
		"\t\3\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1:\3\2\2\2\u00b2\u00b4\t\3\2\2\u00b3\u00b2\3\2\2\2"+
		"\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7\u00b8\7\60\2\2\u00b8\u00b9\t\3\2\2\u00b9\u00ba\t\3\2\2"+
		"\u00ba<\3\2\2\2\u00bb\u00bd\t\4\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3"+
		"\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf>\3\2\2\2\u00c0\u00c1"+
		"\7\61\2\2\u00c1\u00c2\7\61\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\n\5\2\2"+
		"\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7"+
		"\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00cb\7\17\2\2"+
		"\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd"+
		"\7\f\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\b \2\2\u00cf@\3\2\2\2\u00d0\u00d1"+
		"\7\61\2\2\u00d1\u00d2\7,\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d5\13\2\2\2"+
		"\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\7,\2\2\u00da"+
		"\u00db\7\61\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\b!\2\2\u00ddB\3\2\2\2"+
		"\u00de\u00e0\t\6\2\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df"+
		"\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\b\"\2\2\u00e4"+
		"D\3\2\2\2\u00e5\u00e8\7^\2\2\u00e6\u00e9\t\7\2\2\u00e7\u00e9\5G$\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9F\3\2\2\2\u00ea\u00eb\7w\2\2\u00eb"+
		"\u00ec\5I%\2\u00ec\u00ed\5I%\2\u00ed\u00ee\5I%\2\u00ee\u00ef\5I%\2\u00ef"+
		"H\3\2\2\2\u00f0\u00f1\t\b\2\2\u00f1J\3\2\2\2\16\2\u00a1\u00a6\u00a8\u00b0"+
		"\u00b5\u00be\u00c6\u00ca\u00d6\u00e1\u00e8\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}