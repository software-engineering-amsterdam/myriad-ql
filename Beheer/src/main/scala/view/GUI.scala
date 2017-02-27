package view

import java.io.FileReader

import ast._
import checker.{ Error, FormChecker, Warning }
import model.{ ComputedQuestion, OpenQuestion }
import parser.FormParser

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.{ FlowPane, HBox }
import scalafx.scene.text.Text

object GUI extends JFXApp {
  private lazy val questions = parsedForm.displayQuestions.map { question =>
    question.`type` match {
      case BooleanType => new BooleanQuestion(question).element
      case DateType => new DateQuestion(question).element
      case StringType => new StringQuestion(question).element
      case _: NumericType => question match {
        case c: ComputedQuestion => new ComputedNumericQuestion(question).element
        case o: OpenQuestion => new NumericQuestion(question).element
      }
    }
  }
  private lazy val issues = {
    val issueMessages = formModel.map {
      case Warning(message) => new HBox(new Text(message))
      case Error(message) => new HBox(new Text(message))
    }
    new HBox {
      children = new HBox(new Text("Errors and warnings:")) +: issueMessages
    }
  }
  private val filename = "src/main/resources/example.ql"
  private val parsedForm = FormParser(new FileReader(filename))
  private val formModel = FormChecker(parsedForm)

  stage = new JFXApp.PrimaryStage {
    title.value = "Beheer QL Form"
    width = 600
    height = 450
    scene = new Scene {
      content = new FlowPane {
        hgap = 10
        vgap = 10
        children = issues +: questions
      }
    }
  }
}