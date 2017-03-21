require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QLS
  module Parser
    include AST
    describe FormTransformer do
      let(:transformer) { FormTransformer.new }

      describe 'widgets' do
        context 'spinbox' do
          it 'transforms' do
            expect(transformer.apply(widget: 'spinbox')).to be_a SpinboxWidget
          end
        end

        context 'radio' do
          it 'transforms' do
            expect(transformer.apply(widget: { radio: { first_value: { string_literal: 'Yes' }, second_value: { string_literal: 'No' } } })).to be_a RadioWidget
          end
        end
      end

      describe 'literal' do
        it 'transforms integer literal' do
          expect(transformer.apply(integer_literal: '400')).to be_a QL::AST::IntegerLiteral
        end

        it 'transforms string literal' do
          expect(transformer.apply(string_literal: 'Arial')).to be_a QL::AST::StringLiteral
        end
      end

      describe 'type' do
        it 'transforms boolean type' do
          expect(transformer.apply(type: 'boolean')).to be_a QL::AST::BooleanType
        end

        it 'transforms boolean type' do
          expect(transformer.apply(type: 'money')).to be_a QL::AST::MoneyType
        end
      end

      describe 'variable' do
        it 'transforms variable' do
          expect(transformer.apply(variable: 'hasSoldHouse')).to be_a QL::AST::Variable
        end
      end

      describe 'property' do
        it 'transforms width' do
          expect(transformer.apply(width: { integer_literal: '400' })).to be_a Width
        end

        it 'transforms font' do
          expect(transformer.apply(font: { string_literal: 'Arial' })).to be_a Font
        end

        it 'transforms fontsize' do
          expect(transformer.apply(fontsize: { integer_literal: '14' })).to be_a Fontsize
        end

        it 'transforms color' do
          expect(transformer.apply(color: { string_literal: '#999999' })).to be_a Color
          expect(transformer.apply(color: { string_literal: '#999' })).to be_a Color
        end
      end

      describe 'default properties' do
        context 'boolean default' do
          it 'transforms' do
            expect(transformer.apply(default_properties: { type: 'boolean', widgets: [{ width: '400' }] })).to be_a DefaultProperties
          end
        end
      end

      describe 'question' do
        it 'transforms' do
          expect(transformer.apply(question: { id: {variable: 'hasSoldHouse'}, widgets: [{ width: '400' }] })).to be_a Question
        end
      end

      describe 'section' do
        it 'transforms' do
          expect(transformer.apply(section: { string_literal: 'Section name', body: {} })).to be_a Section
        end
      end

      describe 'page' do
        it 'transforms' do
          expect(transformer.apply(page: { id: {variable: 'Section name'}, body: {} })).to be_a Page
        end
      end

      describe 'stylesheet' do
        it 'transforms' do
          expect(transformer.apply(stylesheet: { id: {variable: 'Section name'}, pages: {} })).to be_a Stylesheet
        end
      end
    end
  end
end
