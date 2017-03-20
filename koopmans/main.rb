# TODO rubocop, cmd+alt+l
# TODO catch errors
# TODO if-else-then?
# TODO create Undefined class
# TODO gui tests
# TODO don't abbreviate: eval_type wordt evaluate_type
# TODO constructivist style for errors? see page 155 of coding styles book

require 'require_all'
require 'pp'

require_rel 'lib'
include NotificationTable

# read file

gui = QL::GUI::GUI.new

# parse content
ql_contents = File.read('examples/simple_questionnaire.ql')
# pp ql_contents
ql_parse_tree = QL::Parser::FormParser.new.parse(ql_contents)
pp ql_parse_tree
ql_ast = QL::Parser::FormTransformer.new.apply(ql_parse_tree)
# pp ql_ast
pp ql_ast
question_frames = ql_ast.accept(QL::GUI::FormBuilder.new)
# gui.question_frames = question_frames
# gui.render
QL::TypeChecker::TypeChecker.new.check(ql_ast)
pp NotificationTable.index
ql_notifications = nil



# qls_contents = File.read('examples/example.qls')
# pp qls_contents
# qls_parsed = QLS::Parser::Parser.new.parse(qls_contents)
# pp qls_parsed
# qls_ast = QLS::Parser::Transformer.new.apply(qls_parsed)
# pp qls_ast
# qls_notifications = QLS::TypeChecker::TypeChecker.check(qls_ast, ql_ast)
# pp qls_notifications
#
qls_ast = nil
# #
# question_frames = ql_ast.accept(QL::GUI::FormBuilder.new)
# gui.question_frames = question_frames
# gui.run