module QLS
  module GUI
    class QuestionFrameStyleBuilder
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
        style = @styles[section.object_id]
        style.merge_with(parent_style)
        section.body.map { |element| element.accept(self, style) }
      end

      def visit_question(question, parent_style)
        style = @styles[question.object_id]
        style.merge_with(parent_style)
        @question_frame_styles[question.variable.name] = style if style
      end

      def visit_default_properties(_, _) end
    end
  end
end
