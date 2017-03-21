package checker

import ast.Stylesheet.DefaultStyles
import ast._
import model.StyleModel
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.words.ShouldVerb
import org.scalatest.{ Matchers, PropSpec }

class StyleCheckerTest extends PropSpec with Matchers with TableDrivenPropertyChecks with ShouldVerb {
  // widgets
  private val checkbox = Checkbox
  private val datePicker = DatePicker
  private val dropdown = Dropdown("Yes", "No")
  private val radio = Radio("True", "False")
  private val slider = Slider(0, 100)
  private val spinbox = Spinbox
  private val textfield = Textfield

  // questions
  private val boolean = QuestionStyle("boolean", Map.empty, None)
  private val integer = QuestionStyle("integer", Map.empty, None)
  private val decimal = QuestionStyle("decimal", Map.empty, None)
  private val money = QuestionStyle("money", Map.empty, None)
  private val date = QuestionStyle("date", Map.empty, None)
  private val string = QuestionStyle("string", Map.empty, None)
  private val all = Seq(boolean, integer, decimal, money, date, string)

  private val unreferencedQuestion = QuestionStyle("question", Map.empty, None)
  private val invalidWidget = QuestionStyle("boolean", Map.empty, Some(Textfield))

  // styles
  private val color = Color("#999999")
  private val font = Font("Ariel")
  private val fontSize = FontSize(12)
  private val width = Width(100)

  private val styling = Map(
    "color" -> color,
    "font" -> font,
    "fontSize" -> fontSize,
    "width" -> width
  )

  private val questionsWithType: Map[String, Type] = Map(
    "boolean" -> BooleanType,
    "integer" -> IntegerType,
    "decimal" -> DecimalType,
    "money" -> MoneyType,
    "date" -> DateType,
    "string" -> StringType
  )

  // defaultstyles with(out) widgets
  private val checkboxStyling = DefaultStyle(BooleanType, styling, Some(checkbox))
  private val datePickerStyling = DefaultStyle(DateType, styling, Some(datePicker))
  private val drodownStyling = DefaultStyle(BooleanType, styling, Some(dropdown))
  private val radioStyling = DefaultStyle(BooleanType, styling, Some(radio))
  private val sliderStyling = DefaultStyle(MoneyType, styling, Some(slider))
  private val spinboxStyling = DefaultStyle(MoneyType, styling, Some(spinbox))
  private val textfieldStringStyling = DefaultStyle(StringType, styling, Some(textfield))
  private val textfieldNumericStyling = DefaultStyle(MoneyType, styling, Some(textfield))
  private val textfieldDateStyling = DefaultStyle(DateType, styling, Some(textfield))
  private val defaultStyling = DefaultStyle(BooleanType, styling, None)

  private val defaultStyles = Seq(
    checkboxStyling,
    datePickerStyling,
    drodownStyling,
    radioStyling,
    sliderStyling,
    spinboxStyling,
    textfieldStringStyling,
    textfieldNumericStyling,
    textfieldDateStyling,
    defaultStyling
  )

  private val errorStyles = Seq(
    DefaultStyle(DecimalType, styling, Some(datePicker))
  )

  private val validStyles = Table[Stylesheet, Map[String, Type]](
    ("expr", "expectedType"),
    (createStylesheet(all), questionsWithType),
    (createStylesheet(all, defaultStyles), questionsWithType)
  )

  private val invalidStyles = Table[Stylesheet, Map[String, Type]](
    ("style", "styling"),
    (createStylesheet(Seq(integer, integer)), questionsWithType),
    (createStylesheet(Seq(integer), defaultStyles), questionsWithType),
    (createStylesheet(Seq(decimal)), Map.empty),
    (createStylesheet(Seq(decimal), errorStyles), questionsWithType),
    (createStylesheet(Seq(invalidWidget), defaultStyles), questionsWithType)
  )

  private def createStylesheet(questions: Seq[QuestionStyle]) = {
    val section = Section("section", questions, Nil)
    val nestedSection = Section("section", Seq(section), Nil)
    val page = Page("page", Seq(nestedSection), Nil)
    Stylesheet("stylesheet", Seq(page))
  }

  private def createStylesheet(questions: Seq[QuestionStyle], defaultStyles: DefaultStyles) = {
    val section = Section("section", questions, defaultStyles)
    val page = Page("page", Seq(section), defaultStyles)
    Stylesheet("stylesheet", Seq(page))
  }

  property("valid styles should never have errors") {
    forAll(validStyles) {
      (style, styling) => StyleChecker(new StyleModel(style, questionsWithType), styling) should be(Nil)
    }
  }

  property("invalid styles should have at least 1 errors") {
    forAll(invalidStyles) {
      (style, styling) => StyleChecker(new StyleModel(style, questionsWithType), styling) should not be Nil
    }
  }

  property("Question not defined in QL") {
    val error = intercept[Exception] {
      val stylesheet = createStylesheet(Seq(unreferencedQuestion))
      val styleModel = new StyleModel(stylesheet, questionsWithType)
      StyleChecker(styleModel, questionsWithType)
    }
    error.getMessage should be("Unable to determine question type for styling.")
  }

}
