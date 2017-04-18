# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility,PyMissingOrEmptyDocstring
class UnaryExpressionVisitor(metaclass=ABCMeta):
    @abstractmethod
    def positive(self, node):
        pass

    @abstractmethod
    def negative(self, node):
        pass

    @abstractmethod
    def negation(self, node):
        pass
