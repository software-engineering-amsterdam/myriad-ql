module QLS.AST exposing (..)

import AST as QL exposing (ValueType, Id)


type alias StyleSheet =
    { id : Id
    , pages : List Page
    }


type alias Page =
    { name : String
    , sections : List Section
    , defaults : List DefaultValueConfig
    }


type Section
    = SingleChildSection String SectionChild
    | MultiChildSection String (List SectionChild)


type SectionChild
    = SubSection Section
    | Field Question
    | Config DefaultValueConfig


type Question
    = Question Id
    | ConfiguredQuestion Id Configuration


type DefaultValueConfig
    = DefaultValueConfig ValueType Configuration


type Configuration
    = SingleConfig ConfigItem
    | MultiConfig (List ConfigItem)


type ConfigItem
    = StyleConfig Style
    | WidgetConfig Widget


type Style
    = Width Int
    | Font String
    | FontSize Int
    | Color String


type Widget
    = Radio (List String)
    | Spinbox
    | Checkbox
