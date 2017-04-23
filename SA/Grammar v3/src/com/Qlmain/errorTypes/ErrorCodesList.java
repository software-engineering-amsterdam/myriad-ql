package com.Qlmain.errorTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sotos on 8/4/2017.
 */
public class ErrorCodesList {
    private List<ErrorCodes> errCodesList;

    public ErrorCodesList() {errCodesList = new ArrayList<>();}

    public void addElem(ErrorCodes code) {errCodesList.add(code);}

    public List<ErrorCodes> getErrorList() {return errCodesList;}
}
