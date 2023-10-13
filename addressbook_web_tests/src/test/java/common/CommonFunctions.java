package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            result.append((char) ('a' + random.nextInt(26)));
        }
        return result.toString();
    }

    public static String randomFile(String dir) {
        String[] fileNames = new File(dir).list();
        int index = new Random().nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }
}
