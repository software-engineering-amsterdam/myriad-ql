# -*- coding: utf-8 -*-
"""
This module is used to launch the QL Workbench. It is a CLI application which
reads a path from the command line. If the file contains errors, they will be
printed in the screen. If not, the application will be launched.
"""
import click
from ql.lexer import QLLexer
from ql.parser import QLParser
from ui.builder import Visitor


@click.command()
@click.argument('file', metavar='<file>', type=click.File('r'))
def cli(file):
    """
    This program generates a formulary based on QL files. It parses the given
    <file> and generates a graphical interface based on the content. In case it
    contains errors, they will be displayed in the screen.
    """
    data = file.read()
    lex = QLLexer()
    par = QLParser(lex.tokens)
    ast = par.parse(data, lexer=lex.lexer)
    ast.typechecker.check()

    if ast.get_warnings():
        for warn in ast.get_warnings():
            click.secho(warn.__str__(), fg='yellow')

    if ast.get_errors():
        for error in ast.get_errors():
            click.secho(error.__str__(), fg='red')
    else:
        ui_generator = Visitor(ast)
        ui = ui_generator.execute()
        ui.run()


if __name__ == '__main__':
    cli()
