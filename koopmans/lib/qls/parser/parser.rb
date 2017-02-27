require 'parslet'
require 'require_all'

require_rel '../ast'

# parser for forms
class QLSParser < Parslet::Parser
  # spaces
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }


  rule(:stylesheet) { spaces? >> (str('stylesheet') >> spaces? >> variable >> spaces? >> page.repeat).as(:form) }
  rule(:page) { spaces? >> str('page') >> spaces? >> (variable >> spaces? >> str('{') >> spaces? >> section.repeat).as(:page) >> str('}') }
  rule(:block) { spaces? >> str('{') >> spaces? >> section.repeat >> str('}') >> spaces? }
  rule(:variable) { match('\w+').repeat(1).as(:variable) }


  rule(:section) { (spaces? >> str('section') >> spaces? >> string_literal >> spaces? >> (section_brackets | section_no_brackets)).as(:section) }
  rule(:section_brackets) { str('{') >> spaces? >> (section | question).repeat >> str('}') }
  rule(:section_no_brackets) { (section | question).repeat }

  rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string) >> str('"') >> spaces? }
  rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer) >> spaces? }

  rule(:question) { spaces? >> str('question') >> spaces? >> (variable >> spaces? >> widget.maybe).as(:question) >> spaces? }
  rule(:widget) { str('widget') >> spaces? >> (str('checkbox') | str('spinbox') | radio_button).as(:widget) >> spaces? }
  rule(:radio_button) { str('radio') >> spaces? >> str('(') >> spaces? >> string_literal.as(:first) >> spaces? >> str(',') >> spaces? >> string_literal.as(:second) >> spaces? >> str(')') }


  rule(:default) { str('default') >> spaces? >> (type >> default_brackets | default_no_brackets).as(:default) }
  rule(:default_brackets) { str('{') >> default_no_brackets >> str('}') }
  rule(:default_no_brackets) { (spaces? >> (width | font | fontsize | color | widget)).repeat).as(:default) >> spaces? }

  rule(:type) { (str('boolean') | str('string') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> spaces? }
  rule(:width) { str('width:') >> spaces? >> integer_literal.as(:width) }
  rule(:font) { str('font:') >> spaces? >> string_literal.as(:font) }
  rule(:fontsize) { str('fontsize:') >> spaces? >> integer_literal.as(:fontsize) }

  rule(:color) { str('color:') >> spaces? >> (hex_value | variable).as(:color) }
  rule(:hex_value) { str('#') >> (digit_hex.repeat(6, 6) | digit_hex.repeat(3, 3)) }
  rule(:digit_hex) { match('[0-9a-fA-F]') }

  root(:stylesheet)


  #
  # # expression
  # rule(:integer_negation?) { str('-').as(:integer_negation).maybe }
  # rule(:boolean_negation?) { str('!').as(:boolean_negation).maybe }
  # rule(:negation?) { (str('!') | str('-')).as(:negation).maybe }
  # rule(:variable_or_literal) { (boolean_negation? >> boolean_literal | integer_negation? >> integer_literal | string_literal | negation? >> variable) >> spaces? }
  # rule(:calculation) { variable_or_literal.as(:left) >> operator >> expression.as(:right) }
  # rule(:operator) { (str('-') | str('+') | str('*') | str('/') | str('<=') | str('>=') | str('==') | str('!=') | str('<') | str('>') | str('&&') | str('||') | str('!')).as(:operator) >> spaces? }
  # rule(:expression) { str('(') >> spaces? >> expression.as(:expression) >> spaces? >> str(')') >> spaces? | calculation | variable_or_literal }
  #
  # # form
  # rule(:form) { spaces? >> (str('form') >> spaces? >> variable >> spaces? >> block).as(:form) }
  #
  # # literal
  # rule(:boolean_literal) { (str('true') | str('false')).as(:boolean) >> spaces? }
  rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer) >> spaces? }
  # rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string) >> str('"') >> spaces? }
  #
  # # statement
  # rule(:assignment?) { (str('=') >> spaces? >> expression).maybe >> spaces? }
  # rule(:question) { (string_literal >> variable_assignment >> type >> assignment?).as(:question) >> spaces? }
  # rule(:block) { str('{') >> spaces? >> (question | if_statement).repeat.as(:block) >> str('}') >> spaces? }
  # rule(:if_statement) { (str('if') >> spaces? >> expression >> block).as(:if_statement) }
  #
  # # type

  #
  # # variable
  # rule(:variable) { match('\w+').repeat(1).as(:variable) }
  # rule(:variable_assignment) { variable >> str(':') >> spaces? }
end
