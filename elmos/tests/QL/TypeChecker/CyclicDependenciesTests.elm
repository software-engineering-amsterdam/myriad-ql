module QL.TypeChecker.CyclicDependenciesTests exposing (all)

import Expect
import QL.AST exposing (..)
import QL.ASTTestUtil exposing (emptyLoc)
import QL.TypeChecker.CyclicDependencies exposing (check)
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(DependencyCycle))
import Test exposing (Test, describe, test)


all : Test
all =
    describe "CyclicDependencies"
        [ test "should find cycle for a self referencing ComputedQuestion" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ ComputedQuestion "label" ( "x", emptyLoc ) StringType (Var ( "x", emptyLoc ))
                        ]
                    )
                    |> Expect.equal [ Error <| DependencyCycle [ "x", "x" ] ]
        , test "should find simple DependencyCycle between three fields and return cycle in order of references" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ ComputedQuestion "label" ( "a", emptyLoc ) StringType (Var ( "t", emptyLoc ))
                        , ComputedQuestion "label" ( "t", emptyLoc ) StringType (Var ( "o", emptyLoc ))
                        , ComputedQuestion "label" ( "o", emptyLoc ) StringType (Var ( "a", emptyLoc ))
                        ]
                    )
                    |> Expect.equal [ Error <| DependencyCycle [ "a", "t", "o", "a" ] ]
        , test "should find multiple DependencyCycles" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ ComputedQuestion "label"
                            ( "x", emptyLoc )
                            StringType
                            (BinaryExpression (Comparison Equal)
                                emptyLoc
                                (Var ( "y", emptyLoc ))
                                (Var ( "p", emptyLoc ))
                            )
                        , ComputedQuestion "label"
                            ( "y", emptyLoc )
                            StringType
                            (BinaryExpression (Comparison Equal)
                                emptyLoc
                                (Var ( "z", emptyLoc ))
                                (Var ( "a", emptyLoc ))
                            )
                        , ComputedQuestion "label" ( "z", emptyLoc ) StringType (Var ( "x", emptyLoc ))
                        , ComputedQuestion "label" ( "p", emptyLoc ) StringType (Var ( "y", emptyLoc ))
                        ]
                    )
                    |> Expect.equal
                        [ Error <| DependencyCycle [ "x", "p", "y", "z", "x" ]
                        , Error <| DependencyCycle [ "x", "y", "z", "x" ]
                        ]
        , test "should find DependencyCycle and return the first occurence of the cycle in order of the cycle" <|
            \() ->
                check
                    (Form
                        ( "", emptyLoc )
                        [ ComputedQuestion "label" ( "x", emptyLoc ) StringType (Var ( "y", emptyLoc ))
                        , ComputedQuestion "label" ( "y", emptyLoc ) StringType (Var ( "a", emptyLoc ))
                        , ComputedQuestion "label"
                            ( "a", emptyLoc )
                            StringType
                            (BinaryExpression (Comparison Equal)
                                emptyLoc
                                (Var ( "x", emptyLoc ))
                                (Var ( "y", emptyLoc ))
                            )
                        ]
                    )
                    |> Expect.equal [ Error <| DependencyCycle [ "x", "y", "a", "x" ], Error <| DependencyCycle [ "y", "a", "y" ] ]
        ]
