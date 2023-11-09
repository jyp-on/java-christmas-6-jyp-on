package christmas.util;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Beverage;
import christmas.model.menu.Dessert;
import christmas.model.menu.Main;
import christmas.model.menu.Menu;
import christmas.view.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidateUtil {
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

    public static List<Map<Menu, Integer>> parseOrder(String input) {
        try {
            String[] parseOrder = input.split(",");
            List<Map<Menu, Integer>> orderList = new ArrayList<>();
            for (int i = 0; i < parseOrder.length; i++) {
                String[] menuAndCount = parseOrder[i].split("-");
                int count = validateCount(menuAndCount[1]);
                orderList.add(Map.of(getMenuByName(menuAndCount[0]), count));
            }
            return orderList;
        } catch (IllegalArgumentException e) {
            ErrorMessage.getInvalidOrder();
            throw new IllegalArgumentException();
        }
    }

    public static int validateCount(String input) {
        int count = convertToInt(input);
        if (count < 1) {
            throw new IllegalArgumentException();
        }
        return count;
    }

    public static Menu getMenuByName(String name) {
        if (name.equals("양송이스프") || name.equals("타파스") || name.equals("시저샐러드")) {
            return Appetizer.valueOf(name);
        } else if (name.equals("티본스테이크") || name.equals("바비큐립") || name.equals("해산물파스타") || name.equals("크리스마스파스타")) {
            return Main.valueOf(name);
        } else if (name.equals("초코케이크") || name.equals("아이스크림")) {
            return Dessert.valueOf(name);
        } else if (name.equals("제로콜라") || name.equals("레드와인") || name.equals("샴페인")) {
            return Beverage.valueOf(name);
        }
        throw new IllegalArgumentException();
    }


}
