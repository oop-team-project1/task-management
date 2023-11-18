package utils;

import java.util.Arrays;
import java.util.List;

public class TestHelpers {
    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }

    public static String getString(int length) {
        return "x".repeat(length);
    }

}
