require 'require_all'
require 'tk'
require 'parslet'
require 'pp'

require_rel 'lib'

# # read file
# contents = File.read('examples/simple_questionnaire.ql')
#
# # parse content
# parsed = Parser.new.parse(contents)
# ast = Transformer.new.apply(parsed)
#
# GUI.new(ast, TypeChecker.check(ast))


contents = File.read('examples/example.qls')
p contents