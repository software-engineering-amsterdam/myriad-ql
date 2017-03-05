module QL.Samples.TypeChecking exposing (badExamples)


badExamples : List ( String, String )
badExamples =
    [ badExample1
    , badExample2
    , badExample3
    , badExample4
    , badExample5
    , badExample6
    , badExample7
    , badExample8
    , badExample9
    , badExample10
    , badExample11
    ]


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


badExample7 : ( String, String )
badExample7 =
    ( "cyclic dependency over 4 fields"
    , """form myForm {
  "Value 1:"
    bool1: boolean = (bool2)

  "Value 2:"
    bool2: boolean = (bool3)

  "Value 3:"
    bool3: boolean = (bool4)

  "Value 4:"
    bool4: boolean = (bool1)
}
"""
    )


badExample8 : ( String, String )
badExample8 =
    ( "arithmitic with boolean values"
    , """form myForm {
  "Value 1:"
    bool1: boolean

  "Value 2:"
    bool2: boolean

  "Computation:"
    myNumber: number = (bool1 + bool2)
}
"""
    )


badExample9 : ( String, String )
badExample9 =
    ( "arithmetic with string values"
    , """form myForm {
  "Value 1:"
    text1: string

  "Value 2:"
    text2: string

  "Computation:"
    myNumber: number = (text1 + text2)
}
"""
    )


badExample10 : ( String, String )
badExample10 =
    ( "boolean logic with string values"
    , """form myForm {
  "Value 1:"
    text1: string

  "Value 2:"
    text2: string

  "Value 3:"
    bool1: bool = (text1 && text2)
}
"""
    )


badExample11 : ( String, String )
badExample11 =
    ( "boolean logic with numeric values"
    , """form myForm {
  "Value 1:"
    number1: money

  "Value 2:"
    number2: money

  "Value 3:"
    bool1: bool = (number1 && number2)
}
"""
    )
