require 'spec_helper'
require 'byebug'

describe Prophet::Parser do
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

  describe '#comment' do
    it 'consumes an empty comment' do
      expect(subject.comment).to parse("#\n")
    end

    it 'consumes a comment' do
      expect(subject.comment).to parse("# foo bar \n")
    end

    it 'consumes a comment with no whitespace' do
      expect(subject.comment).to parse("#foobar\n")
    end

    it 'only consumes strings ending with a new line' do
      expect do
        subject.comment.parse('# Just a comment')
      end.to raise_error(Parslet::ParseFailed)
    end
  end

  describe '#text' do
    it 'consumes an arbitrary text' do
      expect(subject.text).to parse('"aB3# ?"')
    end

    it 'consumes empty text' do
      expect(subject.text).to parse('""')
    end

    it 'only consumes text enclosed in quotes' do
      expect do
        subject.text.parse('abc')
      end.to raise_error(Parslet::ParseFailed)
    end
  end

  describe '#number' do
    it 'consumes digits' do
      expect(subject.number).to parse('123')
    end

    it 'consumes a single digit' do
      expect(subject.number).to parse('1')
    end
  end

  describe '#bool' do
    it 'consumes true' do
      expect(subject.bool).to parse('true')
    end

    it 'consumes false' do
      expect(subject.bool).to parse('false')
    end
  end

  describe '#literal' do
    it 'consumes a text' do
      expect(subject.literal).to parse('"foo"')
    end

    it 'consumes a number' do
      expect(subject.literal).to parse('123')
    end

    it 'consumes a bool' do
      expect(subject.literal).to parse('true')
    end
  end

  describe '#type' do
    it 'consumes text' do
      expect(subject.type).to parse('text')
    end

    it 'consumes number' do
      expect(subject.type).to parse('number')
    end

    it 'consumes bool' do
      expect(subject.type).to parse('bool')
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

  describe '#logical_and' do
    it 'consumes a &&' do
      expect(subject.logical_and).to parse('&&')
    end
  end

  describe '#logical_or' do
    it 'consumes a ||' do
      expect(subject.logical_or).to parse('||')
    end
  end

  describe '#equal' do
    it 'consumes a ==' do
      expect(subject.equal).to parse('==')
    end
  end

  describe '#not_equal' do
    it 'consumes a !=' do
      expect(subject.not_equal).to parse('!=')
    end
  end

  describe '#less_then_or_equal' do
    it 'consumes a <=' do
      expect(subject.less_then_or_equal).to parse('<=')
    end
  end

  describe '#less_then' do
    it 'consumes a <' do
      expect(subject.less_then).to parse('<')
    end
  end

  describe '#greater_then' do
    it 'consumes a >' do
      expect(subject.greater_then).to parse('>')
    end
  end

  describe '#greater_then_or_equal' do
    it 'consumes a >=' do
      expect(subject.greater_then_or_equal).to parse('>=')
    end
  end

  describe '#plus' do
    it 'consumes a +' do
      expect(subject.plus).to parse('+')
    end
  end

  describe '#minus' do
    it 'consumes a -' do
      expect(subject.minus).to parse('-')
    end
  end

  describe '#multiply' do
    it 'consumes a *' do
      expect(subject.multiply).to parse('*')
    end
  end

  describe '#divide' do
    it 'consumes a /' do
      expect(subject.divide).to parse('/')
    end
  end

  describe '#negation' do
    it 'consumes a !' do
      expect(subject.negation).to parse('!')
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

    it 'supports nested expressions' do
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

    it 'consumes a comment' do
      expect(subject.block).to parse("# foo bar \n")
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
      expect(subject.if_statement).to parse('if foo "bar?" text baz else "foobar?" text qux end')
    end
  end

  describe '#question' do
    it 'consumes a well formed question' do
      expect(subject.question).to parse('"foo bar?" text baz')
    end

    it 'consumes a well formed question with a value' do
      expect(subject.question).to parse('"foo bar?" text baz => qux')
    end
  end

  describe '#form' do
    it 'consumes a well formed form' do
      expect(subject.form).to parse('form fooBar "foo?" text bar end')
    end
  end
end
