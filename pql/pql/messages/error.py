# coding=utf-8
class Error(object):
    def __init__(self, text, location=None):
        self.text = text
        self.location = location

    def __str__(self):
        return "{} : {}".format(self.text, self.location)

    def __repr__(self):
        return str(self)
