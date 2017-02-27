package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.BooleanType;

import java.util.ArrayList;
import java.util.List;

public class FormBuilder {
    private Identifier name;
    private List<Statement> statements = new ArrayList<>();

    public Form build() {
        return new Form(this.name, this.statements);
    }

    public FormBuilder setName(String name) {
        this.name = new Identifier(name);

        return this;
    }

    public FormBuilder addStatement(Statement statement) {
        statements.add(statement);

        return this;
    }

    public FormBuilder getDefault() {
        this.name = new Identifier("example");
        this.statements.add(new Question(new Identifier("example"), new QuestionText("example question?"), new BooleanType(), null));

        return this;
    }

}
