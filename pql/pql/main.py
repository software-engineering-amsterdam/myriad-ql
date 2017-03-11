# coding=utf-8
from sys import argv
from sys import exit

from PyQt5.QtWidgets import QApplication
from pql.gui.FileWindow import FileWindow


if __name__ == '__main__':
    app = QApplication(argv)
    file_window = FileWindow(argv)
    exit(app.exec_())


#TODO : Line numbers in AST Node object stoppen
#TODO: Line numbers tonen in error messages van IdentifierChecker
#TODO: Line numbers tonen in error messages van TypeChecker
#TODO: Eventueel wanener Quiestionarre wordt afgesloten de FileWindow weer openen (of gewoon open laten)
#TODO: IdentifierChecker laat nu in errors de objecten zien, niet de naam van property
# e.g : Key: placeHolder2 contained multiple entries, the following: [<pql.ast.ast.Money object at 0x0000029DAA9BA9B0>, <pql.ast.ast.Money object at 0x0000029DAA9C9198>]
#TODO: Checken of alle properties die in een Expression zitten bestaan
