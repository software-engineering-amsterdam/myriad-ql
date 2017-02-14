require 'rspec'
require 'parslet/rig/rspec'
require 'parser'
require 'pp'

describe Parser do
  let(:parser) { Parser.new }

  context 'label' do
    it 'parses' do
      expect(parser.label).to parse('"How much is?"')
    end
  end

  context 'variable assignment' do
    it 'parses' do
      expect(parser.variable_assignment).to parse('hasSoldHouse:')
    end
  end

  # context 'integer' do
  #   it 'parses' do
  #     expect(parser.integer).to parse('1')
  #     expect(parser.integer).to_not parse('a')
  #   end
  # end

  context 'type' do
    it 'parses' do
      expect(parser.type).to parse('boolean')
    end
  end

  context 'variable' do
    it 'parses' do
      expect(parser.variable).to parse('sellingPrice')
    end
  end

  context 'arithmetic' do
    it 'parses' do
      expect(parser.arithmetic).to parse('+')
    end
  end

  context 'arithmetic expression' do
    it 'parses' do
      expect(parser.expression).to parse('(sellingPrice - privateDebt + anotherVariable)')
    end
  end

  context 'boolean' do
    it 'parses' do
      expect(parser.boolean).to parse('&&')
    end
  end

  context 'boolean expression' do
    it 'parses' do
      expect(parser.expression).to parse('(true && false)')
    end
  end

  context 'comparison' do
    it 'parses' do
      expect(parser.comparison).to parse('<')
    end
  end

  context 'comparison expression' do
    it 'parses' do
      expect(parser.expression).to parse('(5 < 10)')
    end
  end


  context 'question' do
    it 'parses' do
      expect(parser.question).to parse('"How much is?" hasSoldHouse: boolean')
      expect(parser.question).to parse('"Value residue:" valueResidue: money = (sellingPrice - privateDebt)')
    end
  end

  context 'block' do
    it 'parses' do
      expect(parser.block).to parse('{
          "What was the selling price?"
            sellingPrice: money
        }')
    end
  end

  context 'if statement with one question' do
    it 'parses' do
      expect(parser.if_statement).to parse('if (hasSoldHouse) {
          "What was the selling price?"
            sellingPrice: money
        }')
    end
  end

  context 'form' do
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
