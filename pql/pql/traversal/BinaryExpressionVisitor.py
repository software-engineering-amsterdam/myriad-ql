# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility,PyMissingOrEmptyDocstring
class BinaryExpressionVisitor(metaclass=ABCMeta):

    @abstractmethod
    def subtraction(self, node, args=None):
        pass

    @abstractmethod
    def division(self, node, args=None):
        pass

    @abstractmethod
    def multiplication(self, node, args=None):
        pass

    @abstractmethod
    def addition(self, node, args=None):
        pass

    @abstractmethod
    def greater_exclusive(self, node, args=None):
        pass

    @abstractmethod
    def greater_inclusive(self, node, args=None):
        pass

    @abstractmethod
    def lower_inclusive(self, node, args=None):
        pass

    @abstractmethod
    def lower_exclusive(self, node, args=None):
        pass

    @abstractmethod
    def equality(self, node, args=None):
        pass

    @abstractmethod
    def inequality(self, node, args=None):
        pass

    @abstractmethod
    def and_(self, node, args=None):
        pass

    @abstractmethod
    def or_(self, node, args=None):
        pass
