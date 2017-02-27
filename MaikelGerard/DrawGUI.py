from Undefined import Undefined


class DrawGUI(object):
    def __init__(self, built_gui, ast, environment, evaluator, error_handler):
        self.env = environment
        self.ast = ast
        self.handler = error_handler
        self.evaluator = evaluator

        # Obtain the GUI instances.
        self.form_gui = built_gui.gui
        self.built_gui = built_gui
        self.form_gui.redraw_function = self.redraw

    def adjust_env(self, question_values):
        for question in question_values:
            question_node = self.env.variables[question]["node"]

            new_value = question_values[question]
            if new_value == "@undefined":
                continue
            elif new_value == "":
                self.env.set_var_value(question, Undefined)
                continue

            question_type = question_node.type
            new_value = question_type.convert_to_type(new_value)

            # Value is non default, update the environment.
            if question_node.is_defined or new_value != question_type.default:
                self.env.set_var_value(question, new_value)
                question_node.is_defined = True

    def start(self):
        self.evaluator.start_traversal()
        for question in self.env.variables.values():
            question["node"].accept(self)

        self.form_gui.start()

    def redraw(self, question_values):
        self.adjust_env(question_values)
        self.evaluator.start_traversal()
        for question in self.env.variables.values():
            question["node"].accept(self)

    def question_node(self, question_node):
        identifier = question_node.get_identifier()
        if self.env.is_hidden(identifier):
            (get_data_func, set_data_func, show, hide) = \
                self.form_gui.get_question_functions(identifier)
            self.form_gui.main.hideLabel(identifier)
            hide(identifier)
        else:
            (get_data_func, set_data_func, show, hide) = \
                self.form_gui.get_question_functions(identifier)
            self.form_gui.main.showLabel(identifier)

            show(identifier)
            value = self.env.get_var_value(identifier)
            if value == Undefined:
                value = question_node.type.default
            set_data_func(identifier, value)

    def comp_question_node(self, comp_question):
        identifier = comp_question.get_identifier()
        if self.env.is_hidden(identifier):
            self.form_gui.main.hideLabel("@computed_" + identifier)
            self.form_gui.main.hideLabel(identifier)
        else:
            value = self.env.get_var_value(identifier)
            if value == Undefined:
                return
            self.form_gui.main.showLabel("@computed_" + identifier)
            self.form_gui.main.showLabel(identifier)
            self.form_gui.main.setLabel(identifier, value)
