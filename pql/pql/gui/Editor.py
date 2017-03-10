from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont
from PyQt5.QtGui import QFontMetrics
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QDockWidget
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QSizePolicy
from PyQt5.QtWidgets import QStatusBar
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QWidget


class Editor(QMainWindow, QWidget):
    def __init__(self, file_dict=None, list_errors=None, parent=None):
        super(Editor, self).__init__(parent)
        if list_errors is None:
            list_errors = list()
        if file_dict is None:
            file_dict = dict()

        self.resize(800, 600)
        self.setWindowTitle("Editor - Leuker kunnen we het niet maken")
        self.setFocusPolicy(Qt.StrongFocus)

        menu_bar = self.menuBar()
        file = menu_bar.addMenu("File")
        evaluate = QAction("Evaluate", self)
        evaluate.setShortcuts(["Ctrl+S", "Cmd+S"])
        file.addAction(evaluate)
        file.triggered[QAction].connect(lambda q, file_path=file_dict["file_path"]: self.evaluate(q, file_path))

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
        self.text_editor.cursorPositionChanged.connect(self.update_text_cursor_position)

        statusBar = QStatusBar()
        self.cursor_position = QLabel()
        self.cursor_position.setSizePolicy(QSizePolicy.Expanding, QSizePolicy.Expanding)
        self.cursor_position.setAlignment(Qt.AlignRight | Qt.AlignVCenter)
        self.cursor_position.setText(self.update_text_cursor_position())
        statusBar.addWidget(self.cursor_position, 1)
        self.setStatusBar(statusBar)

        self.setCentralWidget(self.text_editor)
        items = QDockWidget("Error list", self)
        list_widget = QListWidget()
        list_widget.setStyleSheet("QListWidget {color:red;}")
        list_widget.addItems(list_errors)
        items.setWidget(list_widget)
        self.addDockWidget(Qt.BottomDockWidgetArea, items)

    def evaluate(self, q, file_path):
        contents = self.text_editor.toPlainText()
        with open(file_path, 'w') as open_file:
            open_file.truncate()
            open_file.write(contents)
        self.parent().show()
        self.parent().handle_file(file_path)
        self.close()

    def update_text_cursor_position(self):
        text_cursor = self.text_editor.textCursor()
        cursor_row = text_cursor.blockNumber()
        cursor_column = text_cursor.positionInBlock()
        position = "{}:{}".format(cursor_row, cursor_column)
        self.cursor_position.setText(position)
        return position

    def closeEvent(self, event):
        event.accept()

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
