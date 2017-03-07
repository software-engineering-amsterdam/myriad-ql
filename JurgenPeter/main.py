from json import dump
from ql.grammar import parse_file as parse_ql
from ql.visitors.printer import Printer
from ql.visitors.symbol_checker import SymbolChecker
from ql.visitors.type_checker import TypeChecker
from ql.visitors.dependency_checker import DependencyChecker
from qls.grammar import parse_file as parse_qls
from gui.formapp import FormApp


def export(filename, dictionary):
    with open(filename, "w") as fp:
        dump(dictionary, fp)


def main():

    filename = "exampleForm.ql"
    # filename = "wrongForm.ql"

    form = parse_ql(filename)
    Printer().print(form)

    errors = []
    symboltable = {}

    SymbolChecker(symboltable, errors).check(form)
    TypeChecker(symboltable, errors).check(form)
    DependencyChecker(errors).check(form)

    for error in errors:
        print(error)

    if any(error.critical for error in errors):
        return

    layout = parse_qls("examplestylesheet.qls")
    # layout = None

    app = FormApp(form, layout=layout, on_exit=lambda form_app: export("output.json", form_app.environment))
    app.start()

if __name__ == "__main__":
    main()
