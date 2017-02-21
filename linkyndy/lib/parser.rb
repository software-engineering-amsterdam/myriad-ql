require 'parslet'

class Parser < Parslet::Parser
  rule(:space) { match('\s').repeat(1) }
  rule(:space?) { space.maybe }

  rule(:lparen) { str('(') >> space? }
  rule(:rparen) { str(')') >> space? }
  rule(:quote) { str('"') >> space? }
  rule(:qmark) { str('?') >> space? }
  rule(:hashrocket) { space? >> str('=>') >> space? }

  rule(:string) { quote >> match['^"'].repeat >> quote }
  rule(:integer) { match['0-9'].repeat(1) >> space? }
  rule(:boolean) { (str('true') | str('false')) >> space? }
  rule(:literal) { string | integer | boolean }
  rule(:operator) { match['*/+-'] >> space? }

  rule(:comment) { str('#') >> any.repeat >> space? }

  rule(:type) { (str('text') | str('bool') | str('number') | str('money')) >> space? }

  rule(:identifier) { match['a-zA-Z'] >> match['a-zA-z0-9'].repeat >> space? }
  rule(:expression) do
    lparen >> expression >> rparen |
    # infix_expression(literal,
    #   [match['*/'], 2, :left],
    #   [match['+-'], 1, :left]
    # ) |
    literal >> operator >> expression |
    identifier |
    literal
  end
  rule(:block) { (if_statement | item).repeat(1) }

  rule(:if_statement) { str('if') >> space >> expression >> block >> (str('else') >> space >> block).maybe >> str('end') >> space? }

  rule(:question) { quote >> string >> qmark >> quote }
  rule(:answer) { type >> space >> identifier >> (space? >> str('=>') >> space? >> expression).maybe }

  rule(:item) { question >> space >> answer }

  rule(:form) { str('form') >> space >> identifier >> block >> str('end') }

  root :form
end
