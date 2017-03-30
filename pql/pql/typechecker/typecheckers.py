# coding=utf-8
from pql.typechecker.boolean_type_checker import BooleanTypeChecker
from pql.typechecker.integer_type_checker import IntegerTypeChecker
from pql.typechecker.money_type_checker import MoneyTypeChecker
from pql.typechecker.none_type_checker import NoneTypeChecker
from pql.typechecker.string_type_checker import StringTypeChecker
from pql.typechecker.types import DataTypes

checker_of_data_type = {
            DataTypes.boolean: BooleanTypeChecker(),
            DataTypes.money: MoneyTypeChecker(),
            DataTypes.integer: IntegerTypeChecker(),
            DataTypes.string: StringTypeChecker(),
            DataTypes.none: NoneTypeChecker(),
        }

