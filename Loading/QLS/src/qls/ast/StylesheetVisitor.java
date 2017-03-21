package qls.ast;


public interface StylesheetVisitor {

    void visit(Stylesheet stylesheet);
    void visit(Page page);
    void visit(PageWithDefault page);
    void visit(Section section);
    void visit(SectionWithDefault section);
    void visit(Question question);
    void visit(QuestionWithWidget questionWithDefault);
	
}
