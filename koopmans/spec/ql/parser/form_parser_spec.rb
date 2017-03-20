require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module Parser
    describe FormParser do
      let(:form_parser) { FormParser.new }

      describe 'literals and their negations' do
        context 'boolean' do
          it 'parses' do
            expect(form_parser.boolean_literal).to parse('true')
            expect(form_parser.boolean_literal).to_not parse('True')
          end
        end

        context 'integer' do
          it 'parses' do
            expect(form_parser.integer_literal).to parse('42')
          end
        end

        context 'string' do
          it 'parses' do
            expect(form_parser.string_literal).to parse('"How much is?"')
          end
        end
      end


      describe 'variables' do
        context 'variable' do
          it 'parses' do
            expect(form_parser.variable).to parse('sellingPrice')
          end
        end

        context 'variable assignment' do
          it 'parses' do
            expect(form_parser.variable_assignment).to parse('hasSoldHouse:')
          end
        end
      end

      describe 'types' do
        it 'parses' do
          expect(form_parser.type).to parse('boolean')
          expect(form_parser.type).to parse('integer')
        end
      end

      describe 'expressions' do
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

      describe 'statements' do
        context 'question' do
          it 'parses' do
            expect(form_parser.question).to parse('"How much is?" hasSoldHouse: boolean')
            expect(form_parser.question).to parse('"Value residue:" valueResidue: money = (sellingPrice - privateDebt)')
          end
        end

        context 'if statement with one question' do
          it 'parses' do
            expect(form_parser.if_statement).to parse('if (hasSoldHouse) {
              "What was the selling price?"
                sellingPrice: money
            }')
          end
          it 'parses body' do
            expect(form_parser.body).to parse('
            "What was the selling price?"
              sellingPrice: money
            ')
          end
        end

        context 'if else statement' do
          it 'parses' do
            expect(form_parser.if_else_statement).to parse('if (hasSoldHouse) {
              "What was the selling price?"
                sellingPrice: money
            } else {
              "How much is?"
                valueResidue: money
            }')
          end
        end
      end


      describe 'form' do
        it 'parses' do
          expect(form_parser.form).to parse('form taxOfficeExample {
            "Did you sell a house in 2010?"
              hasSoldHouse: boolean
            "Did you buy a house in 2010?"
              hasBoughtHouse: boolean
            "Did you enter a loan?"
              hasMaintLoan: boolean
          }')
          expect(form_parser.form).to parse('form taxOfficeExample {
            "Did you sell a house in 2010?"
              hasSoldHouse: boolean
            "Did you buy a house in 2010?"
              hasBoughtHouse: boolean
            "Did you enter a loan?"
              hasMaintLoan: boolean

            if (hasSoldHouse) {
              "What was the selling price?"
                sellingPrice: money
              "Private debts for the sold house:"
                privateDebt: money
              "Value residue:"
                valueResidue: money =
                  (sellingPrice - privateDebt)
            }
          }')
        end
      end
    end
  end
end