package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Dessert;
import christmas.model.menu.Main;
import christmas.model.menu.Menu;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class DiscountTest {

    List<Menu> menuList = new ArrayList<>();

    @Test
    void d_day_discount() {
        menuList.add(Appetizer.양송이수프);
        Discount maxDiscount = new Discount(25, menuList);
        assertThat(maxDiscount.dDay()).isEqualTo(3400);

        Discount minDiscount = new Discount(1, menuList);
        assertThat(minDiscount.dDay()).isEqualTo(1000);
    }

    @Test
    void weekday_discount_with_dessert() {
        menuList.add(Dessert.초코케이크);

        Discount friday = new Discount(1, menuList);
        assertThat(friday.weekDay()).isEqualTo(2023);

        Discount sunday = new Discount(3, menuList);
        assertThat(sunday.weekDay()).isEqualTo(0);
    }

    @Test
    void weekday_discount_without_dessert() {
        menuList.add(Appetizer.타파스);

        Discount friday = new Discount(1, menuList);
        assertThat(friday.weekDay()).isEqualTo(0);

        Discount sunday = new Discount(3, menuList);
        assertThat(sunday.weekDay()).isEqualTo(0);
    }

    @Test
    void weekend_discount_with_main() {
        menuList.add(Main.바비큐립);

        Discount friday = new Discount(3, menuList);
        assertThat(friday.weekEnd()).isEqualTo(2023);

        Discount sunday = new Discount(8, menuList);
        assertThat(sunday.weekEnd()).isEqualTo(0);
    }

    @Test
    void weekend_discount_without_main() {
        menuList.add(Appetizer.타파스);

        Discount friday = new Discount(3, menuList);
        assertThat(friday.weekEnd()).isEqualTo(0);

        Discount sunday = new Discount(8, menuList);
        assertThat(sunday.weekEnd()).isEqualTo(0);
    }

    @Test
    void special_discount() {
        Discount discount_1 = new Discount(3, menuList);
        Discount discount_2 = new Discount(4, menuList);
        Discount discount_3 = new Discount(25, menuList);
        Discount discount_4 = new Discount(30, menuList);

        assertThat(discount_1.special()).isEqualTo(1000);
        assertThat(discount_2.special()).isEqualTo(0);
        assertThat(discount_3.special()).isEqualTo(1000);
        assertThat(discount_4.special()).isEqualTo(0);
    }
}