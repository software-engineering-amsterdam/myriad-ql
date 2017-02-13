import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
    from pyparsing import *
    from tkinter import *
    from wickeddsl import WickedDSL
    import argparse
    from pprint import pprint
else:
    exit("Did you forget to run it using python >= 3.0 ??")

class WickedQLS(Frame):
    # public
    num_pages = 0

    # internal
    _verbose = False
    _ql_file = None

    # private
    __root = None
    __ql_content = None
    __pages = []
    __id_counter = 0

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

        # fix bracketing
        self.__ql_content = WickedDSL.escape_curlies(self.__ql_content)

        # print(self.__ql_content)

        # extract the main block (i.e. parse the stylesheet)
        self.__ql_content = WickedDSL.stylesheet.parseString(self.__ql_content)

        # determine the number of pages
        self.num_pages = int((len(self.__ql_content) - 2) / 3)

        # first two k/v types are the type identifier and the name
        self.name = self.__ql_content[1]
        del self.__ql_content[0:2]

        # construct pages
        for x in range(0, self.num_pages):
            current_page = {'id': self.get_id(), 'name': '', 'content': []}
            # parse the content
            if(self.__ql_content[3*x] != "page"):
                raise Exception("Not a page?!")
            current_page['name'] = self.__ql_content[3*x+1]
            # construct the content
            current_content = WickedDSL.escape_curlies(self.__ql_content[3*x+2])
            # current_content = self.__ql_content[3*x+2]
            parsed_content = WickedDSL.content_type.parseString(current_content)
            # store the parsed content
            # content_holder = self.get_new_content_holder(current_page['id'])
            content_holder = None
            __skip_next = False
            for x in range(0, len(parsed_content)):
                if(__skip_next):
                    __skip_next = False
                    continue
                if(parsed_content[x] is "section"): # create new section
                    # current_page['content'].append(content_holder)
                    if content_holder is not None:
                        current_page['content'].append(content_holder)
                    content_holder = self.get_new_content_holder(current_page['id'])
                    content_holder['name'] = parsed_content[x+1]
                    __skip_next = True
                # print(parsed_content[x])
                else:
                    content_holder['content'] += parsed_content[x] + " "
                # print(content_holder)
            current_page['content'].append(content_holder)

            # continue

            # see if we need to further refine the parsed content
            # parsed_content = self.parse_refine(parsed_content)

            self.__pages.append(current_page)

        # DEBUG
        if(self._verbose):
            pprint(self.__pages)
            # for page in self.__pages:
            #     print(page)
            #     for content in page['content']:
            #         print(content)
            #     print("\n")

        # print(self.__ql_content)

    def get_new_content_holder(self, parent_id):
        return {'id': self.get_id(),'parent': parent_id,
                'name': None,'type': None,'content': ""}

    def get_id(self):
        tmp = self.__id_counter
        self.__id_counter += 1
        return tmp

    def parse_refine(self,content):
        for x in range(0, len(content)):
            if '{' in content[x]:
                # parse the codeblock
                parsed_content = WickedDSL.codeblock_unquoted.parseString(content[x])
                if(len(parsed_content) > 1): # more than 1 codeblock
                    pass
                else: # 1 codeblock
                    print(parsed_content)

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
