import QLS.GUI.widgets as widgets
from QL.undefined import Undefined


class DetermineWidgetClass(object):
    def __init__(self, qls_env, draw_gui):
        self.qls_env = qls_env
        self.draw_gui = draw_gui

    @staticmethod
    def comp_question_node(_):
        return widgets.ComputedLabelWidget

    def question_node(self, question_node):
        identifier = question_node.name
        styling = self.qls_env.get_styling(identifier)

        if styling == Undefined:
            # Return default widget_type as no styling is defined;
            # thus default QL widgets.
            return question_node.type.accept(self.draw_gui)
        elif styling is not None:
            return styling.widget_type.accept(self)

        assert False, "Invalid state of styling; was never set properly."

    @staticmethod
    def slider_node(_):
        return widgets.SliderWidget

    @staticmethod
    def spinbox_node(_):
        return widgets.SpinBoxWidget

    @staticmethod
    def text_node(_):
        return widgets.EntryWidget

    @staticmethod
    def numeric_node(_):
        return widgets.NumericWidget

    @staticmethod
    def radio_node(_):
        return widgets.RadioButtonWidget

    @staticmethod
    def checkbox_node(_):
        return widgets.CheckBoxWidget

    @staticmethod
    def dropdown_node(_):
        return widgets.DropDownWidget

    @staticmethod
    def date_input_node(_):
        return widgets.DateWidget
