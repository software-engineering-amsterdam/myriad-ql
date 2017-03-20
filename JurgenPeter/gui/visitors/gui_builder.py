from misc.visitor import Visitor


class GuiBuilder(Visitor):

    def __init__(self, app, listener, on_exit, widgets):
        self.app = app
        self.listener = listener
        self.on_exit = on_exit
        self.widgets = widgets



    def create_exit_button(self):
        self.app.addButton("exit_button", self.on_exit)
        self.app.setButtonSticky("exit_button", "s")
        self.app.getButtonWidget("exit_button").config(text="Save and exit")
