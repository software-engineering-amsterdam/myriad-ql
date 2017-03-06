package sc.ql.model.form_elements;

import java.util.List;
import sc.ql.model.NodeVisitor;
import sc.ql.model.Node;
import sc.ql.model.ConditionalBlock;

public class IfStatement implements Node {
	private final List<ConditionalBlock> conditional_blocks;
	private final List<Node> form_elements;
	
	public IfStatement(List<ConditionalBlock> conditional_blocks, List<Node> form_elements) {
		this.conditional_blocks = conditional_blocks;
		this.form_elements = form_elements;
	}
	
	public List<ConditionalBlock> getConditionalBlocks() {
		return this.conditional_blocks;
	}
	
	public List<Node> getFormElements() {
		return this.form_elements;
	}

	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}