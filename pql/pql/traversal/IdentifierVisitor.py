# coding=utf-8
from abc import ABCMeta, abstractmethod


# noinspection PyCompatibility
class IdentifierVisitor(metaclass=ABCMeta):
    @abstractmethod
    def identifier(self, node, args=None):
        pass
