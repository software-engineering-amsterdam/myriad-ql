# -*- coding: utf-8 -*-
from QL.GUI.drawGUI import DrawGUI as QLDrawGUI

from QL.undefined import Undefined
from QLS.GUI.determine_widget_class import DetermineWidgetClass
import QLS.GUI.widgets as widgets

"""
Quick rundown of what we probably need to do:
- Traverse QLS ast, to add formatting, frames etc.
- When at QLS questions, retrieve the QL question and call the
  parent(?) to create the appropriate GUI widget.
- After the widgets are created, use the parent's methods to update the
  GUI. What do we do when all Questions in a section are hidden? Hide the
  section as well? How are we going to do that?
"""


class DrawGUI(QLDrawGUI):
    def __init__(self, qls_ast, qls_env, ql_ast, ql_env):
        super(DrawGUI, self).__init__(ql_ast, ql_env)
        self.qls_ast = qls_ast
        self.qls_env = qls_env
        self.determine_class = DetermineWidgetClass(self.qls_env, self)

    def start_traversal(self):
        self.qls_ast.accept(self)
        self.show()

    def style_sheet_node(self, style_sheet_node):
        self.main.startPagedWindow("Don't forget to save your input!")
        style_sheet_node.body.accept(self)
        self.main.stopPagedWindow()

    def page_node(self, page_node):
        self.main.startPage()
        self.main.setSticky("ew")
        page_node.body.accept(self)
        self.main.stopPage()

    def page_with_defaults_node(self, page_node):
        self.page_node(page_node)

    def section_node(self, section_node):
        self.main.startLabelFrame(section_node.name)
        self.main.setSticky("ew")
        section_node.body.accept(self)
        self.main.stopLabelFrame()

    def section_with_defaults_node(self, section_node):
        self.section_node(section_node)

    def apply_styling(self, identifier, styling):
        if styling != Undefined:
            styling.accept(self, self.widgets[identifier])

    def question_node(self, question_node):
        # Create a frame around the question to improve interface.
        self.main.startFrame("@frame_" + question_node.name)

        # Retrieve QL question, determine if there is default styling.
        ql_node = self.env.get_question_node(question_node.name)
        identifier = ql_node.name
        question = ql_node.question

        widget_class = ql_node.accept(self.determine_class)
        self.add_widget(widget_class, identifier, question)

        # widget_class = self.define_widget_class(ql_node, styling)

        styling = self.qls_env.get_styling(identifier)
        self.apply_styling(identifier, styling)
        self.main.stopFrame()

    def widget_question_node(self, question_node):
        # Create a frame around the question to improve interface.
        self.main.startFrame("@frame_" + question_node.name)

        # Retrieve QL question, determine if there is default styling.
        ql_node = self.env.get_question_node(question_node.name)
        identifier = ql_node.name
        question = ql_node.question

        widget_class = ql_node.accept(self.determine_class)
        if widget_class != widgets.ComputedLabelWidget:
            widget_class = question_node.type.accept(self.determine_class)

        self.add_widget(widget_class, identifier, question)

        styling = self.qls_env.get_styling(identifier)
        self.apply_styling(identifier, styling)

        self.main.stopFrame()

    def default_node(self, _, widget):
        pass

    def default_with_props_node(self, default_node, widget):
        default_node.props.accept(self, widget)

    @staticmethod
    def width_node(width_node, widget):
        widget.set_width(width_node.val)

    @staticmethod
    def height_node(height_node, widget):
        widget.set_height(height_node.val)

    @staticmethod
    def font_node(font_node, widget):
        widget.set_font_family(font_node.val)

    @staticmethod
    def fontsize_node(fontsize_node, widget):
        widget.set_font_size(fontsize_node.val)

    @staticmethod
    def color_node(color_node, widget):
        widget.set_font_color(color_node.val)
