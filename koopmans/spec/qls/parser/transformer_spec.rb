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
            expect(transformer.apply(widget: 'spinbox')).to be_a(SpinboxWidget)
          end
        end
        context 'radio' do
          it 'transforms' do
            expect(transformer.apply(widget: {radio: {first_value: {string: 'Yes'}, second_value: {string: 'No'}}})).to be_a(RadioWidget)
          end
        end
      end
      describe 'properties' do
        context 'width' do
          it 'transforms' do
            expect
            rule(width: {integer: simple(:value)}) { Width.new(value) }
          end
        end
      end
    end
  end
end