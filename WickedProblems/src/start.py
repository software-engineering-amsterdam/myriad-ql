import sys
req_version = (3,0)
cur_version = sys.version_info

if cur_version >= req_version:
	from pyparsing import *
	import argparse
	import wickeddsl
else:
   exit("Did you forget to run it using python >= 3.0 ??")

class WickedDSL:
	# internal
	_verbose = False
	_ql_file = None

	# private
	__ql_content = None
	__ql_structure = []
	__parent = 0
	__id_counter = 0

	def main(self):
		with open(self._ql_file, 'r') as ql_file_stream:
			self.__ql_content = ql_file_stream.readlines()

		self.__ql_content = [x.strip() for x in self.__ql_content]

		# squash the list into a string
		self.__ql_content = ' '.join(self.__ql_content)
		# Do the initial format to make sure all blocks can be extracted
		if(self.__ql_content.count('\}') > 0):
			raise Exception("Illegal token \"}\"")

		# escape all but the last bracket
		self.__ql_content = self.__ql_content.replace('}','\}', self.__ql_content.count('}')-1)

		# extract the main block (i.e. parse the form)
		self.__ql_content = wickeddsl.form_outer.parseString(self.__ql_content)

		# init the main form structure
		self.__ql_structure.append(((self.get_id(), None), self.__ql_content[1]))
		self.__ql_content = self.__ql_content[len(self.__ql_content)-1]

		while True:
			if(self._verbose):
				print(self.__ql_content)
				print(self.__ql_structure)

			try:
				self.parse_block()
			except ParseException:
				if not self.needs_refining():
					break;
				else:
					if(self._verbose):
						print(self.__ql_content)
					self.parse_block()

			self.parse_structure()

		if(self._verbose):
			print(self.__ql_structure)

	def get_id(self):
		tmp = self.__id_counter
		self.__id_counter += 1
		return tmp

	def parse_structure(self):
		fields = self.__ql_content[0:len(self.__ql_content)]
		if(self._verbose):
			print(fields)

		if(len(fields) > 0):
			# Store all fields (tuples of 3: name, variable, type)
			for x in range(0,int(len(fields)/3)):
				try:
					if(fields[3*x+3] == "="):
						fields[3*x+2:3*x+5] = [' '.join(fields[3*x+2:3*x+5])]
					else:
						pass
				except IndexError:
					if(self._verbose):
						print("Appears that we don't have a statement")
				self.__ql_structure.append(((self.get_id(), self.__parent),fields[3*x],fields[3*x+1],fields[3*x+2]))
		self.__ql_content = self.__ql_content[len(self.__ql_content)-1]

	# use pyparsing to parse parts of the form. might add some logic here later.
	def parse_block(self):
		self.__ql_content = wickeddsl.form_inner.parseString(self.__ql_content)
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
	wicked = WickedDSL()
	parser = argparse.ArgumentParser(description='WickedDSL Start File')

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
