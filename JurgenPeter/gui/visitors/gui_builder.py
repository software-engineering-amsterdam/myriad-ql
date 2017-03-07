from abc import abstractmethod


class GuiBuilder:

    def __init__(self, app, listener, exit, widgets):
        self.app = app
        self.listener = listener
        self.exit = exit
        self.widgets = widgets

    @abstractmethod
    def build(self, node):
        pass

    @abstractmethod
    def visit(self, node):
        pass

    def create_exit_button(self):
        self.app.addButton("exit_button", self.exit)
        self.app.setButtonSticky("exit_button", "s")
        self.app.getButtonWidget("exit_button").config(text="Save and exit")
