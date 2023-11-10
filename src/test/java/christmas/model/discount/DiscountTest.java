package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import christmas.util.ValidateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @DisplayName("독립적인 테스트를 위한 전역 변수 초기화")
    @BeforeEach
    void cleanUp() {
        Discount.EVENT_FLAG = true;
        Discount.RECEIVE_FLAG = false;
        Discount.TOTAL_DISCOUNT = 0;

        ValidateUtil.menuNames.clear();
        ValidateUtil.countMenu = 0;
    }

    @Test
    void d_day_discount() {
        Order order = ValidateUtil.parseOrder("타파스-1,제로콜라-2");

        Discount maxDiscount = new Discount(25, order);
        assertThat(maxDiscount.dDay()).isEqualTo(3400);

        Discount minDiscount = new Discount(1, order);
        assertThat(minDiscount.dDay()).isEqualTo(1000);
    }

    @Test
    void weekday_discount_with_dessert() {
        Order order = ValidateUtil.parseOrder("초코케이크-2");

        Discount friday = new Discount(1, order);
        assertThat(friday.weekDay()).isEqualTo(0);

        Discount sunday = new Discount(3, order);
        assertThat(sunday.weekDay()).isEqualTo(4046);
    }

    @Test
    void weekday_discount_without_dessert() {
        Order order = ValidateUtil.parseOrder("타파스-1");

        Discount friday = new Discount(1, order);
        assertThat(friday.weekDay()).isEqualTo(0);

        Discount sunday = new Discount(3, order);
        assertThat(sunday.weekDay()).isEqualTo(0);
    }

    @Test
    void weekend_discount_with_main() {
        Order order = ValidateUtil.parseOrder("바비큐립-1");

        Discount friday = new Discount(3, order);
        assertThat(friday.weekEnd()).isEqualTo(0);

        Discount sunday = new Discount(8, order);
        assertThat(sunday.weekEnd()).isEqualTo(2023);
    }

    @Test
    void weekend_discount_without_main() {
        Order order = ValidateUtil.parseOrder("타파스-2");

        Discount friday = new Discount(3, order);
        assertThat(friday.weekEnd()).isEqualTo(0);

        Discount sunday = new Discount(8, order);
        assertThat(sunday.weekEnd()).isEqualTo(0);
    }

    @Test
    void special_discount() {
        Order order = ValidateUtil.parseOrder("타파스-2");

        Discount discount_1 = new Discount(3, order);
        Discount discount_2 = new Discount(4, order);
        Discount discount_3 = new Discount(25, order);

        assertThat(discount_1.special()).isEqualTo(1000);
        assertThat(discount_2.special()).isEqualTo(0);
        assertThat(discount_3.special()).isEqualTo(1000);
    }
}