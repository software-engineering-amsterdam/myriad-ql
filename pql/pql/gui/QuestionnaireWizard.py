from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWizard
from PyQt5.QtWidgets import QWizardPage


class QuestionnaireWizard(QWizard):
    def __init__(self, parent=None):
        super(QuestionnaireWizard, self).__init__(parent)
        self.init_uit()

    def init_uit(self):
        self.resize(800 , 600)
        self.setWindowTitle('Leuker kunnen we het niet maken')
        self.center()

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())

    def add_page(self, page):
        self.addPage(page)


class Page(QWizardPage):
    def __init__(self, title, parent=None):
        super(Page, self).__init__(parent)
        self.setTitle(title.name)
        self.layout = QVBoxLayout()

    def add_layout(self, layout):
        self.layout.addLayout(layout)

    def set_layout(self):
        self.setLayout(self.layout)
