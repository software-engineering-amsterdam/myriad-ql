package com.mcsa;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sotos on 13/2/2017.
 */
class OpenAndReadTheQl {

    public String QlRead () {
        String fileInString = "";
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileInString = fileInString.concat(line);
                // process the line.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInString;
    }

}
