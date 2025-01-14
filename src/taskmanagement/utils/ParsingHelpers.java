package taskmanagement.utils;

import taskmanagement.exceptions.InvalidUserInputException;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.models.tasks.enums.story.StoryStatus;

public class ParsingHelpers {
    public static final String NO_SUCH_FEEDBACK_STATUS_ENUM = "None of the enums in FeedbackStatus matches the value %s";
    private static final String INVALID_NUMBER_FIELD_MESSAGE = "Invalid value for %s. Should be a number.";
    public static final String NO_SUCH_BUG_STATUS_ENUM = "None of the enums in BugStatus matches the value %s.";
    public static final String NO_SUCH_STORY_STATUS_ENUM = "None of the enums in StoryStatus matches the value %s.";

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
        try {
            return FeedbackStatus.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_FEEDBACK_STATUS_ENUM, valueToParse));
        }
    }

    public static BugStatus tryParseBugStatus(String valueToParse) {
        try {
            return BugStatus.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_BUG_STATUS_ENUM, valueToParse));
        }
    }

    public static StoryStatus tryParseStoryStatus(String valueToParse) {
        try {
            return StoryStatus.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_STORY_STATUS_ENUM, valueToParse));
        }
    }
}
