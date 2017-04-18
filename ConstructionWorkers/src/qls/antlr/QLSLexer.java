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
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, STRING=24, 
		Identifier=25, INTEGER=26, HEX=27, WS=28;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "STRING", "Identifier", 
		"INTEGER", "HEX", "WS", "ESC", "HEXDIGIT", "UNICODE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'default'", "'section'", "'question'", 
		"'widget checkbox'", "'widget spinbox'", "'widget slider'", "'widget textbox'", 
		"'widget radio'", "'('", "','", "')'", "'widget dropdown'", "'width:'", 
		"'font:'", "'fontsize:'", "'color:'", "'boolean'", "'integer'", "'string'", 
		"'money'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"STRING", "Identifier", "INTEGER", "HEX", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\36\u0133\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\7\31\u0107\n\31\f\31"+
		"\16\31\u010a\13\31\3\31\3\31\3\32\6\32\u010f\n\32\r\32\16\32\u0110\3\33"+
		"\6\33\u0114\n\33\r\33\16\33\u0115\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\35\6\35\u0121\n\35\r\35\16\35\u0122\3\35\3\35\3\36\3\36\3\36\5\36"+
		"\u012a\n\36\3\37\3\37\3 \3 \3 \3 \3 \3 \2\2!\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\2=\2?\2\3\2\b\4\2$$^^\4\2C\\"+
		"c|\3\2\62;\5\2\13\f\16\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\u0135"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3A\3"+
		"\2\2\2\5L\3\2\2\2\7N\3\2\2\2\tP\3\2\2\2\13X\3\2\2\2\r`\3\2\2\2\17i\3\2"+
		"\2\2\21y\3\2\2\2\23\u0088\3\2\2\2\25\u0096\3\2\2\2\27\u00a5\3\2\2\2\31"+
		"\u00b2\3\2\2\2\33\u00b4\3\2\2\2\35\u00b6\3\2\2\2\37\u00b8\3\2\2\2!\u00c8"+
		"\3\2\2\2#\u00cf\3\2\2\2%\u00d5\3\2\2\2\'\u00df\3\2\2\2)\u00e6\3\2\2\2"+
		"+\u00ee\3\2\2\2-\u00f6\3\2\2\2/\u00fd\3\2\2\2\61\u0103\3\2\2\2\63\u010e"+
		"\3\2\2\2\65\u0113\3\2\2\2\67\u0117\3\2\2\29\u0120\3\2\2\2;\u0126\3\2\2"+
		"\2=\u012b\3\2\2\2?\u012d\3\2\2\2AB\7u\2\2BC\7v\2\2CD\7{\2\2DE\7n\2\2E"+
		"F\7g\2\2FG\7u\2\2GH\7j\2\2HI\7g\2\2IJ\7g\2\2JK\7v\2\2K\4\3\2\2\2LM\7}"+
		"\2\2M\6\3\2\2\2NO\7\177\2\2O\b\3\2\2\2PQ\7f\2\2QR\7g\2\2RS\7h\2\2ST\7"+
		"c\2\2TU\7w\2\2UV\7n\2\2VW\7v\2\2W\n\3\2\2\2XY\7u\2\2YZ\7g\2\2Z[\7e\2\2"+
		"[\\\7v\2\2\\]\7k\2\2]^\7q\2\2^_\7p\2\2_\f\3\2\2\2`a\7s\2\2ab\7w\2\2bc"+
		"\7g\2\2cd\7u\2\2de\7v\2\2ef\7k\2\2fg\7q\2\2gh\7p\2\2h\16\3\2\2\2ij\7y"+
		"\2\2jk\7k\2\2kl\7f\2\2lm\7i\2\2mn\7g\2\2no\7v\2\2op\7\"\2\2pq\7e\2\2q"+
		"r\7j\2\2rs\7g\2\2st\7e\2\2tu\7m\2\2uv\7d\2\2vw\7q\2\2wx\7z\2\2x\20\3\2"+
		"\2\2yz\7y\2\2z{\7k\2\2{|\7f\2\2|}\7i\2\2}~\7g\2\2~\177\7v\2\2\177\u0080"+
		"\7\"\2\2\u0080\u0081\7u\2\2\u0081\u0082\7r\2\2\u0082\u0083\7k\2\2\u0083"+
		"\u0084\7p\2\2\u0084\u0085\7d\2\2\u0085\u0086\7q\2\2\u0086\u0087\7z\2\2"+
		"\u0087\22\3\2\2\2\u0088\u0089\7y\2\2\u0089\u008a\7k\2\2\u008a\u008b\7"+
		"f\2\2\u008b\u008c\7i\2\2\u008c\u008d\7g\2\2\u008d\u008e\7v\2\2\u008e\u008f"+
		"\7\"\2\2\u008f\u0090\7u\2\2\u0090\u0091\7n\2\2\u0091\u0092\7k\2\2\u0092"+
		"\u0093\7f\2\2\u0093\u0094\7g\2\2\u0094\u0095\7t\2\2\u0095\24\3\2\2\2\u0096"+
		"\u0097\7y\2\2\u0097\u0098\7k\2\2\u0098\u0099\7f\2\2\u0099\u009a\7i\2\2"+
		"\u009a\u009b\7g\2\2\u009b\u009c\7v\2\2\u009c\u009d\7\"\2\2\u009d\u009e"+
		"\7v\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7z\2\2\u00a0\u00a1\7v\2\2\u00a1"+
		"\u00a2\7d\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4\7z\2\2\u00a4\26\3\2\2\2\u00a5"+
		"\u00a6\7y\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7f\2\2\u00a8\u00a9\7i\2\2"+
		"\u00a9\u00aa\7g\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7\"\2\2\u00ac\u00ad"+
		"\7t\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af\7f\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7q\2\2\u00b1\30\3\2\2\2\u00b2\u00b3\7*\2\2\u00b3\32\3\2\2\2\u00b4"+
		"\u00b5\7.\2\2\u00b5\34\3\2\2\2\u00b6\u00b7\7+\2\2\u00b7\36\3\2\2\2\u00b8"+
		"\u00b9\7y\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7f\2\2\u00bb\u00bc\7i\2\2"+
		"\u00bc\u00bd\7g\2\2\u00bd\u00be\7v\2\2\u00be\u00bf\7\"\2\2\u00bf\u00c0"+
		"\7f\2\2\u00c0\u00c1\7t\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7r\2\2\u00c3"+
		"\u00c4\7f\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7y\2\2\u00c6\u00c7\7p\2\2"+
		"\u00c7 \3\2\2\2\u00c8\u00c9\7y\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7f\2"+
		"\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7j\2\2\u00cd\u00ce\7<\2\2\u00ce\"\3"+
		"\2\2\2\u00cf\u00d0\7h\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7p\2\2\u00d2"+
		"\u00d3\7v\2\2\u00d3\u00d4\7<\2\2\u00d4$\3\2\2\2\u00d5\u00d6\7h\2\2\u00d6"+
		"\u00d7\7q\2\2\u00d7\u00d8\7p\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7u\2\2"+
		"\u00da\u00db\7k\2\2\u00db\u00dc\7|\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de"+
		"\7<\2\2\u00de&\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2"+
		"\7n\2\2\u00e2\u00e3\7q\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7<\2\2\u00e5"+
		"(\3\2\2\2\u00e6\u00e7\7d\2\2\u00e7\u00e8\7q\2\2\u00e8\u00e9\7q\2\2\u00e9"+
		"\u00ea\7n\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7p\2\2"+
		"\u00ed*\3\2\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0\7p\2\2\u00f0\u00f1\7v\2"+
		"\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7i\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5"+
		"\7t\2\2\u00f5,\3\2\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9"+
		"\7t\2\2\u00f9\u00fa\7k\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7i\2\2\u00fc"+
		".\3\2\2\2\u00fd\u00fe\7o\2\2\u00fe\u00ff\7q\2\2\u00ff\u0100\7p\2\2\u0100"+
		"\u0101\7g\2\2\u0101\u0102\7{\2\2\u0102\60\3\2\2\2\u0103\u0108\7$\2\2\u0104"+
		"\u0107\5;\36\2\u0105\u0107\n\2\2\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2"+
		"\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\7$\2\2\u010c\62\3\2\2\2"+
		"\u010d\u010f\t\3\2\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e"+
		"\3\2\2\2\u0110\u0111\3\2\2\2\u0111\64\3\2\2\2\u0112\u0114\t\4\2\2\u0113"+
		"\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\66\3\2\2\2\u0117\u0118\7%\2\2\u0118\u0119\5=\37\2\u0119\u011a"+
		"\5=\37\2\u011a\u011b\5=\37\2\u011b\u011c\5=\37\2\u011c\u011d\5=\37\2\u011d"+
		"\u011e\5=\37\2\u011e8\3\2\2\2\u011f\u0121\t\5\2\2\u0120\u011f\3\2\2\2"+
		"\u0121\u0122\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124"+
		"\3\2\2\2\u0124\u0125\b\35\2\2\u0125:\3\2\2\2\u0126\u0129\7^\2\2\u0127"+
		"\u012a\t\6\2\2\u0128\u012a\5? \2\u0129\u0127\3\2\2\2\u0129\u0128\3\2\2"+
		"\2\u012a<\3\2\2\2\u012b\u012c\t\7\2\2\u012c>\3\2\2\2\u012d\u012e\7w\2"+
		"\2\u012e\u012f\5=\37\2\u012f\u0130\5=\37\2\u0130\u0131\5=\37\2\u0131\u0132"+
		"\5=\37\2\u0132@\3\2\2\2\t\2\u0106\u0108\u0110\u0115\u0122\u0129\3\2\3"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}