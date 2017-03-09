from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget


# class FileButton(QPushButton):
#     def __init__(self, title, parent):
#         super(FileButton, self).__init__(title, parent)


class FileWindow(QWidget):
    def __init__(self, parent=None):
        super(FileWindow, self).__init__(parent)
        self.init_ui()

    def init_ui(self):
        self.resize(320, 180)
        self.setWindowTitle("Leuker kunnen we het niet maken")
        self.center()

        layout = QVBoxLayout()
        button = QPushButton("Load file", self)
        button.resize(button.sizeHint())
        button.clicked.connect(self.handleButton)
        layout.addWidget(button)
        self.setLayout(layout)

    def handleButton(self):
        file_path, file_filter = QFileDialog.getOpenFileName(filter="*.ql")
        file_read = open(file_path, 'r').read()
        print(file_read)

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
