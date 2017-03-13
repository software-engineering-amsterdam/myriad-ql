require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module Parser
    include AST
    describe ExpressionTransformer do
      let(:form_parser) { FormParser.new }
      let(:form_transformer) { FormTransformer.new }

      describe 'expressions' do
        context 'general' do
          it 'parses' do
            expect(form_transformer.apply(form_parser.expression.parse('5 + 10')).expression).to include(Add)
            expect(form_transformer.apply(form_parser.expression.parse('true == false')).expression).to include(Equal)
          end
        end
        #
        # context 'arithmetic' do
        #   it 'parses' do
        #     expect(form_transformer.apply(form_parser.expression.parse('(5 + 10 * 5)')).eval.value).to eq('55')
        #     expect(form_transformer.apply(form_parser.expression.parse('(10 / 2 - 4 * 10)')).eval.value).to eq('-35')
        #     expect(form_transformer.apply(form_parser.expression.parse('(10 / (2 - 4) * 10)')).eval.value).to eq('-50')
        #   end
        # end
        #
        # context 'boolean' do
        #   it 'parses' do
        #     expect(form_transformer.apply(form_parser.expression.parse('(true && false)')).eval.value).to eq('false')
        #     expect(form_transformer.apply(form_parser.expression.parse('false || false')).eval.value).to eq('false')
        #     expect(form_transformer.apply(form_parser.expression.parse('true && (false || true)')).eval.value).to eq('true')
        #   end
        # end
        #
        # context 'comparison' do
        #   it 'parses' do
        #     expect(form_transformer.apply(form_parser.expression.parse('(5 < 10)')).eval.value).to eq('true')
        #     expect(form_transformer.apply(form_parser.expression.parse('(8 != 10)')).eval.value).to eq('true')
        #     expect(form_transformer.apply(form_parser.expression.parse('(8 == 10)')).eval.value).to eq('false')
        #   end
        # end
        #
        # context 'negations' do
        #   it 'parses' do
        #     expect(form_transformer.apply(form_parser.expression.parse('(!true)')).eval.value).to eq('false')
        #     expect(form_transformer.apply(form_parser.expression.parse('(-5)')).eval.value).to eq('-5')
        #   end
        # end
      end
    end
  end
end