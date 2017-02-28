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

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
}
