from .eval import Eval
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
