module Parser.Expression exposing (expression)

import AST
    exposing
        ( Expression(Integer, ParensExpression, Var, Boolean, LogicExpression, ComparisonExpression, RelationExpression, ArithmeticExpression)
        , Logic(And, Or)
        , Comparison(Equal, NotEqual)
        , Relation(GreaterThanOrEqual, LessThanOrEqual, LessThan, GreaterThan)
        , Operator(Plus, Minus, Multiply, Divide)
        )
import Combine exposing (Parser, chainl, choice, lazy, string, parens, (<$), (<$>), (*>), (<*), (<|>))
import Combine.Num exposing (int)
import List exposing (foldr)
import Parser.Token exposing (variableName)
import Combine.Extra exposing (trimmed)


expression : Parser s Expression
expression =
    lazy <|
        \() -> foldr chainl atom expressions


expressions : List (Parser s (Expression -> Expression -> Expression))
expressions =
    [ orOp, andOp, comparisonOp, relationalOp, addOp, multiplyOp ]


andOp : Parser s (Expression -> Expression -> Expression)
andOp =
    LogicExpression And <$ string "&&"


orOp : Parser s (Expression -> Expression -> Expression)
orOp =
    LogicExpression Or <$ string "||"


comparisonOp : Parser s (Expression -> Expression -> Expression)
comparisonOp =
    choice
        [ ComparisonExpression Equal <$ string "=="
        , ComparisonExpression NotEqual <$ string "!="
        ]


relationalOp : Parser s (Expression -> Expression -> Expression)
relationalOp =
    choice
        [ RelationExpression GreaterThanOrEqual <$ string ">="
        , RelationExpression LessThanOrEqual <$ string "<="
        , RelationExpression GreaterThan <$ string ">"
        , RelationExpression LessThan <$ string "<"
        ]


addOp : Parser s (Expression -> Expression -> Expression)
addOp =
    choice
        [ ArithmeticExpression Plus <$ string "+"
        , ArithmeticExpression Minus <$ string "-"
        ]


multiplyOp : Parser s (Expression -> Expression -> Expression)
multiplyOp =
    choice
        [ ArithmeticExpression Multiply <$ string "*"
        , ArithmeticExpression Divide <$ string "/"
        ]


atom : Parser s Expression
atom =
    trimmed
        (choice
            [ Integer <$> int
            , Var <$> variableName
            , Boolean <$> (True <$ string "true" <|> False <$ string "false")
            , ParensExpression <$> parens expression
            ]
        )
