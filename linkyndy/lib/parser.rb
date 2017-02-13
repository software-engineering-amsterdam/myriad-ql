require 'parslet'

class Parser < Parslet::Parser
  rule(:space) { match('\s').repeat(1) }
  rule(:space?) { space.maybe }

  rule(:lparen) { str('\(') }
  rule(:rparen) { str('\)') }

  rule(:comment) { str('#') >> space? >> match['a-zA-z'].repeat(1) }

  rule(:type) { str('text') | str('bool') | str('number') | str('money') }

  rule(:literal) { text | number }
  rule(:text) { match['"a-zA-z"'].repeat(1) }
  rule(:number) { match['0-9'].repeat(1) }
  rule(:operator) { str('+') | str('-') | str('*') | str('/') }

  rule(:identifier) { match['a-zA-z'].repeat(1) }
  rule(:expression) { lparen >> space? >> (operation | identifier | literal) >> space? >> rparen }
  rule(:operation) { expression >> space? >> operator >> space? >> expression }

  rule(:value?) { str('=>') >> spaces? >> expression }

  rule(:question) { str('"') >> match['a-zA-z\?'].repeat(1) >> str('"') >> spaces? }
  rule(:answer) { type >> spaces? >> identifier >> spaces? >> value? >> spaces? }

  rule(:item) { question >> answer }

  rule(:form)
end
