# coding=utf-8
boolean = "boolean"
money = "money"
integer = "integer"
string = "string"

types = [boolean, money, string, integer]


def is_type(type_string):
    return type_string in types
