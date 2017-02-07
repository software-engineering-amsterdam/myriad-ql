module ExpressionParser exposing (..)

import Combine exposing (..)
import Combine.Num exposing (int)
import AST exposing (..)


expression : Parser s Expression
expression =
    lazy <|
        \() ->
            chainl compareOp <|
                chainl equalsOp <|
                    chainl addOp <|
                        chainl mulOp atom


equalsOp : Parser s (Expression -> Expression -> Expression)
equalsOp =
    choice
        [ EqualToExpression <$ string "=="
        , NotEqualToExpression <$ string "!="
        ]


compareOp : Parser s (Expression -> Expression -> Expression)
compareOp =
    choice
        [ GreaterThanOrEqualExpression <$ string ">="
        , LessThanOrEqualExpression <$ string "<="
        , GreaterThanExpression <$ string ">"
        , LessThanExpression <$ string "<"
        ]


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
    whitespace
        *> choice
            [ Integer <$> int
            , Var <$> variableName
            , Boolean <$> (True <$ string "true" <|> False <$ string "false")
            , ParensExpression <$> (parens expression)
            ]
        <* whitespace


variableName : Parser s String
variableName =
    regex "[a-z0-9][a-zA-Z0-9_]*"
