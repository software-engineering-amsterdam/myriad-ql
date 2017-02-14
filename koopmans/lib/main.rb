require_relative 'parser/reader'
require_relative 'parser/parser'
require_relative 'ast/ast'

require 'pp'

# read file
reader = Reader.new
contents = reader.read_file('../examples/simple_questionnaire.ql')

# parse content
parser = Parser.new
parsed = parser.parse(contents)

pp parsed

ast = Ast.new
form = ast.apply(parsed)

# pp parsed
pp form

# gui = Gui.new
# gui.question('joe?')
# gui.launch