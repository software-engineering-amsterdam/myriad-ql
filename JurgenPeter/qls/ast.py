class StyleSheet:
    def __init__(self, name, body):
        self.name = name
        self.body = body

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class Page:
    def __init__(self, name, body):
        self.name = name
        self.body = body

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class Section:
    def __init__(self, name, body):
        self.name = name
        self.body = body

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class Question:
    def __init__(self, name, widget_type=None):
        self.name = name
        self.widget_type = widget_type

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__


class DefaultStyle:
    def __init__(self, datatype, attributes):
        self.datatype = datatype
        self.attributes = attributes

    def __eq__(self, other):
        return type(self) == type(other) and self.__dict__ == other.__dict__

