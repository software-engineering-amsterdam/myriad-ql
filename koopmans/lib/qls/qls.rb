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
      @parse_tree = parse_form
      @ast = transform_form
      check_types
      @styles = collect_styles
      @question_frame_styles = style_question_frame
      @gui = build_gui
    end

    def read_file(file_name)
      File.read(file_name)
    rescue
      error = Notification::Error.new('Error while reading QL file')
      NotificationTable.store(error)
      'stylesheet _'
    end

    def parse_form
      Parser::FormParser.new.parse(content)
    rescue
      error = Notification::Error.new('Error while creating parse tree')
      NotificationTable.store(error)
      { stylesheet: { id: '_', pages: [] } }
    end

    def transform_form
      Parser::FormTransformer.new.apply(parse_tree)
    rescue
      error = Notification::Error.new('Error while creating AST')
      NotificationTable.store(error)
      AST::Stylesheet.new('_', [])
    end

    def check_types
      TypeChecker::TypeChecker.new.check(ast, ql.ast)
    rescue
      error = Notification::Error.new('Error while type checking')
      NotificationTable.store(error)
    end

    def collect_styles
      style_collector = GUI::StyleCollector.new
      ast.accept(style_collector)
      style_collector.styles
    rescue
      error = Notification::Error.new('Error while collecting style')
      NotificationTable.store(error)
      {}
    end

    def style_question_frame
      question_frame_styler = GUI::QuestionFrameStyleBuilder.new(styles)
      ast.accept(question_frame_styler)
      question_frame_styler.question_frame_styles
    rescue
      error = Notification::Error.new('Error styling question frame')
      NotificationTable.store(error)
      {}
    end

    def build_gui
      gui_with_style = GUI::GUIWithStyle.new(ql.gui)
      gui_with_style.question_frame_styles = question_frame_styles
      gui_with_style
    rescue
      error = Notification::Error.new('Error while building gui with style')
      NotificationTable.store(error)
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
      error = Notification::Error.new('Runtime error')
      NotificationTable.store(error)
      render_errors
    end
  end
end