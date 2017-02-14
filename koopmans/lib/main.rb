require_relative 'parser/file_reader'
require_relative 'parser/parser'
require_relative 'parser/transformer'

require 'pp'

# read file
file_reader = FileReader.new
contents = file_reader.read_file('../examples/simple_questionnaire.ql')

# parse content
parser = Parser.new
parsed = parser.parse(contents)

pp parsed

transformer = Transformer.new
form = transformer.apply(parsed)

# pp parsed
pp form

# gui = Gui.new
# gui.question('joe?')
# gui.launch