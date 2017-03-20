module QL.Parser.Expression exposing (expression)

import Combine exposing (Parser, chainl, choice, lazy, parens, succeed, (<$>), (<*>), (<|>))
import Combine.Num exposing (int, float)
import Combine.Extra exposing (trimmed, stringAs)
import List exposing (foldr)
import QL.AST
    exposing
        ( Location
        , Expression
            ( Integer
            , Decimal
            , ParensExpression
            , Var
            , Str
            , Boolean
            , BinaryExpression
            )
        , LogicOperator(And, Or)
        , ComparisonOperator(Equal, NotEqual)
        , RelationOperator(GreaterThanOrEqual, LessThanOrEqual, LessThan, GreaterThan)
        , Operator(..)
        , ArithmeticOperator(Plus, Minus, Multiply, Divide)
        )
import QL.Parser.Token exposing (identifier, quotedString, withLocation, parseLocation)


type alias BinaryOperator =
    Expression -> Expression -> Expression


expression : Parser s Expression
expression =
    lazy <| \() -> foldr chainl atom precedenceOrderedOperators


precedenceOrderedOperators : List (Parser s BinaryOperator)
precedenceOrderedOperators =
    List.map trimmed <|
        [ orOp
        , andOp
        , comparisonOp
        , relationalOp
        , addOrMinOp
        , multiplyOrDivideOp
        ]


orOp : Parser s BinaryOperator
orOp =
    defineBinary "||" (BinaryExpression (Logic Or))


andOp : Parser s BinaryOperator
andOp =
    defineBinary "&&" (BinaryExpression (Logic And))


defineBinary : String -> (Location -> BinaryOperator) -> Parser s BinaryOperator
defineBinary token expr =
    trimmed (withLocation (stringAs token expr))


comparisonOp : Parser s BinaryOperator
comparisonOp =
    choice
        [ defineBinary "==" (BinaryExpression (Comparison Equal))
        , defineBinary "!=" (BinaryExpression (Comparison NotEqual))
        ]


relationalOp : Parser s BinaryOperator
relationalOp =
    choice
        [ defineBinary ">=" (BinaryExpression (Relation GreaterThanOrEqual))
        , defineBinary "<=" (BinaryExpression (Relation LessThanOrEqual))
        , defineBinary ">" (BinaryExpression (Relation GreaterThan))
        , defineBinary "<" (BinaryExpression (Relation LessThan))
        ]


addOrMinOp : Parser s BinaryOperator
addOrMinOp =
    choice
        [ defineBinary "+" (BinaryExpression (Arithmetic Plus))
        , defineBinary "-" (BinaryExpression (Arithmetic Minus))
        ]


multiplyOrDivideOp : Parser s BinaryOperator
multiplyOrDivideOp =
    choice
        [ defineBinary "*" (BinaryExpression (Arithmetic Multiply))
        , defineBinary "/" (BinaryExpression (Arithmetic Divide))
        ]


atom : Parser s Expression
atom =
    lazy <|
        \() ->
            choice
                [ decimalAtom
                , integerAtom
                , stringAtom
                , booleanAtom
                , parensAtom
                , varAtom
                ]


integerAtom : Parser s Expression
integerAtom =
    succeed Integer
        <*> parseLocation
        <*> int


decimalAtom : Parser s Expression
decimalAtom =
    succeed Decimal
        <*> parseLocation
        <*> float


stringAtom : Parser s Expression
stringAtom =
    succeed Str
        <*> parseLocation
        <*> quotedString


varAtom : Parser s Expression
varAtom =
    Var <$> identifier


booleanAtom : Parser s Expression
booleanAtom =
    succeed Boolean
        <*> parseLocation
        <*> (stringAs "true" True <|> stringAs "false" False)


parensAtom : Parser s Expression
parensAtom =
    succeed ParensExpression
        <*> parseLocation
        <*> parens (trimmed expression)
