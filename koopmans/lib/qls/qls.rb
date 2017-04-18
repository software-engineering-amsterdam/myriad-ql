require 'require_all'
require 'pp'
require_rel %w(../ql ../notification ../notification_table ast gui parser type_checker)

module QLS
  class QLS < SimpleDelegator
    attr_reader :ql, :content, :parse_tree, :ast, :styles, :question_frame_styles, :gui

    def initialize(ql)
      @ql = ql
      super
    end

    def build(filename)
      @content = read_file(filename)
      @parse_tree = form_parser
      @ast = form_transformer
      type_checker
      @styles = style_collector
      @question_frame_styles = question_frame_styler
      @gui = gui_builder
    end

    def read_file(file_name)
      File.read(file_name)
    rescue
      NotificationTable.store(Notification::Error.new('Error while reading QL file'))
      'stylesheet _'
    end

    def form_parser
      Parser::FormParser.new.parse(content)
    rescue
      NotificationTable.store(Notification::Error.new('Error while creating parse tree'))
      { stylesheet: { id: '_', pages: [] } }
    end

    def form_transformer
      Parser::FormTransformer.new.apply(parse_tree)
    rescue
      NotificationTable.store(Notification::Error.new('Error while creating AST'))
      AST::Stylesheet.new('_', [])
    end

    def type_checker
      TypeChecker::TypeChecker.new.check(ast, ql.ast)
    rescue
      NotificationTable.store(Notification::Error.new('Error while type checking'))
    end

    def style_collector
      style_collector = GUI::StyleCollector.new
      ast.accept(style_collector)
      style_collector.styles
    rescue
      NotificationTable.store(Notification::Error.new('Error while collecting style'))
      {}
    end

    def question_frame_styler
      question_frame_styler = GUI::QuestionFrameStyler.new(styles)
      ast.accept(question_frame_styler)
      question_frame_styler.question_frame_styles
    rescue
      NotificationTable.store(Notification::Error.new('Error styling question frame'))
      {}
    end

    def gui_builder
      gui_with_style = GUI::GUIWithStyle.new(ql.gui)
      gui_with_style.question_frame_styles = question_frame_styles
      gui_with_style
    rescue
      NotificationTable.store(Notification::Error.new('Error while building gui with style'))
    end

    def run_gui
      if NotificationTable.errors.any?
        render_errors
        return
      end
      if NotificationTable.warnings.any?
        render_warnings
      end
      gui.render
    rescue
      NotificationTable.store(Notification::Error.new('Runtime error'))
      render_errors
    end
  end
end
