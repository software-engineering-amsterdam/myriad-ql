import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from tkinter import *
    from ast.ast import *
    from parser.ql import QL
else:
    exit("Did you forget to run it using python >= 3.0 ??")

class Interface(Frame):
    # private
    _root = None
    __ast = None
    __variables = {}
    __row_counter = 0

    def __init__(self, ast):
        self._root = Tk()
        self._root.minsize(width=400, height=300)
        super().__init__(self._root)
        self.__ast = ast

    def main(self):
        pass

    def start_gui(self):
        if(self.__ast is None):
            self._root.destroy()
            exit("No Data to construct interface... Exiting...")
        self.build_interface()

    def callback(self):
        print("callback!")

    def refresh(self):
        for widget in self._root.winfo_children():
            widget.destroy()
        self.__row_counter = 0
        self.construct_interface()

    def get_new_row(self):
        self.__row_counter += 1
        return self.__row_counter-1

    def construct_from_node(self,node):
        node.draw(self)

    def construct_interface(self):
        # Add Menu
        self.construct_menu()
        _row = self.get_new_row()

        # walk through the nodes
        for content_node in self.__ast._children:
            self.construct_from_node(content_node[0])

    def construct_menu(self):
        self.menu = Menu(self._root)
        self._root.config(menu=self.menu)

        self.filemenu = Menu(self.menu)
        self.menu.add_cascade(label="File", menu=self.filemenu)
        self.filemenu.add_command(label="New", command=self.callback)
        self.filemenu.add_command(label="Open...", command=self.callback)
        self.filemenu.add_separator()
        self.filemenu.add_command(label="Exit", command=self._root.destroy)

        self.helpmenu = Menu(self.menu)
        self.menu.add_cascade(label="Help", menu=self.helpmenu)
        self.helpmenu.add_command(label="About...", command=self.callback)

    def build_interface(self):
        # Title (based on identifier of root node)
        self._root.title(self.__ast._identifier._identifier)

        # construct widgets (frame content)
        self.construct_interface()

        self._root.mainloop()
