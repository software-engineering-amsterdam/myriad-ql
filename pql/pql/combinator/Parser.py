from combinator.Concat import Concat
from combinator.Exp import Exp
from combinator.Alternate import Alternate
from combinator.Process import Process


class Parser:
    def __call__(self, tokens, pos):
        return None  # subclasses will override this

    def __add__(self, other):
        return Concat(self, other)

    def __mul__(self, other):
        return Exp(self, other)

    def __or__(self, other):
        return Alternate(self, other)

    def __xor__(self, function):
        return Process(self, function)
