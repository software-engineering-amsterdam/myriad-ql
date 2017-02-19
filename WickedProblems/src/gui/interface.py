import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from pyparsing import *
    from tkinter import *
    from ast.node import *
    from parser.ql import QL
else:
    exit("Did you forget to run it using python >= 3.0 ??")

class Interface(Frame):
    # private
    __root = None
    __tree = None
    __variables = {}

    def __init__(self, ast):
        self.__root = Tk()
        self.__root.minsize(width=400, height=300)
        super().__init__(self.__root)
        self.pack()
        self.__tree = ast

    def main(self):
        pass

    def start_gui(self):
        if(self.__tree is None):
            self.__root.destroy()
            exit("No Data to construct interface... Exiting...")
        self.build_interface()

    def callback(self):
        print("callback!")

    def print_current_variables(self):
        __ret = ""
        for k,v in self.__variables.items():
            print(k + ":" + str(v.get()))

    def refresh(self):
        for widget in self.__root.winfo_children():
            widget.destroy()
        self.construct_interface()
        self.print_current_variables()
        # print("refresh!")

    def variable_is_defined(self, variable):
        try:
            self.__variables[variable]
        except KeyError:
            return False
        return True

    def construct_from_node(self,node):
        # Get possible displayables
        if(node.__class__ == QuestionNode):
            if(node._field_type == "boolean"):
                if not self.variable_is_defined(node.get_identifier()):
                    self.__variables[node.get_identifier()] = IntVar()
                item = Checkbutton(self.__root,
                                   text=node._text,
                                   variable=self.__variables[node.get_identifier()],
                                   command=self.refresh)
            elif(node._field_type == "string"):
                if not self.variable_is_defined(node.get_identifier()):
                    self.__variables[node.get_identifier()] = StringVar()
                item = Entry(self.__root,
                             textvariable=self.__variables[node.get_identifier()])
            elif(node._field_type == "integer"):
                return
            elif(node._field_type == "data"):
                return
            elif(node._field_type == "decimal"):
                return
            elif(node._field_type == "money"):
                # TODO: Improve Money Input Check
                if not self.variable_is_defined(node.get_identifier()):
                    self.__variables[node.get_identifier()] = StringVar()
                item = Entry(self.__root,
                             textvariable=self.__variables[node.get_identifier()])
            elif(node._field_type == "currency"):
                return
            else:
                return
            # Add item to node
            node.set_interface_item(item)
            node.get_interface_item().pack()
        elif(node.__class__ == ConditionalNode):
            # evaluate condition
            # print(QL.match_evaluation.parseString(node._evaluation))
            # print(node._evaluation)
            # TODO: parse the evaluation [LINE BELOW IS TEMPORARY]
            _var = QL.evaluation_unquoted.parseString(node._evaluation)[0]
            # print(_var)
            # print(self.__variables[_var].get())
            # when evaluated as TRUE
            if(self.__variables[_var].get() == 1):
                # recursive
                for child in node.get_children():
                    # print(child.__class__.__name__)
                    self.construct_from_node(child)
        elif(node.__class__ == StatementNode):
            # statement node
            Label(self.__root, text=node._text).pack()
        else:
            pass

    def construct_interface(self):
        # walk through the nodes
        for content_node in self.__tree.get_children():
            self.construct_from_node(content_node)

    def build_interface(self):
        # Title (based on identifier of root node)
        self.__root.title(self.__tree.get_identifier())

        # construct widgets (frame content)
        self.construct_interface()

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

        self.__root.mainloop()
