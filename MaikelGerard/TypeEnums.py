class TypeEnums(object):
    BOOLEAN = "boolean"
    INTEGER = "integer"
    VARIABLE = "variable"
    DECIMAL = "decimal"
    MONEY = "money"
    DATE = "date"
    STRING = "string"

    ALL_TYPES = [BOOLEAN, INTEGER, VARIABLE, DECIMAL, MONEY, DATE, STRING]
    NUMERIC = [DECIMAL, INTEGER, MONEY]
    ALPHANUMERIC = [DECIMAL, INTEGER, MONEY, STRING]
    NOT_BOOLEAN = [DECIMAL, INTEGER, MONEY, STRING, DATE]
