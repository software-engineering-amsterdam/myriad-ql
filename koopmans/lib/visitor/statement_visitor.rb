require_relative 'base_visitor'

# class StatementVisitor
#   def visit(subject)
#     if subject.kind_of?(Question)
#       # visit_question(subject)
#     elsif subject.kind_of?(IfStatement)
#       visit_if_statement(subject)
#     else
#       puts'"ERROR'
#     end
#   end
#
#   def visit_question(subject)
#     puts 'Visiting Question: %s' % subject.label
#   end
#
#   def visit_if_statement(subject)
#     puts 'Visiting If Statement: %s' % subject.expression
#   end
# end