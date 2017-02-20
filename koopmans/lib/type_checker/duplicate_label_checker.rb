require_relative 'base_checker'

class DuplicateLabelChecker < BaseChecker
  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    labels = subject.statements.map { |statement| visit_statement(statement) }.flatten
    labels.select{ |e| labels.count(e) > 1 }.uniq
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # only return the label of the given question
  def visit_question(subject)
    subject.label
  end
end