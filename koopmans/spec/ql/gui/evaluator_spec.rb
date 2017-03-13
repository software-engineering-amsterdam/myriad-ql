require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module GUI
    include Parser
    describe Evaluator do
      let(:evaluator) { Evaluator.new }
      let(:form_parser) { FormParser.new }
      let(:form_transformer) { FormTransformer.new }

      context 'general' do
        it 'evaluates' do
          expression_1 = form_transformer.apply(form_parser.expression.parse('5 + -10 > 0'))
          expect(expression_1.accept(evaluator).to_value).to eq(false)
          expression_2 = form_transformer.apply(form_parser.expression.parse('(5 + -10 > 0) == false'))
          expect(expression_2.accept(evaluator).to_value).to eq(true)
        end
      end

      context 'arithmetic' do
        it 'evaluates' do
          expression_1 = form_transformer.apply(form_parser.expression.parse('(5 + 10 * 5)'))
          expect(expression_1.accept(evaluator).to_value).to eq(55)
          expression_2 = form_transformer.apply(form_parser.expression.parse('(10 / 2 - 4 * 10)'))
          expect(expression_2.accept(evaluator).to_value).to eq(-35)
          expression_3 = form_transformer.apply(form_parser.expression.parse('(10 / (2 - 4) * 10)'))
          expect(expression_3.accept(evaluator).to_value).to eq(-50)
        end
      end

      context 'boolean' do
        it 'evaluates' do
          expression_1 = form_transformer.apply(form_parser.expression.parse('(true && false)'))
          expect(expression_1.accept(evaluator).to_value).to eq(false)
          expression_2 = form_transformer.apply(form_parser.expression.parse('false || false'))
          expect(expression_2.accept(evaluator).to_value).to eq(false)
          expression_3 = form_transformer.apply(form_parser.expression.parse('true && (false || true)'))
          expect(expression_3.accept(evaluator).to_value).to eq(true)
        end
      end

      context 'comparison' do
        it 'evaluates' do
          expression_1 = form_transformer.apply(form_parser.expression.parse('(5 < 10)'))
          expect(expression_1.accept(evaluator).to_value).to eq(true)
          expression_2 = form_transformer.apply(form_parser.expression.parse('(8 != 10)'))
          expect(expression_2.accept(evaluator).to_value).to eq(true)
          expression_3 = form_transformer.apply(form_parser.expression.parse('(8 == 10)'))
          expect(expression_3.accept(evaluator).to_value).to eq(false)
        end
      end

      context 'negations' do
        it 'evaluates' do
          expression_1 = form_transformer.apply(form_parser.expression.parse('(!false)'))
          expect(expression_1.accept(evaluator).to_value).to eq(true)
          expression_2 = form_transformer.apply(form_parser.expression.parse('(-5)'))
          expect(expression_2.accept(evaluator).to_value).to eq(-5)
        end
      end
    end
  end
end