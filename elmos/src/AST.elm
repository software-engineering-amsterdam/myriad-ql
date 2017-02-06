module AST exposing (..)


type alias Form =
    { name : String
    , items : List FormItem
    }


type FormItem
    = FieldItem Field
    | IfItem IfBlock


type alias Field =
    { label : String
    , id : String
    , valueType : ValueType
    }


type alias IfBlock =
    { expression : Expression
    , thenBranch : List FormItem
    , elseBranch : List FormItem
    }


{-| TODO Add other cases
-}
type Expression
    = Var String


type ValueType
    = String
    | Boolean
    | Integer
