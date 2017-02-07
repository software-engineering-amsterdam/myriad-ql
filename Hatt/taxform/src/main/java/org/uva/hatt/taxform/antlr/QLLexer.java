// $ANTLR 3.4 src/main/java/org/uva/hatt/taxform/antlr/QL.g 2017-02-07 11:01:58

package org.uva.hatt.taxform.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class QLLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__10=10;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int COMMENT=4;
    public static final int Ident=5;
    public static final int Int=6;
    public static final int Str=7;
    public static final int WS=8;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public QLLexer() {} 
    public QLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public QLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "src/main/java/org/uva/hatt/taxform/antlr/QL.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:6:6: ( '!' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:6:8: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:7:7: ( '!=' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:7:9: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:8:7: ( '&&' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:8:9: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:9:7: ( '*' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:9:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:10:7: ( '+' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:10:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:11:7: ( '-' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:11:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:12:7: ( '/' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:12:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:13:7: ( '<' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:13:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:14:7: ( '<=' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:14:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:15:7: ( '==' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:15:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:16:7: ( '>' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:16:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:17:7: ( '>=' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:17:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:18:7: ( '||' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:18:9: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:85:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:85:7: ( ' ' | '\\t' | '\\n' | '\\r' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:89:6: ( '/*' ( . )* '*/' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:89:8: '/*' ( . )* '*/'
            {
            match("/*"); 



            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:89:13: ( . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1=='/') ) {
                        alt1=2;
                    }
                    else if ( ((LA1_1 >= '\u0000' && LA1_1 <= '.')||(LA1_1 >= '0' && LA1_1 <= '\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0 >= '\u0000' && LA1_0 <= ')')||(LA1_0 >= '+' && LA1_0 <= '\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:89:13: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match("*/"); 



            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "Ident"
    public final void mIdent() throws RecognitionException {
        try {
            int _type = Ident;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:92:6: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:92:10: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:92:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Ident"

    // $ANTLR start "Int"
    public final void mInt() throws RecognitionException {
        try {
            int _type = Int;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:94:4: ( ( '0' .. '9' )+ )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:94:6: ( '0' .. '9' )+
            {
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:94:6: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Int"

    // $ANTLR start "Str"
    public final void mStr() throws RecognitionException {
        try {
            int _type = Str;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:96:4: ( '\"' ( . )* '\"' )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:96:6: '\"' ( . )* '\"'
            {
            match('\"'); 

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:96:10: ( . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\"') ) {
                    alt4=2;
                }
                else if ( ((LA4_0 >= '\u0000' && LA4_0 <= '!')||(LA4_0 >= '#' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:96:10: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Str"

    public void mTokens() throws RecognitionException {
        // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | WS | COMMENT | Ident | Int | Str )
        int alt5=18;
        switch ( input.LA(1) ) {
        case '!':
            {
            int LA5_1 = input.LA(2);

            if ( (LA5_1=='=') ) {
                alt5=2;
            }
            else {
                alt5=1;
            }
            }
            break;
        case '&':
            {
            alt5=3;
            }
            break;
        case '*':
            {
            alt5=4;
            }
            break;
        case '+':
            {
            alt5=5;
            }
            break;
        case '-':
            {
            alt5=6;
            }
            break;
        case '/':
            {
            int LA5_6 = input.LA(2);

            if ( (LA5_6=='*') ) {
                alt5=15;
            }
            else {
                alt5=7;
            }
            }
            break;
        case '<':
            {
            int LA5_7 = input.LA(2);

            if ( (LA5_7=='=') ) {
                alt5=9;
            }
            else {
                alt5=8;
            }
            }
            break;
        case '=':
            {
            alt5=10;
            }
            break;
        case '>':
            {
            int LA5_9 = input.LA(2);

            if ( (LA5_9=='=') ) {
                alt5=12;
            }
            else {
                alt5=11;
            }
            }
            break;
        case '|':
            {
            alt5=13;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt5=14;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=16;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt5=17;
            }
            break;
        case '\"':
            {
            alt5=18;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 5, 0, input);

            throw nvae;

        }

        switch (alt5) {
            case 1 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:10: T__9
                {
                mT__9(); 


                }
                break;
            case 2 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:15: T__10
                {
                mT__10(); 


                }
                break;
            case 3 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:21: T__11
                {
                mT__11(); 


                }
                break;
            case 4 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:27: T__12
                {
                mT__12(); 


                }
                break;
            case 5 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:33: T__13
                {
                mT__13(); 


                }
                break;
            case 6 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:39: T__14
                {
                mT__14(); 


                }
                break;
            case 7 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:45: T__15
                {
                mT__15(); 


                }
                break;
            case 8 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:51: T__16
                {
                mT__16(); 


                }
                break;
            case 9 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:57: T__17
                {
                mT__17(); 


                }
                break;
            case 10 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:63: T__18
                {
                mT__18(); 


                }
                break;
            case 11 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:69: T__19
                {
                mT__19(); 


                }
                break;
            case 12 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:75: T__20
                {
                mT__20(); 


                }
                break;
            case 13 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:81: T__21
                {
                mT__21(); 


                }
                break;
            case 14 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:87: WS
                {
                mWS(); 


                }
                break;
            case 15 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:90: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 16 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:98: Ident
                {
                mIdent(); 


                }
                break;
            case 17 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:104: Int
                {
                mInt(); 


                }
                break;
            case 18 :
                // src/main/java/org/uva/hatt/taxform/antlr/QL.g:1:108: Str
                {
                mStr(); 


                }
                break;

        }

    }


 

}