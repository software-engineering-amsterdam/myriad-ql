//package ast.atom;
//
//import ast.Visitor;
//
//public class MoneyAtom extends Atom {
//    private Float value;
//
//    // TODO dont use float for money
//    public MoneyAtom(Float value) {
//        this.value = value;
//    }
//    
//    @Override
//    public Number  getNumber() {
//        return this.value;
//    }
//    
//	@Override
//	public void accept(Visitor v) {
//		v.visit(this);		
//	}
//}