require_relative 'base_checker'

class TypeVisitor < BaseChecker
  def visit_form(subject)
    types = subject.statements.map{|u| visit_statement(u)}
    # convert to hash
    Hash[*types.flatten]
  end

  def visit_question(subject)
    [subject.variable.name, subject.type]
  end
end