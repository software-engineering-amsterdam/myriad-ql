from PyQt5.QtWidgets import QWidget
from PyQt5.QtWidgets import QDesktopWidget
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtCore import Qt


class QuestionairWindows(QWidget):

    def __init__(self):
        super(QuestionairWindows, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.resize(640, 480)
        self.setWindowTitle('Leuker kunnen we het niet maken')
        self.center()
        self.show()

    def center(self):
        frame_geo = self.frameGeometry()
        center_point = QDesktopWidget().availableGeometry().center()
        frame_geo.moveCenter(center_point)
        self.move(frame_geo.topLeft())
