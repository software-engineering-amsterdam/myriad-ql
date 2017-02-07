import java.io.FileReader

import parser.FormParser

/**
  * Created by jasper on 07/02/17.
  */
object Main extends App {
  val filename = "src/main/resources/example.ql"
  println(FormParser(new FileReader(filename)))
}
