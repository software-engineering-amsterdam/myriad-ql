require 'parslet'

# parser for forms
class Parser < Parslet::Parser
  rule(:spaces) do
    match('\s').repeat(1)
  end
  rule(:spaces?) do
    spaces.maybe
  end

  def self.symbols(symbols)
    symbols.each do |name, symbol|
      rule(name) { str(symbol) }
    end
  end

  symbols left_brace: '{',
          right_brace: '}',
          left_parenthesis: '(',
          right_parenthesis: ')',
          quote: '"',
          colon: ':',

          subtract: '-',
          add: '+',
          multiply: '*',
          divide: '/',
          assign: '=',

          boolean: 'boolean',
          integer: 'integer',
          string: 'string',
          money: 'money',

          if_: 'if',
          form_: 'form'


  # question(s) with optional expression
  rule(:label) do
    quote >> match('[^"]').repeat.as(:label) >> quote >> spaces?
  end

  rule(:type) do
    (boolean | integer | string | money).as(:type) >> spaces?
  end

  rule(:variable) do
    match('\w+').repeat(1).as(:variable) >> spaces?
  end

  rule(:variable_assignment) do
    variable >> colon >> spaces?
  end

  rule(:arithmetic) do
    (subtract | add | multiply | divide).as(:arithmetic) >> spaces?
  end

  rule(:expression) do
    left_parenthesis >> (variable >> (arithmetic >> variable).repeat).repeat.as(:expression) >> right_parenthesis
  end

  rule(:assignment?) do
    (assign >> spaces? >> expression).maybe >> spaces?
  end

  rule(:question) do
    (label >> variable_assignment >> type >> assignment?).as(:question) >> spaces?
  end


  # if block
  rule(:condition) do
    spaces? >> left_parenthesis >> spaces? >> variable.as(:condition) >> right_parenthesis >> spaces?
  end

  rule(:block) do
    left_brace >> spaces? >> (question | if_statement).repeat.as(:block) >> right_brace >> spaces?
  end

  rule(:if_statement) do
    (if_ >> condition >> block).as(:if_statement)
  end


  # form
  rule(:form) do
    (spaces? >> form_ >> spaces? >> variable >> spaces? >> block)
  end

  root :form
end
