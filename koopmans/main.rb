# TODO if-else-then?
# TODO create Undefined class
# TODO create Error & Warning classes
# TODO rubocop
# TODO rename if block to if body
# TODO qls.select { |variable| qls.count(variable) > 1 }.uniq as funciton in helper?

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


qls_contents = File.read('examples/example.qls')
p qls_contents
qls_parsed = QLS::Parser::Parser.new.parse(qls_contents)
pp qls_parsed
qls_ast = QLS::Parser::Transformer.new.apply(qls_parsed)
pp qls_ast
type_checker = QLS::TypeChecker::TypeChecker.check(qls_ast, ast)
pp type_checker