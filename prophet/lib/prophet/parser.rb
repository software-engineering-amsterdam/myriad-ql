module Prophet
  class Parser < Parslet::Parser
    rule(:space) { match('\s').repeat(1) }
    rule(:space?) { space.maybe }

    rule(:lparen) { str('(') >> space? }
    rule(:rparen) { str(')') >> space? }
    rule(:quote) { str('"') >> space? }
    rule(:qmark) { str('?') >> space? }
    rule(:hashrocket) { space? >> str('=>') >> space? }

    rule(:comment) { str('#') >> match['^\n'].repeat >> match('\n') >> space? }

    rule(:text) { quote >> match['^"'].repeat.as(:text) >> quote }
    rule(:number) { match['0-9'].repeat(1).as(:number) >> space? }
    rule(:bool) { (str('true') | str('false')).as(:bool) >> space? }
    rule(:literal) { text | number | bool }

    rule(:type) { (str('text') | str('bool') | str('number')).as(:type) >> space? }

    rule(:identifier) { (match['a-zA-Z'] >> match['a-zA-z0-9'].repeat).as(:identifier) >> space? }

    rule(:logical_and) { str('&&').as(:operator) >> space? }
    rule(:logical_or) { str('||').as(:operator) >> space? }

    rule(:equal) { str('==').as(:operator) >> space? }
    rule(:not_equal) { str('!=').as(:operator) >> space? }

    rule(:less_then_or_equal) { str('<=').as(:operator) >> space? }
    rule(:less_then) { str('<').as(:operator) >> space? }
    rule(:greater_then) { str('>').as(:operator) >> space? }
    rule(:greater_then_or_equal) { str('>=').as(:operator) >> space? }

    rule(:plus) { str('+').as(:operator) >> space? }
    rule(:minus) { str('-').as(:operator) >> space? }

    rule(:multiply) { str('*').as(:operator) >> space? }
    rule(:divide) { str('/').as(:operator) >> space? }

    rule(:negation) { str('!').as(:operator) >> space? }

    rule(:expression) { logical_term.as(:left) >> (logical_and | logical_or) >> expression.as(:right) | logical_term }
    rule(:logical_term) { equality_term.as(:left) >> (equal | not_equal) >> logical_term.as(:right) | equality_term }
    rule(:equality_term) { comparison_term.as(:left) >> (less_then_or_equal | less_then | greater_then | greater_then_or_equal) >> equality_term.as(:right) | comparison_term }
    rule(:comparison_term) { addition_term.as(:left) >> (plus | minus) >> comparison_term.as(:right) | addition_term }
    rule(:addition_term) { multiplication_term.as(:left) >> (multiply | divide) >> addition_term.as(:right) | multiplication_term }
    rule(:multiplication_term) { negation >> factor.as(:value) | factor }
    rule(:factor) { lparen >> expression >> rparen | literal | identifier }

    rule(:block) { (if_statement | question | comment).repeat }

    rule(:if_statement) { (str('if') >> space >> expression.as(:condition) >> block.as(:true_branch) >> (str('else') >> space >> block.as(:false_branch)).maybe >> str('end')).as(:if_statement) >> space? }

    rule(:question) { (text >> type.as(:type) >> identifier.as(:identifier) >> (hashrocket >> expression.as(:value)).maybe).as(:question) }

    rule(:form) { (str('form') >> space >> identifier.as(:identifier) >> block.as(:body) >> str('end')).as(:form) >> space? }

    root :form
  end
end
