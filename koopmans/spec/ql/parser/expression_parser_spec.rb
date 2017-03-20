require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module Parser
    describe ExpressionParser do
      let(:form_parser) { FormParser.new }
      context 'general' do
        it 'parses' do
          expect(form_parser.expression).to parse('(-5 + !true * 5 || (sellingPrice > anotherVariable) + 5)')
          expect(form_parser.expression).to parse('( -privateDebt / anotherVariable)')
          expect(form_parser.expression).to_not parse('( -privateDebt / anotherVariable')
        end
      end

      context 'arithmetic' do
        it 'parses' do
          expect(form_parser.expression).to parse('(5 + 10 * 5)')
          expect(form_parser.expression).to parse('(sellingPrice - privateDebt / anotherVariable)')
        end
      end

      context 'boolean' do
        it 'parses' do
          expect(form_parser.expression).to parse('(true && false)')
          expect(form_parser.expression).to parse('false || false')
          expect(form_parser.expression).to parse('sellingPrice || privateDebt')
        end
      end

      context 'comparison' do
        it 'parses' do
          expect(form_parser.expression).to parse('(5 < 10)')
          expect(form_parser.expression).to parse('(8 != 10)')
          expect(form_parser.expression).to parse('(sellingPrice == privateDebt)')
        end
      end

      context 'negations' do
        it 'parses' do
          expect(form_parser.expression).to parse('(!true)')
          expect(form_parser.expression).to parse('(-5)')
        end
      end
    end
  end
end
