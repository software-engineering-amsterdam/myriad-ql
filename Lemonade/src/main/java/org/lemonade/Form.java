package org.lemonade;

//Maybe everything inherits from the org.lemonade.ASTNode class so we can easily walk through
//the constructed tree?
public class Form extends ASTNode {
    private String identifier;

    public Form(String identifier, int lineNo){
        super(lineNo, null);
        this.identifier = identifier;
    }

    @Override
    public String toString(){
        return this.identifier;
    }
}
