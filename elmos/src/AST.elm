module AST exposing (..)


type alias Label =
    String


type alias Id =
    String


type alias Form =
    { id : Id
    , items : Block
    }


type FormItem
    = Field Label Id ValueType
    | ComputedField Label Id ValueType Expression
    | IfThen Expression Block
    | IfThenElse Expression Block Block


type alias Block =
    List FormItem


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
