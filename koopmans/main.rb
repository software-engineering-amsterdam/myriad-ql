require 'require_all'

require_rel 'lib'
require_relative 'lib/ql/ql'

# # QL
# ql = QL::QL.new
# ql.build('examples/simple_questionnaire.ql')
# # ql.run_gui
#
# # QLS
# qls = QLS::QLS.new(ql)
# qls.build('examples/example.qls')
# qls.run_gui



# TODO: oude main::

# parse content
ql_contents = File.read('examples/simple_questionnaire.ql')
# pp ql_contents
ql_parse_tree = QL::Parser::FormParser.new.parse(ql_contents)
# pp ql_parse_tree
ql_ast = QL::Parser::FormTransformer.new.apply(ql_parse_tree)
# pp ql_ast
QL::TypeChecker::TypeChecker.new.check(ql_ast)
# pp NotificationTable.index
# ql_notifications = nil
form_builder = QL::GUI::FormBuilder.new
ql_ast.accept(form_builder)
gui = QL::GUI::GUI.new(form_builder.question_frames)

# gui.render

qls_contents = File.read('examples/example.qls')
# pp qls_contents
qls_parsed = QLS::Parser::FormParser.new.parse(qls_contents)
qls_parsed
qls_ast = QLS::Parser::FormTransformer.new.apply(qls_parsed)
# pp qls_ast
'-------------------------------------'

style_collector = QLS::GUI::StyleCollector.new
qls_ast.accept(style_collector)
# pp style_collector.styles
question_frame_styler = QLS::GUI::QuestionFrameStyler.new(style_collector.styles)
qls_ast.accept(question_frame_styler)
question_frame_styles = question_frame_styler.question_frame_styles
# pp question_frame_styles
# qls_notifications = QLS::TypeChecker::TypeChecker.check(qls_ast, ql_ast)
# pp qls_notifications
#
# qls_ast = nil
# #
# QL::GUI::GUI.new(ql_ast, qls_ast, ql_notifications)

gui_with_style = QLS::GUI::GUIWithStyle.new(gui)
gui_with_style.question_frame_styles = question_frame_styles
gui_with_style.render

# gui.extend(QLS::GUI::GUIStylesheet)
# gui.question_frame_styles = question_frame_styles
# gui.render