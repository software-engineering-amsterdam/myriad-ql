require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QLS
  module GUI
    include QL::AST
    include AST

    describe StyleCollector do
      it 'collects' do
        style_collector = StyleCollector.new
        ast = generate_stylesheet
        ast.accept(style_collector)
        expect(style_collector.styles[@question.object_id].width).to be(100)
        expect(style_collector.styles[@section.object_id].width).to be(200)
      end

      def generate_stylesheet
        @question = Question.new(Variable.new('hasSoldHouse'), [Width.new(IntegerLiteral.new(100))])
        default_properties = DefaultProperties.new(BooleanType.new, [Width.new(IntegerLiteral.new(200))])
        @section = Section.new('_', [@question, default_properties])
        page = Page.new('_', [@section])
        Stylesheet.new('_', [page])
      end
    end
  end
end