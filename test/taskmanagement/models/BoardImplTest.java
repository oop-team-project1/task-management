package taskmanagement.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taskmanagement.models.contracts.Board;
import taskmanagement.utils.TestHelpers;

public class BoardImplTest {

    public static final String VALID_BOARD_NAME = TestHelpers.getString(BoardImpl.NAME_MIN_LENGTH + 1);
    public static final String INVALID_BOARD_NAME = TestHelpers.getString(BoardImpl.NAME_MAX_LENGTH + 1);

    @Test
    public void constructor_Should_ThrowException_When_NameLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BoardImpl(INVALID_BOARD_NAME));
    }


    public static Board initializeBoard() {
        return new BoardImpl(VALID_BOARD_NAME);
    }
}
