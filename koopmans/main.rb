# TODO if-else-then?
# TODO BooleanType.new naar BooleanType

require 'require_all'
require 'pp'

require_rel 'lib'

# read file
contents = File.read('examples/simple_questionnaire.ql')

# parse content
parsed = QL::Parser::Parser.new.parse(contents)
pp parsed
ast = QL::Parser::Transformer.new.apply(parsed)
pp ast
type_checker = QL::TypeChecker::TypeChecker.check(ast)
pp type_checker
# QL::GUI::GUI.new(ast, type_checker)


contents = File.read('examples/example.qls')
p contents
parsed = QLS::Parser::Parser.new.parse(contents)
pp parsed
ast = QLS::Parser::Transformer.new.apply(parsed)
pp ast