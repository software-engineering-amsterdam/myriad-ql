require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module Parser
    describe Parser do
      let(:parser) { Parser.new }

      describe 'literals and their negations' do
        context 'boolean' do
          it 'parses' do
            expect(parser.boolean_literal).to parse('true')
            expect(parser.boolean_literal).to_not parse('True')
          end
        end

        context 'integer' do
          it 'parses' do
            expect(parser.integer_literal).to parse('42')
          end
        end

        context 'string' do
          it 'parses' do
            expect(parser.string_literal).to parse('"How much is?"')
          end
        end
      end


      describe 'variables' do
        context 'variable' do
          it 'parses' do
            expect(parser.variable).to parse('sellingPrice')
          end
        end

        context 'variable assignment' do
          it 'parses' do
            expect(parser.variable_assignment).to parse('hasSoldHouse:')
          end
        end
      end

      describe 'types' do
        it 'parses' do
          expect(parser.type).to parse('boolean')
          expect(parser.type).to parse('integer')
        end
      end

      describe 'expressions' do
        context 'expressions' do
          it 'parses' do
            expect(parser.expression).to parse('(-5 + !true * 5 || (sellingPrice > anotherVariable) + 5)')
            expect(parser.expression).to parse('( - privateDebt / anotherVariable)')
            expect(parser.expression).to_not parse('( -privateDebt / anotherVariable')
          end
        end

        context 'arithmetic' do
          it 'parses' do
            expect(parser.expression).to parse('(5 + 10 * 5)')
            expect(parser.expression).to parse('(sellingPrice - privateDebt / anotherVariable)')
          end
        end

        context 'boolean' do
          it 'parses' do
            expect(parser.expression).to parse('(true && false)')
            expect(parser.expression).to parse('false || false')
            expect(parser.expression).to parse('sellingPrice || privateDebt')
          end
        end

        context 'comparison' do
          it 'parses' do
            expect(parser.expression).to parse('(5 < 10)')
            expect(parser.expression).to parse('(8 != 10)')
            expect(parser.expression).to parse('(sellingPrice == privateDebt)')
          end
        end

        context 'negations' do
          it 'parses' do
            expect(parser.expression).to parse('(!true)')
            expect(parser.expression).to parse('(-5)')
          end
        end
      end

      describe 'statements' do
        context 'question' do
          it 'parses' do
            expect(parser.question).to parse('"How much is?" hasSoldHouse: boolean')
            expect(parser.question).to parse('"Value residue:" valueResidue: money = (sellingPrice - privateDebt)')
          end
        end

        context 'if statement with one question' do
          it 'parses' do
            expect(parser.if_statement).to parse('if (hasSoldHouse) {
              "What was the selling price?"
                sellingPrice: money
            }')
          end
          it 'parses body' do
            expect(parser.body).to parse('
            "What was the selling price?"
              sellingPrice: money
            ')
          end
        end
      end


      describe 'form' do
        it 'parses' do
          expect(parser.form).to parse('form taxOfficeExample {
            "Did you sell a house in 2010?"
              hasSoldHouse: boolean
            "Did you buy a house in 2010?"
              hasBoughtHouse: boolean
            "Did you enter a loan?"
              hasMaintLoan: boolean
          }')
          expect(parser.form).to parse('form taxOfficeExample {
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