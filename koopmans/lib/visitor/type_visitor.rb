require_relative '../visitor/base_visitor'

class TypeVisitor < BaseVisitor
  def visit_form(subject)
    types = subject.statements.map{|u| visit_statement(u)}
    # convert to hash
    Hash[*types.flatten]
  end

  def visit_question(subject)
    [subject.variable.name, subject.type]
  end
end