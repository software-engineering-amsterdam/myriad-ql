package org.uva.taxfree.ql.util;

public class FileUtility {

    public static String replaceExtension(String fileName, String newExtension) {
        int dotPos = fileName.indexOf(".");
        if (-1 != dotPos) {
            fileName = fileName.substring(0, dotPos);
        }
        return fileName + newExtension;
    }
}
