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

	def main(self):
		with open(self._ql_file, 'r') as ql_file_stream:
			self.__ql_content = ql_file_stream.readlines()
		self.__ql_content = [x.strip() for x in self.__ql_content]

		print(wickeddsl.form.parseString(self.__ql_content))


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
