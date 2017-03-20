from .ql import VoidAlg


class MessageContainer(object):

    def __init__(self):
        self.warning_messages = []
        self.error_messages = []

    def add_warning(self, message):
        self.warning_messages.append(message)

    def add_error(self, message):
        self.error_messages.append(message)

    def get_errors(self):
        return self.error_messages

    def get_warnings(self):
        return self.warning_messages

    def has_errors(self):
        return len(self.error_messages) > 0


class DuplicateLabelsChecker(VoidAlg):

    def __init__(self, message_container):
        self.labels = []
        self.message_container = message_container

    def StringLiteral(self, value):
        def _statements():
            if (value in self.labels):
                self.message_container.add_warning("Duplicate label: " + value)
            else:
                self.labels.append(value)

        return _statements


class UndefinedVariableChecker(VoidAlg):

    def __init__(self, message_container):
        self.variables = []
        self.message_container = message_container

    def Variable(self, name, datatype):
        def _statements():
            if name not in self.variables:
                self.variables.append(name)

        return _statements

    def RefVariable(self, name):
        def _statements():
            if (name not in self.variables):
                self.message_container.add_error("Undefined variable: " + name)

        return _statements


class QuestionTypeChecker(VoidAlg):

    def __init__(self, message_container):
        self.variables = {}
        self.message_container = message_container

    def Variable(self, name, datatype):
        def _statements():
            _datatype = datatype()
            if name not in self.variables:
                self.variables.update({name: datatype()})
            else:
                current_datatype = self.variables.get(name)
                if current_datatype != datatype():
                    self.message_container.add_error(
                        "Incompatible types: {} is defined with {}. Cannot be redefined with {}".format(name, current_datatype, _datatype))

        return _statements

    def String(self, value):
        return lambda: 'string'

    def Boolean(self, value):
        return lambda: 'boolean'

    def Money(self, value):
        return lambda: 'money'

    def Integer(self, value):
        return lambda: 'integer'

class InvalidOperandChecker(VoidAlg):

    def __init__(self, message_container):
        self.variables = {}
        self.message_container = message_container

    def Variable(self, name, datatype):
        def _statements():
            _datatype = datatype()
            if name not in self.variables:
                self.variables.update({name: datatype()})

        return _statements

    def RefVariable(self, name):
        def _statements():
            return self.variables.get(name)

        return _statements

    def GreaterThan(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} > {}".format(lhs(), rhs()))
            return lhs()

        return _statements

    def GreaterThanEquals(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} >= {}".format(lhs(), rhs()))
            return lhs()

        return _statements

    def LessThan(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} < {}".format(lhs(), rhs()))
            return lhs()

        return _statements

    def LessThanEquals(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} <= {}".format(lhs(), rhs()))
            return lhs()

        return _statements

    def Substraction(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} - {}".format(lhs(), rhs()))

            return lhs()

        return _statements

    def Addition(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} + {}".format(lhs(), rhs()))

            return lhs()

        return _statements

    def Multiplication(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} * {}".format(lhs(), rhs()))

            return lhs()

        return _statements

    def Division(self, lhs, rhs):
        def _statements():
            valid_types = [self.Integer()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} / {}".format(lhs(), rhs()))

            return lhs()

        return _statements

    def LogicalAnd(self, lhs, rhs):
        def _statements():
            valid_types = [self.Boolean()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} && {}".format(lhs(), rhs()))

            return lhs()

        return _statements

    def LogicalOr(self, lhs, rhs):
        def _statements():
            valid_types = [self.Boolean()()]
            if (lhs() not in valid_types or rhs() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} || {}".format(lhs(), rhs()))

            return lhs()

        return _statements

    def String(self, value=None):
        return lambda: 'string'

    def Boolean(self, value=None):
        return lambda: 'boolean'

    def Money(self, value=None):
        return lambda: 'money'

    def Integer(self, value=None):
        return lambda: 'integer'


class TypeChecker(object):
    ''' 
    Add checker
    interface: is_valid, add_checker

    '''

    def __init__(self):
        self.checkers = []

    def add_checker(self, checker):
        self.checkers.append(checker)

    def is_valid(self, ast):
        message_container = MessageContainer()
        self._validate(ast, message_container)
        for message in message_container.get_errors():
            print("Error: " + message)
        for message in message_container.get_warnings():
            print("Warning: " + message)

        return not message_container.has_errors()

    def _validate(self, ast, message_container):
        [ast.alg(checker(message_container))()
         for checker in self.checkers]
