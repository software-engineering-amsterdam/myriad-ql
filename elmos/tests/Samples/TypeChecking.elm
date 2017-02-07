module Samples.TypeChecking exposing (..)


badExample1 : ( String, String )
badExample1 =
    ( "if expresion on money value"
    , """form taxOfficeExample {
  "Give me a number:"
    someNumber: money

  if (someNumber) {
    "Is it a lot:"
      aLot: boolean

  }
}"""
    )


badExample2 : ( String, String )
badExample2 =
    ( "duplicate form field identifier"
    , """form myForm {
  "Give me a number:"
    someNumber: money

  "Give me a another number:"
    someNumber: money
}
"""
    )


badExample3 : ( String, String )
badExample3 =
    ( "assigning a number to a boolean"
    , """form myForm {
  "Give me a number:"
    someNumber: money

  "This is a boolean:"
    someBoolean : boolean = (someNumber)
}
"""
    )


badExample4 : ( String, String )
badExample4 =
    ( "assigning a boolean to a number"
    , """form myForm {
  "Check me:"
    someBoolean: boolean

  "Your computed age is:"
    someNumber : money = (someNumber)
}
"""
    )


badExample5 : ( String, String )
badExample5 =
    ( "cyclic dependency over 2 fields"
    , """form myForm {
  "Check me:"
    bool1: boolean = (bool2)

  "Your computed age is:"
    bool2 : boolean = (bool1)
}
"""
    )


badExample6 : ( String, String )
badExample6 =
    ( "self reference in expression"
    , """form myForm {
  "Check me:"
    bool1: boolean = (bool1)

}
"""
    )
