// $ANTLR 3.4 /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g 2013-01-29 13:31:30

package org.uva.sea.ql.parser.antlr;


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
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
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
    public String getGrammarFileName() { return "/Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:6:6: ( '!' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:6:8: '!'
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:7:7: ( '!=' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:7:9: '!='
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:8:7: ( '&&' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:8:9: '&&'
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:9:7: ( '(' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:9:9: '('
            {
            match('('); 

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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:10:7: ( ')' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:10:9: ')'
            {
            match(')'); 

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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:11:7: ( '*' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:11:9: '*'
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
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:12:7: ( '+' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:12:9: '+'
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
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:13:7: ( '-' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:13:9: '-'
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:14:7: ( '/' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:14:9: '/'
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:15:7: ( ':' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:15:9: ':'
            {
            match(':'); 

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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:16:7: ( '<' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:16:9: '<'
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:17:7: ( '<=' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:17:9: '<='
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:18:7: ( '==' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:18:9: '=='
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:19:7: ( '>' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:19:9: '>'
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:20:7: ( '>=' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:20:9: '>='
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
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:21:7: ( 'bool' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:21:9: 'bool'
            {
            match("bool"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:22:7: ( 'else' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:22:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:23:7: ( 'false' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:23:9: 'false'
            {
            match("false"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:24:7: ( 'form' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:24:9: 'form'
            {
            match("form"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:25:7: ( 'if' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:25:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:26:7: ( 'int' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:26:9: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:27:7: ( 'str' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:27:9: 'str'
            {
            match("str"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:28:7: ( 'true' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:28:9: 'true'
            {
            match("true"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:29:7: ( '{' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:29:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:30:7: ( '||' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:30:9: '||'
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
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:31:7: ( '}' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:31:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:125:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:125:7: ( ' ' | '\\t' | '\\n' | '\\r' )
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:129:6: ( '/*' ( . )* '*/' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:129:8: '/*' ( . )* '*/'
            {
            match("/*"); 



            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:129:13: ( . )*
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
            	    // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:129:13: .
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:132:6: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:132:10: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:132:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:134:4: ( ( '0' .. '9' )+ )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:134:6: ( '0' .. '9' )+
            {
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:134:6: ( '0' .. '9' )+
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
            	    // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:
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
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:136:4: ( '\"' ( . )* '\"' )
            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:136:6: '\"' ( . )* '\"'
            {
            match('\"'); 

            // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:136:10: ( . )*
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
            	    // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:136:10: .
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
        // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | WS | COMMENT | Ident | Int | Str )
        int alt5=31;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:10: T__9
                {
                mT__9(); 


                }
                break;
            case 2 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:15: T__10
                {
                mT__10(); 


                }
                break;
            case 3 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:21: T__11
                {
                mT__11(); 


                }
                break;
            case 4 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:27: T__12
                {
                mT__12(); 


                }
                break;
            case 5 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:33: T__13
                {
                mT__13(); 


                }
                break;
            case 6 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:39: T__14
                {
                mT__14(); 


                }
                break;
            case 7 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:45: T__15
                {
                mT__15(); 


                }
                break;
            case 8 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:51: T__16
                {
                mT__16(); 


                }
                break;
            case 9 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:57: T__17
                {
                mT__17(); 


                }
                break;
            case 10 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:63: T__18
                {
                mT__18(); 


                }
                break;
            case 11 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:69: T__19
                {
                mT__19(); 


                }
                break;
            case 12 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:75: T__20
                {
                mT__20(); 


                }
                break;
            case 13 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:81: T__21
                {
                mT__21(); 


                }
                break;
            case 14 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:87: T__22
                {
                mT__22(); 


                }
                break;
            case 15 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:93: T__23
                {
                mT__23(); 


                }
                break;
            case 16 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:99: T__24
                {
                mT__24(); 


                }
                break;
            case 17 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:105: T__25
                {
                mT__25(); 


                }
                break;
            case 18 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:111: T__26
                {
                mT__26(); 


                }
                break;
            case 19 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:117: T__27
                {
                mT__27(); 


                }
                break;
            case 20 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:123: T__28
                {
                mT__28(); 


                }
                break;
            case 21 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:129: T__29
                {
                mT__29(); 


                }
                break;
            case 22 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:135: T__30
                {
                mT__30(); 


                }
                break;
            case 23 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:141: T__31
                {
                mT__31(); 


                }
                break;
            case 24 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:147: T__32
                {
                mT__32(); 


                }
                break;
            case 25 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:153: T__33
                {
                mT__33(); 


                }
                break;
            case 26 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:159: T__34
                {
                mT__34(); 


                }
                break;
            case 27 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:165: WS
                {
                mWS(); 


                }
                break;
            case 28 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:168: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 29 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:176: Ident
                {
                mIdent(); 


                }
                break;
            case 30 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:182: Int
                {
                mInt(); 


                }
                break;
            case 31 :
                // /Users/tvdstorm/SEA/courses/sc/2012-2013/repos/sea-of-ql/prototypes/QLJava/src/org/uva/sea/ql/parser/antlr/QL.g:1:186: Str
                {
                mStr(); 


                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\1\33\6\uffff\1\35\1\uffff\1\37\1\uffff\1\41\6\27\17\uffff"+
        "\4\27\1\56\7\27\1\uffff\1\66\1\67\1\27\1\71\1\72\1\27\1\74\2\uffff"+
        "\1\75\2\uffff\1\76\3\uffff";
    static final String DFA5_eofS =
        "\77\uffff";
    static final String DFA5_minS =
        "\1\11\1\75\6\uffff\1\52\1\uffff\1\75\1\uffff\1\75\1\157\1\154\1"+
        "\141\1\146\1\164\1\162\17\uffff\1\157\1\163\1\154\1\162\1\60\1\164"+
        "\1\162\1\165\1\154\1\145\1\163\1\155\1\uffff\2\60\1\145\2\60\1\145"+
        "\1\60\2\uffff\1\60\2\uffff\1\60\3\uffff";
    static final String DFA5_maxS =
        "\1\175\1\75\6\uffff\1\52\1\uffff\1\75\1\uffff\1\75\1\157\1\154\1"+
        "\157\1\156\1\164\1\162\17\uffff\1\157\1\163\1\154\1\162\1\172\1"+
        "\164\1\162\1\165\1\154\1\145\1\163\1\155\1\uffff\2\172\1\145\2\172"+
        "\1\145\1\172\2\uffff\1\172\2\uffff\1\172\3\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\uffff\1\12\1\uffff\1\15\7\uffff"+
        "\1\30\1\31\1\32\1\33\1\35\1\36\1\37\1\2\1\1\1\34\1\11\1\14\1\13"+
        "\1\17\1\16\14\uffff\1\24\7\uffff\1\25\1\26\1\uffff\1\20\1\21\1\uffff"+
        "\1\23\1\27\1\22";
    static final String DFA5_specialS =
        "\77\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\26\2\uffff\1\26\22\uffff\1\26\1\1\1\31\3\uffff\1\2\1\uffff"+
            "\1\3\1\4\1\5\1\6\1\uffff\1\7\1\uffff\1\10\12\30\1\11\1\uffff"+
            "\1\12\1\13\1\14\2\uffff\32\27\6\uffff\1\27\1\15\2\27\1\16\1"+
            "\17\2\27\1\20\11\27\1\21\1\22\6\27\1\23\1\24\1\25",
            "\1\32",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\34",
            "",
            "\1\36",
            "",
            "\1\40",
            "\1\42",
            "\1\43",
            "\1\44\15\uffff\1\45",
            "\1\46\7\uffff\1\47",
            "\1\50",
            "\1\51",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\70",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\73",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | WS | COMMENT | Ident | Int | Str );";
        }
    }
 

}