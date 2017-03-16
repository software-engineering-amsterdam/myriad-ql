package com.Qlmain.QL;

/**
 * Created by matt on 21/02/2017.
 */
public class Expr {
    private int line;

    public Expr(){

    }

    public Expr(int line){
        this.line=line;
    }

    public int getLine(){
            return this.line;
    }


    public enum Type {
        INTEGER, MONEY, STRING, BOOLEAN, WRONGTYPE;

    }

    public class giveValEqual extends Expr {
        private Type lhs;
        private Expr rhs;
        public giveValEqual(Type lhs, Expr rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Type getValEquallhs() { return this.lhs; }
        public Expr getValEqualrhs() { return this.rhs; }

    }

    public class Add extends Expr {
        private Expr lhs;
        private Expr rhs;

        public Add(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getAddlhs() { return this.lhs; }
        public Expr getAddrhs() { return this.rhs; }
    }

    public class Sub extends Expr {
        private Expr lhs;
        private Expr rhs;

        public Sub(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getSublhs() { return this.lhs; }
        public Expr getSubrhs() { return this.rhs; }
    }

    public class Mul extends Expr {
        private Expr lhs;
        private Expr rhs;

        public Mul(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getMullhs() { return this.lhs; }
        public Expr getMulrhs() { return this.rhs; }
    }

    public class Div extends Expr {
        private Expr lhs;
        private Expr rhs;

        public Div(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getDivlhs() { return this.lhs; }
        public Expr getDivrhs() { return this.rhs; }
    }

    public class And extends Expr {
        private Expr lhs;
        private Expr rhs;
        public And(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getAndlhs() { return this.lhs; }
        public Expr getAndrhs() { return this.rhs; }
    }

    public class Or extends Expr {
        private Expr lhs;
        private Expr rhs;
        public Or(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getOrlhs() { return this.lhs; }
        public Expr getOrrhs() { return this.rhs; }
    }

    public class Greater extends Expr {
        private Expr lhs;
        private Expr rhs;
        public Greater(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getGreaterlhs() { return this.lhs; }
        public Expr getGreaterrhs() { return this.rhs; }
    }

    public class Smaller extends Expr {
        private Expr lhs;
        private Expr rhs;
        public Smaller(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getSmallerlhs() { return this.lhs; }
        public Expr getSmallerrhs() { return this.rhs; }
    }

    public class GreaterEq extends Expr {
        private Expr lhs;
        private Expr rhs;
        public GreaterEq(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getGreaterEqlhs() { return this.lhs; }
        public Expr getGreaterEqrhs() { return this.rhs; }
    }

    public class SmallerEq extends Expr {
        private Expr lhs;
        private Expr rhs;
        public SmallerEq(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getSmallerEqlhs() { return this.lhs; }
        public Expr getSmallerEqrhs() { return this.rhs; }
    }

    public class Equal extends Expr {
        private Expr lhs;
        private Expr rhs;
        public Equal(Expr lhs, Expr rhs){
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public Expr getEquallhs() { return this.lhs; }
        public Expr getEqualrhs() { return this.rhs; }
    }

    public class TypeBoolean extends Expr {
        private boolean value;
        public TypeBoolean(boolean value) {
            this.value = value;
        }
        public boolean getBooleanValue(){ return this.value;}
        public Type getBooleanType(){ return Type.BOOLEAN;}
    }

    public class IntValue extends Expr {
        private int val;
        public IntValue(String val){
            this.val = Integer.parseInt(val);
        }
        public int getIntValue() { return this.val; }
        public Type getIntType(){ return Type.INTEGER;}
    }

    public class MoneyValue extends Expr {
        private double val;

        public MoneyValue(String val){
            this.val = Double.parseDouble(val);
        }
        public double getMoneyValue() { return this.val; }
        public Type getMoneyType(){ return Type.MONEY;}
    }

    public class SympleTypeValue extends Expr {
        private Type val;

        public SympleTypeValue(Type val){
            this.val = val;
        }
        public Type getSympleTypeValue() { return this.val; }
    }

    public class IdValue extends Expr {
        private String val;

        public IdValue(String val){
            this.val = val;
        }
        public String getIdValue() { return this.val; }
    }

}
