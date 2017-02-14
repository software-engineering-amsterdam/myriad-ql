//package ast.atom;
//
//import ast.Visitor;
//
//public class DecimalAtom extends Atom {
//    
//	private Float value;
//
//    public DecimalAtom(Float value) {
//        this.value = value;
//    }
//
//    @Override
//    public Number getNumber() {
//        return this.value;
//    }
//    
//	@Override
//	public void accept(Visitor v) {
//		v.visit(this);		
//	}
//}