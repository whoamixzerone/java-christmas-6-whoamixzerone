package christmas.constants;

import java.util.Arrays;

public enum MenuType {
    MUSHROOM_CREATE_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바베큐립", 54_000),
    SEA_FOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    COKE_ZERO("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private String foodName;
    private long amount;

    MenuType(String foodName, long amount) {
        this.foodName = foodName;
        this.amount = amount;
    }

    public static boolean isNotMenuType(String menuType) {
        return !Arrays.stream(MenuType.values())
                .anyMatch(menu -> menuType.equals(menu.getFoodName()));
    }

    public String getFoodName() {
        return foodName;
    }

    public long getAmount() {
        return amount;
    }
}
