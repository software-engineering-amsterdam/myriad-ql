package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.astnodes.visitors.StyleSheetVisitor;

/**
 * Created by LGGX on 02-Mar-17.
 */
public abstract class AbstractSection extends Node{

    public AbstractSection(LineNumber lineNumber) {
        super(lineNumber);
    }

    public abstract <T> T accept(StyleSheetVisitor<T> visitor);
}
