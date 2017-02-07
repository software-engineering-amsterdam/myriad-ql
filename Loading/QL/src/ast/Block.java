package ast;

public class Block extends Node {
	
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public Block(String content) {
		this.content = content;
	}
	
	void print() {
		System.out.println(content);
	}
	
}
