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
            cond = (result is not None and result)
            if_block_container.setEnabled(cond)
            if cond:
                if_block_container.show()
            else:
                if_block_container.hide()

    def trigger_conditional_if_else(self, evaluator, environment):
        for if_container, else_container, node in self.conditional_if_else_list:
            result = node.condition.apply(evaluator, environment)
            cond = (result is not None and result)
            if_container.setEnabled(cond)
            else_container.setEnabled(not cond)
            if cond:
                if_container.show()
                else_container.hide()
            else:
                if_container.hide()
                else_container.show()
