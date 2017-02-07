// $ANTLR 3.4 src/main/java/org/uva/hatt/taxform/antlr/QL.g 2017-02-07 11:01:58

package org.uva.hatt.taxform.antlr;
//import org.uva.sea.ql.ast.expr.*;
//import org.uva.sea.ql.ast.stat.*;
//import org.uva.sea.ql.ast.form.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class QLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COMMENT", "Ident", "Int", "Str", "WS", "'!'", "'!='", "'&&'", "'*'", "'+'", "'-'", "'/'", "'<'", "'<='", "'=='", "'>'", "'>='", "'||'"
    };

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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public QLParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public QLParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[20+1];
         

    }

    public String[] getTokenNames() { return QLParser.tokenNames; }
    public String getGrammarFileName() { return "src/main/java/org/uva/hatt/taxform/antlr/QL.g"; }



    // $ANTLR start "unExpr"
    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:18:1: unExpr returns [Expr result] : ( '+' x= unExpr | '-' x= unExpr | '!' x= unExpr );
    public final Expr unExpr() throws RecognitionException {
        Expr result = null;

        int unExpr_StartIndex = input.index();

        Expr x =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return result; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:19:5: ( '+' x= unExpr | '-' x= unExpr | '!' x= unExpr )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt1=1;
                }
                break;
            case 14:
                {
                alt1=2;
                }
                break;
            case 9:
                {
                alt1=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return result;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }

            switch (alt1) {
                case 1 :
                    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:19:8: '+' x= unExpr
                    {
                    match(input,13,FOLLOW_13_in_unExpr54); if (state.failed) return result;

                    pushFollow(FOLLOW_unExpr_in_unExpr58);
                    x=unExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Pos(x); }

                    }
                    break;
                case 2 :
                    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:20:8: '-' x= unExpr
                    {
                    match(input,14,FOLLOW_14_in_unExpr69); if (state.failed) return result;

                    pushFollow(FOLLOW_unExpr_in_unExpr73);
                    x=unExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Neg(x); }

                    }
                    break;
                case 3 :
                    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:21:8: '!' x= unExpr
                    {
                    match(input,9,FOLLOW_9_in_unExpr84); if (state.failed) return result;

                    pushFollow(FOLLOW_unExpr_in_unExpr88);
                    x=unExpr();

                    state._fsp--;
                    if (state.failed) return result;

                    if ( state.backtracking==0 ) { result = new Not(x); }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, unExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "unExpr"



    // $ANTLR start "mulExpr"
    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:25:1: mulExpr returns [Expr result] : lhs= unExpr (op= ( '*' | '/' ) rhs= unExpr )* ;
    public final Expr mulExpr() throws RecognitionException {
        Expr result = null;

        int mulExpr_StartIndex = input.index();

        Token op=null;
        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return result; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:26:5: (lhs= unExpr (op= ( '*' | '/' ) rhs= unExpr )* )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:26:9: lhs= unExpr (op= ( '*' | '/' ) rhs= unExpr )*
            {
            pushFollow(FOLLOW_unExpr_in_mulExpr124);
            lhs=unExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:26:45: (op= ( '*' | '/' ) rhs= unExpr )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==12||LA2_0==15) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:26:47: op= ( '*' | '/' ) rhs= unExpr
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==12||input.LA(1)==15 ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_unExpr_in_mulExpr144);
            	    rhs=unExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { 
            	          if ((op!=null?op.getText():null).equals("*")) {
            	            result = new Mul(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("<=")) {
            	            result = new Div(result, rhs);      
            	          }
            	        }

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, mulExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "mulExpr"



    // $ANTLR start "addExpr"
    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:38:1: addExpr returns [Expr result] : lhs= mulExpr (op= ( '+' | '-' ) rhs= mulExpr )* ;
    public final Expr addExpr() throws RecognitionException {
        Expr result = null;

        int addExpr_StartIndex = input.index();

        Token op=null;
        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return result; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:39:5: (lhs= mulExpr (op= ( '+' | '-' ) rhs= mulExpr )* )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:39:9: lhs= mulExpr (op= ( '+' | '-' ) rhs= mulExpr )*
            {
            pushFollow(FOLLOW_mulExpr_in_addExpr185);
            lhs=mulExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:39:46: (op= ( '+' | '-' ) rhs= mulExpr )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= 13 && LA3_0 <= 14)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:39:48: op= ( '+' | '-' ) rhs= mulExpr
            	    {
            	    op=(Token)input.LT(1);

            	    if ( (input.LA(1) >= 13 && input.LA(1) <= 14) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_mulExpr_in_addExpr203);
            	    rhs=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { 
            	          if ((op!=null?op.getText():null).equals("+")) {
            	            result = new Add(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("-")) {
            	            result = new Sub(result, rhs);      
            	          }
            	        }

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, addExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "addExpr"



    // $ANTLR start "relExpr"
    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:50:1: relExpr returns [Expr result] : lhs= addExpr (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )* ;
    public final Expr relExpr() throws RecognitionException {
        Expr result = null;

        int relExpr_StartIndex = input.index();

        Token op=null;
        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return result; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:51:5: (lhs= addExpr (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )* )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:51:9: lhs= addExpr (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )*
            {
            pushFollow(FOLLOW_addExpr_in_relExpr238);
            lhs=addExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:51:46: (op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==10||(LA4_0 >= 16 && LA4_0 <= 20)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:51:48: op= ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) rhs= addExpr
            	    {
            	    op=(Token)input.LT(1);

            	    if ( input.LA(1)==10||(input.LA(1) >= 16 && input.LA(1) <= 20) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return result;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_addExpr_in_relExpr262);
            	    rhs=addExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { 
            	          if ((op!=null?op.getText():null).equals("<")) {
            	            result = new LT(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("<=")) {
            	            result = new LEq(result, rhs);      
            	          }
            	          if ((op!=null?op.getText():null).equals(">")) {
            	            result = new GT(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals(">=")) {
            	            result = new GEq(result, rhs);      
            	          }
            	          if ((op!=null?op.getText():null).equals("==")) {
            	            result = new Eq(result, rhs);
            	          }
            	          if ((op!=null?op.getText():null).equals("!=")) {
            	            result = new NEq(result, rhs);
            	          }
            	        }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, relExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "relExpr"



    // $ANTLR start "andExpr"
    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:74:1: andExpr returns [Expr result] : lhs= relExpr ( '&&' rhs= relExpr )* ;
    public final Expr andExpr() throws RecognitionException {
        Expr result = null;

        int andExpr_StartIndex = input.index();

        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return result; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:75:5: (lhs= relExpr ( '&&' rhs= relExpr )* )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:75:9: lhs= relExpr ( '&&' rhs= relExpr )*
            {
            pushFollow(FOLLOW_relExpr_in_andExpr300);
            lhs=relExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result =lhs; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:75:46: ( '&&' rhs= relExpr )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==11) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:75:48: '&&' rhs= relExpr
            	    {
            	    match(input,11,FOLLOW_11_in_andExpr306); if (state.failed) return result;

            	    pushFollow(FOLLOW_relExpr_in_andExpr310);
            	    rhs=relExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { result = new And(result, rhs); }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, andExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "andExpr"



    // $ANTLR start "orExpr"
    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:79:1: orExpr returns [Expr result] : lhs= andExpr ( '||' rhs= andExpr )* ;
    public final Expr orExpr() throws RecognitionException {
        Expr result = null;

        int orExpr_StartIndex = input.index();

        Expr lhs =null;

        Expr rhs =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return result; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:80:5: (lhs= andExpr ( '||' rhs= andExpr )* )
            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:80:9: lhs= andExpr ( '||' rhs= andExpr )*
            {
            pushFollow(FOLLOW_andExpr_in_orExpr345);
            lhs=andExpr();

            state._fsp--;
            if (state.failed) return result;

            if ( state.backtracking==0 ) { result = lhs; }

            // src/main/java/org/uva/hatt/taxform/antlr/QL.g:80:48: ( '||' rhs= andExpr )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==21) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src/main/java/org/uva/hatt/taxform/antlr/QL.g:80:50: '||' rhs= andExpr
            	    {
            	    match(input,21,FOLLOW_21_in_orExpr351); if (state.failed) return result;

            	    pushFollow(FOLLOW_andExpr_in_orExpr355);
            	    rhs=andExpr();

            	    state._fsp--;
            	    if (state.failed) return result;

            	    if ( state.backtracking==0 ) { result = new Or(result, rhs); }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, orExpr_StartIndex); }

        }
        return result;
    }
    // $ANTLR end "orExpr"

    // Delegated rules


 

    public static final BitSet FOLLOW_13_in_unExpr54 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_unExpr_in_unExpr58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_unExpr69 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_unExpr_in_unExpr73 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_unExpr84 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_unExpr_in_unExpr88 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unExpr_in_mulExpr124 = new BitSet(new long[]{0x0000000000009002L});
    public static final BitSet FOLLOW_set_in_mulExpr132 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_unExpr_in_mulExpr144 = new BitSet(new long[]{0x0000000000009002L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr185 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_set_in_addExpr193 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr203 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_addExpr_in_relExpr238 = new BitSet(new long[]{0x00000000001F0402L});
    public static final BitSet FOLLOW_set_in_relExpr246 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_addExpr_in_relExpr262 = new BitSet(new long[]{0x00000000001F0402L});
    public static final BitSet FOLLOW_relExpr_in_andExpr300 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_andExpr306 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_relExpr_in_andExpr310 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_andExpr_in_orExpr345 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_orExpr351 = new BitSet(new long[]{0x0000000000006200L});
    public static final BitSet FOLLOW_andExpr_in_orExpr355 = new BitSet(new long[]{0x0000000000200002L});

}