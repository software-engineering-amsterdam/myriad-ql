package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.StyleOption;

import java.util.List;

public class DefaultStyle extends StyleDeclaration {
    private final Type mType;

    public DefaultStyle(Type type, List<StyleOption> styleOptions, SourceInfo sourceInfo) {
        super(styleOptions, sourceInfo);
        mType = type;
    }

    public boolean isOneOf(List<Type> types) {
        return types.contains(mType);
    }

}
