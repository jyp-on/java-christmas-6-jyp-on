package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.discount.Discount;
import christmas.util.ValidateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class OrderTest {

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
    void getTotalPrice() {
        Order order = ValidateUtil.parseOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        int totalPrice = order.getTotalPrice();
        assertThat(totalPrice).isEqualTo(142000);
    }
}