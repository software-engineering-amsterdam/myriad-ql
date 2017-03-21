require 'parslet'
require 'require_all'

require_rel '../ast'

module QLS
  module Parser
    class FormParser < Parslet::Parser
      root(:stylesheet)

      # spaces, breaks, tabs
      rule(:_) { match('\s').repeat(1).maybe }

      # literals and variable
      rule(:literal)         { string_literal | integer_literal }
      rule(:string_literal)  { str('"') >> match('[^"]').repeat.as(:string_literal) >> str('"') >> _ }
      rule(:integer_literal) { match('[0-9]').repeat(1).as(:integer_literal) >> _ }
      rule(:variable)        { match('\w+').repeat(1).as(:variable) }

      # stylesheet
      rule(:stylesheet) { _ >> (str('stylesheet') >> _ >> variable >> _ >> page.repeat.as(:pages) >> _).as(:stylesheet) }

      # page
      rule(:page) { _ >> str('page') >> _ >> (variable >> _ >> str('{') >> (_ >> (section | default)).repeat.as(:body)).as(:page) >> str('}') >> _ }

      # section
      rule(:section)                  { (_ >> str('section') >> _ >> string_literal >> _ >> (section_brackets | section_without_brackets).as(:body) >> _).as(:section) }
      rule(:section_brackets)         { str('{') >> _ >> (section | question | default).repeat >> _ >> str('}') }
      rule(:section_without_brackets) { (question | default).repeat }

      # question
      rule(:question)                  { (_ >> str('question') >> _ >> variable >> _ >> (question_brackets | question_without_brackets).as(:properties) >> _).as(:question) }
      rule(:question_brackets)         { str('{') >> (_ >> properties).repeat >> _ >> str('}') }
      rule(:question_without_brackets) { properties.maybe }

      # widget
      rule(:widget)        { str('widget') >> _ >> (str('checkbox') | str('spinbox') | str('text') | slider | radio | dropdown).as(:widget) >> _ }
      rule(:widget_values) { literal.as(:first_value) >> _ >> str(',') >> _ >> literal.as(:second_value) }
      rule(:radio)         { str('radio') >> str('(') >> _ >> widget_values.as(:radio) >> _ >> str(')') }
      rule(:dropdown)      { str('dropdown') >> str('(') >> _ >> widget_values.as(:dropdown) >> _ >> str(')') }
      rule(:slider)        { str('slider') >> str('(') >> _ >> widget_values.as(:slider) >> _ >> str(')') }

      # default
      rule(:default)                  { str('default') >> _ >> (type.as(:type) >> (default_brackets | default_without_brackets).as(:properties)).as(:default) >> _ }
      rule(:default_brackets)         { str('{') >> default_without_brackets >> str('}') }
      rule(:default_without_brackets) { (_ >> properties).repeat >> _ }

      # properties
      rule(:properties) { width | font | fontsize | color | widget }
      rule(:type)       { (str('boolean') | str('string') | str('integer') | str('decimal') | str('date') | str('money')).as(:type) >> _ }
      rule(:width)      { str('width:') >> _ >> integer_literal.as(:width) }
      rule(:font)       { str('font:') >> _ >> string_literal.as(:font) }
      rule(:fontsize)   { str('fontsize:') >> _ >> integer_literal.as(:fontsize) }
      rule(:color)      { str('color:') >> _ >> hex_value.as(:string_literal).as(:color) }
      rule(:hex_value)  { str('#') >> (digit_hex.repeat(6, 6) | digit_hex.repeat(3, 3)) }
      rule(:digit_hex)  { match('[0-9a-fA-F]') }
    end
  end
end
