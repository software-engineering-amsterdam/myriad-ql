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
    = Question Label Id ValueType
    | ComputedQuestion Label Id ValueType Expression
    | IfThen Expression Block
    | IfThenElse Expression Block Block


type Expression
    = Var Id
    | Str Location String
    | Decimal Location Float
    | Integer Location Int
    | Boolean Location Bool
    | ParensExpression Location Expression
    | BinaryExpression Operator Location Expression Expression


type Operator
    = Arithmetic ArithmeticOperator
    | Relation RelationOperator
    | Logic LogicOperator
    | Comparison ComparisonOperator


type ArithmeticOperator
    = Plus
    | Minus
    | Divide
    | Multiply


type LogicOperator
    = And
    | Or


type ComparisonOperator
    = Equal
    | NotEqual


type RelationOperator
    = LessThan
    | LessThanOrEqual
    | GreaterThan
    | GreaterThanOrEqual


type ValueType
    = StringType
    | BooleanType
    | IntegerType
    | MoneyType
