from misc.visitor import Visitor


class QlsGuiUpdater(Visitor):
    
    def __init__(self, gui, visible_questions):
        self.gui = gui
        self.visible_questions = visible_questions
    
    def update(self, node):
        self.visit(node)

    def visit_layout(self, node):
        for element in node.body:
            self.visit(element, node.name)

    def visit_styled_layout(self, node):
        self.visit_layout(node)

    def visit_page(self, node, layout_name):
        questions_in_body = sum([self.visit(element) for
                                 element in node.body], [])
        if any(question in self.visible_questions for
               question in questions_in_body):
            self.gui.setTabbedFrameDisabledTab(layout_name, node.name, False)
        else:
            self.gui.setTabbedFrameDisabledTab(layout_name, node.name, True)

    def visit_styled_page(self, node, layout_name):
        self.visit_page(node, layout_name)

    def visit_section(self, node):
        questions_in_body = sum([self.visit(element) for
                                 element in node.body], [])
        if any(question in self.visible_questions for
               question in questions_in_body):
            self.gui.showLabelFrame(node.name)
        else:
            self.gui.hideLabelFrame(node.name)
        return questions_in_body

    def visit_styled_section(self, node):
        return self.visit_section(node)

    def visit_question_anchor(self, node):
        return [node.name]
    
    def visit_styled_question_anchor(self, node):
        return [node.name]
