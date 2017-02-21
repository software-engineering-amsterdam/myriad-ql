require 'require_all'
require 'tk'
require 'parslet'
require 'pp'

require_rel 'lib'

# read file
file_reader = FileReader.new
contents = file_reader.read_file('examples/simple_questionnaire.ql')

# parse content
parser = Parser.new
parsed = parser.parse(contents)

transformer = Transformer.new
ast = transformer.apply(parsed)

typechecker = TypeChecker.check(ast)
# pp errors
# DuplicateLabelChecker.new.visit_ast(ast)
# pp parsed
# pp ast
# pp ast.accept(QuestionVisitor.new)
GUI.new(ast, typechecker)
# gui.question('joe?')
# gui.launch


