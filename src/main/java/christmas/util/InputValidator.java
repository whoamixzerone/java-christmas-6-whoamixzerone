package christmas.util;

import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private static final int FROM_DAY = 1;
    private static final int TO_DAY = 31;

    public static void validNumber(String input) {
        validBlank(input);
        validNumeric(input);
        validDateRange(input);
    }

    private static void validBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validNumeric(String input) {
        if (isNotDigit(input)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validDateRange(String input) {
        if (isNotBetweenFromOrTo(Number.toInt(input))) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isNotBetweenFromOrTo(int day) {
        return day < FROM_DAY || day > TO_DAY;
    }

    private static boolean isNotDigit(String input) {
        return !NUMBER_REGEX.matcher(input).matches();
    }
}
