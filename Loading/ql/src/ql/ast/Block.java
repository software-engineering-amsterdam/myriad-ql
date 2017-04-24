package ql.ast;

import java.util.Iterator;
import java.util.List;

public class Block extends Node implements Iterable<BlockItem> { 
	
	private final List<BlockItem> blockItems;
	
	public Block(List<BlockItem> blockItems, int line) {
		super(line);
		this.blockItems = blockItems;
	}

	public void accept(FormVisitor v) {
		v.visit(this);
		
	}

	@Override
	public Iterator<BlockItem> iterator() {
		return blockItems.iterator();
	}
}
