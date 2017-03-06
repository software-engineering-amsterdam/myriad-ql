// Generated from Questionnaires\QLS\Grammar\QLS.g4 by ANTLR 4.6
#pragma warning disable 3021
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
		T__9=10, Whitespace=11, MultiLineComment=12, SingleLineComment=13, HexDigit=14, 
		LiteralValue=15, Type=16, Identifier=17, Property=18, StringLiteral=19, 
		NumberLiteral=20, ColorLiteral=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "Whitespace", "MultiLineComment", "SingleLineComment", "HexDigit", 
		"LiteralValue", "Type", "Identifier", "Property", "StringLiteral", "NumberLiteral", 
		"ColorLiteral"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'\"'", "'widget'", 
		"'question'", "'default'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "Whitespace", 
		"MultiLineComment", "SingleLineComment", "HexDigit", "LiteralValue", "Type", 
		"Identifier", "Property", "StringLiteral", "NumberLiteral", "ColorLiteral"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u00fd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\6\f"+
		"g\n\f\r\f\16\fh\3\f\3\f\3\r\3\r\3\r\3\r\7\rq\n\r\f\r\16\rt\13\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\177\n\16\f\16\16\16\u0082\13\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u009f\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00c7"+
		"\n\21\3\22\3\22\7\22\u00cb\n\22\f\22\16\22\u00ce\13\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u00e6\n\23\3\24\3\24\7\24\u00ea\n\24\f"+
		"\24\16\24\u00ed\13\24\3\24\3\24\3\25\6\25\u00f2\n\25\r\25\16\25\u00f3"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3r\2\27\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27\3\2\7\5\2\13\f\16\17\"\"\4\2\f\f\17\17\5\2C\\aac|\6\2\62;C\\"+
		"aac|\3\2$$\u010d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3"+
		"\2\2\2\58\3\2\2\2\7:\3\2\2\2\t<\3\2\2\2\13A\3\2\2\2\rI\3\2\2\2\17K\3\2"+
		"\2\2\21R\3\2\2\2\23[\3\2\2\2\25c\3\2\2\2\27f\3\2\2\2\31l\3\2\2\2\33z\3"+
		"\2\2\2\35\u0085\3\2\2\2\37\u009e\3\2\2\2!\u00c6\3\2\2\2#\u00c8\3\2\2\2"+
		"%\u00e5\3\2\2\2\'\u00e7\3\2\2\2)\u00f1\3\2\2\2+\u00f5\3\2\2\2-.\7u\2\2"+
		"./\7v\2\2/\60\7{\2\2\60\61\7n\2\2\61\62\7g\2\2\62\63\7u\2\2\63\64\7j\2"+
		"\2\64\65\7g\2\2\65\66\7g\2\2\66\67\7v\2\2\67\4\3\2\2\289\7}\2\29\6\3\2"+
		"\2\2:;\7\177\2\2;\b\3\2\2\2<=\7r\2\2=>\7c\2\2>?\7i\2\2?@\7g\2\2@\n\3\2"+
		"\2\2AB\7u\2\2BC\7g\2\2CD\7e\2\2DE\7v\2\2EF\7k\2\2FG\7q\2\2GH\7p\2\2H\f"+
		"\3\2\2\2IJ\7$\2\2J\16\3\2\2\2KL\7y\2\2LM\7k\2\2MN\7f\2\2NO\7i\2\2OP\7"+
		"g\2\2PQ\7v\2\2Q\20\3\2\2\2RS\7s\2\2ST\7w\2\2TU\7g\2\2UV\7u\2\2VW\7v\2"+
		"\2WX\7k\2\2XY\7q\2\2YZ\7p\2\2Z\22\3\2\2\2[\\\7f\2\2\\]\7g\2\2]^\7h\2\2"+
		"^_\7c\2\2_`\7w\2\2`a\7n\2\2ab\7v\2\2b\24\3\2\2\2cd\7<\2\2d\26\3\2\2\2"+
		"eg\t\2\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\b\f\2\2"+
		"k\30\3\2\2\2lm\7\61\2\2mn\7,\2\2nr\3\2\2\2oq\13\2\2\2po\3\2\2\2qt\3\2"+
		"\2\2rs\3\2\2\2rp\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7,\2\2vw\7\61\2\2wx\3\2"+
		"\2\2xy\b\r\2\2y\32\3\2\2\2z{\7\61\2\2{|\7\61\2\2|\u0080\3\2\2\2}\177\n"+
		"\3\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2"+
		"\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\b\16\2\2\u0084\34"+
		"\3\2\2\2\u0085\u0086\4\62;\2\u0086\u0087\4ch\2\u0087\u0088\4CH\2\u0088"+
		"\36\3\2\2\2\u0089\u008a\7o\2\2\u008a\u008b\7q\2\2\u008b\u008c\7p\2\2\u008c"+
		"\u008d\7g\2\2\u008d\u009f\7{\2\2\u008e\u008f\7d\2\2\u008f\u0090\7q\2\2"+
		"\u0090\u0091\7q\2\2\u0091\u0092\7n\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7c\2\2\u0094\u009f\7p\2\2\u0095\u0096\7u\2\2\u0096\u0097\7v\2\2\u0097"+
		"\u0098\7t\2\2\u0098\u0099\7k\2\2\u0099\u009a\7p\2\2\u009a\u009f\7i\2\2"+
		"\u009b\u009c\7k\2\2\u009c\u009d\7p\2\2\u009d\u009f\7v\2\2\u009e\u0089"+
		"\3\2\2\2\u009e\u008e\3\2\2\2\u009e\u0095\3\2\2\2\u009e\u009b\3\2\2\2\u009f"+
		" \3\2\2\2\u00a0\u00a1\7u\2\2\u00a1\u00a2\7r\2\2\u00a2\u00a3\7k\2\2\u00a3"+
		"\u00a4\7p\2\2\u00a4\u00a5\7d\2\2\u00a5\u00a6\7q\2\2\u00a6\u00c7\7z\2\2"+
		"\u00a7\u00a8\7u\2\2\u00a8\u00a9\7n\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab"+
		"\7f\2\2\u00ab\u00ac\7g\2\2\u00ac\u00c7\7t\2\2\u00ad\u00ae\7v\2\2\u00ae"+
		"\u00af\7g\2\2\u00af\u00b0\7z\2\2\u00b0\u00c7\7v\2\2\u00b1\u00b2\7t\2\2"+
		"\u00b2\u00b3\7c\2\2\u00b3\u00b4\7f\2\2\u00b4\u00b5\7k\2\2\u00b5\u00c7"+
		"\7q\2\2\u00b6\u00b7\7e\2\2\u00b7\u00b8\7j\2\2\u00b8\u00b9\7g\2\2\u00b9"+
		"\u00ba\7e\2\2\u00ba\u00bb\7m\2\2\u00bb\u00bc\7d\2\2\u00bc\u00bd\7q\2\2"+
		"\u00bd\u00c7\7z\2\2\u00be\u00bf\7f\2\2\u00bf\u00c0\7t\2\2\u00c0\u00c1"+
		"\7q\2\2\u00c1\u00c2\7r\2\2\u00c2\u00c3\7f\2\2\u00c3\u00c4\7q\2\2\u00c4"+
		"\u00c5\7y\2\2\u00c5\u00c7\7p\2\2\u00c6\u00a0\3\2\2\2\u00c6\u00a7\3\2\2"+
		"\2\u00c6\u00ad\3\2\2\2\u00c6\u00b1\3\2\2\2\u00c6\u00b6\3\2\2\2\u00c6\u00be"+
		"\3\2\2\2\u00c7\"\3\2\2\2\u00c8\u00cc\t\4\2\2\u00c9\u00cb\t\5\2\2\u00ca"+
		"\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd$\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7y\2\2\u00d0\u00d1"+
		"\7k\2\2\u00d1\u00d2\7f\2\2\u00d2\u00d3\7v\2\2\u00d3\u00e6\7j\2\2\u00d4"+
		"\u00d5\7h\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7p\2\2\u00d7\u00e6\7v\2\2"+
		"\u00d8\u00d9\7h\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7p\2\2\u00db\u00dc"+
		"\7v\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de\7k\2\2\u00de\u00df\7|\2\2\u00df"+
		"\u00e6\7g\2\2\u00e0\u00e1\7e\2\2\u00e1\u00e2\7q\2\2\u00e2\u00e3\7n\2\2"+
		"\u00e3\u00e4\7q\2\2\u00e4\u00e6\7t\2\2\u00e5\u00cf\3\2\2\2\u00e5\u00d4"+
		"\3\2\2\2\u00e5\u00d8\3\2\2\2\u00e5\u00e0\3\2\2\2\u00e6&\3\2\2\2\u00e7"+
		"\u00eb\7$\2\2\u00e8\u00ea\n\6\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00ed\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed"+
		"\u00eb\3\2\2\2\u00ee\u00ef\7$\2\2\u00ef(\3\2\2\2\u00f0\u00f2\4\62;\2\u00f1"+
		"\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2"+
		"\2\2\u00f4*\3\2\2\2\u00f5\u00f6\7%\2\2\u00f6\u00f7\5\35\17\2\u00f7\u00f8"+
		"\5\35\17\2\u00f8\u00f9\5\35\17\2\u00f9\u00fa\5\35\17\2\u00fa\u00fb\5\35"+
		"\17\2\u00fb\u00fc\5\35\17\2\u00fc,\3\2\2\2\f\2hr\u0080\u009e\u00c6\u00cc"+
		"\u00e5\u00eb\u00f3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}