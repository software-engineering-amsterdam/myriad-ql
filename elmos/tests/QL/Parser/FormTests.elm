module QL.Parser.FormTests exposing (all)

import QL.AST
    exposing
        ( FormItem(Field, ComputedField, IfThen, IfThenElse)
        , ValueType(IntegerType, BooleanType, StringType, MoneyType)
        , Expression(Var, Integer, ArithmeticExpression)
        , Operator(Plus)
        , Location(Location)
        )
import QL.ASTTestUtil exposing (removeLocationFromBlock, removeLocationFromFormItem)
import Expect
import QL.Parser.Form as Form
import ParserTestUtil exposing (parseToMaybe, testWithParser, testWithParserAndMap)
import QL.Samples.Form as Samples
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
    testWithParserAndMap Form.formItems
        removeLocationFromBlock
        "formItems"
        [ ( "should parse multiple form items"
          , "\"label\" id: integer\nif (bar) { \"label\" id: integer } else { \"label\" id: integer }"
          , Just
                [ Field "label" ( "id", Location 0 0 ) IntegerType
                , IfThenElse (Var ( "bar", Location 0 0 ))
                    [ Field "label" ( "id", Location 0 0 ) IntegerType ]
                    [ Field "label" ( "id", Location 0 0 ) IntegerType ]
                ]
          )
        , ( "should parse multiple form items"
          , """"label"
          id: integer = bar

          "label"
          id: integer"""
          , Just
                [ ComputedField "label" ( "id", Location 0 0 ) IntegerType (Var ( "bar", Location 0 0 ))
                , Field "label" ( "id", Location 0 0 ) IntegerType
                ]
          )
        ]


formItemTests : Test
formItemTests =
    testWithParserAndMap Form.formItem
        removeLocationFromFormItem
        "formItem"
        [ ( "should parse a simple field", "\"label\" id: integer", Just <| Field "label" ( "id", Location 0 0 ) IntegerType )
        , ( "should parse an if block"
          , "if (bar) { \"label\" id: integer } else { \"label\" id: integer }"
          , Just <|
                IfThenElse
                    (Var ( "bar", Location 0 0 ))
                    [ Field "label" ( "id", Location 0 0 ) IntegerType ]
                    [ Field "label" ( "id", Location 0 0 ) IntegerType ]
          )
        ]


fieldTests : Test
fieldTests =
    testWithParserAndMap Form.formItem
        removeLocationFromFormItem
        "field and computed fields"
        [ ( "should parse a simple field", "\"label\" id: integer", Just (Field "label" ( "id", Location 0 0 ) IntegerType) )
        , ( "expects whitespace after the label", "\"label\"id: integer", Nothing )
        , ( "allows no whitespace after the colon", "\"label\" id:integer", Just (Field "label" ( "id", Location 0 0 ) IntegerType) )
        , ( "id should be a varName", "\"label\" Other: integer", Nothing )
        , ( "should only support valid types", "\"label\" id: invalid", Nothing )
        , ( "should parse field with expression"
          , "\"label\" id: integer = 1 +3"
          , Just
                (ComputedField "label"
                    ( "id", Location 0 0 )
                    IntegerType
                    (ArithmeticExpression Plus
                        (Location 0 0)
                        (Integer (Location 0 0) 1)
                        (Integer (Location 0 0) 3)
                    )
                )
          )
        , ( "should parse field with expression that is only a var name"
          , "\"label\" id: integer = someVarName"
          , Just (ComputedField "label" ( "id", Location 0 0 ) IntegerType (Var ( "someVarName", Location 0 0 )))
          )
        ]


ifBlockTests : Test
ifBlockTests =
    let
        basicBlockContent =
            [ Field "label" ( "id", Location 0 0 ) IntegerType
            ]
    in
        testWithParserAndMap Form.formItem
            removeLocationFromFormItem
            "ifThen and ifThenElse"
            [ ( "should parse a simple if block"
              , "if (x) { \"label\" id: integer }"
              , Just (IfThen (Var ( "x", Location 0 0 )) basicBlockContent)
              )
            , ( "should allow no whitespace"
              , "if(x){\"label\" id:integer}"
              , Just (IfThen (Var ( "x", Location 0 0 )) basicBlockContent)
              )
            , ( "should parse if with else block"
              , "if (x) {\"label\" id: integer} else {\"label\" id: integer}"
              , Just (IfThenElse (Var ( "x", Location 0 0 )) basicBlockContent basicBlockContent)
              )
            , ( "should allow whitespace between parens in condition for if block"
              , "if( x ){\"label\" id:integer}"
              , Just (IfThen (Var ( "x", Location 0 0 )) basicBlockContent)
              )
            , ( "should allow whitespace between parens in condition for if else block"
              , "if ( x ) {\"label\" id: integer} else {\"label\" id: integer}"
              , Just (IfThenElse (Var ( "x", Location 0 0 )) basicBlockContent basicBlockContent)
              )
            ]


valueTypeTests : Test
valueTypeTests =
    testWithParser Form.valueType
        "valueType"
        [ ( "should parse string", "string", Just StringType )
        , ( "should parse boolean", "boolean", Just BooleanType )
        , ( "should parse integer", "integer", Just IntegerType )
        , ( "should parse money as integer", "money", Just MoneyType )
        ]
