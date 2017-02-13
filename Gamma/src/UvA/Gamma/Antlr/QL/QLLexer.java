package UvA.Gamma.Antlr.QL;// Generated from src/UvA/Gamma/Antlr/QL//QL.g4 by ANTLR 4.6
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
		T__17=18, BOOL=19, STRING=20, INT=21, DATE=22, DEC=23, MONEY=24, ADD=25, 
		SUB=26, NUMBER=27, QUESTION=28, ID=29, BRACKET_OPEN=30, BRACKET_CLOSE=31, 
		WHITESPACE=32;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "BOOL", "STRING", "INT", "DATE", "DEC", "MONEY", "ADD", "SUB", 
		"NUMBER", "QUESTION", "ID", "BRACKET_OPEN", "BRACKET_CLOSE", "WHITESPACE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'form'", "':'", "'='", "'('", "')'", "'if'", "'&&'", "'||'", "'<'", 
		"'>'", "'<='", "'>='", "'!='", "'=='", "'true'", "'false'", "'*'", "'/'", 
		"'boolean'", "'string'", "'integer'", "'date'", "'decimal'", "'money'", 
		"'+'", "'-'", null, null, null, "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "BOOL", "STRING", "INT", "DATE", 
		"DEC", "MONEY", "ADD", "SUB", "NUMBER", "QUESTION", "ID", "BRACKET_OPEN", 
		"BRACKET_CLOSE", "WHITESPACE"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\"\u00c3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\6\34\u00a8\n\34"+
		"\r\34\16\34\u00a9\3\35\3\35\6\35\u00ae\n\35\r\35\16\35\u00af\3\35\3\35"+
		"\3\36\6\36\u00b5\n\36\r\36\16\36\u00b6\3\37\3\37\3 \3 \3!\6!\u00be\n!"+
		"\r!\16!\u00bf\3!\3!\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"\3\2\5\3\2$$\4\2C\\c|\5\2\13\f\16\17"+
		"\"\"\u00c6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5H\3\2\2\2"+
		"\7J\3\2\2\2\tL\3\2\2\2\13N\3\2\2\2\rP\3\2\2\2\17S\3\2\2\2\21V\3\2\2\2"+
		"\23Y\3\2\2\2\25[\3\2\2\2\27]\3\2\2\2\31`\3\2\2\2\33c\3\2\2\2\35f\3\2\2"+
		"\2\37i\3\2\2\2!n\3\2\2\2#t\3\2\2\2%v\3\2\2\2\'x\3\2\2\2)\u0080\3\2\2\2"+
		"+\u0087\3\2\2\2-\u008f\3\2\2\2/\u0094\3\2\2\2\61\u009c\3\2\2\2\63\u00a2"+
		"\3\2\2\2\65\u00a4\3\2\2\2\67\u00a7\3\2\2\29\u00ab\3\2\2\2;\u00b4\3\2\2"+
		"\2=\u00b8\3\2\2\2?\u00ba\3\2\2\2A\u00bd\3\2\2\2CD\7h\2\2DE\7q\2\2EF\7"+
		"t\2\2FG\7o\2\2G\4\3\2\2\2HI\7<\2\2I\6\3\2\2\2JK\7?\2\2K\b\3\2\2\2LM\7"+
		"*\2\2M\n\3\2\2\2NO\7+\2\2O\f\3\2\2\2PQ\7k\2\2QR\7h\2\2R\16\3\2\2\2ST\7"+
		"(\2\2TU\7(\2\2U\20\3\2\2\2VW\7~\2\2WX\7~\2\2X\22\3\2\2\2YZ\7>\2\2Z\24"+
		"\3\2\2\2[\\\7@\2\2\\\26\3\2\2\2]^\7>\2\2^_\7?\2\2_\30\3\2\2\2`a\7@\2\2"+
		"ab\7?\2\2b\32\3\2\2\2cd\7#\2\2de\7?\2\2e\34\3\2\2\2fg\7?\2\2gh\7?\2\2"+
		"h\36\3\2\2\2ij\7v\2\2jk\7t\2\2kl\7w\2\2lm\7g\2\2m \3\2\2\2no\7h\2\2op"+
		"\7c\2\2pq\7n\2\2qr\7u\2\2rs\7g\2\2s\"\3\2\2\2tu\7,\2\2u$\3\2\2\2vw\7\61"+
		"\2\2w&\3\2\2\2xy\7d\2\2yz\7q\2\2z{\7q\2\2{|\7n\2\2|}\7g\2\2}~\7c\2\2~"+
		"\177\7p\2\2\177(\3\2\2\2\u0080\u0081\7u\2\2\u0081\u0082\7v\2\2\u0082\u0083"+
		"\7t\2\2\u0083\u0084\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086\7i\2\2\u0086"+
		"*\3\2\2\2\u0087\u0088\7k\2\2\u0088\u0089\7p\2\2\u0089\u008a\7v\2\2\u008a"+
		"\u008b\7g\2\2\u008b\u008c\7i\2\2\u008c\u008d\7g\2\2\u008d\u008e\7t\2\2"+
		"\u008e,\3\2\2\2\u008f\u0090\7f\2\2\u0090\u0091\7c\2\2\u0091\u0092\7v\2"+
		"\2\u0092\u0093\7g\2\2\u0093.\3\2\2\2\u0094\u0095\7f\2\2\u0095\u0096\7"+
		"g\2\2\u0096\u0097\7e\2\2\u0097\u0098\7k\2\2\u0098\u0099\7o\2\2\u0099\u009a"+
		"\7c\2\2\u009a\u009b\7n\2\2\u009b\60\3\2\2\2\u009c\u009d\7o\2\2\u009d\u009e"+
		"\7q\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7{\2\2\u00a1"+
		"\62\3\2\2\2\u00a2\u00a3\7-\2\2\u00a3\64\3\2\2\2\u00a4\u00a5\7/\2\2\u00a5"+
		"\66\3\2\2\2\u00a6\u00a8\4\62;\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2"+
		"\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa8\3\2\2\2\u00ab\u00ad"+
		"\7$\2\2\u00ac\u00ae\n\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7$"+
		"\2\2\u00b2:\3\2\2\2\u00b3\u00b5\t\3\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6"+
		"\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7<\3\2\2\2\u00b8"+
		"\u00b9\7}\2\2\u00b9>\3\2\2\2\u00ba\u00bb\7\177\2\2\u00bb@\3\2\2\2\u00bc"+
		"\u00be\t\4\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\b!\2\2\u00c2"+
		"B\3\2\2\2\7\2\u00a9\u00af\u00b6\u00bf\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
