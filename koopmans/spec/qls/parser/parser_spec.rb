require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

describe QLSParser do
  let(:parser) { QLSParser.new }

  describe 'question' do
    it 'parses' do
      expect(parser.question).to parse('question hasBoughtHouse widget checkbox')
      expect(parser.question).to parse('question hasMaintLoan')
      # expect(parser.question.parse('question hasBoughtHouse')).to eq('aa')
    end
  end

  describe 'section' do
    it 'parses' do
      expect(parser.section).to parse('section "Buying" question hasBoughtHouse widget checkbox')
      expect(parser.section).to parse('section "Selling" { question hasSoldHouse widget checkbox }')
    end
  end

  describe 'widget' do
    it 'parses' do
      expect(parser.widget).to parse('widget radio("Yes", "No")')
      expect(parser.widget).to parse('widget checkbox')
    end
  end

  describe 'radio button' do
    it 'parses' do
      expect(parser.radio_button).to parse('radio("Yes", "No")')
    end
  end

  describe 'default' do
    it 'parses' do
      expect(parser.default.parse('default money {
      width: 400
      font: "Arial"
      fontsize: 14
      color: #999999
      widget spinbox
    }')).to eq('aa')
    end
  end

  describe 'page' do
    it 'parses' do
      expect(parser.page).to parse('page Housing {
        section "Buying"
          question hasBoughtHouse
            widget checkbox
        section "Loaning"
          question hasMaintLoan
      }')
    end
  end

  # describe 'literals and their negations' do
  #   context 'boolean' do
  #     it 'parses' do
  #       expect(parser.boolean_literal).to parse('true')
  #       expect(parser.boolean_literal).to_not parse('True')
  #     end
  #
  #     it 'parses negation' do
  #       expect(parser.boolean_negation?).to parse('!')
  #     end
  #   end
  #
  #   context 'integer' do
  #     it 'parses' do
  #       expect(parser.integer_literal).to parse('42')
  #     end
  #
  #     it 'parses negation' do
  #       expect(parser.integer_negation?).to parse('-')
  #     end
  #   end
  #
  #   context 'string' do
  #     it 'parses' do
  #       expect(parser.string_literal).to parse('"How much is?"')
  #     end
  #   end
  # end
  #
  #
  # describe 'variables' do
  #   context 'variable' do
  #     it 'parses' do
  #       expect(parser.variable).to parse('sellingPrice')
  #     end
  #
  #     it 'parses negation' do
  #       expect(parser.negation?).to parse('!')
  #       expect(parser.negation?).to parse('-')
  #     end
  #   end
  #
  #   context 'variable assignment' do
  #     it 'parses' do
  #       expect(parser.variable_assignment).to parse('hasSoldHouse:')
  #     end
  #   end
  # end
  #
  # describe 'types' do
  #   it 'parses' do
  #     expect(parser.type).to parse('boolean')
  #     expect(parser.type).to parse('integer')
  #   end
  # end
  #
  # describe 'expressions' do
  #   context 'arithmetic' do
  #     it 'parses' do
  #       expect(parser.operator).to parse('+')
  #     end
  #
  #     it 'parses expression' do
  #       expect(parser.expression).to parse('(sellingPrice - privateDebt + anotherVariable)')
  #     end
  #   end
  #
  #   context 'boolean' do
  #     it 'parses' do
  #       expect(parser.operator).to parse('&&')
  #     end
  #
  #     it 'parses expression' do
  #       expect(parser.expression).to parse('(true && false)')
  #     end
  #   end
  #
  #   context 'comparison' do
  #     it 'parses' do
  #       expect(parser.operator).to parse('<=')
  #     end
  #
  #     it 'parses comparison' do
  #       expect(parser.expression).to parse('(5 < 10)')
  #     end
  #   end
  # end
  #
  # describe 'statements' do
  #   context 'question' do
  #     it 'parses' do
  #       expect(parser.question).to parse('"How much is?" hasSoldHouse: boolean')
  #       expect(parser.question).to parse('"Value residue:" valueResidue: money = (sellingPrice - privateDebt)')
  #     end
  #   end
  #
  #   context 'if statement with one question' do
  #     it 'parses' do
  #       expect(parser.if_statement).to parse('if (hasSoldHouse) {
  #         "What was the selling price?"
  #           sellingPrice: money
  #       }')
  #     end
  #     it 'parses block' do
  #       expect(parser.block).to parse('{
  #       "What was the selling price?"
  #         sellingPrice: money
  #       }')
  #     end
  #   end
  # end
  #
  #
  # describe 'form' do
  #   it 'parses' do
  #     expect(parser.form).to parse('form taxOfficeExample {
  #       "Did you sell a house in 2010?"
  #         hasSoldHouse: boolean
  #       "Did you buy a house in 2010?"
  #         hasBoughtHouse: boolean
  #       "Did you enter a loan?"
  #         hasMaintLoan: boolean
  #     }')
  #     expect(parser.form).to parse('form taxOfficeExample {
  #       "Did you sell a house in 2010?"
  #         hasSoldHouse: boolean
  #       "Did you buy a house in 2010?"
  #         hasBoughtHouse: boolean
  #       "Did you enter a loan?"
  #         hasMaintLoan: boolean
  #
  #       if (hasSoldHouse) {
  #         "What was the selling price?"
  #           sellingPrice: money
  #         "Private debts for the sold house:"
  #           privateDebt: money
  #         "Value residue:"
  #           valueResidue: money =
  #             (sellingPrice - privateDebt)
  #       }
  #     }')
  #   end
  # end
end
