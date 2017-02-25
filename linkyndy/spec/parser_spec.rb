require 'spec_helper'
require 'parser'
require 'byebug'

describe Parser do
  describe '#space' do
    it 'consumes a single space' do
      expect(subject.space).to parse(' ')
    end

    it 'consumes multiple spaces' do
      expect(subject.space).to parse('   ')
    end
  end

  describe '#space?' do
    it 'consumes zero spaces' do
      expect(subject.space?).to parse('')
    end

    it 'consumes a single space' do
      expect(subject.space?).to parse(' ')
    end

    it 'consumes multiple spaces' do
      expect(subject.space?).to parse('   ')
    end
  end

  describe '#lparen' do
    it 'consumes a left paranthesis' do
      expect(subject.lparen).to parse('(')
    end
  end

  describe '#rparen' do
    it 'consumes a right paranthesis' do
      expect(subject.rparen).to parse(')')
    end
  end

  describe '#quote' do
    it 'consumes a quote' do
      expect(subject.quote).to parse('"')
    end
  end

  describe '#qmark' do
    it 'consumes a question mark' do
      expect(subject.qmark).to parse('?')
    end
  end

  describe '#hashrocket' do
    it 'consumes a hashrocket' do
      expect(subject.hashrocket).to parse('=>')
    end
  end

  describe '#string' do
    it 'consumes an arbitrary string' do
      expect(subject.string).to parse('"aB3# ?"')
    end

    it 'consumes empty strings' do
      expect(subject.string).to parse('""')
    end

    it 'only consumes strings enclosed in quotes' do
      expect do
        subject.string.parse('abc')
      end.to raise_error(Parslet::ParseFailed)
    end
  end

  describe '#integer' do
    it 'consumes digits' do
      expect(subject.integer).to parse('123')
    end

    it 'consumes a single digit' do
      expect(subject.integer).to parse('1')
    end
  end

  describe '#boolean' do
    it 'consumes true' do
      expect(subject.boolean).to parse('true')
    end

    it 'consumes false' do
      expect(subject.boolean).to parse('false')
    end
  end

  describe '#literal' do
    it 'consumes a string' do
      expect(subject.literal).to parse('"foo"')
    end

    it 'consumes an integer' do
      expect(subject.literal).to parse('123')
    end

    it 'consumes a boolean' do
      expect(subject.literal).to parse('true')
    end
  end

  describe '#operator' do
    it 'consumes addition' do
      expect(subject.operator).to parse('+')
    end

    it 'consumes subtraction' do
      expect(subject.operator).to parse('-')
    end

    it 'consumes multiplication' do
      expect(subject.operator).to parse('*')
    end

    it 'consumes division' do
      expect(subject.operator).to parse('/')
    end
  end

  describe '#comment' do
    it 'consumes an empty comment' do
      expect(subject.comment).to parse('#')
    end

    it 'consumes a comment' do
      expect(subject.comment).to parse('# foo bar')
    end

    it 'consumes a comment with no whitespace' do
      expect(subject.comment).to parse('#foobar')
    end
  end

  describe '#type' do
    it 'consumes text' do
      expect(subject.type).to parse('text')
    end

    it 'consumes bool' do
      expect(subject.type).to parse('bool')
    end

    it 'consumes number' do
      expect(subject.type).to parse('number')
    end

    it 'consumes money' do
      expect(subject.type).to parse('money')
    end
  end

  describe '#identifier' do
    it 'must start with a letter' do
      expect(subject.identifier).to parse('foo')
    end

    it 'must not start with a digit' do
      expect do
        subject.identifier.parse('1foo')
      end.to raise_error(Parslet::ParseFailed)
    end

    it 'can contain letters and digits starting with the second character' do
      expect(subject.identifier).to parse('foO123')
    end
  end

  describe '#expression' do
    it 'treats whitespaces as optional' do
      expect(subject.expression).to parse('1 *2 ')
      expect(subject.expression).to parse('(1 *2 )')
    end

    it 'consumes an identifier' do
      expect(subject.expression).to parse('foo')
      expect(subject.expression).to parse('(foo)')
    end

    it 'consumes a literal' do
      expect(subject.expression).to parse('1')
      expect(subject.expression).to parse('(1)')
    end

    it 'consumes infix expressions' do
      expect(subject.expression).to parse('1 + 1')
      expect(subject.expression).to parse('(1 + 1)')
    end

    xit 'supports nested expressions' do
      expect(subject.expression).to parse('(1 + (2 * foo))')
      expect(subject.expression).to parse('((1 + 2) * foo)')
    end
  end

  describe '#block' do
    it 'consumes an if statement' do
      expect(subject.block).to parse('if foo "bar?" text baz end')
    end

    it 'consumes a question' do
      expect(subject.block).to parse('"foo bar?" text baz')
    end

    it 'may be empty' do
      expect(subject.block).to parse('')
    end
  end

  describe '#if_statement' do
    it 'consumes a well formed conditional' do
      expect(subject.if_statement).to parse('if foo "bar?" text baz end')
    end

    it 'consumes a well formed conditional with an alternative' do
      # byebug
      expect(subject.if_statement).to parse('if foo "bar?" text baz else "foobar?" text qux end')
    end
  end

  describe '#question' do
    it 'consumes a well formed question' do
      expect(subject.question).to parse('"foo bar?" text baz')
    end

    it 'consumes a well formed question with an expression' do
      expect(subject.question).to parse('"foo bar?" text baz => qux')
    end
  end

  describe '#form' do
    it 'consumes a well formed questionnaire' do
      expect(subject.form).to parse('form fooBar "foo?" text bar end')
    end
  end
end
