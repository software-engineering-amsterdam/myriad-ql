module QLS.Parser.Configuration exposing (configuration)

import Combine exposing (Parser, braces, choice, or, sepBy, string, (<$>), (*>))
import QLS.AST exposing (Configuration(SingleConfig, MultiConfig), ConfigItem(StyleConfig, WidgetConfig))
import QLS.Parser.Style exposing (style)
import QLS.Parser.Widget exposing (widget)
import Combine.Extra exposing (whitespace1, trimmed)


configuration : Parser state Configuration
configuration =
    or
        (SingleConfig <$> configItem)
        (MultiConfig <$> configItemBlock)


configItemBlock : Parser state (List ConfigItem)
configItemBlock =
    braces (trimmed (sepBy whitespace1 configItem))


configItem : Parser state ConfigItem
configItem =
    choice
        [ StyleConfig <$> style
        , WidgetConfig <$> (string "widget" *> whitespace1 *> widget)
        ]
