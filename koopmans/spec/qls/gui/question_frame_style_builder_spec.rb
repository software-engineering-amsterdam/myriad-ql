require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QLS
  module GUI
    include QL::AST
    include AST

    describe QuestionFrameStyleBuilder do
      let(:question_frame_styles) { generate_question_frame_styles }

      it 'inherits default properties from page' do
        expect(question_frame_styles['hasSoldHouse'].font).to eq('Arial')
        expect(question_frame_styles['hasBoughtHouse'].font).to eq('Arial')
      end

      it 'inherits from default properties of section' do
        expect(question_frame_styles['hasBoughtHouse'].width).to be(200)
      end

      it 'overwrites default properties of section by question style' do
        expect(question_frame_styles['hasSoldHouse'].width).to be(100)
      end

      def generate_question_frame_styles
        style_collector = StyleCollector.new
        ast = generate_stylesheet
        ast.accept(style_collector)

        question_frame_styler = GUI::QuestionFrameStyleBuilder.new(style_collector.styles)
        ast.accept(question_frame_styler)
        question_frame_styler.question_frame_styles
      end

      def generate_stylesheet
        question = Question.new(Variable.new('hasSoldHouse'), [Width.new(IntegerLiteral.new(100))])
        question_without_style = Question.new(Variable.new('hasBoughtHouse'), [])

        default_properties = DefaultProperties.new(BooleanType.new, [Width.new(IntegerLiteral.new(200))])
        section = Section.new('_', [question, question_without_style, default_properties])

        default_properties = DefaultProperties.new(BooleanType.new, [Font.new(StringLiteral.new('Arial'))])
        page = Page.new('_', [section, default_properties])
        Stylesheet.new('_', [page])
      end
    end
  end
end