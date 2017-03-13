from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont
from PyQt5.QtGui import QFontMetrics
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QDockWidget
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QSizePolicy
from PyQt5.QtWidgets import QStatusBar
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QWidget

from pql.gui.Questionnaire import Questionnaire
from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.parser.parser import parse
from pql.typechecker.typechecker import TypeChecker


class Editor(QMainWindow, QWidget):
    def __init__(self, argv, parent=None):
        super(Editor, self).__init__(parent)
        self.resize(800, 600)
        self.setWindowTitle("Editor - Leuker kunnen we het niet maken")
        self.setFocusPolicy(Qt.StrongFocus)
        self.center()
        self.file_path = None

        self.menu_file = self.menuBar().addMenu("File")
        self.action_evaluate = QAction("Evaluate", self)
        self.text_editor = QTextEdit()

        self.init_text_editor()
        self.init_status_bar()
        self.list_errors = QListWidget()
        self.setCentralWidget(self.text_editor)
        self.set_error_list(self.list_errors)
        self.add_eval_action()
        self.add_load_action()
        if len(argv) > 1:
            self.load_file(argv[1])

    def set_error_list(self, list_errors):
        items = QDockWidget("Error list", self)
        list_errors.setStyleSheet("QListWidget {color:red;}")
        items.setWidget(list_errors)
        self.addDockWidget(Qt.BottomDockWidgetArea, items)

    def init_text_editor(self):
        font = QFont()
        font.setFamily("Courier")
        font.setStyleHint(QFont.Monospace)
        font.setFixedPitch(True)
        font.setPointSize(12)
        metrics = QFontMetrics(font)
        self.text_editor.acceptRichText()
        self.text_editor.setFont(font)
        self.text_editor.setTabStopWidth(4 * metrics.width(' '))
        self.text_editor.cursorPositionChanged.connect(self.update_text_cursor_position)
        self.text_editor.textChanged.connect(self.on_text_changed)
        self.on_text_changed()

    def init_status_bar(self):
        statusBar = QStatusBar()
        self.cursor_position = QLabel()
        self.cursor_position.setSizePolicy(QSizePolicy.Expanding, QSizePolicy.Expanding)
        self.cursor_position.setAlignment(Qt.AlignRight | Qt.AlignVCenter)
        self.cursor_position.setText(self.update_text_cursor_position())
        statusBar.addWidget(self.cursor_position, 1)
        self.setStatusBar(statusBar)

    def on_text_changed(self):
        self.action_evaluate.setEnabled(len(self.text_editor.toPlainText()) > 0)

    def add_load_action(self):
        menu_bar_load_action = QAction("Load file", self)
        menu_bar_load_action.setShortcuts(["Ctrl+L", "Cmd+L"])
        menu_bar_load_action.triggered.connect(self.file_selection)
        self.menu_file.addAction(menu_bar_load_action)

    def add_eval_action(self):
        self.action_evaluate.setShortcuts(["Ctrl+E", "Cmd+E"])
        self.menu_file.addAction(self.action_evaluate)
        self.action_evaluate.triggered.connect(self.evaluate)

    def evaluate(self):
        self.list_errors.clear()
        contents = self.text_editor.toPlainText()
        self.write_contents_to_file(contents, self.file_path)
        ast = self.create_ast(contents)
        if ast is not None:
            identifier_errors = self.check_ids(ast)
            if identifier_errors:
                self.list_errors.addItems(identifier_errors)
            else:
                type_errors = self.check_type(ast)
                if type_errors:
                    self.list_errors.addItems(type_errors)
                else:
                    self.form = Questionnaire().visit(ast)
                    self.form.show()

    def write_contents_to_file(self, contents, file_path):
        if file_path is not None:
            with open(file_path, 'w') as file_contents:
                file_contents.truncate()
                file_contents.write(contents)

    def file_selection(self):
        file_path, _ = QFileDialog.getOpenFileName(filter="*.ql")
        self.load_file(file_path)

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

    def load_file(self, file_path):
        self.list_errors.clear()
        self.file_path = file_path
        file_contents = self.open_file(file_path)
        if file_contents is not None:
            self.text_editor.setText(file_contents)

    def check_ids(self, ql_ast):
        try:
            return IdentifierChecker().visit(ql_ast)
        except Exception as e:
            self.list_errors.addItem("Checking ids:\n    {}".format(e))

    def check_type(self, ql_ast):
        try:
            return TypeChecker().visit(ql_ast)
        except Exception as e:
            self.list_errors.addItem("Checking types:\n    {}".format(e))

    def open_file(self, file_path):
        try:
            with open(file_path, 'r') as open_file:
                return open_file.read()
        except FileNotFoundError as fnfe:
            # self.list_errors.addItem("Given file could not be found:\n    {}".format(fnfe))
            print("Given file could not be found:\n    {}".format(fnfe))
        except Exception as e:
            self.list_errors.addItem("Opening:\n    {}".format(e))

    def create_ast(self, ql_str):
        try:
            return parse(ql_str)
        except Exception as e:
            self.list_errors.addItem("Parsing:\n    {}".format(e))
