from .ql import VoidAlg



class MessageContainer(object):

    def __init__(self):
        self.warning_messages = []
        self.error_messages = []

    def add_warning(self, message):
        self.warning_messages.append(message)

    def add_error(self, message):
        self.error_messages.append(message)

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
        return not message_container.has_errors()

    def _validate(self, ast, message_container):
        [ast.alg(checker(message_container)).execute() for checker in self.checkers]




