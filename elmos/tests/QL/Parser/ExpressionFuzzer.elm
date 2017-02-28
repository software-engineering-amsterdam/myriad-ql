module QL.Parser.ExpressionFuzzer exposing (expression)

import Char
import Fuzz exposing (Fuzzer, frequencyOrCrash, intRange, constant)
import Lazy as L


type ExprPart
    = Literal String
    | Operator ExprPart String ExprPart
    | Parenthesized ExprPart


expression : Fuzzer String
expression =
    exprPart |> Fuzz.map exprToString


exprToString : ExprPart -> String
exprToString x =
    case x of
        Literal y ->
            y

        Operator left y right ->
            String.concat [ exprToString left, y, exprToString right ]

        Parenthesized y ->
            wrapInParens (exprToString y)


wrapInParens : String -> String
wrapInParens s =
    "(" ++ s ++ ")"


exprPart : Fuzzer ExprPart
exprPart =
    L.force <|
        L.lazy <|
            \() ->
                frequencyOrCrash
                    [ ( 2.0, literal )
                    , ( 1.0, operator )
                    , ( 0.5, parenthesized )
                    ]


operator : Fuzzer ExprPart
operator =
    L.force <|
        L.lazy <|
            \() ->
                Fuzz.map3 Operator exprPart operatorToken exprPart


operatorToken : Fuzzer String
operatorToken =
    [ "+", "-", "/", "*" ]
        |> List.map (constant >> (,) 1.0)
        |> frequencyOrCrash


parenthesized : Fuzzer ExprPart
parenthesized =
    L.force <|
        L.lazy <|
            \() ->
                Fuzz.map Parenthesized exprPart


literal : Fuzzer ExprPart
literal =
    Fuzz.map Literal <|
        frequencyOrCrash
            [ ( 0.5, number )
            , ( 0.5, variable )
            ]


number : Fuzzer String
number =
    intRange 1 100 |> Fuzz.map toString


variable : Fuzzer String
variable =
    intRange 97 (97 + 26)
        |> Fuzz.map (Char.fromCode >> String.fromChar)
