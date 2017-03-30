# coding=utf-8
from enum import Enum


class DataTypes(Enum):
    boolean = "boolean"
    money = "money"
    integer = "integer"
    string = "string"
    none = "none"


def is_number_type(node_data_type):
    return node_data_type is DataTypes.money or node_data_type is DataTypes.integer
