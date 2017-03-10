from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont
from PyQt5.QtGui import QFontMetrics
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QDockWidget
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QTextEdit


class Editor(QMainWindow):
    def __init__(self, file_dict=None, list_errors=None, parent=None):
        super(Editor, self).__init__(parent)
        if list_errors is None:
            list_errors = list()
        if file_dict is None:
            file_dict = dict()

        self.resize(800, 600)
        self.setWindowTitle("Editor - Leuker kunnen we het niet maken")
        self.setFocusPolicy(Qt.StrongFocus)

        bar = self.menuBar()
        file = bar.addMenu("File")
        file.addAction(QAction("Evaluate", self))
        file.triggered[QAction].connect(lambda q, file_path=file_dict["file_path"]: self.processtrigger(q, file_path))

        font = QFont()
        font.setFamily("Courier")
        font.setStyleHint(QFont.Monospace)
        font.setFixedPitch(True)
        font.setPointSize(12)
        metrics = QFontMetrics(font)

        self.text_editor = QTextEdit()
        self.text_editor.acceptRichText()
        self.text_editor.setFont(font)
        self.text_editor.setTabStopWidth(4 * metrics.width(' '))
        self.text_editor.setText(file_dict["file_body"])
        self.setCentralWidget(self.text_editor)
        self.items = QDockWidget("Error list", self)
        self.listWidget = QListWidget()
        self.listWidget.addItems(list_errors)
        self.items.setWidget(self.listWidget)
        self.addDockWidget(Qt.BottomDockWidgetArea, self.items)

    def processtrigger(self, q, file_path):
        contents = self.text_editor.toPlainText()
        with open(file_path, 'w') as open_file:
            open_file.truncate()
            open_file.write(contents)
        self.close()

    def closeEvent(self, event):
        event.accept()

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
