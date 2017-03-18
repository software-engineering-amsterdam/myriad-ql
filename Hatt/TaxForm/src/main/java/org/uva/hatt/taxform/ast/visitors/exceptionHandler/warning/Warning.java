package org.uva.hatt.taxform.ast.visitors.exceptionHandler.warning;

public class Warning {
    private String msg;

    public Warning(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }
}
