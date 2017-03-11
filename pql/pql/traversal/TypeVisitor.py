# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility
class TypeVisitor(metaclass=ABCMeta):
    @abstractmethod
    def integer(self, node):
        pass

    @abstractmethod
    def money(self, node):
        pass

    @abstractmethod
    def boolean(self, node):
        pass
