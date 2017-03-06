package com.mcsa.QL;

/**
 * Created by matt on 21/02/2017.
 */
public class Expr {

    public Expr(){

    }

    public class giveValEqual extends Expr {
        private String lhs;
        private Expr rhs;
        public giveValEqual(String lhs, Expr rhs) {
            this.lhs = lhs;
            this.rhs = rhs;
        }
        public String getValEquallhs() { return this.lhs; }
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

    public class StrValue extends Expr {
        private String val;

        public StrValue(String val){
            this.val = val;
        }
        public String getStrValue() { return this.val; }
    }

    public class IdValue extends Expr {
        private String val;

        public IdValue(String val){
            this.val = val;
        }
        public String getIdValue() { return this.val; }
    }

    public class IntValue extends Expr {
        private int val;
        public IntValue(int val){
            this.val = val;
        }
        public int getIntValue() { return this.val; }
    }

    public class DoubleValue extends Expr {
        private double val;

        public DoubleValue(double val){
            this.val = val;
        }
        public double getDoubleValue() { return this.val; }
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

    public class Bool extends Expr {
        private boolean val;
        public Bool(boolean value){
            this.val = value;
        }
        public boolean getBoolVal() { return this.val; }
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
        public String getEqualsign() { return "=="; }
    }

}
