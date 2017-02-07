module ParserTests exposing (all)

import AST exposing (..)
import Expect
import ExpressionParser exposing (..)
import Parser exposing (..)
import ParserTestUtil exposing (parseToMaybe, testWithParser)
import Samples.Form
import Test exposing (Test, concat, describe, test)


all : Test
all =
    describe "ParserTests"
        [ sampleTests
        , formTokenTests
        , varNameTests
        , fieldTests
        , ifBlockTests
        , valueTypeTests
        , expressionTests
        ]


sampleTests : Test
sampleTests =
    describe "sample tests"
        (Samples.Form.goodSamples
            |> List.indexedMap
                (\n input ->
                    test ("Sample " ++ toString (n + 1)) <|
                        \() -> parseToMaybe Parser.form input |> Expect.notEqual Nothing
                )
        )


formTokenTests : Test
formTokenTests =
    testWithParser Parser.formToken
        "formToken"
        [ ( "should parse a form token", "form", Just "form" )
        ]


varNameTests : Test
varNameTests =
    testWithParser ExpressionParser.variableName
        "variableName"
        [ ( "should not parse an empty string", "", Nothing )
        , ( "should parse a single lower case character", "a", Just "a" )
        , ( "should not parse a single upper case character", "B", Nothing )
        , ( "should support camel case variable names", "fooBarBaz", Just "fooBarBaz" )
        , ( "should support underscores", "a_b", Just "a_b" )
        , ( "should not support question mark", "a?b", Nothing )
        ]


fieldTests : Test
fieldTests =
    testWithParser Parser.field
        "field"
        [ ( "should parse a simple field", "\"label\" id: integer", Just { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } )
        , ( "expects whitespace after the label", "\"label\"id: integer", Nothing )
        , ( "allows no whitespace after the colon", "\"label\" id:integer", Just { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing } )
        , ( "id should be a varName", "\"label\" Other: integer", Nothing )
        , ( "should only support valid types", "\"label\" id: invalid", Nothing )
        ]


ifBlockTests : Test
ifBlockTests =
    let
        basicBlockContent =
            [ FieldItem { label = "label", id = "id", valueType = IntegerType, valueExpression = Nothing }
            ]
    in
        testWithParser Parser.ifBlock
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
    testWithParser Parser.valueType
        "valueType"
        [ ( "should parse string", "string", Just StringType )
        , ( "should parse boolean", "boolean", Just BooleanType )
        , ( "should parse integer", "integer", Just IntegerType )
        ]


expressionTests : Test
expressionTests =
    testWithParser Parser.expression
        "expression"
        [ ( "Should parse varName", "someVarName", Just (Var "someVarName") )
        , ( "Should parse expression between parentheses", "(someVarName)", Just (ParensExpression (Var "someVarName")) )
        ]
