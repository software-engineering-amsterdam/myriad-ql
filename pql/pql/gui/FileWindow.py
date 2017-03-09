# -*- coding: utf-8 -*-
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget


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

        try:
            file_read = open(file_path, 'r').read()
        except FileNotFoundError:
            self.list_errors.addItem("File not found.")

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
