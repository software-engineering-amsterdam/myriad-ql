# coding=utf-8
from pql.typechecker.types import DataTypes


class NoneType(object):
    def __init__(self):
        self.data_type = DataTypes.none

    def apply(self, visitor, args=None):
        return self
