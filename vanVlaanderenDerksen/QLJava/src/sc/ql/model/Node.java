package sc.ql.model;

import sc.ql.model.types.Type;

public abstract class Node {
	private Integer lineNumber;
	private Integer charPosition;
	
	public void setPosition(Integer lineNumber, Integer charPosition) {
		this.lineNumber = lineNumber;
		this.charPosition = charPosition;
	}
	
	public String getPosition() {
		return lineNumber.toString()+":"+charPosition.toString();
	}
}