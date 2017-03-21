# coding=utf-8
import json


class Environment(object):
    def __init__(self):
        self.environment = dict()

    def update(self, key, value):
        self.environment[key] = value
        return self

    def clear(self):
        self.environment.clear()

    def value(self, key):
        return self.environment[key]

    def items(self):
        return self.environment.items()

    def contains(self, key):
        return key in self.environment

    def __str__(self):
        return json.dumps(self.environment, sort_keys=True, indent=4, separators=(',', ': '))
