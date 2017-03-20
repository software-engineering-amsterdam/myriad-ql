package QL.ast;

import java.util.List;

public class Block extends Node { 
	
	private final List<BlockItem> blockItems;
	
	public List<BlockItem> getBlockItems() {
		return blockItems;
	}
	
	public Block(List<BlockItem> blockItems, int line) {
		super(line);
		this.blockItems = blockItems;
	}

	public void accept(FormVisitor v) {
		v.visit(this);
		
	}
}
