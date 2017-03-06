from QL.GUI.EvaluateDrawState import EvaluateDrawState
from QL.Undefined import Undefined


class DrawGUI(object):
    def __init__(self, built_gui, ast, env, evaluator, error_handler):
        self.env = env
        self.ast = ast
        self.handler = error_handler
        self.evaluator = evaluator

        # Obtain the GUI instances.
        self.form_gui = built_gui.gui
        self.built_gui = built_gui
        self.form_gui.redraw_function = self.redraw

        self.set_draw_state = EvaluateDrawState(ast, env, evaluator,
                                                self.form_gui, error_handler)

    def adjust_env(self, question_values):
        for question in question_values:
            question_node = self.env.variables[question]["node"]

            new_value = question_values[question]
            if new_value == '':
                self.env.set_var_value(question, Undefined)
                continue

            question_type = question_node.type
            new_value = question_type.convert_to_type(new_value)

            # Value is non default, update the environment.
            self.env.set_var_value(question, new_value)

    def start(self):
        self.evaluator.start_traversal()
        self.set_draw_state.start_traversal()
        self.form_gui.start()

    def redraw(self, question_values):
        self.adjust_env(question_values)
        self.evaluator.start_traversal()
        self.set_draw_state.start_traversal()
