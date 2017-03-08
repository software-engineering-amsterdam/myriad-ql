require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QL
  module Parser
    include AST
    describe FormTransformer do
      let(:form_transformer) { FormTransformer.new }

      describe 'literals' do
        context 'boolean' do
          it 'transforms' do
            expect(form_transformer.apply(boolean_literal: true)).to be_a BooleanLiteral
          end
          # it 'transforms negation' do
          #   expect(form_transformer.apply(boolean_negation: '!', boolean: true)).to be_a BooleanNegation
          # end
        end

        context 'integer' do
          it 'transforms' do
            expect(form_transformer.apply(integer_literal: 42)).to be_a IntegerLiteral
          end
          # it 'transforms negation' do
          #   expect(form_transformer.apply(integer_negation: '-', integer: 42)).to be_a IntegerNegation
          # end
        end

        context 'strings' do
          it 'transforms' do
            expect(form_transformer.apply(string_literal: '"How much is?"')).to be_a StringLiteral
          end
        end
      end

      describe 'variables' do
        it 'transforms' do
          expect(form_transformer.apply(variable: 'sellingPrice')).to be_a Variable
        end
        #
        # it 'transforms negation' do
        #   expect(form_transformer.apply(negation: '!', variable: 'sellingPrice')).to be_a BooleanNegation
        #   expect(form_transformer.apply(negation: '-', variable: 'sellingPrice')).to be_a IntegerNegation
        # end
      end

      # describe 'operators' do
      #   it 'transforms arithmetic' do
      #     expect(form_transformer.apply({ left: {}, operator: '/', right: {} })).to be_a Divide
      #   end
      #   it 'transforms boolean' do
      #     expect(form_transformer.apply({ left: {}, operator: '&&', right: {} })).to be_a And
      #   end
      #   it 'transforms comparison' do
      #     expect(form_transformer.apply({ left: {}, operator: '<', right: {} })).to be_a Less
      #   end
      # end

      describe 'statements' do
        context 'questions' do
          it 'transforms' do
            expect(form_transformer.apply(question: { label: '"How much is?"', id: 'hasSoldHouse', type: 'boolean' })).to be_a Question
            expect(form_transformer.apply(question: { label: '"Value residue:"', id: 'valueResidue', type: 'money', assignment: '_' })).to be_a Question
          end
        end

        context 'if statement' do
          it 'transforms' do
            expect(form_transformer.apply(if_statement: { condition: '_', body: {} })).to be_a IfStatement
          end
        end
      end

      describe 'form' do
        it 'transforms' do
          expect(form_transformer.apply(form: { id: 'taxOfficeExample', body: {} })).to be_a Form
        end
      end
    end
  end
end