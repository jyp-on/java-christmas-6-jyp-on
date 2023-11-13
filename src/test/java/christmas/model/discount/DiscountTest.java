package christmas.model.discount;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.model.Order;
import christmas.util.ValidateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest extends NsTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @DisplayName("독립적인 테스트를 위한 전역 변수 초기화")
    @BeforeEach
    void cleanUp() {
        Discount.EVENT_FLAG = true;
        Discount.RECEIVE_FLAG = false;
        Discount.TOTAL_DISCOUNT = 0;

        ValidateUtil.menuNames.clear();
        ValidateUtil.countMenu = 0;
    }

    @DisplayName("크리스마스 디데이 할인 테스트")
    @Test
    void d_day_discount() {
        Order order = ValidateUtil.parseOrder("타파스-1,제로콜라-2");

        Discount maxDiscount = new Discount(25, order);
        assertThat(maxDiscount.dDay()).isEqualTo(3400);

        Discount minDiscount = new Discount(1, order);
        assertThat(minDiscount.dDay()).isEqualTo(1000);
    }

    @DisplayName("평일 할인 - 디저트 O")
    @Test
    void weekday_discount_with_dessert() {
        Order order = ValidateUtil.parseOrder("초코케이크-2"); // 디저트 O

        Discount friday = new Discount(1, order); // 주말
        assertThat(friday.weekDay()).isEqualTo(0);

        Discount sunday = new Discount(3, order); // 평일
        assertThat(sunday.weekDay()).isEqualTo(4046);
    }

    @DisplayName("평일 할인 - 디저트 X")
    @Test
    void weekday_discount_without_dessert() {
        Order order = ValidateUtil.parseOrder("타파스-1"); // 디저트 X

        Discount friday = new Discount(1, order); // 주말
        assertThat(friday.weekDay()).isEqualTo(0);

        Discount sunday = new Discount(3, order); // 평일
        assertThat(sunday.weekDay()).isEqualTo(0);
    }

    @DisplayName("주말 할인 - 메인 O")
    @Test
    void weekend_discount_with_main() {
        Order order = ValidateUtil.parseOrder("바비큐립-1"); // 메인

        Discount friday = new Discount(3, order); // 평일
        assertThat(friday.weekEnd()).isEqualTo(0);

        Discount sunday = new Discount(8, order); // 주말
        assertThat(sunday.weekEnd()).isEqualTo(2023);
    }

    @DisplayName("주말 할인 - 메인 X")
    @Test
    void weekend_discount_without_main() {
        Order order = ValidateUtil.parseOrder("타파스-2"); // 메인 X

        Discount friday = new Discount(3, order); // 평일
        assertThat(friday.weekEnd()).isEqualTo(0);

        Discount sunday = new Discount(8, order); // 주말
        assertThat(sunday.weekEnd()).isEqualTo(0);
    }

    @DisplayName("특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인")
    @Test
    void special_discount() {
        Order order = ValidateUtil.parseOrder("타파스-2");

        Discount discount_1 = new Discount(3, order); // 별 O
        Discount discount_2 = new Discount(4, order); // 별 X
        Discount discount_3 = new Discount(25, order); // 별 O

        assertThat(discount_1.special()).isEqualTo(1000);
        assertThat(discount_2.special()).isEqualTo(0);
        assertThat(discount_3.special()).isEqualTo(1000);
    }

    @DisplayName("12월 이벤트 배지 테스트 <산타>")
    @Test
    void badge_test_santa() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
        });
    }

    @DisplayName("12월 이벤트 배지 테스트 <트리>")
    @Test
    void badge_test_tree() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-5");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "트리");
        });
    }

    @DisplayName("12월 이벤트 배지 테스트 <별>")
    @Test
    void badge_test_star() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-3");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "별");
        });
    }

    @DisplayName("12월 이벤트 배지 테스트 <없음>")
    @Test
    void badge_test_none() {
        assertSimpleTest(() -> {
            run("3", "아이스크림-1");
            assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}