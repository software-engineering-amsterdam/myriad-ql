# coding=utf-8
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QFont
from PyQt5.QtGui import QFontMetrics
from PyQt5.QtGui import QTextCursor
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QDockWidget
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QLabel
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QSizePolicy
from PyQt5.QtWidgets import QStatusBar
from PyQt5.QtWidgets import QWidget

from pql.messages.fatal_error import FatalError
from pql.gui.CodeArea import CodeArea
from pql.dependencies.dependencieschecker import DependenciesChecker
from pql.gui.Questionnaire import Questionnaire
from pql.gui.widgets import ErrorWidget
from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.labelchecker.labelchecker import LabelChecker
from pql.parser.parser import parse
from pql.typechecker.type_environment import TypeEnvironment
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
        self.toolbar = self.addToolBar("Actions")
        self.run_action = self.add_run_action()
        self.open_action = self.add_open_action()
        self.text_editor = CodeArea()
        status_bar = self.init_status_bar()
        cursor_position = self.add_cursor_position(status_bar)
        self.init_text_editor(cursor_position)
        self.message_list = QListWidget()
        self.setCentralWidget(self.text_editor)
        self.set_error_list(self.message_list)
        if len(argv) > 1:
            self.load_file(argv[1])

    def set_error_list(self, list_errors):
        items = QDockWidget("Error list", self)
        list_errors.setStyleSheet("QListWidget {color:red;}")
        items.setWidget(list_errors)
        self.addDockWidget(Qt.BottomDockWidgetArea, items)
        list_errors.itemClicked.connect(self.highlight_line)

    def init_text_editor(self, cursor_position_widget):
        font = QFont()
        font.setFamily("Courier")
        font.setStyleHint(QFont.Monospace)
        font.setFixedPitch(True)
        font.setPointSize(12)
        metrics = QFontMetrics(font)
        self.text_editor.setFont(font)
        self.text_editor.setTabStopWidth(4 * metrics.width(' '))
        self.text_editor.cursorPositionChanged. \
            connect(lambda: self.update_cursor_position(cursor_position_widget, self.current_cursor_position()))
        self.text_editor.textChanged.connect(self.on_text_changed)
        self.on_text_changed()

    def init_status_bar(self):
        status_bar = QStatusBar()
        self.setStatusBar(status_bar)
        return status_bar

    def update_cursor_position(self, cursor_position, position_label):
        cursor_position.setText(position_label)

    def add_cursor_position(self, status_bar):
        cursor_position = QLabel()
        cursor_position.setSizePolicy(QSizePolicy.Expanding, QSizePolicy.Expanding)
        cursor_position.setAlignment(Qt.AlignRight | Qt.AlignVCenter)
        cursor_position.setText(self.current_cursor_position())
        status_bar.addWidget(cursor_position, 1)
        return cursor_position

    def on_text_changed(self):
        self.run_action.setEnabled(len(self.text_editor.toPlainText()) > 0)

    def add_open_action(self):
        load_action = QAction("Load file", self)
        load_action.setShortcuts(["Ctrl+O", "Cmd+O"])
        load_action.triggered.connect(self.file_selection)
        self.menu_file.addAction(load_action)
        self.toolbar.addAction(load_action)
        return load_action

    def add_run_action(self):
        run_action = QAction("Run", self)
        run_action.setShortcuts(["Ctrl+R", "Cmd+R"])
        run_action.triggered.connect(self.evaluate)
        self.menu_file.addAction(run_action)
        self.toolbar.addAction(run_action)
        return run_action

    def evaluate(self):
        self.message_list.clear()
        contents = self.text_editor.toPlainText()
        self.write_contents_to_file(contents, self.file_path)
        ast = self.create_ast(contents)
        if ast is not None:
            identifier_errors = self.check_ids(ast)
            if identifier_errors:
                self.add_messages(identifier_errors)
            else:
                label_warnings = self.check_labels(ast)
                if label_warnings:
                    self.add_messages(label_warnings)
                dependencies_errors = self.check_dependencies(ast)
                if not dependencies_errors:
                    type_errors = self.check_type(ast)
                    if type_errors:
                        self.add_messages(type_errors)
                    else:
                        form = Questionnaire(ast).visit()
                        form.show()
                else:
                    self.add_messages(dependencies_errors)

    def write_contents_to_file(self, contents, file_path):
        if file_path is not None:
            with open(file_path, 'w') as file_contents:
                file_contents.truncate()
                file_contents.write(contents)

    def file_selection(self):
        file_path, _ = QFileDialog.getOpenFileName(filter="*.ql")
        self.load_file(file_path)

    def current_cursor_position(self):
        text_cursor = self.text_editor.textCursor()
        cursor_row = text_cursor.blockNumber() + 1
        cursor_column = text_cursor.columnNumber() + 1
        position = "{}:{}".format(cursor_row, cursor_column)
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
        self.message_list.clear()
        self.file_path = file_path
        file_contents = self.open_file(file_path)
        if file_contents is not None:
            self.text_editor.setPlainText(file_contents)

    def check_ids(self, ast):
        return IdentifierChecker(ast).visit()

    def check_type(self, ast):
        return TypeChecker(ast, TypeEnvironment).visit()

    def check_dependencies(self, ast):
        return DependenciesChecker(ast).visit()

    def check_labels(self, ast):
        return LabelChecker(ast).visit()

    def open_file(self, file_path):
        try:
            with open(file_path) as open_file:
                return open_file.read()
        except FileNotFoundError as fnfe:
            self.add_message("Given file could not be found:\n    {}".format(fnfe))
        except Exception as e:
            self.add_message("Opening:\n    {}".format(e))

    def create_ast(self, ql_str):
        try:
            return parse(ql_str)
        except FatalError as fe:
            self.add_message("Parsing:\n    {}".format(fe), fe.location)
        except Exception as e:
            raise e

    def add_message(self, string, location=None):
        widget = ErrorWidget(location)
        widget.setText(string)
        self.message_list.addItem(widget)

    def add_messages(self, message_list):
        for message in message_list:
            self.add_message(message.text, message.location)

    def highlight_line(self, item):
        old_line = self.text_editor.textCursor().blockNumber()
        line_number = item.location.line_number
        line_difference = (line_number - old_line) - 1
        move = QTextCursor.Down if line_difference >= 0 else QTextCursor.Up
        for _ in range(abs(line_difference)):
            self.text_editor.moveCursor(move, QTextCursor.MoveAnchor)
