package christmas.model.discount;

import christmas.model.Order;
import christmas.model.menu.Dessert;
import christmas.model.menu.Main;
import christmas.model.menu.Menu;
import java.util.List;
import java.util.Map;

public class Discount {
    private final int YEAR = 2023;
    private final int date;
    private final Order order;
    public static int TOTAL_DISCOUNT = 0;
    public static boolean EVENT_FLAG = true;
    public static boolean RECEIVE_FLAG = false;

    public Discount(int _date, Order _order) {
        this.date = _date;
        this.order = _order;
    }

    public int dDay() {
        if (date < 26 && Discount.EVENT_FLAG) {
            int discount = 1000 + ((this.date - 1) * 100);
            TOTAL_DISCOUNT += discount;
            return discount;
        }
        return 0;
    }

    public int special() {
        for (int i = 3; i <= 31; i += 7) {
            if (date == 25 || date == i && Discount.EVENT_FLAG) {
                TOTAL_DISCOUNT += 1000;
                return 1000;
            }
        }
        return 0;
    }

    public int weekDay() {
        List<Map<Menu, Integer>> menuList = order.getOrder();

        if ((date % 7 > 2 || date % 7 == 0) && findEnumInMenus(Dessert.class, menuList) && Discount.EVENT_FLAG) {
            long count = menuList.stream()
                    .flatMap(order -> order.entrySet().stream())
                    .filter(entry -> entry.getKey() instanceof Dessert)
                    .mapToLong(Map.Entry::getValue)
                    .sum();

            TOTAL_DISCOUNT += (int) (count * YEAR);
            return (int) (count * YEAR);
        }
        return 0;
    }

    public int weekEnd() {
        List<Map<Menu, Integer>> menuList = order.getOrder();

        if ((date % 7 == 1 || date % 7 == 0) && findEnumInMenus(Main.class, menuList) && Discount.EVENT_FLAG) {
            long count = menuList.stream()
                    .flatMap(order -> order.entrySet().stream())
                    .filter(entry -> entry.getKey() instanceof Main)
                    .mapToLong(Map.Entry::getValue)
                    .sum();

            TOTAL_DISCOUNT += (int) (count * YEAR);
            return (int) (count * YEAR);
        }
        return 0;
    }

    private <T extends Enum<T> & Menu> boolean findEnumInMenus(Class<T> enumClass,
                                                               List<Map<Menu, Integer>> orderList) {
        return orderList.stream()
                .flatMap(order -> order.keySet().stream())
                .anyMatch(enumClass::isInstance);
    }
}
