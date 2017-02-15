require 'parslet'

class Parser < Parslet::Parser
  rule(:space) { match('\s').repeat(1) }
  rule(:space?) { space.maybe }

  rule(:lparen) { str('(') }
  rule(:rparen) { str(')') }
  rule(:quote) { str('"') }
  rule(:qmark) { str('?') }
  rule(:string) { match['a-zA-z0-9\s'].repeat(1) }
  rule(:integer) { match['0-9'].repeat(1) }
  rule(:boolean) { str('true') | str('false') }
  rule(:literal) { string | integer | boolean }
  rule(:operator) { match['*/+-'] }

  rule(:comment) { str('#') >> string.maybe }

  rule(:type) { str('text') | str('bool') | str('number') | str('money') }

  rule(:identifier) { match['a-zA-Z'] >> match['a-zA-z0-9'].repeat(1) }
  rule(:block) { ((if_statement | item) >> space).repeat(1) }
  rule(:expression) do
    lparen >> space? >> expression >> space? >> rparen |
    # infix_expression(literal,
    #   [match['*/'], 2, :left],
    #   [match['+-'], 1, :left]
    # ) |
    literal >> space? >> operator >> space? >> expression |
    identifier |
    literal
  end

  rule(:if_statement) { str('if') >> space >> expression >> space >> block >> space >> (str('else') >> space >> block >> space).maybe >> str('end') }

  rule(:question) { quote >> string >> qmark >> quote }
  rule(:answer) { type >> space >> identifier >> (space? >> str('=>') >> space? >> expression).maybe }

  rule(:item) { question >> space >> answer }

  rule(:form) { str('form') >> space >> identifier >> space >> block >> space >> str('end') }

  root :form
end
