require 'require_all'
require 'tk'
require 'parslet'
require 'pp'

require_rel 'lib'

# read file
contents = File.read('examples/simple_questionnaire.ql')

# parse content
parsed = QL::Parser::Parser.new.parse(contents)
# pp parsed
ast = QL::Parser::Transformer.new.apply(parsed)
type_checker = QL::TypeChecker::TypeChecker.check(ast)
pp type_checker
QL::GUI::GUI.new(ast, type_checker)


# contents = File.read('examples/example.qls')
# p contents