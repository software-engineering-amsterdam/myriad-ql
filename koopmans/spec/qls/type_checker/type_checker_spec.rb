require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QLS
  module TypeChecker
    include QL::AST
    include QLS::AST

    describe TypeChecker do
      describe QuestionChecker do
        it 'detects error' do
          messages = stylesheet_ast.accept(QuestionChecker.new, form_ast).map(&:message)
          expect(messages).to include('undefined of the QL program is not placed by the QLS program.')
          expect(messages).to include('valueResidue is referenced to a question that is not in the QL program')
          expect(messages).to include('valueResidue is placed multiple times')
        end
      end

      describe WidgetTypeChecker do
        it 'detects error' do
          messages = stylesheet_ast.accept(WidgetTypeChecker.new, form_ast).map(&:message)
          expect(messages).to include('QLS::AST::SliderWidget can not be used with QL::AST::BooleanType')
          expect(messages).to include('QLS::AST::CheckboxWidget can not be used with undefined')
        end
      end

      # example ast for stylesheet
      def stylesheet_ast
        question  = Question.new(Variable.new('hasSoldHouse'), [widget: SliderWidget.new(0, 10)])
        question2 = Question.new(Variable.new('valueResidue'), [widget: CheckboxWidget.new])
        page = Page.new('_', [Section.new('_', [question, question2, question2])])

        Stylesheet.new('_', [page])
      end

      # example ast for form
      def form_ast
        question  = QL::AST::Question.new('Did you sell a house in 2010?', Variable.new('hasSoldHouse'), BooleanType)
        question2 = QL::AST::Question.new('_', Variable.new('undefined'), BooleanType)

        Form.new('_', [question, question2])
      end
    end
  end
end
