require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QLS
  module GUI
    include QL::AST
    include AST

    describe StyleCollector do
      let(:styles) { generate_styles }

      it 'collects page style' do
        expect(styles[@page.object_id].width).to be(300)
      end

      it 'collects section style' do
        expect(styles[@section.object_id].width).to be(200)
      end

      it 'collects question style' do
        expect(styles[@question.object_id].width).to be(100)
      end

      def generate_styles
        style_collector = StyleCollector.new
        ast = generate_stylesheet
        ast.accept(style_collector)
        style_collector.styles
      end

      def generate_stylesheet
        @question = Question.new(Variable.new('hasSoldHouse'), [Width.new(IntegerLiteral.new(100))])
        default_properties = DefaultProperties.new(BooleanType.new, [Width.new(IntegerLiteral.new(200))])
        @section = Section.new('_', [@question, default_properties])
        default_properties = DefaultProperties.new(BooleanType.new, [Width.new(IntegerLiteral.new(300))])
        @page = Page.new('_', [@section, default_properties])
        Stylesheet.new('_', [@page])
      end
    end
  end
end
