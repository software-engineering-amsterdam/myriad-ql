module QL.AST exposing (..)


type alias Label =
    String


type Location
    = Location Int Int


type alias Id =
    ( String, Location )


type alias Form =
    { id : Id
    , items : Block
    }


type alias Block =
    List FormItem


type FormItem
    = Field Label Id ValueType
    | ComputedField Label Id ValueType Expression
    | IfThen Expression Block
    | IfThenElse Expression Block Block


type Expression
    = Var Id
    | Str Location String
    | Decimal Location Float
    | Integer Location Int
    | Boolean Location Bool
    | ParensExpression Location Expression
    | ArithmeticExpression Operator Location Expression Expression
    | RelationExpression Relation Location Expression Expression
    | LogicExpression Logic Location Expression Expression
    | ComparisonExpression Comparison Location Expression Expression


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
    | MoneyType
