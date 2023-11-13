package christmas.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public enum MenuCategory {
    APPETIZER("에피타이저", Arrays.asList(Menu.MUSHROOM_CREATE_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(Menu.T_BONE_STEAK, Menu.BBQ_RIBS, Menu.SEA_FOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    BEVERAGE("음료", Arrays.asList(Menu.COKE_ZERO, Menu.RED_WINE, Menu.CHAMPAGNE));

    private String category;
    private List<Menu> foods;

    MenuCategory(String category, List<Menu> foods) {
        this.category = category;
        this.foods = foods;
    }

    public static boolean isOnlyBeverage(Set<Menu> menus) {
        List<Menu> beverages = MenuCategory.BEVERAGE.getFoods();

        return menus.stream().noneMatch(menu -> !beverages.contains(menu));
    }

    public String getCategory() {
        return category;
    }

    public List<Menu> getFoods() {
        return foods;
    }
}
