module QLS.AST exposing (..)

import QL.AST exposing (Location, ValueType, Id)


type alias StyleSheet =
    { id : Id
    , pages : List Page
    }


type Page
    = Page String (List Section) (List DefaultValueConfig)


type Section
    = SingleChildSection String SectionChild
    | MultiChildSection String (List SectionChild) (List DefaultValueConfig)


type SectionChild
    = SubSection Section
    | Field Question


type Question
    = Question Id
    | ConfiguredQuestion Id Configuration


type DefaultValueConfig
    = DefaultValueConfig Location ValueType Configuration


type Configuration
    = SingleConfig Widget
    | MultiConfig (List Style) (Maybe Widget)


type Style
    = Width Int
    | Font String
    | FontSize Int
    | Color String


type Widget
    = Radio (List String)
    | Dropdown (List String)
    | Spinbox
    | Checkbox
    | Text
    | Slider SliderArgs


type SliderArgs
    = SliderMax Int
    | SliderMinMax Int Int
