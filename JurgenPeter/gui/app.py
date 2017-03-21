from appJar import gui

from gui.visitors.ql_gui_builder import QlGuiBuilder
from gui.visitors.qls_gui_builder import QlsGuiBuilder
from gui.visitors.computation_updater import ComputationUpdater
from gui.visitors.ql_gui_updater import QlGuiUpdater
from gui.visitors.qls_gui_updater import QlsGuiUpdater


class App:

    def __init__(self, form, on_exit=None):
        self.form = form
        self.environment = {}
        self.widgets = {}
        self.on_exit = on_exit

        self.gui = gui(form.name)
        self.gui.setResizable(False)
        self.gui.setSticky("new")
        self.gui.setBg("white")
        self.gui.bindKey("<KeyPress>", self.update)

    def build(self):
        pass

    def start(self):
        self.update(None)
        self.gui.go()

    def stop(self):
        self.gui.stop()

    def update(self, _):
        pass

    def exit(self, _):
        self.gui.stop()
        if callable(self.on_exit):
            self.on_exit(self)

    def show_widget(self, name):
        self.widgets[name].show()

    def hide_widget(self, name):
        self.widgets[name].hide()

    def set_widget(self, name, value):
        self.widgets[name].set_value(value)


class QlApp(App):

    def build(self):
        QlGuiBuilder(self.gui, self.update, self.exit,
                     self.widgets).build(self.form)

    def update(self, _):
        for name, widget in self.widgets.items():
            self.environment[name] = widget.get_value()
        while ComputationUpdater(self.environment).update(self.form):
            pass
        QlGuiUpdater(self, self.environment).update(self.form)


class QlsApp(App):

    def __init__(self, form, layout, on_exit=None):
        super().__init__(form, on_exit)
        self.layout = layout

    def build(self):
        QlsGuiBuilder(self.gui, self.update, self.exit, self.widgets,
                      self.form).build(self.layout)

    def update(self, _):
        for name, widget in self.widgets.items():
            self.environment[name] = widget.get_value()
        while ComputationUpdater(self.environment).update(self.form):
            pass
        visible_questions = QlGuiUpdater(self, self.environment).update(self.form)
        QlsGuiUpdater(self.gui, visible_questions).update(self.layout)
