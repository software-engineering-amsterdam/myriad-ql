require 'parslet'
require 'require_all'

require_rel '../ast'

# parser for forms
class QLSParser < Parslet::Parser
  root(:stylesheet)

  # spaces
  rule(:spaces) { match('\s').repeat(1) }
  rule(:spaces?) { spaces.maybe }

  # literals and variable
  rule(:string_literal) { str('"') >> match('[^"]').repeat.as(:string) >> str('"') >> spaces? }
  rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer) >> spaces? }
  rule(:variable) { match('\w+').repeat(1).as(:variable) }

  # stylesheet
  rule(:stylesheet) { spaces? >> (str('stylesheet') >> spaces? >> variable >> spaces? >> page.repeat.as(:pages) >> spaces?).as(:stylesheet) }

  # page
  rule(:page) { spaces? >> str('page') >> spaces? >> (variable >> spaces? >> str('{') >> (spaces? >> (section | default)).repeat.as(:block)).as(:page) >> str('}') >> spaces? }

  # section
  rule(:section) { (spaces? >> str('section') >> spaces? >> string_literal >> spaces? >> (section_brackets | section_no_brackets).as(:block) >> spaces?).as(:section) }
  rule(:section_brackets) { str('{') >> spaces? >> (section | question | default).repeat >> spaces? >> str('}') }
  rule(:section_no_brackets) { (question | default).repeat }

  # question
  rule(:question) { (spaces? >> str('question') >> spaces? >> variable >> spaces? >> (question_brackets | question_no_brackets).as(:properties) >> spaces?).as(:question) }
  rule(:question_brackets) { str('{') >> (spaces? >> attributes).repeat >> spaces? >> str('}') }
  rule(:question_no_brackets) { attributes.maybe }

  # widget
  rule(:widget) { str('widget') >> spaces? >> (str('checkbox') | str('spinbox') | str('slider') | str('text') | radio_button | dropdown).as(:widget) >> spaces? }
  rule(:widget_init) { spaces? >> str('(') >> spaces? >> string_literal.as(:true_value) >> spaces? >> str(',') >> spaces? >> string_literal.as(:false_value) >> spaces? >> str(')') }
  rule(:radio_button) { str('radio') >> widget_init.as(:radio_button) }
  rule(:dropdown) { str('dropdown') >> widget_init.as(:dropdown) }

  # default
  rule(:default) { str('default') >> spaces? >> (type >> (default_brackets | default_no_brackets).as(:properties)).as(:default) >> spaces? }
  rule(:default_brackets) { str('{') >> default_no_brackets >> str('}') }
  rule(:default_no_brackets) { (spaces? >> attributes).repeat >> spaces? }
  rule(:attributes) { width | font | fontsize | color | widget }

  # default attributes
  rule(:type) { (str('boolean') | str('string') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> spaces? }
  rule(:width) { str('width:') >> spaces? >> integer_literal.as(:width) }
  rule(:font) { str('font:') >> spaces? >> string_literal.as(:font) }
  rule(:fontsize) { str('fontsize:') >> spaces? >> integer_literal.as(:fontsize) }
  rule(:color) { str('color:') >> spaces? >> hex_value.as(:string).as(:color) }
  rule(:hex_value) { str('#') >> (digit_hex.repeat(6, 6) | digit_hex.repeat(3, 3)) }
  rule(:digit_hex) { match('[0-9a-fA-F]') }
end
