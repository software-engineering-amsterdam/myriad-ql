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
        end

        context 'integer' do
          it 'transforms' do
            expect(form_transformer.apply(integer_literal: 42)).to be_a IntegerLiteral
          end
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
      end

      describe 'statements' do
        context 'question' do
          it 'transforms' do
            expect(form_transformer.apply(question: { label: '"How much is?"', id: 'hasSoldHouse', type: 'boolean' })).to be_a Question
          end
        end

        context 'computed question' do
          it 'transforms' do
            expect(form_transformer.apply(question: { label: '"Value residue:"', id: 'valueResidue', type: 'money', assignment: '_' })).to be_a ComputedQuestion
          end
        end

        context 'if statement' do
          it 'transforms' do
            expect(form_transformer.apply(if_statement: { condition: '_', body: {} })).to be_a IfStatement
          end
        end

        context 'if else statement' do
          it 'transforms' do
            expect(form_transformer.apply(if_else_statement: { if_statement: { condition: '_', body: {} }, body: {} })).to be_a IfElseStatement
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
