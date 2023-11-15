package christmas.constants;

import java.util.*;

public enum Menu {
    MUSHROOM_CREATE_SOUP("양송이수프", 6_000L),
    TAPAS("타파스", 5_500L),
    CAESAR_SALAD("시저샐러드", 8_000L),
    T_BONE_STEAK("티본스테이크", 55_000L),
    BBQ_RIBS("바비큐립", 54_000L),
    SEA_FOOD_PASTA("해산물파스타", 35_000L),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000L),
    CHOCOLATE_CAKE("초코케이크", 15_000L),
    ICE_CREAM("아이스크림", 5_000L),
    COKE_ZERO("제로콜라", 3_000L),
    RED_WINE("레드와인", 60_000L),
    CHAMPAGNE("샴페인", 25_000L),
    EMPTY("없음", 0L);

    private String food;
    private long amount;

    Menu(String food, long amount) {
        this.food = food;
        this.amount = amount;
    }

    public static Menu findByMenu(String order) {
        return Arrays.stream(Menu.values())
                .filter(menu -> order.equals(menu.getFood()))
                .findAny()
                .orElse(EMPTY);
    }

    public String getFood() {
        return food;
    }

    public long getAmount() {
        return amount;
    }
}
