package QL.ui;

import QL.ast.*;
import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.UnknownType;
import QL.ui.field.CheckBoxF;
import QL.ui.field.Field;
import QL.ui.field.NumberF;
import QL.ui.field.TextF;
import QL.value.BoolValue;
import QL.value.IntegerValue;
import QL.value.StringValue;

public class CollectFields implements FormVisitor, TypeVisitor<Field> {
    private final Notifier notifier;
    private String current;
    private final Environment environment;

    CollectFields(Notifier notifier, Environment environment) {
        this.notifier = notifier;
        this.environment = environment;
        this.current = "";
    }

    @Override
    public void visit(Form form) {
        form.getBlock().accept(this);
    }

    @Override
    public void visit(Block block) {
        for (BlockItem blockItem : block.getBlockItems()) {
            blockItem.accept(this);
        }
    }

    @Override
    public void visit(BlockItem blockItem) {
        blockItem.accept(this);
    }

    @Override
    public void visit(Question question) {
        current = question.getName();
        Field field = question.getType().accept(this);
        environment.addField(current, field);
    }

    @Override
    public void visit(Statement statement) {
        statement.getBlock().accept(this);
    }

    @Override
    public void visit(IfElseStatement statement) {
        statement.getBlock().accept(this);
        statement.getElseBlock().accept(this);
    }

    @Override
    public void visit(ComputedQuestion question) {
        current = question.getName();
        Field field = question.getType().accept(this);
        environment.addField(current, field);
    }

    @Override
    public Field visit(BooleanType type) {
        return new CheckBoxF(current, notifier, new BoolValue());
    }

    @Override
    public Field visit(IntegerType type) {
        return new NumberF(current, notifier, new IntegerValue());
    }

    @Override
    public Field visit(StringType type) {
        return new TextF(current, notifier, new StringValue());
    }

    @Override
    public Field visit(UnknownType type) {
        throw new RuntimeException("Should never visit an unknown type when collecting fields");
    }
}
