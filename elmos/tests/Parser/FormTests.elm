module Parser.FormTests exposing (all)

import AST
    exposing
        ( FormItem(Field, ComputedField, IfThen, IfThenElse)
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


formItemsTests : Test
formItemsTests =
    testWithParser Form.formItems
        "formItems"
        [ ( "should parse multiple form items"
          , "\"label\" id: integer\nif (bar) { \"label\" id: integer } else { \"label\" id: integer }"
          , Just
                [ Field "label" "id" IntegerType
                , IfThenElse (Var "bar") [ Field "label" "id" IntegerType ] [ Field "label" "id" IntegerType ]
                ]
          )
        , ( "should parse multiple form items"
          , """"label"
          id: integer = bar

          "label"
          id: integer"""
          , Just
                [ ComputedField "label" "id" IntegerType (Var "bar")
                , Field "label" "id" IntegerType
                ]
          )
        ]


formItemTests : Test
formItemTests =
    testWithParser Form.formItem
        "formItem"
        [ ( "should parse a simple field", "\"label\" id: integer", Just <| Field "label" "id" IntegerType )
        , ( "should parse an if block"
          , "if (bar) { \"label\" id: integer } else { \"label\" id: integer }"
          , Just <|
                IfThenElse
                    (Var "bar")
                    [ Field "label" "id" IntegerType ]
                    [ Field "label" "id" IntegerType ]
          )
        ]


fieldTests : Test
fieldTests =
    testWithParser Form.field
        "field"
        [ ( "should parse a simple field", "\"label\" id: integer", Just (Field "label" "id" IntegerType) )
        , ( "expects whitespace after the label", "\"label\"id: integer", Nothing )
        , ( "allows no whitespace after the colon", "\"label\" id:integer", Just (Field "label" "id" IntegerType) )
        , ( "id should be a varName", "\"label\" Other: integer", Nothing )
        , ( "should only support valid types", "\"label\" id: invalid", Nothing )
        , ( "should parse field with expression"
          , "\"label\" id: integer = 1 +3"
          , Just (ComputedField "label" "id" IntegerType (ArithmeticExpression Plus (Integer 1) (Integer 3)))
          )
        , ( "should parse field with expression that is only a var name"
          , "\"label\" id: integer = someVarName"
          , Just (ComputedField "label" "id" IntegerType (Var "someVarName"))
          )
        ]


ifBlockTests : Test
ifBlockTests =
    let
        basicBlockContent =
            [ Field "label" "id" IntegerType
            ]
    in
        testWithParser Form.field
            "ifThen and ifThenElse"
            [ ( "should parser an simple if block"
              , "if (x) { \"label\" id: integer }"
              , Just (IfThen (Var "x") basicBlockContent)
              )
            , ( "should allow no whitespace"
              , "if(x){\"label\" id:integer}"
              , Just (IfThen (Var "x") basicBlockContent)
              )
            , ( "should parse if with else block"
              , "if (x) {\"label\" id: integer} else {\"label\" id: integer}"
              , Just (IfThenElse (Var "x") basicBlockContent basicBlockContent)
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
