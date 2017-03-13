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
# Adriaan TODO: Line numbers tonen in error messages van TypeChecker

# Adriaan TODO: Export of values ?
# Adriaan TODO: Make all fields mandatory
# Adriaan TODO: Highlight betreffende error line
