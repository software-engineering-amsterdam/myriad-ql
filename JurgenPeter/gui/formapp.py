from appJar import gui

from gui.visitors.widget_creator import WidgetCreator
from gui.visitors.update_computations import UpdateComputations
from gui.visitors.update_gui import UpdateGUI


class FormApp:

    def __init__(self, form):
        self.form = form
        self.environment = {}
        self.widgets = {}

        self.app = gui(form.name)
        self.app.bindKey("<KeyPress>", self.update_gui)

        WidgetCreator(self.app, self.widgets, self.update_gui).visit(form)

    def start(self):
        self.update_gui(None)
        self.app.go()

    def stop(self):
        self.app.stop()

    def update_gui(self, _):
        for name, widget in self.widgets.items():
            self.environment[name] = widget.get_value()
        UpdateComputations(self.environment).visit(self.form)
        UpdateGUI(self, self.environment).visit(self.form)

    def show_widget(self, name):
        self.widgets[name].show()

    def hide_widget(self, name):
        self.widgets[name].hide()

    def set_widget(self, name, value):
        self.widgets[name].set_value(value)
