module QLS.AST exposing (..)

import AST exposing (ValueType)


type alias Stylesheet =
    { name : String
    , pages : List Page
    }


type alias Page =
    { name : String
    , sections : List Section
    , defaults : List Default
    }


type alias Section =
    { name : String
    , childen : List SectionChild
    }


type SectionChild
    = SubSection Section
    | Field Question


type alias Question =
    { id : String
    , config : List Configuration
    }


type alias Default =
    { valueType : ValueType
    , config : List Configuration
    }


type Configuration
    = Width Int
    | Font String
    | FontSize Int
    | Color String
    | Widget WidgetConfig


type WidgetConfig
    = Radio (List String)
    | SpinBox
    | Checkbox
