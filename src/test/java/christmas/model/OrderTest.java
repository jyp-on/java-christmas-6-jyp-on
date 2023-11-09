package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.menu.Menu;
import christmas.util.ValidateUtil;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;


class OrderTest {

    @Test
    void getTotalPrice() {
        List<Map<Menu, Integer>> maps = ValidateUtil.parseOrder("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Order order = new Order(maps);
        int totalPrice = order.getTotalPrice();
        assertThat(totalPrice).isEqualTo(142000);
    }
}