require 'rspec'
require 'parslet/rig/rspec'
require 'parser'
require 'pp'

describe Parser do
  let(:parser) { Parser.new }

  context 'label' do
    label = '"How much is?"'

    it 'parses' do
      expect(parser.label).to parse(label)
      expect(parser.label.parse(label)).to include(:label)
    end
  end

  context 'variable' do
    variable_assignment = 'hasSoldHouse:'
    it 'parses' do
      expect(parser.variable_assignment).to parse(variable_assignment)
      expect(parser.variable_assignment.parse(variable_assignment)).to include(:variable)
    end
  end

  context 'type' do
    type = 'boolean'

    it 'parses' do
      expect(parser.type).to parse(type)
      expect(parser.type.parse(type)).to include(:type)
    end
  end

  context 'variable2' do
    variable2 = 'sellingPrice'
    it 'parses' do
      expect(parser.variable).to parse(variable2)
      expect(parser.variable.parse(variable2)).to include(:variable)
    end
  end

  context 'arithmetic' do
    arithmetic = '+'
    it 'parses' do
      expect(parser.arithmetic).to parse(arithmetic)
      expect(parser.arithmetic.parse(arithmetic)).to include(:arithmetic)
    end
  end

  context 'expression' do
    expression = '(sellingPrice - privateDebt + anotherVariable)'
    it 'parses' do
      expect(parser.expression).to parse(expression)
      expect(parser.expression.parse(expression)).to include(:expression)
      expect(parser.expression.parse(expression)[:expression]).to all include(:variable)
      expect(parser.expression.parse(expression)[:expression][1]).to include(:arithmetic)
      expect(parser.expression.parse(expression)[:expression][2]).to include(:arithmetic)
      expect(parser.expression.parse(expression)[:expression].length).to be(3)
    end
  end

  context 'question' do
    question = '"How much is?" hasSoldHouse: boolean'

    it 'parses' do
      expect(parser.question).to parse(question)
      expect(parser.question.parse(question)).to include(:question)
    end
  end

  context 'question with expression' do
    question = '"Value residue:" valueResidue: money = (sellingPrice - privateDebt)'
    it 'parses' do
      expect(parser.question).to parse(question)
      expect(parser.question.parse(question)[:question]).to include(:expression)
    end
  end

  context 'two questions' do
    two_questions = '"Did you sell a house in 2010?"
        hasSoldHouse: boolean
      "Did you buy a house in 2010?"
        hasBoughtHouse: boolean'

    it 'parses' do
      expect(parser.questions).to parse(two_questions)
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

    it 'parses' do
      expect(parser.questions).to parse(three_questions)
      expect(parser.questions.parse(three_questions)).to include(:questions)
    end
  end

  context 'condition' do
    condition = '(hasSoldHouse)'

    it 'parses' do
      expect(parser.condition.parse(condition)).to eq({condition: 'hasSoldHouse'})
    end
    it 'parses into property' do
      expect(parser.condition.parse(condition)).to include(:condition)
    end
  end

  context 'block' do
    block = '{
        "What was the selling price?"
          sellingPrice: money
      }'

    it 'parses' do
      expect(parser.block).to parse(block)
      expect(parser.block.parse(block)).to include(:block)
    end
  end

  context 'if statement with 1 question' do
    if_statement = 'if (hasSoldHouse) {
        "What was the selling price?"
          sellingPrice: money
      }'

    it 'parses' do
      expect(parser.if_statement).to parse(if_statement)
      expect(parser.if_statement.parse(if_statement)).to include(:if_statement)
    end
  end

  context 'form' do
    simpler_form = 'form taxOfficeExample {
        "Did you sell a house in 2010?"
          hasSoldHouse: boolean
        "Did you buy a house in 2010?"
          hasBoughtHouse: boolean
        "Did you enter a loan?"
          hasMaintLoan: boolean
      }'

    form = 'form taxOfficeExample {
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
      }'


    it 'parses' do
      pp parser.form.parse(simpler_form)
      expect(parser.form).to parse(simpler_form)
      pp parser.form.parse(form)
      expect(parser.form).to parse(form)
      # expect(parser.form.parse(if_statement)).to include(:if_statement)
    end
  end
end
