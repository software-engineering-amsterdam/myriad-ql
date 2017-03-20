module QL.TypeChecker.CyclicDependenciesTests exposing (all)

import Expect
import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import QL.TypeChecker.CyclicDependencies exposing (cyclicDependencies)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(DependencyCycle))
import Test exposing (Test, describe, test)


all : Test
all =
    describe "CyclicDependencies"
        [ test "should find cycle for a self referencing ComputedField" <|
            \() ->
                cyclicDependencies
                    (Form
                        ( "", emptyLoc )
                        [ ComputedField "label" ( "x", emptyLoc ) StringType (Var ( "x", emptyLoc ))
                        ]
                    )
                    |> Expect.equal [ Error <| DependencyCycle [ "x", "x" ] ]
        , test "should find simple DependencyCycle between three fields and return cycle in order of references" <|
            \() ->
                cyclicDependencies
                    (Form
                        ( "", emptyLoc )
                        [ ComputedField "label" ( "a", emptyLoc ) StringType (Var ( "t", emptyLoc ))
                        , ComputedField "label" ( "t", emptyLoc ) StringType (Var ( "o", emptyLoc ))
                        , ComputedField "label" ( "o", emptyLoc ) StringType (Var ( "a", emptyLoc ))
                        ]
                    )
                    |> Expect.equal [ Error <| DependencyCycle [ "a", "t", "o", "a" ] ]
        , test "should find multiple DependencyCycles" <|
            \() ->
                cyclicDependencies
                    (Form
                        ( "", emptyLoc )
                        [ ComputedField "label"
                            ( "x", emptyLoc )
                            StringType
                            (ComparisonExpression Equal
                                emptyLoc
                                (Var ( "y", emptyLoc ))
                                (Var ( "p", emptyLoc ))
                            )
                        , ComputedField "label"
                            ( "y", emptyLoc )
                            StringType
                            (ComparisonExpression Equal
                                emptyLoc
                                (Var ( "z", emptyLoc ))
                                (Var ( "a", emptyLoc ))
                            )
                        , ComputedField "label" ( "z", emptyLoc ) StringType (Var ( "x", emptyLoc ))
                        , ComputedField "label" ( "p", emptyLoc ) StringType (Var ( "y", emptyLoc ))
                        ]
                    )
                    |> Expect.equal
                        [ Error <| DependencyCycle [ "x", "p", "y", "z", "x" ]
                        , Error <| DependencyCycle [ "x", "y", "z", "x" ]
                        ]
        , test "should find DependencyCycle and return the first occurence of the cycle in order of the cycle" <|
            \() ->
                cyclicDependencies
                    (Form
                        ( "", emptyLoc )
                        [ ComputedField "label" ( "x", emptyLoc ) StringType (Var ( "y", emptyLoc ))
                        , ComputedField "label" ( "y", emptyLoc ) StringType (Var ( "a", emptyLoc ))
                        , ComputedField "label"
                            ( "a", emptyLoc )
                            StringType
                            (ComparisonExpression Equal
                                emptyLoc
                                (Var ( "x", emptyLoc ))
                                (Var ( "y", emptyLoc ))
                            )
                        ]
                    )
                    |> Expect.equal [ Error <| DependencyCycle [ "x", "y", "a", "x" ], Error <| DependencyCycle [ "y", "a", "y" ] ]
        ]
