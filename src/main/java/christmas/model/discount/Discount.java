package christmas.model.discount;

import christmas.model.menu.Dessert;
import christmas.model.menu.Main;
import christmas.model.menu.Menu;
import java.util.List;

public class Discount {
    private final int YEAR = 2023;

    private int day;
    private List<Menu> menuList;

    public Discount(int _day, List<Menu> _menuList) {
        this.day = _day;
        this.menuList = _menuList;
    }

    public int dDay() {
        return 1000 + ((this.day - 1) * 100);
    }

    public int special() {
        for (int i = 3; i <= 31; i += 7) {
            if (day == 25 || day == i) {
                return 1000;
            }
        }
        return 0;
    }

    public int weekDay() {
        if (day % 7 <= 2 && day % 7 != 0 && findEnumInMenus(Dessert.class, menuList)) {
            return menuList.size() * YEAR;
        }
        return 0;
    }

    public int weekEnd() {
        if ((day % 7 > 2 || day % 7 == 0) && findEnumInMenus(Main.class, menuList)) {
            return menuList.size() * YEAR;
        }
        return 0;
    }

    private <T extends Enum<T> & Menu> boolean findEnumInMenus(Class<T> enumClass,
                                                               List<Menu> menuList) {
        return menuList.stream()
                .anyMatch(enumClass::isInstance);
    }
}
