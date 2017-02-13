import java.io.FileReader

import parser.FormParser

object Main extends App {
  val filename = "src/main/resources/example.ql"
  println(FormParser(new FileReader(filename)))
}
