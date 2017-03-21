# TODO use wrapper pattern to apply styles
module QLS
  module GUI
    class StylesheetBuilder
      attr_reader :question_frame_styles

      def initialize(styles)
        @question_frame_styles = {}
        @styles = styles
      end

      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        style = @styles[page.object_id]
        page.body.map { |element| element.accept(self, style) }
      end

      def visit_section(section, parent_style)
        style = merge_styles(@styles[section.object_id], parent_style)
        section.body.map { |element| element.accept(self, style)}
      end

      def visit_question(question, parent_style)
        style = merge_styles(@styles[question.object_id], parent_style)
        @question_frame_styles[question.variable.name] = style if style
      end

      def visit_default_properties(_, _) end

      def merge_styles(style, parent_style)
        style.widget = style.widget || parent_style.widget
        style.width = style.width || parent_style.width
        style
      end
    end
  end
end