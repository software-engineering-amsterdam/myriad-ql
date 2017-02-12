from pyparsing import *
from .grammar import Grammar
'''
form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMaintLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }
  "Is this questions being printed?"
    isPrinted: boolean
}
'''

'''
BNF
<form> ::= <type> <identifier> '{' <blockBody> '}'
<blockBody> ::= <question> | <conditional>
<conditional> ::= 'if' '(' <exp> | <boolean> ')' '{' <blockBody> '}'
<question> ::= <label> <variable> ':' <dataType>
<type> ::= <Ident>
<identifier> ::= <Ident>
<dataType> ::= <boolean>|<money>
<boolean> ::= true | false
'''


class QL(Grammar):
    'Grammar class'

    def __init__(self):
        self.grammar = self.compileGrammar()

    def compileGrammar(self):
        grammar = Forward()
        # Literals to be ignored
        lbrace = Literal('{').suppress()
        rbrace = Literal('}').suppress()
        lpar = Literal('(').suppress()
        rpar = Literal(')').suppress()
        colon = Literal(':').suppress()
        equals = Literal('=').suppress()

        identifier = Word(alphanums)
        variable = identifier("variable")
        formType = identifier("type")
        label = QuotedString('"')("label")
        dataType = Literal('boolean')(
            "dataType") | Literal('money')("dataType")
        mathOperator = Group(Literal('+')("addOp") | Literal('-')("minOp")
                             | Literal('/')("divOp") | Literal('*')("mulOp"))("mathOp")
        ifIdent = Literal('if').suppress()("conditionalStatement")
        comparisonOperators = (Literal('==') | Literal('!=') | Literal(
            '>=') | Literal('<=')) | Literal('<') | Literal('>')

        exp = Group(variable + (mathOperator |
                                comparisonOperators) + variable)("expression")
        assignment = Group(dataType + equals + lpar +
                           exp + rpar)("assignment")
        questionValue = Group((assignment | dataType))("questionValue")
        question = Group(label + variable + colon + questionValue)("question")
        blockBody = Forward()
        conditional = Group(ifIdent + lpar + (exp | variable) + rpar +
                            lbrace + Group(blockBody)("body") + rbrace)("conditional")
        blockBody << ZeroOrMore(question | conditional)
        form = formType + identifier("identifier") + lbrace + Group(blockBody)("body") + rbrace("form")
        grammar << form
        return grammar

    def parse(self, input_string):
        return self.grammar.parseString(input_string)
