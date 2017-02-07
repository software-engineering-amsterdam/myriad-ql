module ExpressionParser exposing (..)

import Combine exposing (..)
import Combine.Num exposing (int)
import AST exposing (..)


expression : Parser s Expression
expression =
    lazy <|
        \() ->
            chainl addOp
                (chainl mulOp atom)


addOp : Parser s (Expression -> Expression -> Expression)
addOp =
    choice
        [ PlusExpression <$ string "+"
        , MinusExpression <$ string "-"
        ]


mulOp : Parser s (Expression -> Expression -> Expression)
mulOp =
    choice
        [ MultiplyExpression <$ string "*"
        , DivideExpression <$ string "/"
        ]


atom : Parser s Expression
atom =
    choice
        [ Integer <$> int
        , Var <$> variableName
        , Boolean <$> (True <$ string "true" <|> False <$ string "false")
        ]


variableName : Parser s String
variableName =
    regex "[a-z0-9][a-zA-Z0-9_]*"
