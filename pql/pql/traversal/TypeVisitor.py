# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility
class TypeVisitor(metaclass=ABCMeta):
    @abstractmethod
    def integer(self, node, args=None):
        pass

    @abstractmethod
    def money(self, node, args=None):
        pass

    @abstractmethod
    def boolean(self, node, args=None):
        pass

    @abstractmethod
    def string(self, node, args=None):
        pass
