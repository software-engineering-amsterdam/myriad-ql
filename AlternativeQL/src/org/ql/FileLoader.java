package org.ql;

import java.io.*;
import java.nio.file.Files;

public class FileLoader {
    public String getSourceFromResource(String resourcePath) {
        try {
            InputStream fileStream = new FileInputStream(new File(resourcePath));
            Reader reader = new BufferedReader(new InputStreamReader(fileStream));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();

        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        } catch(Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
