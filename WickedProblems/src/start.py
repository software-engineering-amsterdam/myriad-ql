import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
	from pyparsing import ParseException
	from wickeddsl import WickedDSL
	import argparse
else:
   exit("Did you forget to run it using python >= 3.0 ??")

class WickedQL:
	# internal
	_verbose = False
	_ql_file = None

	# private
	__ql_content = None
	__ql_structure = []
	__parent = 0
	__id_counter = 0

	def __init__(self, ql_file=None):
		self._ql_file = ql_file

	def main(self):
		try:
			self.__ql_content = WickedDSL.load_file(self._ql_file)
		except Exception:
			exit("Could not load QL File")

		# escape all but the last bracket
		self.__ql_content = WickedDSL.escape_curlies(self.__ql_content)

		# extract the main block (i.e. parse the form)
		self.__ql_content = WickedDSL.form_outer.parseString(self.__ql_content)

		# init the main form structure
		self.__ql_structure.append(((self.get_id(), None), self.__ql_content[1]))
		self.__ql_content = self.__ql_content[len(self.__ql_content)-1]

		while True:
			try:
				self.parse_block()
			except ParseException:
				if not self.needs_refining():
					break;
				else:
					self.parse_block()

			self.parse_structure()

		if(self._verbose):
			print(self.__ql_structure)

	def get_id(self):
		tmp = self.__id_counter
		self.__id_counter += 1
		return tmp

	def parse_structure(self):
		(self.__ql_content,self.__ql_structure) = WickedDSL.parse_structure(self.__ql_content, self.__ql_structure, self.get_id(), self.__parent)

	# use pyparsing to parse parts of the form. might add some logic here later.
	def parse_block(self):
		self.__ql_content = WickedDSL.form_inner.parseString(self.__ql_content)

		if(self._verbose):
			print(self.__ql_content)

	def needs_refining(self):
		for x in range(0,len(self.__ql_structure)-1):
			# check for evaluations
			if(self.__ql_structure[x][1] == "if" and self.__ql_structure[x][3] is not "eval"):
				self.__ql_content = self.__ql_structure[x][3].replace("{","",1).replace("}","",1) # remove brackets
				self.__ql_structure[x] = (self.__ql_structure[x][0],self.__ql_structure[x][1],self.__ql_structure[x][2],"eval")
				self.__parent = self.__ql_structure[x][0][0]
				return True

		return False

if __name__ == '__main__':
	wicked = WickedQL()
	parser = argparse.ArgumentParser(description='WickedQL Start File')

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
	wicked._verbose = args.verbose

	if(args.ql_file is not None):
		wicked._ql_file = args.ql_file
	else:
		print("No QL File Specified... Exiting")
		exit(-1)

	wicked.main()
