from ql.traversals.symbol_checker import SymbolChecker
from ql.traversals.type_checker import TypeChecker
from ql.traversals.dependency_checker import DependencyChecker


def check(form, symboltable, errors=[]):
    SymbolChecker(symboltable, errors).check(form)

    if any(error.critical for error in errors):
        return errors

    TypeChecker(symboltable, errors).check(form)
    DependencyChecker(errors).check(form)
    return errors
