from abc import ABCMeta, abstractmethod
from ast.ast import *
from ast.base_nodes import *
from ast.field_types import *

class ExpressionAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Literal(self, value):
        pass

    @abstractmethod
    def Variable(self, name, datatype):
        pass


class StatementAlg(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Question(self, variable, label):
        pass

    @abstractmethod
    def ComputedQuestion(self, variable, label):
        pass

    @abstractmethod
    def Block(self, statements):
        pass


class QlAlg(ExpressionAlg, StatementAlg):
    __metaclass__ = ABCMeta

    @abstractmethod
    def Form(self, statements):
        pass


class Eval(ExpressionAlg):
    def __init__(self, environment):
        self.environment = environment

    def RefVariable(self, name):
        class _anon():
            execute = lambda _ : self.environment.get_value(name)
        return _anon()

    def Literal(self):
        pass

    def String(self, value):
        class _anon():
            execute = lambda self: str(value)
        return _anon()

    def Integer(self, value):
        class _anon():
            execute = lambda self: int(value)
        return _anon()

    def Money(self, value):
        class _anon():
            execute = lambda self: value
        return _anon()

    def Boolean(self, value):
        class _anon():
            execute = lambda self: value
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() - rhs.execute()
        return _anon()

    def Addition(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() + rhs.execute()
        return _anon()

    def Division(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() / rhs.execute() if (lhs.execute() != 0 and rhs.execute() != 0) else 0
        return _anon()

    def Multiplication(self, lhs, rhs):
        class _anon():
            execute = lambda self: lhs.execute() * rhs.execute() if (lhs.execute() != 0 and rhs.execute() != 0) else 0
        return _anon()



class ToString(QlAlg):

    def Literal(self, value):
        class _anon():
            to_string = lambda self: "Literal({})".format(str(value))

        return _anon()

    def Form(self, name, block):
        class _anon():
            to_string = lambda self: "Form({}, {})".format(
                str(name), block.to_string())
        return _anon()

    def Block(self, statements):
        class _anon():
            to_string = lambda self: "Block(\n{}\n)".format(
                (("\n").join([k.to_string() for _, k in enumerate(statements)])))

        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            to_string = lambda self: "Variable(name={}, datatype={})".format(
                str(name), datatype.to_string())
        return _anon()

    def RefVariable(self, name):
        class _anon():
            to_string = lambda self: "RefVariable(name={})".format(str(name))
        return _anon()

    def Question(self, variable, label):
        class _anon():
            to_string = lambda self: "Question(variable={}, label='{}')".format(
                variable.to_string(), str(label))
        return _anon()

    def ifThen(self, expression, block):
        class _anon():
            to_string = lambda self: "ifThen(expression={}, label='{}')".format(
                expression.to_string(), block.to_string())
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        class _anon():
            to_string = lambda self: "Question(variable={}, label='{}', expression={})".format(
                variable.to_string(), str(label), expression.to_string())
        return _anon()

    def Boolean(self, value=False):
        class _anon():
            to_string = lambda self: "Boolean({})".format(str(value))
        return _anon()

    def Money(self, value=False):
        class _anon():
            to_string = lambda self: "Money({})".format(str(value))
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            to_string = lambda self: "Substraction({}, {})".format(
                lhs.to_string(), rhs.to_string())
        return _anon()

    def Integer(self, value):
        class _anon():
            to_string = lambda self: "Integer({})".format(str(value))
        return _anon()


def add_tabs(tabs=0):
    return '\t' * tabs


class PrettyPrint(QlAlg):
    state = []

    def __init__(self, tabs):
        self.tabs = tabs

    def Literal(self, value):
        class _anon():
            to_string = lambda self, tabs: "Literal({})".format(str(value))

        return _anon()

    def Form(self, name, block):
        class _anon():
            to_string = lambda self, tabs: "Form({}, {} \n)".format(
                str(name), block.to_string(tabs + 1))
        return _anon()

    def Block(self, statements):
        class _anon():
            to_string = lambda self, tabs: "Block(\n{})".format(
                (("\n").join([k.to_string(tabs + 1) for _, k in enumerate(statements)])))

        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            to_string = lambda self, tabs: "Variable(name={}, datatype={})".format(
                str(name), datatype.to_string(tabs))
        return _anon()

    def RefVariable(self, name):
        class _anon():
            to_string = lambda self, tabs: "RefVariable(name={})".format(
                str(name))
        return _anon()

    def Question(self, variable, label):
        class _anon():
            to_string = lambda self, tabs: add_tabs(
                tabs) + "Question(variable={}, label='{}')".format(variable.to_string(tabs + 1), label.to_string(tabs))
        return _anon()

    def ifThen(self, expression, block):
        class _anon():
            to_string = lambda self, tabs:  add_tabs(tabs) + "ifThen(expression={}, block={}".format(
                expression.to_string(tabs + 1), block.to_string(tabs + 1)) + "\n" + add_tabs(tabs) + ")"
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        class _anon():
            to_string = lambda self, tabs: add_tabs(tabs) + "ComputedQuestion(variable={}, label='{}',".format(variable.to_string(
                tabs + 1), label.to_string(tabs)) + "\n" + add_tabs(tabs) + "expressions={})".format(expression.to_string(tabs + 1))
        return _anon()

    def Boolean(self, value=False):
        class _anon():
            to_string = lambda self, tabs: "Boolean({})".format(str(value))
        return _anon()

    def Money(self, value=False):
        class _anon():
            to_string = lambda self, tabs: "Money({})".format(str(value))
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            to_string = lambda self, tabs: "Substraction({}, {})".format(
                lhs.to_string(tabs + 1), rhs.to_string(tabs + 1))
        return _anon()

    def Integer(self, value):
        class _anon():
            to_string = lambda self, tabs: "Integer({})".format(str(value))
        return _anon()

    def String(self, value):
        class _anon():
            to_string = lambda self, tabs: "String({})".format(str(value))
        return _anon()


class Environment(object):
    variables = []
    ref_variables = []
    questions = []
    computed_questions = []
    variables_dict = {}
    conditional_questions = {}

    def __init__(self):
        pass

    def add_var(self, var):
        self.variables.append(var)
        self.variables_dict.update({var[0]:var[1]})

    def add_ref(self, var):
        self.ref_variables.append(var)

    def add_question(self, variable, label):
        self.questions.append((variable, label))

    def add_computed_question(self, variable, label, expression):
        self.computed_questions.append((variable, label, expression))

    def add_conditional(self, variable, condition):
        self.conditional_questions.update({variable: condition})

    def get_var_key(self, variable):
        for key,val in self.variables_dict.items():
            if(val == variable):
                return key

    def is_visible(self, variable):
        condition = self.conditional_questions.get(variable)
        if (condition):
            return condition.alg(Eval(self)).execute()
        else:
            return 1

    def update_computed_questions(self):
        for question in self.computed_questions:
            var = self.get_var(question[0].name)
            evalutation = question[2].alg(Eval(self)).execute()
            var.set(evalutation)

    def get_var(self, var):
        return self.variables_dict.get(var)

    def get_value(self, var):
        if self.variables_dict.get(var) is not None:
            return self.variables_dict.get(var).get()
        else:
            return 0

    def update_var(self, var, value):
        self.variables_dict.update({var: value})

    def check_type(self, variable):
        pass

    def check_question_type(self, question):
        pass

    def check_types(self):
        sequence = [(_var, _datatype) for _var, _datatype in self.variables]
        var_table = {}
        issues = []
        for _var, _datatype in sequence:
            if var_table.has_key(_var):
                existing_var = var_table.get(_var)
                if existing_var != _datatype:
                    issues.append('{} is already defined with {}, it can\'t be redeclared with {}'.format(
                        _var, existing_var, _datatype))
            else:
                var_table.update({_var: _datatype})

        return issues

    def all_labels(self):
        return [y for x, y in self.questions]

    def duplicate_labels(self):
        labels = self.all_labels()
        frequency = {_label: labels.count(_label)
                     for _var, _label in self.questions}
        return {label for label, x in frequency.items() if x > 1}

    def is_registerd(self, var):
        return var in set([_var for _var, _type in self.variables])

    def get_variables(self):
        return self.variables

    def export(self):
        return {key: variable.get() for key, variable in self.variables_dict.items()}

    def undefined_variables(self):
        return set([_var for _var in self.ref_variables if self.is_registerd(_var) == False])

class GetVariables(QlAlg):

    def __init__(self, environment_vars):
        self.environment = Environment()

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute()
            return

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self: [k.execute()
                                    for _, k in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        def _register():
            self.environment.add_var((name, datatype.execute()))
            return (name, datatype.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def RefVariable(self, name):
        def _register():
            self.environment.add_ref((name))

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Question(self, variable, label):
        def _register():
            self.environment.add_question(variable.execute(), label.execute())

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ifThen(self, expression, block):
        def _register():
            print("LL", expression)
            expression.execute()
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register():

            variable.execute()
            label.execute()
            expression.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def UnaryNegation(self, value=False):
        def _register(self):
            return 'negation'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Addition(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Division(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Multiplication(self, lhs, rhs):
        def _register():
            lhs.execute()
            rhs.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Integer(self, value):
        def _register():
            return 'integer'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        def _register():
            return 'string'

        class _anon():
            execute = lambda self: _register()
        return _anon()



class RegisterComputedQuestions(QlAlg):
    def __init__(self, environment_vars):
        self.environment = Environment()

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute()
            return

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self: [k.execute()
                                    for _, k in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            execute = lambda self: Variable(name, datatype)
        return _anon()

    def RefVariable(self, name):
        class _anon():
            execute = lambda self: RefVariable(name)
        return _anon()

    def Question(self, variable, label):
        def _register():
            pass
        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ifThen(self, expression, block):
        def _register():

            expression.execute()
            block.execute()

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register():
            self.environment.add_computed_question(variable.execute(), label.execute(), expression.execute())
        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def UnaryNegation(self, lhs):
        class _anon():
            execute = lambda self: UnaryNegation(lhs.execute())
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            execute = lambda self: Substraction(lhs.execute(), rhs.execute())
        return _anon()

    def Addition(self, lhs, rhs):
        class _anon():
            execute = lambda self: Addition(lhs.execute(), rhs.execute())
        return _anon()

    def Division(self, lhs, rhs):
        class _anon():
            execute = lambda self: Division(lhs.execute(), rhs.execute())
        return _anon()

    def Multiplication(self, lhs, rhs):
        class _anon():
            execute = lambda self: Multiplication(lhs.execute(), rhs.execute())
        return _anon()

    def Integer(self, value):
        def _register():
            return 'integer'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        def _register():
            return 'string'

        class _anon():
            execute = lambda self: _register()
        return _anon()


class RegisterConditions(QlAlg):
    def __init__(self, environment_vars):
        self.environment = Environment()

    def Literal(self, value):
        class _anon():
            execute = None

        return _anon()

    def Form(self, name, block):
        def _register():
            block.execute(None)
            return

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Block(self, statements):
        class _anon():
            execute = lambda self, condition: [k.execute(condition)
                                    for _, k in enumerate(statements)]
        return _anon()

    def Variable(self, name, datatype):
        class _anon():
            execute = lambda self: name
        return _anon()

    def RefVariable(self, name):
        class _anon():
            execute = lambda self: RefVariable(name)
        return _anon()

    def Question(self, variable, label):
        def _register(condition):
            if condition:
                print("register...",variable.execute(),  label.execute())
                self.environment.add_conditional(variable.execute(), condition)

        class _anon():
            execute = lambda self, condition: _register(condition)
        return _anon()

    def ifThen(self, expression, block):
        def _register():

            expression.execute()
            block.execute(expression.execute())

        class _anon():
            execute = lambda self, expression: _register()
        return _anon()

    def ComputedQuestion(self, variable, label, expression):
        def _register(condition):
            if condition:
                print("Register CQ...",variable.execute(), label.execute())
                self.environment.add_conditional(variable.execute(), condition)

        class _anon():
            execute = lambda self, condition: _register(condition)
        return _anon()

    def Boolean(self, value=False):
        def _register(self):
            return 'boolean'

        class _anon():
            execute = lambda self: _register(self)
        return _anon()

    def Money(self, value=False):
        def _register():
            return 'money'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def Substraction(self, lhs, rhs):
        class _anon():
            execute = lambda self: Substraction(lhs.execute(), rhs.execute())
        return _anon()

    def UnaryNegation(self, lhs):
        class _anon():
            execute = lambda self: UnaryNegation(lhs.execute())
        return _anon()

    def Addition(self, lhs, rhs):
        class _anon():
            execute = lambda self: Addition(lhs.execute(), rhs.execute())
        return _anon()

    def Division(self, lhs, rhs):
        class _anon():
            execute = lambda self: Division(lhs.execute(), rhs.execute())
        return _anon()

    def Multiplication(self, lhs, rhs):
        class _anon():
            execute = lambda self: Multiplication(lhs.execute(), rhs.execute())
        return _anon()

    def Integer(self, value):
        def _register():
            return 'integer'

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def StringLiteral(self, value):
        def _register():
            return value

        class _anon():
            execute = lambda self: _register()
        return _anon()

    def String(self, value):
        def _register():
            return 'string'

        class _anon():
            execute = lambda self: _register()
        return _anon()
