// Generated from /home/yoan/github/myriad-ql/AlternativeQL/src/org/ql/grammar/QLLexer.g4 by ANTLR 4.6
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
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASSIGN=1, IF=2, ELSE=3, COLON=4, FORM=5, BOOLEAN=6, OPEN_BRACKET=7, CLOSE_BRACKET=8, 
		OPEN_PARENT=9, CLOSE_PARENT=10, SEMICOLON=11, LINE_COMMENT=12, STRING=13, 
		FLOAT=14, ID=15, INTEGER=16, WS=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ASSIGN", "IF", "ELSE", "COLON", "FORM", "BOOLEAN", "OPEN_BRACKET", "CLOSE_BRACKET", 
		"OPEN_PARENT", "CLOSE_PARENT", "SEMICOLON", "LINE_COMMENT", "STRING", 
		"FLOAT", "ID", "INTEGER", "WS", "ESC", "UNICODE", "HEX"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'if'", "'else'", "':'", "'form'", null, "'{'", "'}'", "'('", 
		"')'", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ASSIGN", "IF", "ELSE", "COLON", "FORM", "BOOLEAN", "OPEN_BRACKET", 
		"CLOSE_BRACKET", "OPEN_PARENT", "CLOSE_PARENT", "SEMICOLON", "LINE_COMMENT", 
		"STRING", "FLOAT", "ID", "INTEGER", "WS"
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
	public String getGrammarFileName() { return "QLLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23\u0090\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7F\n\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\7"+
		"\rV\n\r\f\r\16\rY\13\r\3\r\3\r\3\16\3\16\3\16\7\16`\n\16\f\16\16\16c\13"+
		"\16\3\16\3\16\3\17\6\17h\n\17\r\17\16\17i\3\17\3\17\6\17n\n\17\r\17\16"+
		"\17o\3\20\3\20\6\20t\n\20\r\20\16\20u\3\21\6\21y\n\21\r\21\16\21z\3\22"+
		"\6\22~\n\22\r\22\16\22\177\3\22\3\22\3\23\3\23\3\23\5\23\u0087\n\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\2\'\2)\2\3"+
		"\2\n\4\2\f\f\17\17\4\2$$^^\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\16"+
		"\17\"\"\n\2$$\61\61^^ddhhppttvv\5\2\62;CHch\u0096\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3+\3\2\2\2\5-\3\2"+
		"\2\2\7\60\3\2\2\2\t\65\3\2\2\2\13\67\3\2\2\2\rE\3\2\2\2\17G\3\2\2\2\21"+
		"I\3\2\2\2\23K\3\2\2\2\25M\3\2\2\2\27O\3\2\2\2\31Q\3\2\2\2\33\\\3\2\2\2"+
		"\35g\3\2\2\2\37q\3\2\2\2!x\3\2\2\2#}\3\2\2\2%\u0083\3\2\2\2\'\u0088\3"+
		"\2\2\2)\u008e\3\2\2\2+,\7?\2\2,\4\3\2\2\2-.\7k\2\2./\7h\2\2/\6\3\2\2\2"+
		"\60\61\7g\2\2\61\62\7n\2\2\62\63\7u\2\2\63\64\7g\2\2\64\b\3\2\2\2\65\66"+
		"\7<\2\2\66\n\3\2\2\2\678\7h\2\289\7q\2\29:\7t\2\2:;\7o\2\2;\f\3\2\2\2"+
		"<=\7v\2\2=>\7t\2\2>?\7w\2\2?F\7g\2\2@A\7h\2\2AB\7c\2\2BC\7n\2\2CD\7u\2"+
		"\2DF\7g\2\2E<\3\2\2\2E@\3\2\2\2F\16\3\2\2\2GH\7}\2\2H\20\3\2\2\2IJ\7\177"+
		"\2\2J\22\3\2\2\2KL\7*\2\2L\24\3\2\2\2MN\7+\2\2N\26\3\2\2\2OP\7=\2\2P\30"+
		"\3\2\2\2QR\7\61\2\2RS\7\61\2\2SW\3\2\2\2TV\n\2\2\2UT\3\2\2\2VY\3\2\2\2"+
		"WU\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\b\r\2\2[\32\3\2\2\2\\a\7$\2"+
		"\2]`\5%\23\2^`\n\3\2\2_]\3\2\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2"+
		"\2bd\3\2\2\2ca\3\2\2\2de\7$\2\2e\34\3\2\2\2fh\t\4\2\2gf\3\2\2\2hi\3\2"+
		"\2\2ig\3\2\2\2ij\3\2\2\2jk\3\2\2\2km\7\60\2\2ln\t\4\2\2ml\3\2\2\2no\3"+
		"\2\2\2om\3\2\2\2op\3\2\2\2p\36\3\2\2\2qs\t\5\2\2rt\t\6\2\2sr\3\2\2\2t"+
		"u\3\2\2\2us\3\2\2\2uv\3\2\2\2v \3\2\2\2wy\t\4\2\2xw\3\2\2\2yz\3\2\2\2"+
		"zx\3\2\2\2z{\3\2\2\2{\"\3\2\2\2|~\t\7\2\2}|\3\2\2\2~\177\3\2\2\2\177}"+
		"\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\b\22\3\2\u0082"+
		"$\3\2\2\2\u0083\u0086\7^\2\2\u0084\u0087\t\b\2\2\u0085\u0087\5\'\24\2"+
		"\u0086\u0084\3\2\2\2\u0086\u0085\3\2\2\2\u0087&\3\2\2\2\u0088\u0089\7"+
		"w\2\2\u0089\u008a\5)\25\2\u008a\u008b\5)\25\2\u008b\u008c\5)\25\2\u008c"+
		"\u008d\5)\25\2\u008d(\3\2\2\2\u008e\u008f\t\t\2\2\u008f*\3\2\2\2\r\2E"+
		"W_aiouz\177\u0086\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}