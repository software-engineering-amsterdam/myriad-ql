package com.Qlmain.error_types.error_codes;

import com.Qlmain.error_types.ErrorCodes;

/**
 * Created by sotos on 8/4/2017.
 */
public class Wrong_type extends ErrorCodes {
    private String err_name;
    public Wrong_type(String name) {
        err_name = name;
    }

    public String get_error_code() {
        return err_name;
    }
}