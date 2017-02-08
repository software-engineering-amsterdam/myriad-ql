from pql.combinator.Concat import Concat
from pql.combinator.Exp import Exp
from pql.combinator.Alternate import Alternate
from pql.combinator.Process import Process

# TODO: Finish the documentation


class Parser:
    """Template parser for the children

    """
    def __call__(self, tokens, pos):
        """To be overridden by children

        :param tokens: Full list returned by the lexer
        :param pos: Index of the listing indicating the next to parse token
        :return: Overridden by children with which each return a different Result
        """
        return None

    def __add__(self, other):
        """An addition operator (+) override for easy access to concatenation of AST nodes

        :param other: The second element to add from an addition
        :return: Returns the concatenation Result
        """
        return Concat(self, other)

    def __mul__(self, other):
        """A multiplication operator (*) override for easy access to ???

        :param other: ???
        :return: ???
        """
        return Exp(self, other)

    def __or__(self, other):
        """A or operator (|) override for easy access to ???

        :param other: ???
        :return: ???
        """
        return Alternate(self, other)

    def __xor__(self, function):
        """A xor operator (^) override for easy access to ???

        :param function: ???
        :return: ???
        """
        return Process(self, function)
