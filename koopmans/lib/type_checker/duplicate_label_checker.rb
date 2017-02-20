require_relative 'base_checker'

class DuplicateLabelChecker < BaseChecker
  # gather all labels from all questions and check for duplicates
  def visit_form(subject)
    labels = subject.statements.map { |statement| visit_statement(statement) }.flatten.map(&:label)
    labels = labels.select{ |label| labels.count(label) > 1 }.uniq
    labels.map{|label| "[WARNING]: question with label '#{label}' is defined multiple times"}
  end

  # visit all statements of the if block
  def visit_if_statement(subject)
    subject.block.map { |statement| visit_statement(statement) }
  end

  # return question
  def visit_question(subject)
    subject
  end
end