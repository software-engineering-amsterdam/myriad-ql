require_relative 'reader'
require_relative 'parser'
require_relative 'gui'
require 'pp'

class Main
  # read file
  reader = Reader.new
  contents = reader.read_file('../examples/simple_questionnaire.ql')

  # parse content
  parser = Parser.new
  parsed = parser.parse(contents)

  gui = Gui.new
  gui.new_question('"joe?"')
  gui.launch
end