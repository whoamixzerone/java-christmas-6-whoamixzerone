package christmas.constants;

import java.util.Arrays;
import java.util.List;

public enum MenuCategory {
    APPETIZER("에피타이저", Arrays.asList(MenuType.MUSHROOM_CREATE_SOUP, MenuType.TAPAS, MenuType.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(MenuType.T_BONE_STEAK, MenuType.BBQ_RIBS, MenuType.SEA_FOOD_PASTA, MenuType.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(MenuType.CHOCOLATE_CAKE, MenuType.ICE_CREAM)),
    BEVERAGE("음료", Arrays.asList(MenuType.COKE_ZERO, MenuType.RED_WINE, MenuType.CHAMPAGNE));

    private String category;
    private List<MenuType> foodName;

    MenuCategory(String category, List<MenuType> foodName) {
        this.category = category;
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }
}
