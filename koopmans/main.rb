require 'require_all'

require_rel 'lib'
require_relative 'lib/ql/ql'

# filename = 'original'
# filename = 'errors'
# filename = 'styles'
filename = 'widgets'

# QL
ql = QL::QL.new
ql.build("examples/#{filename}.ql")

# apply QLS
ql = QLS::QLS.new(ql)
ql.build("examples/#{filename}.qls")

ql.run_gui
