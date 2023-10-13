package common;

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
}
