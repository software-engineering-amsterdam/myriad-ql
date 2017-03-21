# coding=utf-8
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QWizard


class Wizard(QWizard):
    def __init__(self, parent=None):
        super(Wizard, self).__init__(parent)
        self.init_ui()
        self.currentIdChanged.connect(self.set_page)
        self.current_page = 0

    def set_page(self, value):
        self.current_page = value

    def init_ui(self):
        self.resize(800, 600)
        self.setWindowTitle('Leuker kunnen we het niet maken')
        self.center()

    def connect_finished(self, fn):
        self.finished.connect(fn)

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())

    def add_page(self, page):
        self.addPage(page)

    def trigger_conditionals(self, evaluator, environment):
        current_page = self.page(self.current_page)
        current_page.trigger_conditionals(evaluator, environment)
