package model

class FormModel(form: parser.ast.Form) {

}

object FormModel {
  def apply(form: parser.ast.Form) = new FormModel(form)
}