require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QLS
  module Parser
    include AST
    describe Transformer do
      let(:transformer) { Transformer.new }

      describe 'widgets' do
        context 'spinbox' do
          it 'transforms' do
            expect(transformer.apply(widget: 'spinbox')).to be_a SpinboxWidget
          end
        end

        context 'radio' do
          it 'transforms' do
            expect(transformer.apply(widget: { radio: { first_value: { string: 'Yes' }, second_value: { string: 'No' } } })).to be_a RadioWidget
          end
        end
      end

      describe 'properties' do
        context 'width' do
          it 'transforms' do
            expect(transformer.apply(width: { integer: '400' })).to be_a Width
          end
        end
      end

      describe 'default properties' do
        context 'boolean default' do
          it 'transforms' do
            expect(transformer.apply(default: { type: 'boolean', properties: [{ width: '400' }] })).to be_a Default
          end
        end
      end

      describe 'question' do
        it 'transforms' do
          expect(transformer.apply(question: { variable: 'hasSoldHouse', properties: [{ width: '400' }] })).to be_a Question
        end
      end

      describe 'section' do
        it 'transforms' do
          expect(transformer.apply(section: { string: 'Section name', block: {} })).to be_a Section
        end
      end

      describe 'page' do
        it 'transforms' do
          expect(transformer.apply(page: { variable: 'Section name', block: {} })).to be_a Page
        end
      end

      describe 'stylesheet' do
        it 'transforms' do
          expect(transformer.apply(stylesheet: { variable: 'Section name', pages: {} })).to be_a StyleSheet
        end
      end
    end
  end
end