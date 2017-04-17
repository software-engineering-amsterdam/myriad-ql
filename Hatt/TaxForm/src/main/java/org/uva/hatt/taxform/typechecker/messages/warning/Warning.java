package org.uva.hatt.taxform.typechecker.messages.warning;

public class Warning {
    private final String msg;

    Warning(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }
}
