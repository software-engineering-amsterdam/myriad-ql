package org.uva.hatt.taxform.typeChecker.messages.warning;

public class Warning {
    private String msg;

    public Warning(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }
}
