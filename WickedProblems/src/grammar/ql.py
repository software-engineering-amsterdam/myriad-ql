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
<question> ::= <label> <variable> ':' <data_type>
<question_value> ::= expr | data_type
<type> ::= <Ident>
<identifier> ::= <Ident>
<data_type> ::= <boolean>|<money>
<boolean> ::= 'true' | 'false'
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

        # Keywords
        if_key = Keyword("if")
        true_key = Keyword("true")
        false_key = Keyword("false")

        
        identifier = Word(alphanums)
        variable = identifier("variable")
        formType = identifier("type")
        label = QuotedString('"')("label")
        data_type = Literal('boolean')(
            "data_type") | Literal('money')("data_type")
        mathOperator = Group(Literal('+')("add_op") | Literal('-')("sub_op")
                             | Literal('/')("div_op") | Literal('*')("mul_op"))("math_op")
        comparisonOperators = (Literal('==') | Literal('!=') | Literal(
            '>=') | Literal('<=')) | Literal('<') | Literal('>')

        exp = Group(variable + (mathOperator |
                                comparisonOperators) + variable)("expression")
        assignment = Group(data_type + equals + lpar +
                           exp + rpar)("assignment")
        question_value = Group((assignment | data_type))("question_value")
        question = Group(label + variable + colon + question_value)("question")
        blockBody = Forward()
        conditional = Group(if_key("conditional_statement") + lpar + (exp | variable) + rpar +
                            lbrace + blockBody + rbrace)("conditional")
        blockBody << ZeroOrMore(question| conditional)('form_elements')
        form = formType + identifier("identifier") + lbrace + blockBody + rbrace("form")
        grammar << form
        return grammar

    def parse(self, input_string):
        return self.grammar.parseString(input_string)
