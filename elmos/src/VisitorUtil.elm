module VisitorUtil exposing (Order(Continue, Pre, Post), actionLambda)


type Order context node
    = Continue
    | Pre (node -> context -> context)
    | Post (node -> context -> context)


actionLambda : Order context node -> (context -> context) -> node -> context -> context
actionLambda action =
    case action of
        Continue ->
            (\f _ context -> f context)

        Pre g ->
            (\f node context -> g node context |> f)

        Post g ->
            (\f node context -> f context |> g node)
