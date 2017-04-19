require 'spec_helper'
require 'byebug'

describe Prophet::Transform do
  it 'transforms text to the correct AST node' do
    result = subject.apply(text: 'foobar')
    expect(result).to be_a(Prophet::Ast::TextLiteral)
    expect(result.value).to eq('foobar')
  end

  it 'transforms number to the correct AST node' do
    result = subject.apply(number: '123')
    expect(result).to be_a(Prophet::Ast::NumberLiteral)
    expect(result.value).to eq('123')
  end

  it 'transforms bool to the correct AST node' do
    result = subject.apply(bool: 'true')
    expect(result).to be_a(Prophet::Ast::BoolLiteral)
    expect(result.value).to eq('true')
  end

  it 'transforms text type to the correct AST node' do
    result = subject.apply(type: 'text')
    expect(result).to be_a(Prophet::Ast::TextType)
  end

  it 'transforms number type to the correct AST node' do
    result = subject.apply(type: 'number')
    expect(result).to be_a(Prophet::Ast::NumberType)
  end

  it 'transforms bool type to the correct AST node' do
    result = subject.apply(type: 'bool')
    expect(result).to be_a(Prophet::Ast::BoolType)
  end

  it 'transforms an identifier to the correct AST node' do
    result = subject.apply(identifier: 'foo')
    expect(result).to be_a(Prophet::Ast::Identifier)
    expect(result.name).to eq('foo')
  end

  it 'transforms && to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '&&', right: 'bar')
    expect(result).to be_a(Prophet::Ast::LogicalAnd)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms || to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '||', right: 'bar')
    expect(result).to be_a(Prophet::Ast::LogicalOr)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms == to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '==', right: 'bar')
    expect(result).to be_a(Prophet::Ast::Equal)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms != to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '!=', right: 'bar')
    expect(result).to be_a(Prophet::Ast::NotEqual)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms <= to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '<=', right: 'bar')
    expect(result).to be_a(Prophet::Ast::LessThenOrEqual)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms < to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '<', right: 'bar')
    expect(result).to be_a(Prophet::Ast::LessThen)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms > to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '>', right: 'bar')
    expect(result).to be_a(Prophet::Ast::GreaterThen)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms >= to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '>=', right: 'bar')
    expect(result).to be_a(Prophet::Ast::GreaterThenOrEqual)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms + to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '+', right: 'bar')
    expect(result).to be_a(Prophet::Ast::Addition)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms - to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '-', right: 'bar')
    expect(result).to be_a(Prophet::Ast::Subtraction)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms * to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '*', right: 'bar')
    expect(result).to be_a(Prophet::Ast::Multiplication)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms / to the correct AST node' do
    result = subject.apply(left: 'foo', operator: '/', right: 'bar')
    expect(result).to be_a(Prophet::Ast::Division)
    expect(result.left).to eq('foo')
    expect(result.right).to eq('bar')
  end

  it 'transforms ! to the correct AST node' do
    result = subject.apply(operator: '!', value: 'foo')
    expect(result).to be_a(Prophet::Ast::Negation)
    expect(result.value).to eq('foo')
  end

  it 'transforms a question to the correct AST node' do
    result = subject.apply(question: { text: 'foo', type: 'bar', identifier: 'baz' })
    expect(result).to be_a(Prophet::Ast::Question)
    expect(result.text).to eq('foo')
    expect(result.type).to eq('bar')
    expect(result.identifier).to eq('baz')
  end

  it 'transforms a question with value to the correct AST node' do
    result = subject.apply(question: { text: 'foo', type: 'bar', identifier: 'baz', value: 'qux' })
    expect(result).to be_a(Prophet::Ast::QuestionWithValue)
    expect(result.text).to eq('foo')
    expect(result.type).to eq('bar')
    expect(result.identifier).to eq('baz')
    expect(result.value).to eq('qux')
  end

  it 'transforms an if statement to the correct AST node' do
    result = subject.apply(if_statement: { condition: 'foo', true_branch: 'bar' })
    expect(result).to be_a(Prophet::Ast::IfStatement)
    expect(result.condition).to eq('foo')
    expect(result.true_branch).to eq('bar')
  end

  it 'transforms an if statement with alternative to the correct AST node' do
    result = subject.apply(if_statement: { condition: 'foo', true_branch: 'bar', false_branch: 'baz' })
    expect(result).to be_a(Prophet::Ast::IfElseStatement)
    expect(result.condition).to eq('foo')
    expect(result.true_branch).to eq('bar')
    expect(result.false_branch).to eq('baz')
  end

  it 'transforms a form to the correct AST node' do
    result = subject.apply(form: { identifier: 'foo', body: 'bar' })
    expect(result).to be_a(Prophet::Ast::Form)
    expect(result.identifier).to eq('foo')
    expect(result.body).to eq('bar')
  end
end
