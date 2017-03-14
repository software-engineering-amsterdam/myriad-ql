# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility
class IdentifierVisitor(metaclass=ABCMeta):
    @abstractmethod
    def identifier(self, node):
        pass

    @abstractmethod
    def integer(self, node):
        pass

    @abstractmethod
    def money(self, node):
        pass

    @abstractmethod
    def boolean(self, node):
        pass
