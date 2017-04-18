require 'require_all'

require_rel 'lib'
require_relative 'lib/ql/ql'

# QL
ql = QL::QL.new
ql.build('examples/simple_questionnaire.ql')
# ql.run_gui

# QLS
qls = QLS::QLS.new(ql)
qls.build('examples/example.qls')
qls.run_gui
