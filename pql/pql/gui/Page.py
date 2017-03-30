# coding=utf-8
from PyQt5.QtWidgets import QWizardPage, QMainWindow, QWidget, QScrollArea, QVBoxLayout


class Page(QWizardPage, QMainWindow):
    def __init__(self, title, parent=None):
        super(Page, self).__init__(parent)
        self.setTitle(title.name)
        self.conditional_if_list = list(tuple())
        self.conditional_if_else_list = list(tuple())

    def set_layout(self, layout):
        widget = QWidget()
        scroll = QScrollArea()
        widget.setLayout(layout)
        scroll.setWidget(widget)
        scroll.setWidgetResizable(True)
        scroll_layout = QVBoxLayout()
        scroll_layout.addWidget(scroll)
        self.setLayout(scroll_layout)

    def add_conditional_if(self, node):
        self.conditional_if_list.append(node)

    def add_conditional_if_ese(self, node):
        self.conditional_if_else_list.append(node)

    def trigger_conditionals(self, evaluator, environment):
        self.trigger_conditional_if(evaluator, environment)
        self.trigger_conditional_if_else(evaluator, environment)

    def trigger_conditional_if(self, evaluator, environment):
        for if_block_container, node in self.conditional_if_list:
            result = node.condition.apply(evaluator, environment)
            enabled = (result is not None and result)
            self.toggle_container(if_block_container, enabled)

    def trigger_conditional_if_else(self, evaluator, environment):
        for if_container, else_container, node in self.conditional_if_else_list:
            result = node.condition.apply(evaluator, environment)
            enabled = (result is not None and result)
            self.toggle_container(if_container, enabled)
            self.toggle_container(else_container, not enabled)

    def toggle_container(self, container, enabled):
        container.setEnabled(enabled)
        if enabled:
            container.show()
        else:
            container.hide()
