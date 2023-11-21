package taskmanagement.utils;

import taskmanagement.exceptions.InvalidUserInputException;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;

public class ParsingHelpers
{
    public static final String NO_SUCH_FEEDBACKSTATUS_ENUM = "None of the enums in FeedbackStatus matches the value %s";
    private static final String INVALID_NUMBER_FIELD_MESSAGE = "Invalid value for %s. Should be a number.";

    public static double tryParseDouble(String valueToParse, String parameterName) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(String.format(INVALID_NUMBER_FIELD_MESSAGE, parameterName));
        }
    }

    public static int tryParseInteger(String valueToParse, String parameterName) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(String.format(INVALID_NUMBER_FIELD_MESSAGE, parameterName));
        }
    }

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

    public static FeedbackStatus tryParseFeedbackStatus(String valueToParse) {
        try{
            return FeedbackStatus.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_FEEDBACKSTATUS_ENUM, valueToParse));
        }
    }
}
