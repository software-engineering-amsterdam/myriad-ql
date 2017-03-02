# TODO if-else-then?
# TODO create Undefined class
# TODO create Error & Warning classes
# TODO rubocop

require 'require_all'
require 'pp'

require_rel 'lib'

# read file


# parse content
ql_contents = File.read('examples/simple_questionnaire.ql')
pp ql_contents
ql_parse_tree = QL::Parser::Parser.new.parse(ql_contents)
pp ql_parse_tree
ql_ast = QL::Parser::Transformer.new.apply(ql_parse_tree)
pp ql_ast
ql_errors = QL::TypeChecker::TypeChecker.check(ql_ast)
pp ql_errors



qls_contents = File.read('examples/example.qls')
pp qls_contents
qls_parsed = QLS::Parser::Parser.new.parse(qls_contents)
pp qls_parsed
qls_ast = QLS::Parser::Transformer.new.apply(qls_parsed)
pp qls_ast
type_checker = QLS::TypeChecker::TypeChecker.check(qls_ast, ql_ast)
pp type_checker

QL::GUI::GUI.new(ql_ast, qls_ast, ql_errors)