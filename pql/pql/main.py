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
# TODO: Value opsplitsen in aparte objecten voor de literals / value hernoemen naar literal
# TODO: Field opsplitsen in assignment en question
# TODO: String support toevoegen
# TODO: Typechecker kijken naar ons check systeem en evt minder generic maken
# TODO: Environment een class maken en een update value methode geven
# TODO: Evaluator regel 92 de is None checks weghalen
# TODO: Evaluator latere declaraties die worden niet meegeven ( in een loop evaluaten)

# NICE TO HAVES ---------------------------------
# TODO: LOW PRIO: Eventueel If / IfElse logic combineren in typechecker
# TODO: LOWEST PRIO : ast.apply(typevisitor) ipv Typenvironment
# TODO: NICE TO HAVE Dynamic types in operators ipv python operators
# TODO: Export of values ?
# TODO: Make all fields mandatory
# TODO: NICE TO HAVE Highlight betreffende error line
