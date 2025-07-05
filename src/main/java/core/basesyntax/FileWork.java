package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWork {
    public String[] readFromFile(String fileName) {
        //write your code here
        File file = new File(fileName);

        String str = "";
        ArrayList<String> strArray = new ArrayList<String>();
        int value = -1000;
        boolean toLoadWord = false;
        boolean beginingWord = true;

        String[] arrString;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));

            do {

                value = (int) reader.read();

                if ((value == 87 || value == 119) && str.isEmpty() && beginingWord) {
                    toLoadWord = true;
                }

                if ((value < 97 || value > 122) && (value > 90 || value < 64)) {
                    if (!str.isEmpty()) {
                        strArray.add(str.toLowerCase());
                        str = "";
                        toLoadWord = false;
                    }
                    beginingWord = true;
                    continue;
                }

                if (toLoadWord) {
                    str = str + ((char) value);
                }
                beginingWord = false;

            } while (value != -1);

            if (!strArray.isEmpty()) {
                int arraySize = strArray.size();
                arrString = new String[arraySize];
                for (int i = 0;i < arraySize;i++) {
                    arrString[i] = strArray.get(i);
                }
                Arrays.sort(arrString);
            } else {
                arrString = new String[0];
            }

        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file", e);
        }

        return arrString;
    }

}
