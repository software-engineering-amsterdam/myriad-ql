# -*- coding: utf-8 -*-
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget

from gui.Questionnaire import Questionnaire
from pql.parser.parser import parse
from pql.identifierchecker.identifierchecker import IdentifierChecker


# class FileButton(QPushButton):
#     def __init__(self, title, parent):
#         super(FileButton, self).__init__(title, parent)


class FileWindow(QWidget):
    def __init__(self, parent=None):
        super(FileWindow, self).__init__(parent)

        self.resize(280, 144)
        self.setWindowTitle("Leuker kunnen we het niet maken")
        self.center()

        self.button_load_file = QPushButton("Load file", self)
        self.button_load_file.resize(self.button_load_file.sizeHint())
        self.button_load_file.clicked.connect(self.handleButton)

        self.list_errors = QListWidget()
        self.list_errors.setStyleSheet("QListWidget {color: red}")

        self.main_layout = QVBoxLayout()
        self.main_layout.addWidget(self.button_load_file)
        self.main_layout.addWidget(self.list_errors)

        self.setLayout(self.main_layout)

        self.show()

    def handleButton(self):
        file_path, file_filter = QFileDialog.getOpenFileName(filter="*.ql")

        open_file = self.openFile(file_path)

        if open_file is not None:
            ql_ast = self.parseFile(open_file)
            ql_ids, ql_errors = self.checkIds(ql_ast)
            if ql_errors:
                self.list_errors.addItems(ql_errors)
            else:
                self.close()
            self.showQuestionnaire(ql_ast)

    def showQuestionnaire(self, ql_ast):
        gui = Questionnaire()
        gui.visit(ql_ast).show()

    def closeEvent(self, event):
        event.accept()

    def checkIds(self, ql_ast):
        try:
            return IdentifierChecker().visit(ql_ast)
        except Exception as e:
            self.list_errors.addItem("Something went wrong with checking of the identifiers...\n\t{}".format(e))

    def openFile(self, file_path):
        try:
            with open(file_path, 'r') as open_file:
                return open_file.read()
        except FileNotFoundError as fnfe:
            pass
        except Exception as e:
            self.list_errors.addItem("Something went wrong with opening...\n\t{}".format(e))

    def parseFile(self, ql_str):
        try:
            return parse(ql_str)
        except Exception as e:
            self.list_errors.addItem("Something went wrong with parsing...\n\t{}".format(e))

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
