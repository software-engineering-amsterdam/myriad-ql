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


#TODO: Line numbers tonen in error messages van TypeChecker
#TODO: Checken of alle properties die in een Expression zitten bestaan (circular reference ding)
#TODO: Export of values ?
