# -*- coding: utf-8 -*-
"""
This module is used to launch the QL Workbench. It is a CLI application which
reads a path from the command line. If the file contains errors, they will be
printed in the screen. If not, the application will be launched.
"""
import click
from ql.lexer import QLLexer
from ql.parser import QLParser
from typechecker.checker import Checker
from typechecker.lexer_checker import LexerChecker
from typechecker.parser_checker import ParserChecker
from typechecker.ql_checker import QLChecker
from ui.builder import Visitor


@click.command()
@click.argument('file', metavar='<file>', type=click.File('r'))
def cli(file):
    """
    This program generates a formulary based on QL files. It parses the given
    <file> and generates a graphical interface based on the content. In case it
    contains errors, they will be displayed in the screen.
    """
    # Extracts the content of the QL file.
    data = file.read()

    # Initialises the global checker.
    checker = Checker()

    # Creates a lexer checker and a checker.
    lex_checker = LexerChecker(checker)
    lex = QLLexer(lex_checker)

    # Creates a parser checker and a checker.
    par_checker = ParserChecker(checker)
    par = QLParser(lex.tokens, par_checker)

    # Creates an AST, an AST checker and executes it.
    ast = par.parse(data, lexer=lex.lexer)
    ql_checker = QLChecker(ast, checker)
    ql_checker.check()

    if checker.get_warnings():
        for warn in checker.get_warnings():
            click.secho(warn.__str__(), fg='yellow')

    if checker.get_errors():
        for error in checker.get_errors():
            click.secho(error.__str__(), fg='red')
    else:
        ui_generator = Visitor(ast)
        ui = ui_generator.execute()
        ui.run()


if __name__ == '__main__':
    cli()
