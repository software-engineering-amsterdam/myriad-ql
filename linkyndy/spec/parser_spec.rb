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

  describe 'space?' do
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
end
