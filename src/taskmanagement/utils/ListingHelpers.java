package taskmanagement.utils;

import taskmanagement.models.tasks.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {

   // public static final String JOIN_DELIMITER = "####################";

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.print());
        }

        return String.join( System.lineSeparator(), result).trim();
    }
}
