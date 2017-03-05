package ast;

import java.util.List;
import java.util.ArrayList;

public class Block extends Node { 
	
	private List<BlockItem> blockItems;
	
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
