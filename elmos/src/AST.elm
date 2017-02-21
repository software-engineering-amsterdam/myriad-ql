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
    , valueExpression : Maybe Expression
    }


type alias IfBlock =
    { expression : Expression
    , thenBranch : List FormItem
    , elseBranch : List FormItem
    }


type Expression
    = Var String
    | Str String
    | Integer Int
    | Boolean Bool
    | ParensExpression Expression
    | ArithmeticExpression Operator Expression Expression
    | RelationExpression Relation Expression Expression
    | LogicExpression Logic Expression Expression
    | ComparisonExpression Comparison Expression Expression


type Operator
    = Plus
    | Minus
    | Divide
    | Multiply


type Logic
    = And
    | Or


type Comparison
    = Equal
    | NotEqual


type Relation
    = LessThan
    | LessThanOrEqual
    | GreaterThan
    | GreaterThanOrEqual


type ValueType
    = StringType
    | BooleanType
    | IntegerType
