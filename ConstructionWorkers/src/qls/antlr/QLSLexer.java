// Generated from C:/Users/LGGX/Documents/SC2017/ConstructionWorkers/src/qls\QLS.g4 by ANTLR 4.6
package qls.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, STRING=23, Identifier=24, 
		NUMBER=25, HEX=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "STRING", "Identifier", "NUMBER", 
		"HEX", "WS", "ESC", "HEXDIGIT", "UNICODE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'default'", "'section'", "'question'", 
		"'widget checkbox'", "'widget spinbox'", "'widget slider'", "'widget textbox'", 
		"'widget radio'", "'('", "','", "')'", "'widget dropdown'", "'width:'", 
		"'font:'", "'fontsize:'", "'color:'", "'boolean'", "'integer'", "'string'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "STRING", 
		"Identifier", "NUMBER", "HEX", "WS"
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


	public QLSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u012b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\7\30\u00ff\n\30\f\30\16\30\u0102\13\30\3\30\3\30\3\31\6\31"+
		"\u0107\n\31\r\31\16\31\u0108\3\32\6\32\u010c\n\32\r\32\16\32\u010d\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\6\34\u0119\n\34\r\34\16\34\u011a"+
		"\3\34\3\34\3\35\3\35\3\35\5\35\u0122\n\35\3\36\3\36\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\2\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\2;\2=\2\3\2\b\4\2$$^^\4\2C\\c|\3\2\62;\5\2\13\f\16\17\"\"\n"+
		"\2$$\61\61^^ddhhppttvv\5\2\62;CHch\u012d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\3?\3\2\2\2\5J\3\2\2\2\7L\3\2\2\2\tN\3\2\2\2\13"+
		"V\3\2\2\2\r^\3\2\2\2\17g\3\2\2\2\21w\3\2\2\2\23\u0086\3\2\2\2\25\u0094"+
		"\3\2\2\2\27\u00a3\3\2\2\2\31\u00b0\3\2\2\2\33\u00b2\3\2\2\2\35\u00b4\3"+
		"\2\2\2\37\u00b6\3\2\2\2!\u00c6\3\2\2\2#\u00cd\3\2\2\2%\u00d3\3\2\2\2\'"+
		"\u00dd\3\2\2\2)\u00e4\3\2\2\2+\u00ec\3\2\2\2-\u00f4\3\2\2\2/\u00fb\3\2"+
		"\2\2\61\u0106\3\2\2\2\63\u010b\3\2\2\2\65\u010f\3\2\2\2\67\u0118\3\2\2"+
		"\29\u011e\3\2\2\2;\u0123\3\2\2\2=\u0125\3\2\2\2?@\7u\2\2@A\7v\2\2AB\7"+
		"{\2\2BC\7n\2\2CD\7g\2\2DE\7u\2\2EF\7j\2\2FG\7g\2\2GH\7g\2\2HI\7v\2\2I"+
		"\4\3\2\2\2JK\7}\2\2K\6\3\2\2\2LM\7\177\2\2M\b\3\2\2\2NO\7f\2\2OP\7g\2"+
		"\2PQ\7h\2\2QR\7c\2\2RS\7w\2\2ST\7n\2\2TU\7v\2\2U\n\3\2\2\2VW\7u\2\2WX"+
		"\7g\2\2XY\7e\2\2YZ\7v\2\2Z[\7k\2\2[\\\7q\2\2\\]\7p\2\2]\f\3\2\2\2^_\7"+
		"s\2\2_`\7w\2\2`a\7g\2\2ab\7u\2\2bc\7v\2\2cd\7k\2\2de\7q\2\2ef\7p\2\2f"+
		"\16\3\2\2\2gh\7y\2\2hi\7k\2\2ij\7f\2\2jk\7i\2\2kl\7g\2\2lm\7v\2\2mn\7"+
		"\"\2\2no\7e\2\2op\7j\2\2pq\7g\2\2qr\7e\2\2rs\7m\2\2st\7d\2\2tu\7q\2\2"+
		"uv\7z\2\2v\20\3\2\2\2wx\7y\2\2xy\7k\2\2yz\7f\2\2z{\7i\2\2{|\7g\2\2|}\7"+
		"v\2\2}~\7\"\2\2~\177\7u\2\2\177\u0080\7r\2\2\u0080\u0081\7k\2\2\u0081"+
		"\u0082\7p\2\2\u0082\u0083\7d\2\2\u0083\u0084\7q\2\2\u0084\u0085\7z\2\2"+
		"\u0085\22\3\2\2\2\u0086\u0087\7y\2\2\u0087\u0088\7k\2\2\u0088\u0089\7"+
		"f\2\2\u0089\u008a\7i\2\2\u008a\u008b\7g\2\2\u008b\u008c\7v\2\2\u008c\u008d"+
		"\7\"\2\2\u008d\u008e\7u\2\2\u008e\u008f\7n\2\2\u008f\u0090\7k\2\2\u0090"+
		"\u0091\7f\2\2\u0091\u0092\7g\2\2\u0092\u0093\7t\2\2\u0093\24\3\2\2\2\u0094"+
		"\u0095\7y\2\2\u0095\u0096\7k\2\2\u0096\u0097\7f\2\2\u0097\u0098\7i\2\2"+
		"\u0098\u0099\7g\2\2\u0099\u009a\7v\2\2\u009a\u009b\7\"\2\2\u009b\u009c"+
		"\7v\2\2\u009c\u009d\7g\2\2\u009d\u009e\7z\2\2\u009e\u009f\7v\2\2\u009f"+
		"\u00a0\7d\2\2\u00a0\u00a1\7q\2\2\u00a1\u00a2\7z\2\2\u00a2\26\3\2\2\2\u00a3"+
		"\u00a4\7y\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7f\2\2\u00a6\u00a7\7i\2\2"+
		"\u00a7\u00a8\7g\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7\"\2\2\u00aa\u00ab"+
		"\7t\2\2\u00ab\u00ac\7c\2\2\u00ac\u00ad\7f\2\2\u00ad\u00ae\7k\2\2\u00ae"+
		"\u00af\7q\2\2\u00af\30\3\2\2\2\u00b0\u00b1\7*\2\2\u00b1\32\3\2\2\2\u00b2"+
		"\u00b3\7.\2\2\u00b3\34\3\2\2\2\u00b4\u00b5\7+\2\2\u00b5\36\3\2\2\2\u00b6"+
		"\u00b7\7y\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7i\2\2"+
		"\u00ba\u00bb\7g\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7\"\2\2\u00bd\u00be"+
		"\7f\2\2\u00be\u00bf\7t\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7r\2\2\u00c1"+
		"\u00c2\7f\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7y\2\2\u00c4\u00c5\7p\2\2"+
		"\u00c5 \3\2\2\2\u00c6\u00c7\7y\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7f\2"+
		"\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7j\2\2\u00cb\u00cc\7<\2\2\u00cc\"\3"+
		"\2\2\2\u00cd\u00ce\7h\2\2\u00ce\u00cf\7q\2\2\u00cf\u00d0\7p\2\2\u00d0"+
		"\u00d1\7v\2\2\u00d1\u00d2\7<\2\2\u00d2$\3\2\2\2\u00d3\u00d4\7h\2\2\u00d4"+
		"\u00d5\7q\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7u\2\2"+
		"\u00d8\u00d9\7k\2\2\u00d9\u00da\7|\2\2\u00da\u00db\7g\2\2\u00db\u00dc"+
		"\7<\2\2\u00dc&\3\2\2\2\u00dd\u00de\7e\2\2\u00de\u00df\7q\2\2\u00df\u00e0"+
		"\7n\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7<\2\2\u00e3"+
		"(\3\2\2\2\u00e4\u00e5\7d\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7q\2\2\u00e7"+
		"\u00e8\7n\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb\7p\2\2"+
		"\u00eb*\3\2\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee\7p\2\2\u00ee\u00ef\7v\2"+
		"\2\u00ef\u00f0\7g\2\2\u00f0\u00f1\7i\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3"+
		"\7t\2\2\u00f3,\3\2\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f6\7v\2\2\u00f6\u00f7"+
		"\7t\2\2\u00f7\u00f8\7k\2\2\u00f8\u00f9\7p\2\2\u00f9\u00fa\7i\2\2\u00fa"+
		".\3\2\2\2\u00fb\u0100\7$\2\2\u00fc\u00ff\59\35\2\u00fd\u00ff\n\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2"+
		"\2\2\u0100\u0101\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2\2\2\u0103"+
		"\u0104\7$\2\2\u0104\60\3\2\2\2\u0105\u0107\t\3\2\2\u0106\u0105\3\2\2\2"+
		"\u0107\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\62"+
		"\3\2\2\2\u010a\u010c\t\4\2\2\u010b\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\64\3\2\2\2\u010f\u0110\7%\2\2"+
		"\u0110\u0111\5;\36\2\u0111\u0112\5;\36\2\u0112\u0113\5;\36\2\u0113\u0114"+
		"\5;\36\2\u0114\u0115\5;\36\2\u0115\u0116\5;\36\2\u0116\66\3\2\2\2\u0117"+
		"\u0119\t\5\2\2\u0118\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u0118\3\2"+
		"\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\b\34\2\2\u011d"+
		"8\3\2\2\2\u011e\u0121\7^\2\2\u011f\u0122\t\6\2\2\u0120\u0122\5=\37\2\u0121"+
		"\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122:\3\2\2\2\u0123\u0124\t\7\2\2"+
		"\u0124<\3\2\2\2\u0125\u0126\7w\2\2\u0126\u0127\5;\36\2\u0127\u0128\5;"+
		"\36\2\u0128\u0129\5;\36\2\u0129\u012a\5;\36\2\u012a>\3\2\2\2\t\2\u00fe"+
		"\u0100\u0108\u010d\u011a\u0121\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}