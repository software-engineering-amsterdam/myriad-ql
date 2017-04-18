package org.uva.taxfree.ql.model;

public class SourceInfo {
    private final int mStartLineNumber;
    private final int mStartColumn;
    private final int mEndLineNumber;
    private final int mEndColumn;

    public SourceInfo(int startLineNumber, int startColumn, int endLineNumber, int endColumn) {
        mStartLineNumber = startLineNumber;
        mStartColumn = startColumn;
        mEndLineNumber = endLineNumber;
        mEndColumn = endColumn;
    }

    public String sourceString() {
        return "(" +
                mStartLineNumber + ":" + mStartColumn +
                "-" +
                mEndLineNumber + ":" + mEndColumn +
                "): ";
    }
}
