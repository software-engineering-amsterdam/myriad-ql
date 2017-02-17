require_relative 'parser/file_reader'
require_relative 'parser/parser'
require_relative 'visitor/form_visitor'
require_relative 'visitor/statement_visitor'

require_relative 'ast/expression'
require_relative 'ast/type'
require_relative 'ast/literal'
require_relative 'ast/statement'
require_relative 'ast/form'
require_relative 'ast/variable'

require_relative 'visitor/duplicate_label_checker'

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

# ast.accept(FormVisitor.new)
duplicate_labels = ast.accept(DuplicateLabelChecker.new)
p '[WARNING] Duplicate labels found:'
p duplicate_labels if duplicate_labels
# DuplicateLabelChecker.new.visit_ast(ast)
pp parsed
pp ast

# gui = Gui.new
# gui.question('joe?')
# gui.launch


