package christmas.validate;

import christmas.constants.Promotion;
import christmas.util.InputNumber;

import java.util.regex.Pattern;

public class ValidateDay {
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]+$");

    private static final String INVALID_DAY = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static void blank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_DAY);
        }
    }

    public static void numeric(String input) {
        if (isNotDigit(input)) {
            throw new IllegalArgumentException(INVALID_DAY);
        }
    }

    private static boolean isNotDigit(String input) {
        return !NUMBER_REGEX.matcher(input).matches();
    }

    public static void range(String input) {
        if (isNotBetweenFromOrTo(InputNumber.toInt(input))) {
            throw new IllegalArgumentException(INVALID_DAY);
        }
    }

    private static boolean isNotBetweenFromOrTo(int day) {
        return day < Promotion.FROM_DAY || day > Promotion.TO_DAY;
    }
}
