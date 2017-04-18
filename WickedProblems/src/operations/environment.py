from .eval import Eval


class Environment(object):

    def __init__(self):
        self.variables = []
        self.ref_variables = []
        self.questions = []
        self.computed_questions = []
        self.variables_dict = {}
        self.conditional_questions = {}

    def add_var(self, var):
        self.variables.append(var)
        self.variables_dict.update({var[0]: var[1]})

    def add_ref(self, var):
        self.ref_variables.append(var)

    def add_question(self, variable, label):
        self.questions.append((variable, label))

    def add_computed_question(self, variable, label, expression):
        self.computed_questions.append((variable, label, expression))

    def add_conditional(self, variable, condition):
        self.conditional_questions.update({variable: condition})

    def get_var_key(self, variable):
        for key, val in self.variables_dict.items():
            if(val == variable):
                return key

    def is_visible(self, variable):
        condition = self.conditional_questions.get(variable)
        if (condition):
            return condition.alg(Eval(self))()
        else:
            return 1

    def update_computed_questions(self):
        for question in self.computed_questions:
            var = self.get_var(question[0].name)
            evalutation = question[2].alg(Eval(self))()
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

    def is_registerd(self, var):
        return var in set([_var for _var, _type in self.variables])

    def get_variables(self):
        return self.variables

    def export(self):
        return {key: variable.get() for key, variable in self.variables_dict.items()}
