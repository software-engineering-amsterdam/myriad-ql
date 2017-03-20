from abc import ABCMeta, abstractmethod
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *

class ExpressionAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Literal(self, value):
        pass

    @abstractmethod
    def Variable(self, name, datatype):
        pass


class StatementAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Question(self, variable, label):
        pass

    @abstractmethod
    def ComputedQuestion(self, variable, label):
        pass

    @abstractmethod
    def Block(self, statements):
        pass


class QlAlg(ExpressionAlg, StatementAlg):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Form(self, statements):
        pass
