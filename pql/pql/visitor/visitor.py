# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility
class Visitor(metaclass=ABCMeta):
    @abstractmethod
    def visit(self, pql_ast):
        pass

    @abstractmethod
    def form(self, node):
        pass

    @abstractmethod
    def field(self, node):
        pass

    @abstractmethod
    def subtraction(self, node):
        pass

    @abstractmethod
    def division(self, node):
        pass

    @abstractmethod
    def multiplication(self, node):
        pass

    @abstractmethod
    def addition(self, node):
        pass

    @abstractmethod
    def conditional(self, node):
        pass

    @abstractmethod
    def greater_exclusive(self, node):
        pass

    @abstractmethod
    def greater_inclusive(self, node):
        pass

    @abstractmethod
    def lower_inclusive(self, node):
        pass

    @abstractmethod
    def lower_exclusive(self, node):
        pass

    @abstractmethod
    def equality(self, node):
        pass

    @abstractmethod
    def inequality(self, node):
        pass

    @abstractmethod
    def and_(self, node):
        pass

    @abstractmethod
    def or_(self, node):
        pass

    @abstractmethod
    def identifier(self, node):
        pass

    @abstractmethod
    def value(self, node):
        pass
