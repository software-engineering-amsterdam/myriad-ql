class StyleSheet:
    def __init__(self, name, body):
        self.name = name
        self.body = body


class Page:
    def __init__(self, name, body):
        self.name = name
        self.body = body


class Section:
    def __init__(self, name, body):
        self.name = name
        self.body = body


class Question:
    def __init__(self, name, widget_type=None):
        self.name = name
        self.widget_type = widget_type


class DefaultStyle:
    def __init__(self, datatype, attributes):
        self.datatype = datatype
        self.attributes = attributes
