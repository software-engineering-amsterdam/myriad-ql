// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.antlr;
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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, BOOLEAN_LITERAL=26, MONEY_LITERAL=27, INTEGER_LITERAL=28, ID_LITERAL=29, 
		STRING_LITERAL=30, WS=31, COMMENT=32, LINE_COMMENT=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"BOOLEAN_LITERAL", "MONEY_LITERAL", "INTEGER_LITERAL", "ID_LITERAL", "STRING_LITERAL", 
		"WS", "COMMENT", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "'endform'", "'='", "'if'", "'('", "')'", "'else'", "'endif'", 
		"'!'", "'*'", "'/'", "'+'", "'-'", "'<'", "'<='", "'>'", "'>='", "'=='", 
		"'!='", "'&&'", "'||'", "'BOOLEAN'", "'INTEGER'", "'MONEY'", "'STRING'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BOOLEAN_LITERAL", "MONEY_LITERAL", "INTEGER_LITERAL", "ID_LITERAL", 
		"STRING_LITERAL", "WS", "COMMENT", "LINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00f2\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5"+
		"\33\u00ad\n\33\3\34\6\34\u00b0\n\34\r\34\16\34\u00b1\3\34\3\34\6\34\u00b6"+
		"\n\34\r\34\16\34\u00b7\3\35\6\35\u00bb\n\35\r\35\16\35\u00bc\3\36\3\36"+
		"\7\36\u00c1\n\36\f\36\16\36\u00c4\13\36\3\37\3\37\3\37\3\37\7\37\u00ca"+
		"\n\37\f\37\16\37\u00cd\13\37\3\37\3\37\3 \6 \u00d2\n \r \16 \u00d3\3 "+
		"\3 \3!\3!\3!\3!\7!\u00dc\n!\f!\16!\u00df\13!\3!\3!\3!\3!\3!\3\"\3\"\3"+
		"\"\3\"\7\"\u00ea\n\"\f\"\16\"\u00ed\13\"\3\"\3\"\3\"\3\"\5\u00cb\u00dd"+
		"\u00eb\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#\3\2\6\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\""+
		"\4\2\f\f\17\17\u00fb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\3E\3\2\2\2\5J\3\2\2\2\7R\3\2\2\2\tT\3\2\2\2\13W\3\2\2\2\rY\3\2\2\2\17"+
		"[\3\2\2\2\21`\3\2\2\2\23f\3\2\2\2\25h\3\2\2\2\27j\3\2\2\2\31l\3\2\2\2"+
		"\33n\3\2\2\2\35p\3\2\2\2\37r\3\2\2\2!u\3\2\2\2#w\3\2\2\2%z\3\2\2\2\'}"+
		"\3\2\2\2)\u0080\3\2\2\2+\u0083\3\2\2\2-\u0086\3\2\2\2/\u008e\3\2\2\2\61"+
		"\u0096\3\2\2\2\63\u009c\3\2\2\2\65\u00ac\3\2\2\2\67\u00af\3\2\2\29\u00ba"+
		"\3\2\2\2;\u00be\3\2\2\2=\u00c5\3\2\2\2?\u00d1\3\2\2\2A\u00d7\3\2\2\2C"+
		"\u00e5\3\2\2\2EF\7h\2\2FG\7q\2\2GH\7t\2\2HI\7o\2\2I\4\3\2\2\2JK\7g\2\2"+
		"KL\7p\2\2LM\7f\2\2MN\7h\2\2NO\7q\2\2OP\7t\2\2PQ\7o\2\2Q\6\3\2\2\2RS\7"+
		"?\2\2S\b\3\2\2\2TU\7k\2\2UV\7h\2\2V\n\3\2\2\2WX\7*\2\2X\f\3\2\2\2YZ\7"+
		"+\2\2Z\16\3\2\2\2[\\\7g\2\2\\]\7n\2\2]^\7u\2\2^_\7g\2\2_\20\3\2\2\2`a"+
		"\7g\2\2ab\7p\2\2bc\7f\2\2cd\7k\2\2de\7h\2\2e\22\3\2\2\2fg\7#\2\2g\24\3"+
		"\2\2\2hi\7,\2\2i\26\3\2\2\2jk\7\61\2\2k\30\3\2\2\2lm\7-\2\2m\32\3\2\2"+
		"\2no\7/\2\2o\34\3\2\2\2pq\7>\2\2q\36\3\2\2\2rs\7>\2\2st\7?\2\2t \3\2\2"+
		"\2uv\7@\2\2v\"\3\2\2\2wx\7@\2\2xy\7?\2\2y$\3\2\2\2z{\7?\2\2{|\7?\2\2|"+
		"&\3\2\2\2}~\7#\2\2~\177\7?\2\2\177(\3\2\2\2\u0080\u0081\7(\2\2\u0081\u0082"+
		"\7(\2\2\u0082*\3\2\2\2\u0083\u0084\7~\2\2\u0084\u0085\7~\2\2\u0085,\3"+
		"\2\2\2\u0086\u0087\7D\2\2\u0087\u0088\7Q\2\2\u0088\u0089\7Q\2\2\u0089"+
		"\u008a\7N\2\2\u008a\u008b\7G\2\2\u008b\u008c\7C\2\2\u008c\u008d\7P\2\2"+
		"\u008d.\3\2\2\2\u008e\u008f\7K\2\2\u008f\u0090\7P\2\2\u0090\u0091\7V\2"+
		"\2\u0091\u0092\7G\2\2\u0092\u0093\7I\2\2\u0093\u0094\7G\2\2\u0094\u0095"+
		"\7T\2\2\u0095\60\3\2\2\2\u0096\u0097\7O\2\2\u0097\u0098\7Q\2\2\u0098\u0099"+
		"\7P\2\2\u0099\u009a\7G\2\2\u009a\u009b\7[\2\2\u009b\62\3\2\2\2\u009c\u009d"+
		"\7U\2\2\u009d\u009e\7V\2\2\u009e\u009f\7T\2\2\u009f\u00a0\7K\2\2\u00a0"+
		"\u00a1\7P\2\2\u00a1\u00a2\7I\2\2\u00a2\64\3\2\2\2\u00a3\u00a4\7v\2\2\u00a4"+
		"\u00a5\7t\2\2\u00a5\u00a6\7w\2\2\u00a6\u00ad\7g\2\2\u00a7\u00a8\7h\2\2"+
		"\u00a8\u00a9\7c\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ad"+
		"\7g\2\2\u00ac\u00a3\3\2\2\2\u00ac\u00a7\3\2\2\2\u00ad\66\3\2\2\2\u00ae"+
		"\u00b0\4\62;\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\7.\2\2\u00b4"+
		"\u00b6\4\62;\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b88\3\2\2\2\u00b9\u00bb\4\62;\2\u00ba\u00b9"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		":\3\2\2\2\u00be\u00c2\t\2\2\2\u00bf\u00c1\t\3\2\2\u00c0\u00bf\3\2\2\2"+
		"\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3<\3"+
		"\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00cb\7$\2\2\u00c6\u00c7\7^\2\2\u00c7"+
		"\u00ca\7$\2\2\u00c8\u00ca\13\2\2\2\u00c9\u00c6\3\2\2\2\u00c9\u00c8\3\2"+
		"\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc"+
		"\u00ce\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00cf\7$\2\2\u00cf>\3\2\2\2\u00d0"+
		"\u00d2\t\4\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2"+
		"\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\b \2\2\u00d6"+
		"@\3\2\2\2\u00d7\u00d8\7\61\2\2\u00d8\u00d9\7,\2\2\u00d9\u00dd\3\2\2\2"+
		"\u00da\u00dc\13\2\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00de"+
		"\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0"+
		"\u00e1\7,\2\2\u00e1\u00e2\7\61\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\b!"+
		"\2\2\u00e4B\3\2\2\2\u00e5\u00e6\7\61\2\2\u00e6\u00e7\7\61\2\2\u00e7\u00eb"+
		"\3\2\2\2\u00e8\u00ea\13\2\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2"+
		"\u00eb\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ee\u00ef\t\5\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\b\"\2\2\u00f1"+
		"D\3\2\2\2\r\2\u00ac\u00b1\u00b7\u00bc\u00c2\u00c9\u00cb\u00d3\u00dd\u00eb"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}