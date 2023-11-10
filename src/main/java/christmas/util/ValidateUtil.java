package christmas.util;

import christmas.model.Order;
import christmas.model.discount.Discount;
import christmas.model.menu.Appetizer;
import christmas.model.menu.Beverage;
import christmas.model.menu.Dessert;
import christmas.model.menu.Main;
import christmas.model.menu.Menu;
import christmas.view.ErrorMessage;
import christmas.view.InfoMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateUtil {

    public static final List<String> menuNames = new ArrayList<>();
    public static int countMenu = 0;

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ErrorMessage.getInvalidDate();
            throw new NumberFormatException();
        }
    }

    public static void validateRangeOfDate(int input) {
        try {
            if (input > 31 || input < 1) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            ErrorMessage.getInvalidDate();
            throw new IllegalArgumentException();
        }
    }

    public static Order parseOrder(String input) {
        try {
            String[] parseOrder = input.split(",");
            Order order = new Order(createOrder(parseOrder));
            checkMinPrice(order);
            return order;
        } catch (IllegalArgumentException e) {
            menuNames.clear();
            throw new IllegalArgumentException();
        }
    }

    private static List<Map<Menu, Integer>> createOrder(String[] parseOrder) {
        List<Map<Menu, Integer>> orderList = new ArrayList<>();
        for (int i = 0; i < parseOrder.length; i++) {
            String[] menuAndCount = parseOrder[i].split("-");
            String menuName = menuAndCount[0];
            int count = validateCount(menuAndCount[1]);
            checkDuplicateMenu(menuName);
            countMenuAndValidate(count);
            orderList.add(Map.of(getMenuByName(menuName), count));
        }
        checkOnlyBeverage(orderList);
        return orderList;
    }

    private static void checkMinPrice(Order order) {
        if (order.getTotalPrice() < 10000) {
            Discount.EVENT_FLAG = false;
            InfoMessage.getUnderPrice();
        }
    }

    private static void checkDuplicateMenu(String name) {
        if (menuNames.contains(name)) {
            ErrorMessage.getInvalidOrder();
            throw new IllegalArgumentException();
        }
        menuNames.add(name);
    }

    private static void countMenuAndValidate(int count) {
        if (countMenu + count >= 20) {
            countMenu = 0;
            ErrorMessage.getInvalidCountOrder();
            throw new IllegalArgumentException();
        }
        countMenu += count;
    }

    private static void checkOnlyBeverage(List<Map<Menu, Integer>> orderList) {
        if (findBeverage(orderList) && (countAllMenu(orderList) == countBeverageMenu(orderList))) {
            ErrorMessage.getInvalidMenuOrder();
            throw new IllegalArgumentException();
        }
    }

    private static boolean findBeverage(List<Map<Menu, Integer>> orderList) {
        return orderList.stream()
                .flatMap(order -> order.keySet().stream())
                .anyMatch(Beverage.class::isInstance);
    }

    private static int countAllMenu(List<Map<Menu, Integer>> orderList) {
        return (int) orderList.stream()
                .mapToLong(order -> order.keySet().size())
                .sum();
    }

    private static int countBeverageMenu(List<Map<Menu, Integer>> orderList) {
        return (int) orderList.stream()
                .flatMap(order -> order.keySet().stream())
                .filter(Beverage.class::isInstance)
                .count();
    }

    private static int validateCount(String input) {
        try {
            int count = Integer.parseInt(input);
            if (count < 1) {
                throw new IllegalArgumentException();
            }
            return count;
        } catch (IllegalArgumentException e) {
            ErrorMessage.getInvalidOrder();
            throw new IllegalArgumentException();
        }
    }

    private static Menu getMenuByName(String name) {
        if (name.equals("양송이스프") || name.equals("타파스") || name.equals("시저샐러드")) {
            return Appetizer.valueOf(name);
        } else if (name.equals("티본스테이크") || name.equals("바비큐립") || name.equals("해산물파스타") || name.equals("크리스마스파스타")) {
            return Main.valueOf(name);
        } else if (name.equals("초코케이크") || name.equals("아이스크림")) {
            return Dessert.valueOf(name);
        } else if (name.equals("제로콜라") || name.equals("레드와인") || name.equals("샴페인")) {
            return Beverage.valueOf(name);
        }
        ErrorMessage.getInvalidOrder();
        throw new IllegalArgumentException();
    }

}
