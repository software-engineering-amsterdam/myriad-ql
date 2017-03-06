from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QWizard
from PyQt5.QtWidgets import QWizardPage


class QuestionairWizard(QWizard):
    def __init__(self, parent=None):
        super(QuestionairWizard, self).__init__(parent)
        self.init_uit()

    def init_uit(self):
        self.resize(640, 480)
        self.setWindowTitle('Leuker kunnen we het niet maken')
        self.center()
        self.show()

    def center(self):
        frameGm = self.frameGeometry()
        screen = QApplication.desktop().screenNumber(QApplication.desktop().cursor().pos())
        centerPoint = QApplication.desktop().screenGeometry(screen).center()
        frameGm.moveCenter(centerPoint)
        self.move(frameGm.topLeft())

    def add_page(self, page):
        self.addPage(page)


class Page(QWizardPage):
    def __init__(self, title, subtitle, parent=None):
        super(Page, self).__init__(parent)
        self.setTitle(title)
        self.setSubTitle(subtitle)

    def set_layout(self, node):
        self.setLayout(node)
#         gui.build(ql_ast)


