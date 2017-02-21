import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
	from grammar.grammar import Grammar
	from ast.tree import Tree
	from gui.interface import Interface
	import argparse
else:
   exit("Did you forget to run it using python >= 3.0 ??")

if __name__ == '__main__':
	grammar = Grammar()
	parser = argparse.ArgumentParser(description='QL Parser Start File')

	parser.add_argument(
		"-v", "--verbose",
		help="increase output verbosity",
		action="store_true",
		default=False
	)

	parser.add_argument(
		"-f", dest="ql_file",
		help="Specify the QL file",
		action="store",
		default=None
	)

	args = parser.parse_args()
	grammar._verbose = args.verbose

	if(args.ql_file is not None):
		grammar._ql_file = args.ql_file
	else:
		print("No QL File Specified... Exiting")
		exit(-1)

	# parse the ql file
	form_parsed = grammar.parse()

	# pass the parsed ql file and construc the tree
	tree = Tree()
	ast = tree.construct(form_parsed)
	if(grammar._verbose):
		print("\n====================================")
		print("======= TREE REPRESENTATION ========")
		print("====================================")
		print(ast.get_tree_representation())
		print("====================================")

	gui = Interface(ast)
	gui.build_interface()
