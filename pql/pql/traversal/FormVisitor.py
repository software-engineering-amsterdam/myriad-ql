# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility
class FormVisitor(metaclass=ABCMeta):
    @abstractmethod
    def form(self, node, args=None):
        pass

    @abstractmethod
    def field(self, node, args=None):
        pass

    @abstractmethod
    def assignment(self, node, args=None):
        pass

    @abstractmethod
    def conditional_if(self, node, args=None):
        pass

    @abstractmethod
    def conditional_if_else(self, node, args=None):
        pass
