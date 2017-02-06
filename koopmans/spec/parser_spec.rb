require 'rspec'
require 'parslet/rig/rspec'
require 'parser'

describe Parser do
  let(:parser) { Parser.new }

  context 'label' do
    label = '"How much is?"'
    it 'should parse' do
      expect(parser.label).to parse(label)
    end
    it 'should parse into property' do
      expect(parser.label.parse(label)).to include(:label)
    end
  end

  context 'variable' do
    variable = 'hasSoldHouse:'
    it 'should parse' do
      expect(parser.variable).to parse(variable)
    end
    it 'should parse into property' do
      expect(parser.variable.parse(variable)).to include(:variable)
    end
  end

  context 'type' do
    type = 'boolean'
    it 'should parse' do
      expect(parser.type).to parse(type)
    end
    it 'should parse into property' do
      expect(parser.type.parse(type)).to include(:type)
    end
  end

  context 'question' do
    question = '"How much is?" hasSoldHouse: boolean'
    it 'should parse' do
      expect(parser.question).to parse(question)
    end
    it 'should parse into properties' do
      expect(parser.question.parse(question)).to include(:question)
    end
  end

  context 'two questions' do
    two_questions = '"Did you sell a house in 2010?"
                        hasSoldHouse: boolean
                     "Did you buy a house in 2010?"
                        hasBoughtHouse: boolean'
    it 'should parse' do
      expect(parser.questions).to parse(two_questions)
    end
    it 'should parse into properties' do
      expect(parser.questions.parse(two_questions)).to include(:questions)
    end
  end

  context 'three questions' do
    three_questions = '"Did you sell a house in 2010?"
                          hasSoldHouse: boolean
                       "Did you buy a house in 2010?"
                          hasBoughtHouse: boolean
                       "Did you enter a loan?"
                          hasMaintLoan: boolean'
    it 'should parse' do
      expect(parser.questions).to parse(three_questions)
    end
    it 'should parse into properties' do
      expect(parser.questions.parse(three_questions)).to include(:questions)
    end
  end

  context 'condition' do
    it 'should parse' do
      expect(parser.condition.parse('(hasSoldHouse)')).to eq({condition: 'hasSoldHouse'})
    end
  end
  #
  # context 'block' do
  #   it 'should parse' do
  #     expect(parser.block.parse('
  #       {
  #       "What was the selling price?"
  #         sellingPrice: money
  #       }
  #     ')).to eq({block: [{question: {label: 'What was the selling price?', variable: 'sellingPrice', type: 'money'}}]})
  #   end
  # end

  context 'if block with 1 question' do
    it 'should parse' do
      expect(parser.if_block.parse('
        if (hasSoldHouse) {
          "What was the selling price?"
          sellingPrice: money
        }
      ')).to eq({condition: 'hasSoldHouse', block: [{question: {label: 'What was the selling price?', variable: 'sellingPrice', type: 'money'}}]})
    end
  end

  context 'if block with 2 question' do
    it 'should parse' do
      expect(parser.if_block.parse('
        if (hasSoldHouse) {
          "What was the selling price?"
            sellingPrice: money
          "Private debts for the sold house:"
            privateDebt: money
        }
      ')).to eq({condition: 'hasSoldHouse', block: [{question: {label: 'What was the selling price?', variable: 'sellingPrice', type: 'money'}}, question: {label: 'Private debts for the sold house:', variable: 'privateDebt', type: 'money'}]})
    end
  end
end
