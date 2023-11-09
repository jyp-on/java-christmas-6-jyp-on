package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.menu.Appetizer;
import christmas.model.menu.Beverage;
import christmas.model.menu.Menu;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ValidateUtilTest {

    @DisplayName("숫자 형식이 아닌 날짜에 대한 예외 처리")
    @Test
    void convertToInt() {
        assertThatThrownBy(() -> ValidateUtil.convertToInt("a"))
                .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("1~31일 사이가 아닌 날짜에 대한 예외 처리")
    @Test
    void validateRangeOfDate() {
        assertThatThrownBy(() -> ValidateUtil.validateRangeOfDate(32))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 파싱 테스트")
    @Test
    void parseOrder() {
        List<Map<Menu, Integer>> maps = ValidateUtil.parseOrder("타파스-1,제로콜라-3");
        assertThat(maps.get(0).get(Appetizer.타파스)).isEqualTo(1);
        assertThat(maps.get(1).get(Beverage.제로콜라)).isEqualTo(3);
    }

    @DisplayName("중복 주문 예외 처리")
    @Test
    void checkDuplicateOrder() {
        assertThatThrownBy(() -> ValidateUtil.parseOrder("타파스-1,타파스-2"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}