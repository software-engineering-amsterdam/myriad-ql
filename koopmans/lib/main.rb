require_relative 'parser/file_reader'
require_relative 'parser/parser'

require_relative 'ast/expression'
require_relative 'ast/type'
require_relative 'ast/literal'
require_relative 'ast/statement'
require_relative 'ast/form'
require_relative 'ast/variable'

require_relative 'type_checker/main_checker'

require 'parslet'
require 'pp'

# read file
file_reader = FileReader.new
contents = file_reader.read_file('../examples/simple_questionnaire.ql')

# parse content
parser = Parslet::Parser.new
parsed = parser.parse(contents)

transformer = Parslet::Transform.new
ast = transformer.apply(parsed)

checker = MainChecker.new
errors = checker.check(ast)
pp errors
# DuplicateLabelChecker.new.visit_ast(ast)
# pp parsed
# pp ast

# gui = Gui.new
# gui.question('joe?')
# gui.launch


