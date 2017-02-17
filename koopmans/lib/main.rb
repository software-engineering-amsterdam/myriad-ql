require_relative 'parser/file_reader'
require_relative 'parser/parser'
require_relative 'visitor/form_visitor'

require_relative 'ast/expression'
require_relative 'ast/type'
require_relative 'ast/literal'
require_relative 'ast/statement'
require_relative 'ast/form'
require_relative 'ast/variable'

require_relative 'visitor/duplicate_label_checker'
require_relative 'visitor/duplicate_variable_checker'
require_relative 'visitor/variable_visitor'
require_relative 'visitor/undefined_variable_checker'

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

duplicate_labels = ast.accept(DuplicateLabelChecker.new)
if duplicate_labels
  p '[WARNING] Duplicate labels found:'
  p duplicate_labels
end

duplicate_question_variables = ast.accept(DuplicateVariableChecker.new)
if duplicate_question_variables
  p '[ERROR] Duplicate variables found:'
  p duplicate_question_variables
end

undefinedVariables = ast.accept(UndefinedVariableChecker.new)
if undefinedVariables
  p '[ERROR] Undefined variables found:'
  p undefinedVariables
end
# DuplicateLabelChecker.new.visit_ast(ast)
pp parsed
pp ast

# gui = Gui.new
# gui.question('joe?')
# gui.launch


