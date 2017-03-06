from appJar import gui

from gui.visitors.gui_builder import GuiBuilder
from gui.visitors.computation_updater import ComputationUpdater
from gui.visitors.gui_updater import GuiUpdater


class FormApp:

    def __init__(self, form, layout=None):
        self.form = form
        self.environment = {}

        self.app = gui(form.name)
        self.app.setResizable(False)
        self.app.setSticky("new")
        self.app.setBg("white")
        self.app.bindKey("<KeyPress>", self.update)

        gui_builder = GuiBuilder(self.app, self.update, self.form)

        if layout is not None:
            self.widgets = gui_builder.visit(layout)
        else:
            self.widgets = gui_builder.visit(form)

    def start(self):
        self.update(None)
        self.app.go()

    def stop(self):
        self.app.stop()

    def update(self, _):
        for name, widget in self.widgets.items():
            self.environment[name] = widget.get_value()
        ComputationUpdater(self.environment).visit(self.form)
        GuiUpdater(self, self.environment).visit(self.form)

    def show_widget(self, name):
        self.widgets[name].show()

    def hide_widget(self, name):
        self.widgets[name].hide()

    def set_widget(self, name, value):
        self.widgets[name].set_value(value)
