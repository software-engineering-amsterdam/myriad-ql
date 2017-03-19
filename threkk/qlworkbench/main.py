import click
from ql.lexer import QLLexer
from ql.parser import QLParser
from ui.visitor import UIVisitor


@click.command()
@click.argument('file', metavar='<file>', type=click.File('r'))
def cli(file):
    """
    This program generates a formulary based on QL files. It parses the given
    <file> and generates a graphical interface based on the content.
    """
    data = file.read()
    lex = QLLexer()
    par = QLParser(lex.tokens)
    ast = par.parse(data, lexer=lex.lexer)

    if ast.get_warnings():
        for warn in ast.get_warnings():
            print(warn)

    if ast.get_errors():
        for error in ast.get_errors():
            print(error)
    else:
        ui_generator = UIVisitor(ast)
        ui = ui_generator.execute()
        ui.run()


if __name__ == '__main__':
    cli()
