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

GUI.new(ast, typechecker)


