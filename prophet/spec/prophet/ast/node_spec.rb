require 'spec_helper'
require 'byebug'

describe Prophet::Ast::Node do
  # Define arbitrary nodes for testing purposes
  let(:foo_class) { Class.new(Prophet::Ast::Node.new(:bar, :baz)) }
  let(:bar_class) { Class.new(Prophet::Ast::Node.new(:qux)) }
  let(:baz_class) { Class.new(Prophet::Ast::Node.new(:qux)) }

  before do
    stub_const('Foo', foo_class)
    stub_const('Bar', bar_class)
    stub_const('Baz', baz_class)
  end

  let(:bar) { Bar.new(:qux) }
  let(:baz) { Baz.new(:qux) }

  subject do
    Foo.new(bar, [baz])
  end

  describe '#visit' do
    it 'calls the correct method on the given visitor' do
      visitor = spy
      subject.visit(visitor)
      expect(visitor).to have_received(:visit_foo).with(subject)
    end
  end

  describe '#children' do
    it 'returns a list of (nested) children' do
      expect(subject.children).to eq([bar, baz])
    end
  end
end
