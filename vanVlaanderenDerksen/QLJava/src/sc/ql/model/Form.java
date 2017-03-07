package sc.ql.model;

import java.util.List;

public class Form implements Node {
	private final List<Node> form_elements;
	
	public Form(List<Node> form_elements) {
		this.form_elements = form_elements; 
	}
	
	public List<Node> getFormElements() {
        return this.form_elements;
    }
	
	public Node getFormElement(Integer index) {
        return this.form_elements.get(index);
    }

	@Override
	public <T> T accept(NodeVisitor<T> visitor) { return null; }
}