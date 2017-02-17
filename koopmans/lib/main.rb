require_relative 'parser/file_reader'
require_relative 'parser/parser'
require_relative 'parser/transformer'
require_relative 'visitor/form_visitor'
require_relative 'visitor/statement_visitor'
# require_relative 'type_checker/duplicate_label_checker'

require 'pp'

# read file
file_reader = FileReader.new
contents = file_reader.read_file('../examples/simple_questionnaire.ql')

# parse content
parser = Parser.new
parsed = parser.parse(contents)

transformer = Transformer.new
ast = transformer.apply(parsed)

ast.accept(FormVisitor.new)
# DuplicateLabelChecker.new.visit_ast(ast)
# pp parsed

# gui = Gui.new
# gui.question('joe?')
# gui.launch


