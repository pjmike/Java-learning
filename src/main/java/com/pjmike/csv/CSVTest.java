package com.pjmike.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pjmike
 * @create 2018-10-13 19:16
 */
public class CSVTest {
    public static void main(String[] args) {
        File file = new File("F://test.csv");
        try(BufferedReader reader= new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line = "";
            List<String> stringList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");
                System.out.println(item[9]);
                stringList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
