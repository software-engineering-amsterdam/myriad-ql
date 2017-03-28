package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.qls.styleoption.StyleOption;

import java.util.List;

public class QuestionStyle extends StyleDeclaration {

    private final String mQuestionId;

    public QuestionStyle(String questionId, List<StyleOption> styleOptions, SourceInfo sourceInfo) {
        super(styleOptions, sourceInfo);
        mQuestionId = questionId;
    }

    public String getId() {
        // TODO: Remove getter! (used in checking semantics)
        return mQuestionId;
    }
}
