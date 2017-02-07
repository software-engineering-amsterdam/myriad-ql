require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) do
    match('\s').repeat(1)
  end
  rule(:spaces?) do
    spaces.maybe
  end


  # question(s) with optional expression
  rule(:label) do
    str('"') >> (str('"').absent? >> any).repeat.as(:label) >> str('"') >> spaces?
  end

  rule(:variable_assignment) do
    (str(':').absent? >> any).repeat.as(:variable) >> str(':') >> spaces?
  end

  rule(:type) do
    (str('boolean') | str('money') | str('integer') | str('string')).as(:type) >> spaces?
  end

  rule(:variable) do
    match('\w+').repeat(1).as(:variable) >> spaces?
  end

  rule(:arithmetic) do
    match('[-+/*]').as(:arithmetic) >> spaces?
  end

  rule(:expression) do
    str('(') >> (str(')').absent? >> (variable >> (arithmetic >> variable).repeat)).repeat.as(:expression) >> str(')')
  end

  rule(:equal_to) do
    str('=') >> spaces?
  end

  rule(:question) do
    (spaces? >> label >> variable_assignment >> type >> (equal_to >> expression).maybe >> spaces? ).as(:question)
  end

  # if block
  rule(:condition) do
    str('(') >> (str(')').absent? >> any).repeat.as(:condition) >> str(')')
  end

  rule(:block) do
    str('{') >> (str('}').absent? >> question | if_statement).repeat.as(:block) >> str('}')
  end

  rule(:if_statement) do
    (str('if') >> spaces? >> condition >> spaces? >> block >> spaces?).as(:if_statement)
  end

  # form
  rule(:form) do
    (str('form') >> spaces? >> variable >> block >> spaces?).as(:form)
  end

  root :question
end
