from lexer.tokens import *
from combinator.combinators import *
# from combinator.Reserved import Reserved
# from combinator.Lazy import Lazy
# from combinator.Tag import Tag
# from combinator.Opt import Opt
# from combinator.Exp import Exp
# from combinator.Phrase import Phrase
from ast.Aexp import IntAexp
from ast.Aexp import VarAexp
from ast.Aexp import BinopAexp
from ast.Bexp import RelopBexp
from ast.Bexp import AndBexp
from ast.Bexp import OrBexp, NotBexp
from ast.Statement import AssignStatement, CompoundStatement, IfStatement, WhileStatement
from functools import reduce

# colon = Tag(COLON)
# plus = Tag(PLUS)
# minus = Tag(MINUS)
# assign = Tag(ASSIGN)
# lparen = Tag(LPAREN)
# rparen = Tag(RPAREN)
# lcurly = Tag(LCURLY)
# rculry = Tag(RCURLY)
# bool = Tag(BOOLEAN)
# money = Tag(MONEY)
# form = Tag(FORM)
# ifs = Tag(IF)
# field = Tag(FIELD)
# id = Tag(IDENTIFIER)


def keyword(kw):
    return Reserved(kw, RESERVED)

field   = Tag(FIELD)
num     = Tag(INT) ^ (lambda i: int(i))
id      = Tag(ID)

# Form

# def f_parse(tokens):
#     ast = fparser()(tokens, 0)
#     return ast
#
# def fparser(tokens, pos):
#     stmt_list = fstmt(tokens, pos);
#     phrase = fphrase(stmt_list())
#     return phrase


# Arithmetic

def aexp_value():
    return (num ^ (lambda i: IntAexp(i))) | \
           (id  ^ (lambda v: VarAexp(v)))

def process_group(parsed):
    ((_, p), _) = parsed
    return p

def aexp_group():
    return keyword('(') + Lazy(aexp) + keyword(')') ^ process_group

def aexp_term():
    return aexp_value() | aexp_group()

def process_binop(op):
    return lambda l, r: BinopAexp(op, l, r)

def any_operator_in_list(ops):
    op_parsers = [keyword(op) for op in ops]
    parser = reduce(lambda l, r: l | r, op_parsers)
    return parser

aexp_precedence_levels = [
    ['*', '/'],
    ['+', '-'],
]

def precedence(value_parser, precedence_levels, combine):
    def op_parser(precedence_level):
        return any_operator_in_list(precedence_level) ^ combine
    parser = value_parser * op_parser(precedence_levels[0])
    for precedence_level in precedence_levels[1:]:
        parser = parser * op_parser(precedence_level)
    return parser

def aexp():
    return precedence(aexp_term(),
                      aexp_precedence_levels,
                      process_binop)

# Boolean

def process_relop(parsed):
    ((left, op), right) = parsed
    return RelopBexp(op, left, right)

def bexp_relop():
    relops = ['<', '<=', '>', '>=', '=', '!=']
    return aexp() + any_operator_in_list(relops) + aexp() ^ process_relop

def bexp_not():
    return keyword('not') + Lazy(bexp_term) ^ (lambda parsed: NotBexp(parsed[1]))

def bexp_group():
    return keyword('(') + Lazy(bexp) + keyword(')') ^ process_group

def bexp_term():
    return bexp_not()   | \
           bexp_relop() | \
           bexp_group()

bexp_precedence_levels = [
    ['and'],
    ['or'],
]

def process_logic(op):
    if op == 'and':
        return lambda l, r: AndBexp(l, r)
    elif op == 'or':
        return lambda l, r: OrBexp(l, r)
    else:
        raise RuntimeError('unknown logic operator: ' + op)

def bexp():
    return precedence(bexp_term(),
                      bexp_precedence_levels,
                      process_logic)

# Statements

def assign_stmt():
    def process(parsed):
        ((name, _), exp) = parsed
        return AssignStatement(name, exp)
    return id + keyword(':=') + aexp() ^ process

def stmt_list():
    separator = keyword(';') ^ (lambda x: lambda l, r: CompoundStatement(l, r))
    return Exp(stmt(), separator)

def if_stmt():
    def process(parsed):
        (((((_, condition), _), true_stmt), false_parsed), _) = parsed
        if false_parsed:
            (_, false_stmt) = false_parsed
        else:
            false_stmt = None
        return IfStatement(condition, true_stmt, false_stmt)
    return keyword('if') + bexp() + \
           keyword('then') + Lazy(stmt_list) + \
           Opt(keyword('else') + Lazy(stmt_list)) + \
           keyword('end') ^ process

def while_stmt():
    def process(parsed):
        ((((_, condition), _), body), _) = parsed
        return WhileStatement(condition, body)
    return keyword('while') + bexp() + \
           keyword('do') + Lazy(stmt_list) + \
           keyword('end') ^ process

def stmt():
    return assign_stmt() | \
           if_stmt()     | \
           while_stmt()

# Parser

def parser():
    return Phrase(stmt_list())

def imp_parse(tokens):
    ast = parser()(tokens, 0)
    return ast