import ply.lex as lex


class QLLexer(object):

    # Reserved words
    reserved = {
        'boolean': 'BOOLEAN',
        'date': 'DATE',
        'decimal': 'DECIMAL',
        'form': 'FORM',
        'if': 'IF',
        'integer': 'INTEGER',
        'money': 'MONEY',
        'string': 'STRING'
    }

    # List of tokens used in the lexer.
    tokens = (
        'LBRACK',    # {
        'RBRACK',    # }
        'LPAREN',    # (
        'RPAREN',    # )
        'COLON',     # :
        'AND',       # &&
        'OR',        # ||
        'NOT',       # !
        'LT',        # <
        'GT',        # >
        'LET',       # <=
        'GET',       # >=
        'EQ',        # ==
        'NEQ',       # !=
        'PLUS',      # +
        'MINUS',     # -
        'MULT',      # *
        'DIV',       # /
        'ASSIGN',    # =
        'ID',        # anyString
        'QUESTION',  # "A question?"
        'COMMENT',   # // This is a comment
        'WHITESPACE'
    ) + tuple(reserved.values())

    # Rules that define the tokens.
    t_FORM = r'form'
    t_LBRACK = r'\{'
    t_RBRACK = r'\}'
    t_LPAREN = r'\('
    t_RPAREN = r'\)'
    t_COLON = r'\:'
    t_AND = r'&&'
    t_OR = r'\|\|'
    t_LT = r'<'
    t_GT = r'>'
    t_LET = r'<='
    t_GET = r'>='
    t_EQ = r'=='
    t_NEQ = r'!='
    t_PLUS = r'\+'
    t_MINUS = r'-'
    t_MULT = r'\*'
    t_DIV = r'/'
    t_ASSIGN = r'='
    t_QUESTION = r'\"[\w\s\.\,\:\?\!\-\+\(\)/]+\"'

    def t_ID(self, t):
        r'[a-zA-Z_][a-zA-Z_0-9]*'
        t.type = self.reserved.get(t.value, 'ID')  # Check for reserved words
        return t

    def t_COMMENT(self, t):
        r'\/\/.*'
        pass

    def t_WHITESPACE(self, t):
        r'\s+'
        pass

    # Error handling rule
    def t_error(self, t):
        print('Illegal character "{value}"'.format(value=t.value[0]))
        t.lexer.skip(1)

    def __init__(self, **kwargs):
        self.lexer = lex.lex(module=self, debug=0, **kwargs)

    def getLexer(self):
        return self.lexer


if __name__ == '__main__':
    data = """form Box1HouseOwning {
        hasSoldHouse: "Did you sell a house in 2010?" boolean
        hasBoughtHouse: "Did you by a house in 2010?" boolean
        sellingPrice: "Price the house was sold for:" money
        // This is a comment.
        hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?"
            boolean
        if (hasSoldHouse) {
            privateDebt: "Private debts for the sold house:" money
            valueResidue: "Value residue:" money(sellingPrice - privateDebt)
        }
    }"""
    lexer = QLLexer()
    lexer.getLexer().input(data)
    while True:
            tok = lexer.getLexer().token()
            if not tok:
                break
            print(tok)
