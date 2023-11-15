package christmas.common.enums;

import christmas.common.CustomException;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),
    TBONE_STEAK("티본스테이크", 55_000, Category.MAIN_DISH),
    BBQ_RIBS("바비큐립", 54_000, Category.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN_DISH),
    CHOCOLATE_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),
    ZERO_COLA("제로콜라", 3_000, Category.BEVERAGE),
    RED_WINE("레드와인", 60_000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE);

    private final String name;
    private final int price;

    private final Category category; // 카테고리 필드 추가

    // 생성자에서 카테고리도 설정
    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static int getPriceByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .map(Menu::getPrice)
                .orElseThrow(CustomException::new);
    }

    public static String getCategoryByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst().orElseThrow(CustomException::new).category.name();
    }

    public static boolean hasName(String name) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.getName().equals(name));
    }

    public static boolean isBeverage(String name) {
        return getCategoryByName(name).equals(Category.BEVERAGE.name());
    }

    public static boolean isMainDish(String name) {
        return getCategoryByName(name).equals(Category.MAIN_DISH.name());
    }

    public static boolean isDessert(String name) {
        return getCategoryByName(name).equals(Category.DESSERT.name());
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    private enum Category {
        APPETIZER, MAIN_DISH, DESSERT, BEVERAGE,
    }
}

