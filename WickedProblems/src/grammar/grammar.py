from pyparsing import ParseException
from parser.ql import QL
from helpers import Helpers

class Grammar:
	# internal
	_verbose = False
	_ql_file = None

	# private
	__ql_content = None

	def __init__(self, ql_file=None):
		self._ql_file = ql_file

	def parse(self):
		if(self._verbose):
			from pprint import pprint

		try:
			self.__ql_content = Helpers.load_ql_file(self._ql_file)
		except Exception:
			exit("Could not load QL File")

		# Parse the form
		try:
			form = QL.form.parseString(self.__ql_content)
		except ParseException:
			form = None
		finally:
			if(self._verbose):
				pprint(form)

			return form
