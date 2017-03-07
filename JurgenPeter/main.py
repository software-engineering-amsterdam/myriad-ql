from ql.grammar import parse_file as parse_ql
from ql.visitors.printer import Printer
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from ql.visitors.dependency_checker import DependencyChecker
from qls.grammar import parse_file as parse_qls
from gui.formapp import FormApp


def main():

    filename = "exampleForm.txt"

    form = parse_ql(filename)
    Printer().visit(form)

    errors = []
    symboltable = {}

    SymbolChecker(symboltable, errors).visit(form)
    TypeChecker(symboltable, errors).visit(form)
    DependencyChecker(errors).visit(form)

    for error in errors:
        print(error)

    if any(error.critical for error in errors):
        return

    layout = parse_qls("examplestylesheet")

    app = FormApp(form, layout=layout)
    app.start()

if __name__ == "__main__":
    main()
