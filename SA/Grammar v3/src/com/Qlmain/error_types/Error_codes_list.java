package com.Qlmain.error_types;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sotos on 8/4/2017.
 */
public final class Error_codes_list {
    private static List<ErrorCodes> errCodesList;
    public Error_codes_list() {
        errCodesList = new ArrayList<>();
    }
    public void add_elem(ErrorCodes code) {
        errCodesList.add(code);
    }
    public static List<ErrorCodes> get_error_list() {
        return errCodesList;
    }
}
