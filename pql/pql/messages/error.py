# coding=utf-8
class Error(object):
    def __init__(self, text, location=None):
        self.text = text
        self.location = location