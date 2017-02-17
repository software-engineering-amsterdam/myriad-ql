require_relative 'base_checker'

class DuplicateLabelChecker < BaseChecker
  def visit_form(subject)
    labels = subject.statements.map { |statement| visit_statement(statement) }.flatten
    labels.select{ |e| labels.count(e) > 1 }.uniq
  end

  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  def visit_question(subject)
    subject.label
  end
end