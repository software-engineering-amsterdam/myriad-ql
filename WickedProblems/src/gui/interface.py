import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from tkinter import *
    from ast.ast import *
    from parser.ql import QL
    from .labeledcb import LabeledCheckbutton
    from .drawables import *
else:
    exit("Did you forget to run it using python >= 3.0 ??")

class Interface(Frame):
    # private
    __root = None
    __ast = None
    __variables = {}
    __row_counter = 0

    def __init__(self, ast):
        self.__root = Tk()
        self.__root.minsize(width=400, height=300)
        super().__init__(self.__root)
        self.__ast = ast

    def main(self):
        pass

    def start_gui(self):
        if(self.__ast is None):
            self.__root.destroy()
            exit("No Data to construct interface... Exiting...")
        self.build_interface()

    def callback(self):
        print("callback!")

    def print_current_variables(self):
        __ret = ""
        for k,v in self.__variables.items():
            print(str(k) + ":" + str(v.get()))

    def refresh(self):
        for widget in self.__root.winfo_children():
            widget.destroy()
        self.__row_counter = 0
        self.construct_interface()
        self.print_current_variables()
        # print("refresh!")

    def get_new_row(self):
        self.__row_counter += 1
        return self.__row_counter-1

    def variable_is_defined(self, variable):
        try:
            self.__variables[variable]
        except KeyError:
            return False
        return True

    def construct_from_node(self,node):
        # Get possible displayables
        if(isinstance(node,Question)):
            if(isinstance(node._field_type,Boolean)):
                if not self.variable_is_defined(node._identifier._identifier):
                    self.__variables[node._identifier._identifier] = IntVar()
                drawable = DrawableBoolean(node._identifier)
                drawable.draw(self.__root,
                              self.__variables[node._identifier._identifier],
                              node._text._value,
                              self.get_new_row(),
                              self.refresh)
                # print(drawable)
            elif(node._field_type == String):
                if not self.variable_is_defined(node._identifier):
                    self.__variables[node._identifier] = StringVar()
                item = Entry(self.__root,
                             textvariable=self.__variables[
                                 node._identifier
                                 ]).grid(row=self.get_new_row(),
                                         columnspan=2,
                                         sticky=W)
            elif(node._field_type == Integer):
                return
            elif(node._field_type == Date):
                return
            elif(node._field_type == Decimal):
                return
            elif(node._field_type == Money):
                # TODO: Improve Money Input Check
                if not self.variable_is_defined(node.get_identifier()):
                    self.__variables[node.get_identifier()] = StringVar()
                label_text=StringVar()
                label_text.set(node.get_text())
                _row = self.get_new_row()
                label = Label(self.__root, textvariable=label_text)
                item = Entry(self.__root,
                             textvariable=self.__variables[
                                 node.get_identifier()],
                             width=20)
                item.bind('<Leave>', (lambda _: self.refresh()))
                label.grid(row=_row, column=0, sticky=W)
                item.grid(row=_row, column=1, sticky=W)
            elif(node._field_type == Currency):
                return
            else:
                return
        elif(isinstance(node,Conditional)):
            # print(node)
            # evaluate condition
            evaluation = node._evaluation
            node.evaluate()
            # print(evaluation.right_child.right_child._identifier)
            return
            if(self.__variables[_var].get() == 1):
                # recursive
                for child in node.get_children():
                    # print(child.__class__.__name__)
                    self.construct_from_node(child[0])
        elif(isinstance(node,Statement)):
            # statement node
            Label(self.__root, text=node._text).grid(row=self.get_new_row(),
                                                     columnspan=2,
                                                     sticky=W)
        else:
            pass

    def construct_interface(self):
        # Add Menu
        self.construct_menu()
        _row = self.get_new_row()

        # walk through the nodes
        for content_node in self.__ast._children:
            self.construct_from_node(content_node[0])

    def construct_menu(self):
        self.menu = Menu(self.__root)
        self.__root.config(menu=self.menu)

        self.filemenu = Menu(self.menu)
        self.menu.add_cascade(label="File", menu=self.filemenu)
        self.filemenu.add_command(label="New", command=self.callback)
        self.filemenu.add_command(label="Open...", command=self.callback)
        self.filemenu.add_separator()
        self.filemenu.add_command(label="Exit", command=self.__root.destroy)

        self.helpmenu = Menu(self.menu)
        self.menu.add_cascade(label="Help", menu=self.helpmenu)
        self.helpmenu.add_command(label="About...", command=self.callback)

    def build_interface(self):
        # Title (based on identifier of root node)
        self.__root.title(self.__ast._identifier._identifier)

        # construct widgets (frame content)
        self.construct_interface()

        self.__root.mainloop()
