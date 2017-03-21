require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QLS
  module TypeChecker
    include QL::AST
    include QLS::AST

    describe TypeChecker do
      let(:notification_messages) do
        TypeChecker.new.check(generate_stylesheet, generate_form)
        NotificationTable.index.map(&:message)
      end

      describe 'questions placed checker' do
        it 'does detect unplaced questions' do
          expect(notification_messages).to include('undefined of the QL program is not placed by the QLS program.')
        end
      end

      describe 'questions reference checker' do
        it 'does detect unreferenced questions' do
          expect(notification_messages).to include('valueResidue is referenced to a question that is not in the QL program')
        end
      end

      describe 'questions uniqueness checker' do
        it 'does detect unreferenced questions' do
          expect(notification_messages).to include('valueResidue is placed multiple times')
        end
      end

      describe 'widget type checker' do
        it 'does detect incompatible types' do
          expect(notification_messages.to_s).to include('incompatible types at ')
        end
      end

      # example stylesheet with errors
      def generate_stylesheet
        question = Question.new(Variable.new('hasSoldHouse'), [SliderWidget.new(0, 10)])
        question_2 = Question.new(Variable.new('valueResidue'), [CheckboxWidget.new])
        section = Section.new('_', [question, question_2, question_2])
        page = Page.new('_', [section])

        Stylesheet.new('_', [page])
      end

      # example form with errors
      def generate_form
        question = QL::AST::Question.new(StringLiteral.new('Did you sell a house in 2010?'), Variable.new('hasSoldHouse'), BooleanType.new)
        question_2 = QL::AST::Question.new(StringLiteral.new('_'), Variable.new('undefined'), BooleanType.new)

        Form.new('_', [question, question_2])
      end
    end
  end
end
