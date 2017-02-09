import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from pyparsing import *
    from tkinter import *
    from wickeddsl import WickedDSL
    import argparse
else:
    exit("Did you forget to run it using python >= 3.0 ??")

class WickedQLS(Frame):
    # internal
    _verbose = False
    _ql_file = None

    # private
    __root = None
    __ql_content = None

    def __init__(self, master=None, ql_file=None):
        master.minsize(width=800, height=600)
        master.maxsize(width=800, height=600)
        super().__init__(master)
        self.pack()
        self.__root = master
        self._ql_file = ql_file

    def main(self):
        if(self.__ql_content is None):
            exit("No QLS Content Loaded")
        # extract the main block (i.e. parse the form)
        self.__ql_content = WickedDSL.stylesheet.parseString(self.__ql_content)

        print(self.__ql_content)

    def load_ql(self, ql_file):
        self.__ql_content = WickedDSL.load_file(ql_file)

    def start_gui(self):
        if(self.__ql_content is None):
            self.__root.destroy()
            exit("No QL File Loaded... Exiting...")
        self.create_widgets()

    def callback(self):
        print("callback!")

    def create_widgets(self):
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

if __name__ == '__main__':
    root = Tk()
    # Load QL data
    gui = WickedQLS(master=root)

    parser = argparse.ArgumentParser(description='WickedQLS Start File')
    parser.add_argument("-v", "--verbose",
                        help="increase output verbosity",
                        action="store_true",
                        default=False
                        )

    parser.add_argument("-f", dest="ql_file",
                        help="Specify the QL file",
                        action="store",
                        default=None
                        )

    args = parser.parse_args()
    gui._verbose = args.verbose

    if(args.ql_file is not None):
        gui.load_ql(args.ql_file)
    else:
        print("No QL File Specified... Exiting")
        exit(-1)

    gui.main()
    # gui.start_gui()
