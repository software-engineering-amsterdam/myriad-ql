module QLS.AST exposing (..)

import AST as QL exposing (ValueType, Id)


type alias Stylesheet =
    { name : Id
    , pages : List Page
    }


type alias Page =
    { name : String
    , pageChildren : List PageChild
    }


type PageChild
    = PageSection Section
    | PageConfig Configuration


type Section
    = SingleChildSection String SectionChild
    | MultiChildSection String (List SectionChild)


type SectionChild
    = SubSection Section
    | Field Question
    | Config Configuration


type Question
    = Question Id Configuration


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
