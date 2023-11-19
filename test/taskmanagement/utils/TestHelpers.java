package taskmanagement.utils;

import java.util.Arrays;
import java.util.List;

public class TestHelpers
{
    public static List<String> getList(int wantedSize) {
        return Arrays.asList(new String[wantedSize]);
    }

    public static String getString(int wantedSize) {
        return "x".repeat(wantedSize);
    }
}
