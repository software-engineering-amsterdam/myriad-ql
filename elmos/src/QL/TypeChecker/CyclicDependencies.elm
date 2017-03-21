module QL.TypeChecker.CyclicDependencies exposing (cyclicDependencies)

import DictList exposing (DictList)
import List.Extra as List
import QL.AST exposing (Expression, Form, Id)
import QL.AST.Collectors as Collectors
import QL.TypeChecker.Messages exposing (Message(Error), ErrorMessage(DependencyCycle))
import Set exposing (Set)


type alias DependencyTable =
    DictList String (Set String)


type alias DependencyEntry =
    ( String, Set String )


type alias DependencyCycle =
    List String


cyclicDependencies : Form -> List Message
cyclicDependencies form =
    let
        dependencyTable =
            Collectors.collectComputedQuestions form
                |> List.map extractDependencies
                |> toDependencyTable
    in
        DictList.keys dependencyTable
            |> List.concatMap (asCyclicDependencies [] dependencyTable)
            |> List.uniqueBy (Set.fromList >> toString)
            |> List.map (Error << DependencyCycle)


extractDependencies : ( Id, Expression ) -> DependencyEntry
extractDependencies ( ( name, _ ), computation ) =
    ( name, Collectors.collectQuestionReferences computation |> uniqueVarNames )


asCyclicDependencies : List String -> DependencyTable -> String -> List DependencyCycle
asCyclicDependencies visited dependencyTable currentVar =
    if List.member currentVar visited then
        [ visited ++ [ currentVar ] ]
    else
        dependenciesOf currentVar dependencyTable
            |> Set.map (asCyclicDependencies (visited ++ [ currentVar ]) dependencyTable)
            |> Set.toList
            |> List.concat


dependenciesOf : String -> DependencyTable -> Set String
dependenciesOf name table =
    DictList.get name table
        |> Maybe.withDefault Set.empty


{-|
Merge dependency entries.
Dict.fromList is not sufficient due to computedQuestions that occur in both the if and the else clause
-}
toDependencyTable : List DependencyEntry -> DependencyTable
toDependencyTable entries =
    let
        updateDependencyTable ( name, dependencies ) result =
            DictList.update
                name
                (Maybe.withDefault Set.empty >> Set.union dependencies >> Just)
                result
    in
        List.foldr updateDependencyTable DictList.empty entries


uniqueVarNames : List Id -> Set String
uniqueVarNames =
    List.map Tuple.first >> Set.fromList
