package org.uva.taxfree.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogWindow {
    private static LogWindow mInstance;
    public static LogWindow getInstance() {
        if (mInstance == null) {
            mInstance = new LogWindow();
        }
        return mInstance;
    }
    public static void error(String message) {
        LogWindow logWindow = getInstance();
        try {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();

            FileWriter writer = new FileWriter("log.err", true);
            writer.write(getCurrentTimeStamp() + className + "." + methodName + "():" + lineNumber + " - " + message + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }


}
