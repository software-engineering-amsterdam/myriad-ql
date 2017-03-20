# coding=utf-8
from PyQt5.QtWidgets import QCheckBox
from PyQt5.QtWidgets import QDoubleSpinBox
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QListWidgetItem
from PyQt5.QtWidgets import QSpinBox


class StringInput(QLineEdit):
    def set_value(self, text):
        self.setText(text)


class BooleanInput(QCheckBox):
    def set_value(self, state):
        self.setChecked(state)


class MoneyInput(QDoubleSpinBox):
    def __init__(self):
        super(QDoubleSpinBox, self).__init__()
        self.setDecimals(2)
        self.setMaximum(10**10)
        self.setMinimum(-(10**10))
        self.setPrefix("\u20ac")

    def set_value(self, value):
        self.setValue(value)


class IntegerInput(QSpinBox):
    def set_value(self, value):
        self.setValue(value)


class ErrorWidget(QListWidgetItem):
    def __init__(self, location):
        super(ErrorWidget, self).__init__()
        self.location = location
