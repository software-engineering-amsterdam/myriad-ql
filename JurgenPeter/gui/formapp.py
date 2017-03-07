from appJar import gui

from gui.visitors.ql_gui_builder import QlGuiBuilder
from gui.visitors.qls_gui_builder import QlsGuiBuilder
from gui.visitors.computation_updater import ComputationUpdater
from gui.visitors.gui_updater import GuiUpdater


class FormApp:

    def __init__(self, form, layout=None, on_exit=None):
        self.form = form
        self.environment = {}
        self.widgets = {}
        self.on_exit = on_exit

        self.app = gui(form.name)
        self.app.setResizable(False)
        self.app.setSticky("new")
        self.app.setBg("white")
        self.app.bindKey("<KeyPress>", self.update)

        if layout is None:
            QlGuiBuilder(self.app, self.update, self.exit, self.widgets).build(form)
        else:
            QlsGuiBuilder(self.app, self.update, self.exit, self.widgets, self.form).build(layout)

    def start(self):
        self.update(None)
        self.app.go()

    def stop(self):
        self.app.stop()

    def update(self, _):
        for name, widget in self.widgets.items():
            self.environment[name] = widget.get_value()
        while ComputationUpdater(self.environment).update(self.form):
            pass
        GuiUpdater(self, self.environment).update(self.form)

    def exit(self, _):
        self.app.stop()
        if self.on_exit is not None:
            self.on_exit(self)

    def show_widget(self, name):
        self.widgets[name].show()

    def hide_widget(self, name):
        self.widgets[name].hide()

    def set_widget(self, name, value):
        self.widgets[name].set_value(value)