// Generated from QLParser.g4 by ANTLR 4.6
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
		ASSIGN=1, IF=2, ELSE=3, COLON=4, FORM=5, OPEN_BRACKET=6, CLOSE_BRACKET=7, 
		OPEN_PARENT=8, CLOSE_PARENT=9, SEMICOLON=10, BANG=11, DIV=12, MUL=13, 
		ADD=14, SUB=15, GT=16, LT=17, EQUAL=18, LE=19, GE=20, NOTEQUAL=21, AND=22, 
		OR=23, BOOLEAN=24, FLOAT=25, INTEGER=26, STRING=27, MONEY=28, DATE=29, 
		LINE_COMMENT=30, STRING_LITERAL=31, BOOLEAN_LITERAL=32, FLOAT_LITERAL=33, 
		INTEGER_LITERAL=34, ID=35, WS=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ASSIGN", "IF", "ELSE", "COLON", "FORM", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_PARENT", "CLOSE_PARENT", "SEMICOLON", "BANG", "DIV", "MUL", "ADD", 
		"SUB", "GT", "LT", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "BOOLEAN", 
		"FLOAT", "INTEGER", "STRING", "MONEY", "DATE", "LINE_COMMENT", "STRING_LITERAL", 
		"BOOLEAN_LITERAL", "FLOAT_LITERAL", "INTEGER_LITERAL", "ID", "WS", "ESC", 
		"UNICODE", "HEX"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'if'", "'else'", "':'", "'form'", "'{'", "'}'", "'('", "')'", 
		"';'", "'!'", "'/'", "'*'", "'+'", "'-'", "'>'", "'<'", "'=='", "'<='", 
		"'>='", "'!='", "'&&'", "'||'", null, "'float'", "'integer'", "'string'", 
		"'money'", "'date'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ASSIGN", "IF", "ELSE", "COLON", "FORM", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_PARENT", "CLOSE_PARENT", "SEMICOLON", "BANG", "DIV", "MUL", "ADD", 
		"SUB", "GT", "LT", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "BOOLEAN", 
		"FLOAT", "INTEGER", "STRING", "MONEY", "DATE", "LINE_COMMENT", "STRING_LITERAL", 
		"BOOLEAN_LITERAL", "FLOAT_LITERAL", "INTEGER_LITERAL", "ID", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0103\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\5\31\u0098\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\3\37\7\37\u00be\n\37\f\37\16\37\u00c1\13\37\3\37\3\37\3 \3 \3 \7 \u00c8"+
		"\n \f \16 \u00cb\13 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u00d8\n!\3\""+
		"\6\"\u00db\n\"\r\"\16\"\u00dc\3\"\3\"\6\"\u00e1\n\"\r\"\16\"\u00e2\3#"+
		"\6#\u00e6\n#\r#\16#\u00e7\3$\3$\6$\u00ec\n$\r$\16$\u00ed\3%\6%\u00f1\n"+
		"%\r%\16%\u00f2\3%\3%\3&\3&\3&\5&\u00fa\n&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3"+
		"(\2\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"C#E$G%I&K\2M\2O\2\3\2\n\4\2\f\f\17\17\4\2$$^^\3\2\62;\4"+
		"\2C\\c|\6\2\62;C\\aac|\5\2\13\f\16\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2"+
		"\62;CHch\u010a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3Q\3\2\2\2\5S\3\2\2\2\7V\3\2\2\2\t[\3\2\2"+
		"\2\13]\3\2\2\2\rb\3\2\2\2\17d\3\2\2\2\21f\3\2\2\2\23h\3\2\2\2\25j\3\2"+
		"\2\2\27l\3\2\2\2\31n\3\2\2\2\33p\3\2\2\2\35r\3\2\2\2\37t\3\2\2\2!v\3\2"+
		"\2\2#x\3\2\2\2%z\3\2\2\2\'}\3\2\2\2)\u0080\3\2\2\2+\u0083\3\2\2\2-\u0086"+
		"\3\2\2\2/\u0089\3\2\2\2\61\u0097\3\2\2\2\63\u0099\3\2\2\2\65\u009f\3\2"+
		"\2\2\67\u00a7\3\2\2\29\u00ae\3\2\2\2;\u00b4\3\2\2\2=\u00b9\3\2\2\2?\u00c4"+
		"\3\2\2\2A\u00d7\3\2\2\2C\u00da\3\2\2\2E\u00e5\3\2\2\2G\u00e9\3\2\2\2I"+
		"\u00f0\3\2\2\2K\u00f6\3\2\2\2M\u00fb\3\2\2\2O\u0101\3\2\2\2QR\7?\2\2R"+
		"\4\3\2\2\2ST\7k\2\2TU\7h\2\2U\6\3\2\2\2VW\7g\2\2WX\7n\2\2XY\7u\2\2YZ\7"+
		"g\2\2Z\b\3\2\2\2[\\\7<\2\2\\\n\3\2\2\2]^\7h\2\2^_\7q\2\2_`\7t\2\2`a\7"+
		"o\2\2a\f\3\2\2\2bc\7}\2\2c\16\3\2\2\2de\7\177\2\2e\20\3\2\2\2fg\7*\2\2"+
		"g\22\3\2\2\2hi\7+\2\2i\24\3\2\2\2jk\7=\2\2k\26\3\2\2\2lm\7#\2\2m\30\3"+
		"\2\2\2no\7\61\2\2o\32\3\2\2\2pq\7,\2\2q\34\3\2\2\2rs\7-\2\2s\36\3\2\2"+
		"\2tu\7/\2\2u \3\2\2\2vw\7@\2\2w\"\3\2\2\2xy\7>\2\2y$\3\2\2\2z{\7?\2\2"+
		"{|\7?\2\2|&\3\2\2\2}~\7>\2\2~\177\7?\2\2\177(\3\2\2\2\u0080\u0081\7@\2"+
		"\2\u0081\u0082\7?\2\2\u0082*\3\2\2\2\u0083\u0084\7#\2\2\u0084\u0085\7"+
		"?\2\2\u0085,\3\2\2\2\u0086\u0087\7(\2\2\u0087\u0088\7(\2\2\u0088.\3\2"+
		"\2\2\u0089\u008a\7~\2\2\u008a\u008b\7~\2\2\u008b\60\3\2\2\2\u008c\u008d"+
		"\7d\2\2\u008d\u008e\7q\2\2\u008e\u008f\7q\2\2\u008f\u0090\7n\2\2\u0090"+
		"\u0091\7g\2\2\u0091\u0092\7c\2\2\u0092\u0098\7p\2\2\u0093\u0094\7d\2\2"+
		"\u0094\u0095\7q\2\2\u0095\u0096\7q\2\2\u0096\u0098\7n\2\2\u0097\u008c"+
		"\3\2\2\2\u0097\u0093\3\2\2\2\u0098\62\3\2\2\2\u0099\u009a\7h\2\2\u009a"+
		"\u009b\7n\2\2\u009b\u009c\7q\2\2\u009c\u009d\7c\2\2\u009d\u009e\7v\2\2"+
		"\u009e\64\3\2\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2\7"+
		"v\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7i\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6"+
		"\7t\2\2\u00a6\66\3\2\2\2\u00a7\u00a8\7u\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa"+
		"\7t\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad\7i\2\2\u00ad"+
		"8\3\2\2\2\u00ae\u00af\7o\2\2\u00af\u00b0\7q\2\2\u00b0\u00b1\7p\2\2\u00b1"+
		"\u00b2\7g\2\2\u00b2\u00b3\7{\2\2\u00b3:\3\2\2\2\u00b4\u00b5\7f\2\2\u00b5"+
		"\u00b6\7c\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8\7g\2\2\u00b8<\3\2\2\2\u00b9"+
		"\u00ba\7\61\2\2\u00ba\u00bb\7\61\2\2\u00bb\u00bf\3\2\2\2\u00bc\u00be\n"+
		"\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c3\b\37"+
		"\2\2\u00c3>\3\2\2\2\u00c4\u00c9\7$\2\2\u00c5\u00c8\5K&\2\u00c6\u00c8\n"+
		"\3\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c9\3\2"+
		"\2\2\u00cc\u00cd\7$\2\2\u00cd@\3\2\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0"+
		"\7t\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d8\7g\2\2\u00d2\u00d3\7h\2\2\u00d3"+
		"\u00d4\7c\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d8\7g\2\2"+
		"\u00d7\u00ce\3\2\2\2\u00d7\u00d2\3\2\2\2\u00d8B\3\2\2\2\u00d9\u00db\t"+
		"\4\2\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\7\60\2\2\u00df\u00e1\t"+
		"\4\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3D\3\2\2\2\u00e4\u00e6\t\4\2\2\u00e5\u00e4\3\2\2\2"+
		"\u00e6\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8F\3"+
		"\2\2\2\u00e9\u00eb\t\5\2\2\u00ea\u00ec\t\6\2\2\u00eb\u00ea\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00eeH\3\2\2\2"+
		"\u00ef\u00f1\t\7\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f0"+
		"\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\b%\3\2\u00f5"+
		"J\3\2\2\2\u00f6\u00f9\7^\2\2\u00f7\u00fa\t\b\2\2\u00f8\u00fa\5M\'\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00f8\3\2\2\2\u00faL\3\2\2\2\u00fb\u00fc\7w\2\2\u00fc"+
		"\u00fd\5O(\2\u00fd\u00fe\5O(\2\u00fe\u00ff\5O(\2\u00ff\u0100\5O(\2\u0100"+
		"N\3\2\2\2\u0101\u0102\t\t\2\2\u0102P\3\2\2\2\16\2\u0097\u00bf\u00c7\u00c9"+
		"\u00d7\u00dc\u00e2\u00e7\u00ed\u00f2\u00f9\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}