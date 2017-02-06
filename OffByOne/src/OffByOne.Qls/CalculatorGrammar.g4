grammar CalculatorGrammar;
 
@parser::members
{
    protected const int EOF = Eof;
}
 
@lexer::members
{
    protected const int EOF = Eof;
    protected const int HIDDEN = Hidden;
}
 
/*
 * Parser Rules
 */
 
prog: expr+ ;
 
expr : expr op=('*'|'/') expr   # MulDiv
     | expr op=('+'|'-') expr   # AddSub
     | INT                  # int
     | '(' expr ')'         # parens
     ;
 
/*
 * Lexer Rules
 */
INT : [0-9]+;
MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';
WS
    :   (' ' | '\r' | '\n') -> channel(HIDDEN)
    ;