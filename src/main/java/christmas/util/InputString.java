package christmas.util;

import java.util.regex.Pattern;

public class InputString {
    private static final Pattern MENU_REGEX = Pattern.compile("([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*)-(\\d*)");

    public static String[] menuGroupSplit(String input) {
        return input.split(",");
    }
}
