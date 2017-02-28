from PyQt5.QtWidgets import QGridLayout
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QWizard
from PyQt5.QtWidgets import QApplication
# from PyQt5.uic.properties import QtGui
from PyQt5.QtWidgets import QWizardPage


class QuestionairWizard(QWizard):
    def __init__(self, parent=None):
        super(QuestionairWizard, self).__init__(parent)

        self.addPage(FirstPage(self))
        self.addPage(SecondPage(self))

        self.initUI()

    def initUI(self):
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


class FirstPage(QWizardPage):
    def __init__(self, parent):
        super(FirstPage, self).__init__(parent)
        self.setTitle('First page')
        self.setSubTitle('Our lovely questionnaire')

        title = QLabel('Title')
        author = QLabel('Author')
        review = QLabel('Review')

        titleEdit = QLineEdit()
        authorEdit = QLineEdit()
        reviewEdit = QTextEdit()

        grid = QGridLayout()
        grid.setSpacing(10)

        grid.addWidget(title, 1, 0)
        grid.addWidget(titleEdit, 1, 1)

        grid.addWidget(author, 2, 0)
        grid.addWidget(authorEdit, 2, 1)

        grid.addWidget(review, 3, 0)
        grid.addWidget(reviewEdit, 3, 1, 5, 1)

        self.setLayout(grid)

class SecondPage(QWizardPage):
    def __init__(self, parent):
        super(SecondPage, self).__init__(parent)
        self.setTitle('Second page')
        self.setSubTitle('OMG second page')
