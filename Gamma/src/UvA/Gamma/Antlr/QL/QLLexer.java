package UvA.Gamma.Antlr.QL;// Generated from src/UvA/Gamma/Antlr/QL//QL.g4 by ANTLR 4.6

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, BOOL = 24, STRING = 25,
            INT = 26, DATE = 27, DEC = 28, MONEY = 29, NUMBER = 30, STRING_LITERAL = 31, ID = 32,
            WHITESPACE = 33;
    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    public static final String[] ruleNames = {
            "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
            "T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16",
            "T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "BOOL", "STRING",
            "INTEGER", "DATE", "DECIMAL", "MONEY", "NUMBER", "STRING_LITERAL", "ID", "WHITESPACE"
    };

    private static final String[] _LITERAL_NAMES = {
            null, "'form'", "'{'", "'}'", "':'", "'if'", "'('", "')'", "'else'", "'='",
            "'&&'", "'||'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'true'",
            "'false'", "'*'", "'/'", "'+'", "'-'", "'boolean'", "'string'", "'integer'",
            "'date'", "'decimal'", "'money'"
    };
    private static final String[] _SYMBOLIC_NAMES = {
            null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null,
            "BOOL", "STRING", "INTEGER", "DATE", "DECIMAL", "MONEY", "NUMBER", "STRING_LITERAL",
            "ID", "WHITESPACE"
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
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "QL.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN =
            "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00d4\b\1\4\2\t" +
                    "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
                    "\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3" +
                    "\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f" +
                    "\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3" +
                    "\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3" +
                    "\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3" +
                    "\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3" +
                    "\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3" +
                    "\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\6\37\u00b3\n\37\r\37\16\37\u00b4" +
                    "\3\37\3\37\6\37\u00b9\n\37\r\37\16\37\u00ba\5\37\u00bd\n\37\3 \3 \6 \u00c1" +
                    "\n \r \16 \u00c2\3 \3 \3!\3!\7!\u00c9\n!\f!\16!\u00cc\13!\3\"\6\"\u00cf" +
                    "\n\"\r\"\16\"\u00d0\3\"\3\"\2\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23" +
                    "\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31" +
                    "\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\6\3\2$$\4\2C\\c|\5\2\62" +
                    ";C\\c|\5\2\13\f\16\17\"\"\u00d9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2" +
                    "\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2" +
                    "\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2" +
                    "\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2" +
                    "\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2" +
                    "\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2" +
                    "\2C\3\2\2\2\3E\3\2\2\2\5J\3\2\2\2\7L\3\2\2\2\tN\3\2\2\2\13P\3\2\2\2\r" +
                    "S\3\2\2\2\17U\3\2\2\2\21W\3\2\2\2\23\\\3\2\2\2\25^\3\2\2\2\27a\3\2\2\2" +
                    "\31d\3\2\2\2\33g\3\2\2\2\35j\3\2\2\2\37l\3\2\2\2!n\3\2\2\2#q\3\2\2\2%" +
                    "t\3\2\2\2\'y\3\2\2\2)\177\3\2\2\2+\u0081\3\2\2\2-\u0083\3\2\2\2/\u0085" +
                    "\3\2\2\2\61\u0087\3\2\2\2\63\u008f\3\2\2\2\65\u0096\3\2\2\2\67\u009e\3" +
                    "\2\2\29\u00a3\3\2\2\2;\u00ab\3\2\2\2=\u00b2\3\2\2\2?\u00be\3\2\2\2A\u00c6" +
                    "\3\2\2\2C\u00ce\3\2\2\2EF\7h\2\2FG\7q\2\2GH\7t\2\2HI\7o\2\2I\4\3\2\2\2" +
                    "JK\7}\2\2K\6\3\2\2\2LM\7\177\2\2M\b\3\2\2\2NO\7<\2\2O\n\3\2\2\2PQ\7k\2" +
                    "\2QR\7h\2\2R\f\3\2\2\2ST\7*\2\2T\16\3\2\2\2UV\7+\2\2V\20\3\2\2\2WX\7g" +
                    "\2\2XY\7n\2\2YZ\7u\2\2Z[\7g\2\2[\22\3\2\2\2\\]\7?\2\2]\24\3\2\2\2^_\7" +
                    "(\2\2_`\7(\2\2`\26\3\2\2\2ab\7~\2\2bc\7~\2\2c\30\3\2\2\2de\7?\2\2ef\7" +
                    "?\2\2f\32\3\2\2\2gh\7#\2\2hi\7?\2\2i\34\3\2\2\2jk\7>\2\2k\36\3\2\2\2l" +
                    "m\7@\2\2m \3\2\2\2no\7>\2\2op\7?\2\2p\"\3\2\2\2qr\7@\2\2rs\7?\2\2s$\3" +
                    "\2\2\2tu\7v\2\2uv\7t\2\2vw\7w\2\2wx\7g\2\2x&\3\2\2\2yz\7h\2\2z{\7c\2\2" +
                    "{|\7n\2\2|}\7u\2\2}~\7g\2\2~(\3\2\2\2\177\u0080\7,\2\2\u0080*\3\2\2\2" +
                    "\u0081\u0082\7\61\2\2\u0082,\3\2\2\2\u0083\u0084\7-\2\2\u0084.\3\2\2\2" +
                    "\u0085\u0086\7/\2\2\u0086\60\3\2\2\2\u0087\u0088\7d\2\2\u0088\u0089\7" +
                    "q\2\2\u0089\u008a\7q\2\2\u008a\u008b\7n\2\2\u008b\u008c\7g\2\2\u008c\u008d" +
                    "\7c\2\2\u008d\u008e\7p\2\2\u008e\62\3\2\2\2\u008f\u0090\7u\2\2\u0090\u0091" +
                    "\7v\2\2\u0091\u0092\7t\2\2\u0092\u0093\7k\2\2\u0093\u0094\7p\2\2\u0094" +
                    "\u0095\7i\2\2\u0095\64\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7p\2\2\u0098" +
                    "\u0099\7v\2\2\u0099\u009a\7g\2\2\u009a\u009b\7i\2\2\u009b\u009c\7g\2\2" +
                    "\u009c\u009d\7t\2\2\u009d\66\3\2\2\2\u009e\u009f\7f\2\2\u009f\u00a0\7" +
                    "c\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7g\2\2\u00a28\3\2\2\2\u00a3\u00a4" +
                    "\7f\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7e\2\2\u00a6\u00a7\7k\2\2\u00a7" +
                    "\u00a8\7o\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7n\2\2\u00aa:\3\2\2\2\u00ab" +
                    "\u00ac\7o\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7g\2\2" +
                    "\u00af\u00b0\7{\2\2\u00b0<\3\2\2\2\u00b1\u00b3\4\62;\2\u00b2\u00b1\3\2" +
                    "\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5" +
                    "\u00bc\3\2\2\2\u00b6\u00b8\7\60\2\2\u00b7\u00b9\4\62;\2\u00b8\u00b7\3" +
                    "\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb" +
                    "\u00bd\3\2\2\2\u00bc\u00b6\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd>\3\2\2\2" +
                    "\u00be\u00c0\7$\2\2\u00bf\u00c1\n\2\2\2\u00c0\u00bf\3\2\2\2\u00c1\u00c2" +
                    "\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4" +
                    "\u00c5\7$\2\2\u00c5@\3\2\2\2\u00c6\u00ca\t\3\2\2\u00c7\u00c9\t\4\2\2\u00c8" +
                    "\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2" +
                    "\2\2\u00cbB\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00cf\t\5\2\2\u00ce\u00cd" +
                    "\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1" +
                    "\u00d2\3\2\2\2\u00d2\u00d3\b\"\2\2\u00d3D\3\2\2\2\t\2\u00b4\u00ba\u00bc" +
                    "\u00c2\u00ca\u00d0\3\b\2\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
