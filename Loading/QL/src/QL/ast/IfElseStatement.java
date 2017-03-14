package QL.ast;

import QL.ast.expression.Expression;

public class IfElseStatement extends Statement {

	private Block elseBlock;

	public IfElseStatement(Expression expression, Block block, Block elseBlock, int line) {
		super(expression, block, line);
		this.elseBlock = elseBlock;
	}

	public Block getElseBlock() {
		return elseBlock;
	}

	@Override
	public void accept(FormVisitor v) {
		v.visit(this);
	}
}
