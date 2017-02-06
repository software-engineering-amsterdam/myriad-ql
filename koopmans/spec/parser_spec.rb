require 'rspec'
require 'parslet/rig/rspec'
require 'parser'

describe Parser do
  let(:parser) { Parser.new }

  context 'label' do
    it 'should parse' do
      expect(parser.label).to parse('"How much is?"')
    end
    it 'should parse into property' do
      expect(parser.label.parse('"How much is?"')).to eq({label: 'How much is?'})
    end
  end

  context 'variable' do
    it 'should parse' do
      expect(parser.variable).to parse('hasSoldHouse:')
    end
    it 'should parse into property' do
      expect(parser.variable.parse('hasSoldHouse:')).to eq({variable: 'hasSoldHouse'})
    end
  end

  context 'type' do
    it 'should parse' do
      expect(parser.type).to parse('boolean')
    end
    it 'should parse into property' do
      expect(parser.type.parse('boolean')).to eq({type: 'boolean'})
    end
  end

  context 'question' do
    it 'should parse' do
      expect(parser.question).to parse('"How much is?" hasSoldHouse: boolean')
    end
    it 'should parse into property' do
      expect(parser.question.parse('"How much is?" hasSoldHouse: boolean')).to eq({question: {label: 'How much is?', variable: 'hasSoldHouse', type: 'boolean'}})
    end
  end

  context 'two questions' do
    it 'should parse' do
      expect(parser.questions).to parse('
        "Did you sell a house in 2010?"
          hasSoldHouse: boolean
        "Did you buy a house in 2010?"
          hasBoughtHouse: boolean
      ')
    end
  end

  context 'three questions' do
    it 'should parse' do
      expect(parser.questions).to parse('
        "Did you sell a house in 2010?"
          hasSoldHouse: boolean
        "Did you buy a house in 2010?"
          hasBoughtHouse: boolean
        "Did you enter a loan?"
          hasMaintLoan: boolean
      ')
    end
  end

  context 'condition' do
    it 'should parse' do
      expect(parser.condition).to parse('(hasSoldHouse)')
    end
  end

  context 'if block' do
    it 'should parse' do
      expect(parser.if_block).to parse('
        if (hasSoldHouse) {
          "What was the selling price?"
          sellingPrice: money
        }
      ')
    end
  end
end
