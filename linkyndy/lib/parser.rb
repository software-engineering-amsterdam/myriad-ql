require 'parslet'

class Parser < Parslet::Parser
  rule(:space) { match('\s').repeat(1) }
  rule(:space?) { space.maybe }

  rule(:lparen) { str('\(') }
  rule(:rparen) { str('\)') }
  rule(:quote) { str('"') }
  rule(:qmark) { str('?') }
  rule(:alnum) { match['a-zA-z0-9'].repeat(1) }
  rule(:num) { match['0-9'].repeat(1) }

  rule(:comment) { str('#') >> space? >> alnum }

  rule(:type) { str('text') | str('bool') | str('number') | str('money') }

  rule(:literal) { string | integer | boolean }
  rule(:string) { quote >> alnum >> quote }
  rule(:integer) { num }
  rule(:boolean) { str('true') | str('false') }
  rule(:operator) { str('+') | str('-') | str('*') | str('/') }

  rule(:identifier) { alnum }
  rule(:expression) { lparen >> space? >> (operation | identifier | literal) >> space? >> rparen }
  rule(:operation) { expression >> space? >> operator >> space? >> expression }
  rule(:block) { ((if_statement | item) >> space).repeat(1) }

  rule(:if_statement) { str('if') >> space >> expression >> space >> block >> space >> (str('else') >> space >> block >> space).maybe >> str('end') }

  rule(:question) { quote >> alnum >> qmark >> quote }
  rule(:answer) { type >> spaces? >> identifier >> (spaces? >> str('=>') >> spaces? >> expression).maybe }

  rule(:item) { question >> spaces? >> answer }

  rule(:form) { str('form') >> space >> identifier >> space >> block >> space >> str('end') }
end
