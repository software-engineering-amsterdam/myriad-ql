module Parser.FormTests exposing (all)

import AST
    exposing
        ( FormItem(FieldItem, IfItem)
        , ValueType(IntegerType, BooleanType, StringType)
        , Expression(Var, Integer, ArithmeticExpression)
        , Operator(Plus)
        )
import Expect
import Parser.Form as Form
import ParserTestUtil exposing (parseToMaybe, testWithParser)
import Samples.Form as Samples
import Test exposing (Test, describe, test)


all : Test
all =
    describe "ParserTests"
        [ sampleTests
        , formTokenTests
        , fieldTests
        , ifBlockTests
        , formItemTests
        , formItemsTests
        , valueTypeTests
        ]


sampleTests : Test
sampleTests =
    describe "sample tests"
        (Samples.goodSamples
            |> List.indexedMap
                (\n input ->
                    test ("Sample " ++ toString (n + 1)) <|
                        \() -> parseToMaybe Form.form input |> Expect.notEqual Nothing
                )
        )


formTokenTests : Test
formTokenTests =
    testWithParser Form.formToken
        "formToken"
        [ ( "should parse a form token", "form", Just "form" )
        ]


formItemsTests : Test
formItemsTests =
    testWithParser Form.formItems
        "formItemTests"
        [ ( "should parse multiple form items"
          , "\"label\" id: integer\nif (bar) { \"label\" id: integer } else { \"label\" id: integer }"
          , Just
                [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing }
                , IfItem
                    { expression = Var "bar"
                    , thenBranch = [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } ]
                    , elseBranch = Just [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } ]
                    }
                ]
          )
        ]


formItemTests : Test
formItemTests =
    testWithParser Form.formItem
        "formItemTests"
        [ ( "should parse a simple field", "\"label\" id: integer", Just <| FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } )
        , ( "should parse an if block"
          , "if (bar) { \"label\" id: integer } else { \"label\" id: integer }"
          , Just <|
                IfItem
                    { expression = Var "bar"
                    , thenBranch = [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } ]
                    , elseBranch = Just [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } ]
                    }
          )
        ]


fieldTests : Test
fieldTests =
    testWithParser Form.field
        "field"
        [ ( "should parse a simple field", "\"label\" id: integer", Just { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } )
        , ( "expects whitespace after the label", "\"label\"id: integer", Nothing )
        , ( "allows no whitespace after the colon", "\"label\" id:integer", Just { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } )
        , ( "id should be a varName", "\"label\" Other: integer", Nothing )
        , ( "should only support valid types", "\"label\" id: invalid", Nothing )
        , ( "should parse field with expression"
          , "\"label\" id: integer = 1 +3"
          , Just { label = "label", id = "id", valueType = IntegerType, valueExpression = Just (ArithmeticExpression Plus (Integer 1) (Integer 3)) }
          )
        ]


ifBlockTests : Test
ifBlockTests =
    let
        basicBlockContent =
            [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing }
            ]
    in
        testWithParser Form.ifBlock
            "ifBlock"
            [ ( "should parser an simple if block"
              , "if (x) { \"label\" id: integer }"
              , Just
                    { expression = Var "x"
                    , thenBranch = basicBlockContent
                    , elseBranch = Nothing
                    }
              )
            , ( "should allow no whitespace"
              , "if(x){\"label\" id:integer}"
              , Just
                    { expression = Var "x"
                    , thenBranch = basicBlockContent
                    , elseBranch = Nothing
                    }
              )
            , ( "should parse if with else block"
              , "if (x) {\"label\" id: integer} else {\"label\" id: integer}"
              , Just
                    { expression = Var "x"
                    , thenBranch = basicBlockContent
                    , elseBranch = Just basicBlockContent
                    }
              )
            ]


valueTypeTests : Test
valueTypeTests =
    testWithParser Form.valueType
        "valueType"
        [ ( "should parse string", "string", Just StringType )
        , ( "should parse boolean", "boolean", Just BooleanType )
        , ( "should parse integer", "integer", Just IntegerType )
        , ( "should parse money as integer", "money", Just IntegerType )
        ]
