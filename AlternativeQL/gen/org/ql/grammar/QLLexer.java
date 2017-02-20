// Generated from /Users/yoan-alexander/Sites/myriad-ql/AlternativeQL/src/org/ql/grammar/QLLexer.g4 by ANTLR 4.6
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
		LINE_COMMENT=1, STRING_LITERAL=2, BOOLEAN_LITERAL=3, DECIMAL_LITERAL=4, 
		INTEGER_LITERAL=5, ID=6, WS=7;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LINE_COMMENT", "STRING_LITERAL", "BOOLEAN_LITERAL", "DECIMAL_LITERAL", 
		"INTEGER_LITERAL", "ID", "WS", "ESCAPE_QUOTE", "UNICODE", "HEX"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LINE_COMMENT", "STRING_LITERAL", "BOOLEAN_LITERAL", "DECIMAL_LITERAL", 
		"INTEGER_LITERAL", "ID", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\ta\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\3\3\3\3\3\3\7"+
		"\3&\n\3\f\3\16\3)\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"\66\n\4\3\5\6\59\n\5\r\5\16\5:\3\5\3\5\6\5?\n\5\r\5\16\5@\3\6\6\6D\n\6"+
		"\r\6\16\6E\3\7\3\7\6\7J\n\7\r\7\16\7K\3\b\6\bO\n\b\r\b\16\bP\3\b\3\b\3"+
		"\t\3\t\3\t\5\tX\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\2\2\f\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\2\23\2\25\2\3\2\n\4\2\f\f\17\17\4\2$$^^\3\2\62"+
		";\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\16\17\"\"\n\2$$\61\61^^ddhhppttvv\5"+
		"\2\62;CHchg\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\3\27\3\2\2\2\5\"\3\2\2\2\7\65\3\2\2\2\t8\3"+
		"\2\2\2\13C\3\2\2\2\rG\3\2\2\2\17N\3\2\2\2\21T\3\2\2\2\23Y\3\2\2\2\25_"+
		"\3\2\2\2\27\30\7\61\2\2\30\31\7\61\2\2\31\35\3\2\2\2\32\34\n\2\2\2\33"+
		"\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37\35"+
		"\3\2\2\2 !\b\2\2\2!\4\3\2\2\2\"\'\7$\2\2#&\5\21\t\2$&\n\3\2\2%#\3\2\2"+
		"\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+\7"+
		"$\2\2+\6\3\2\2\2,-\7v\2\2-.\7t\2\2./\7w\2\2/\66\7g\2\2\60\61\7h\2\2\61"+
		"\62\7c\2\2\62\63\7n\2\2\63\64\7u\2\2\64\66\7g\2\2\65,\3\2\2\2\65\60\3"+
		"\2\2\2\66\b\3\2\2\2\679\t\4\2\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2"+
		"\2\2;<\3\2\2\2<>\7\60\2\2=?\t\4\2\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3"+
		"\2\2\2A\n\3\2\2\2BD\t\4\2\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\f"+
		"\3\2\2\2GI\t\5\2\2HJ\t\6\2\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2L"+
		"\16\3\2\2\2MO\t\7\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2"+
		"\2RS\b\b\3\2S\20\3\2\2\2TW\7^\2\2UX\t\b\2\2VX\5\23\n\2WU\3\2\2\2WV\3\2"+
		"\2\2X\22\3\2\2\2YZ\7w\2\2Z[\5\25\13\2[\\\5\25\13\2\\]\5\25\13\2]^\5\25"+
		"\13\2^\24\3\2\2\2_`\t\t\2\2`\26\3\2\2\2\r\2\35%\'\65:@EKPW\4\2\3\2\b\2"+
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