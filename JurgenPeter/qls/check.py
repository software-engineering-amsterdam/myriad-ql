from qls.traversals.symbol_checker import SymbolChecker as QlsSymbolChecker
from qls.traversals.type_checker import TypeChecker as QlsTypeChecker


def check(layout, symboltable, errors=[]):
    QlsSymbolChecker(symboltable, errors).check(layout)

    if any(error.critical for error in errors):
        return errors

    QlsTypeChecker(symboltable, errors).check(layout)
    return errors
