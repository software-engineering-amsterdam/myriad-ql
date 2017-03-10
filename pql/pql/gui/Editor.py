from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QDockWidget
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QTextEdit


class Editor(QMainWindow):
    def __init__(self, open_file=str(), list_errors=list(), parent=None):
        super(Editor, self).__init__(parent)
        self.resize(800, 600)
        self.setWindowTitle("Editor - Leuker kunnen we het niet maken")
        self.setFocusPolicy(Qt.StrongFocus)

        bar = self.menuBar()
        file = bar.addMenu("File")
        file.addAction(QAction("Evaluate", self))
        file.triggered[QAction].connect(self.processtrigger)

        self.text_editor = QTextEdit()
        self.text_editor.setText(open_file)
        self.setCentralWidget(self.text_editor)
        self.items = QDockWidget("Error list", self)
        self.listWidget = QListWidget()
        self.listWidget.addItems(list_errors)
        self.items.setWidget(self.listWidget)
        self.addDockWidget(Qt.BottomDockWidgetArea, self.items)

    def processtrigger(self, q):
        print(q.text() + " is triggered")

    def closeEvent(self, event):
        event.accept()

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
