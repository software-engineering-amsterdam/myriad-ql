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
        def _register():
            if (value in self.labels):
                self.message_container.add_warning("Duplicate label: " + value)
            else:
                self.labels.append(value)

        class _anon():
            execute = lambda self: _register()
        return _anon()


class UndefinedVariableChecker(VoidAlg):

    def __init__(self, message_container):
        self.variables = []
        self.message_container = message_container

    def Variable(self, name, datatype):
        def _register():
            if name not in self.variables:
                self.variables.append(name)

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            if (name not in self.variables):
                self.message_container.add_error("Undefined variable: " + name)

        class _anon():
            execute = lambda self: _register()
        return _anon()


class QuestionTypeChecker(VoidAlg):

    def __init__(self, message_container):
        self.variables = {}
        self.message_container = message_container

    def Variable(self, name, datatype):
        def _register():
            _datatype = datatype.execute()
            if name not in self.variables:
                self.variables.update({name: datatype.execute()})
            else:
                current_datatype = self.variables.get(name)
                if current_datatype != datatype.execute():
                    self.message_container.add_error(
                        "Incompatible types: {} is defined with {}. Cannot be redefined with {}".format(name, current_datatype, _datatype))

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        class _anon():
            execute = lambda self: 'string'
        return _anon()

    def Boolean(self, value):
        class _anon():
            execute = lambda self: 'boolean'
        return _anon()

    def Money(self, value):
        class _anon():
            execute = lambda self: 'money'
        return _anon()

    def Integer(self, value):
        class _anon():
            execute = lambda self: 'integer'
        return _anon()


class InvalidOperandChecker(VoidAlg):

    def __init__(self, message_container):
        self.variables = {}
        self.message_container = message_container

    def Variable(self, name, datatype):
        def _register():
            _datatype = datatype.execute()
            if name not in self.variables:
                self.variables.update({name: datatype.execute()})

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            return self.variables.get(name)

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def GreaterThan(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} > {}".format(lhs.execute(), rhs.execute()))
            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def GreaterThanEquals(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} >= {}".format(lhs.execute(), rhs.execute()))
            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LessThan(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} < {}".format(lhs.execute(), rhs.execute()))
            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LessThanEquals(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} <= {}".format(lhs.execute(), rhs.execute()))
            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} - {}".format(lhs.execute(), rhs.execute()))

            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Addition(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} + {}".format(lhs.execute(), rhs.execute()))

            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Multiplication(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} * {}".format(lhs.execute(), rhs.execute()))

            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Division(self, lhs, rhs):
        def _register():
            valid_types = [self.Integer().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} / {}".format(lhs.execute(), rhs.execute()))

            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LogicalAnd(self, lhs, rhs):
        def _register():
            valid_types = [self.Boolean().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} && {}".format(lhs.execute(), rhs.execute()))

            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def LogicalOr(self, lhs, rhs):
        def _register():
            valid_types = [self.Boolean().execute()]
            if (lhs.execute() not in valid_types or rhs.execute() not in valid_types):
                self.message_container.add_error(
                    "Invalid operand types: {} || {}".format(lhs.execute(), rhs.execute()))

            return lhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value=None):
        class _anon():
            execute = lambda self: 'string'
        return _anon()

    def Boolean(self, value=None):
        class _anon():
            execute = lambda self: 'boolean'
        return _anon()

    def Money(self, value=None):
        class _anon():
            execute = lambda self: 'money'
        return _anon()

    def Integer(self, value=None):
        class _anon():
            execute = lambda self: 'integer'
        return _anon()


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
        [ast.alg(checker(message_container)).execute()
         for checker in self.checkers]
