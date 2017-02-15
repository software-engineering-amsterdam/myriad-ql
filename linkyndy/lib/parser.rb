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
  rule(:operator) { str('+') | str('-') | str('*') | str('/') }

  rule(:comment) { str('#') >> string.maybe }

  rule(:type) { str('text') | str('bool') | str('number') | str('money') }

  rule(:identifier) { match['a-zA-Z'] >> match['a-zA-z0-9'].repeat(1) }
  rule(:expression) { lparen >> space? >> (operation | identifier | literal) >> space? >> rparen }
  rule(:operation) { expression >> space? >> operator >> space? >> expression }
  rule(:block) { ((if_statement | item) >> space).repeat(1) }

  rule(:if_statement) { str('if') >> space >> expression >> space >> block >> space >> (str('else') >> space >> block >> space).maybe >> str('end') }

  rule(:question) { quote >> string >> qmark >> quote }
  rule(:answer) { type >> space >> identifier >> (space? >> str('=>') >> space? >> expression).maybe }

  rule(:item) { question >> space >> answer }

  rule(:form) { str('form') >> space >> identifier >> space >> block >> space >> str('end') }

  root :form
end
