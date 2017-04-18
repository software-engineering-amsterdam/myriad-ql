module UI.QLS.Pagination exposing (Pagination, current, init, next, hasNext, previous, hasPrevious)

import QLS.AST exposing (Page)


type Pagination
    = Pagination (List Page) Page (List Page)


init : List Page -> Maybe Pagination
init pages =
    case pages of
        [] ->
            Nothing

        x :: xs ->
            Just (Pagination [] x xs)


current : Pagination -> Page
current (Pagination _ current _) =
    current


next : Pagination -> Pagination
next ((Pagination previous current after) as pagination) =
    case after of
        [] ->
            pagination

        n :: remainder ->
            Pagination (current :: previous) n remainder


previous : Pagination -> Pagination
previous ((Pagination previous current after) as pagination) =
    case previous of
        [] ->
            pagination

        newCurrent :: newPrevious ->
            Pagination newPrevious newCurrent (current :: after)


hasNext : Pagination -> Bool
hasNext (Pagination _ _ after) =
    List.isEmpty after


hasPrevious : Pagination -> Bool
hasPrevious (Pagination previous _ _) =
    List.isEmpty previous
