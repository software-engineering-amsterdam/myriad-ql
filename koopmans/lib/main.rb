require_relative 'reader'
require_relative 'parser'
require_relative 'gui'
require_relative 'ast'
require 'pp'


# read file
reader = Reader.new
contents = reader.read_file('../examples/simple_questionnaire.ql')

# parse content
parser = Parser.new
parsed = parser.parse(contents)

# pp parsed

ast = Ast.new
form = ast.apply(parsed)

pp form

# gui = Gui.new
# gui.question('joe?')
# gui.launch