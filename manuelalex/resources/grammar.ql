form := "form" <formName> "{}"
formName := "taxOfficeExample"





prog:	'form' WORD { NEWLINE STATEMENT* };
STATEMENT:	QUESTION | IF_STATEMENT;
NEWLINE : [\r\n]+ ;
QUESTION: '"' WORD+ '"' NEWLINE PROPERTY ': ' TYPE NEWLINE;
PROPERTY: WORD
TYPE: "boolean" | "money"
WORD    : [a-Z]+ ;
IF_STATEMENT: 'if (' PROPERTY ') {' NEWLINE QUESTION* NEWLINE '}'