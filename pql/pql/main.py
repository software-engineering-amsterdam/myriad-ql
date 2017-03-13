# coding=utf-8
from sys import argv
from sys import exit

from PyQt5.QtWidgets import QApplication
from pql.gui.Editor import Editor


if __name__ == '__main__':
    app = QApplication(argv)
    file_window = Editor(argv)
    file_window.show()
    exit(app.exec_())

# Jeffrey TODO: Checken of alle properties die in een Expression zitten bestaan (circular reference ding)
# Adrian TODO: Line numbers tonen in error messages van TypeChecker

# Adrian TODO: Export of values ?
# Adrian TODO: Make all fields mandatory
# Adrian TODO: Highlight betreffende error line




# NOTES:
# Value opsplitsen in aparte objecten voor de literals
# Field opsplitsen in assignment en question
# value hernoemen naar literal
# List comprehension eventueel bekijken
# Loopen over ast voor form weghalen
# Eventueel If / IfElse logic combineren in typechecker
# Typechecker kijken naar ons check systeem en evt minder generic maken
# Identifier_dict naar symbol_table
# ast.apply(typevisitor) ipv Typenvironment
# IdentifierChecker weer niet recursief maken
# TypeChecker de TypeEnvironment meegeven
# Environment een class maken en een update value methode geven
# Unary operator rhs hernoemen naar lhs
# Evaluator regel 92 de is None checks weghalen
# Evaluator latere declaraties die worden niet meegeven ( in een loop evaluaten)
# Identifier regel 39 check kan weg, identifier bestaat altijd
# String support toevoegen
# Dynamic types in operators ipv python operators
