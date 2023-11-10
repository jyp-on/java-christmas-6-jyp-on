package christmas.util;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.model.Order;
import christmas.model.discount.Discount;
import christmas.model.menu.Appetizer;
import christmas.model.menu.Beverage;
import christmas.model.menu.Menu;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ValidateUtilTest extends NsTest {

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
        Order order = ValidateUtil.parseOrder("타파스-1,제로콜라-3");
        List<Map<Menu, Integer>> maps = order.getOrder();
        assertThat(maps.get(0).get(Appetizer.타파스)).isEqualTo(1);
        assertThat(maps.get(1).get(Beverage.제로콜라)).isEqualTo(3);
    }

    @DisplayName("중복 주문 예외 처리")
    @Test
    void checkDuplicateMenu() {
        assertThatThrownBy(() -> ValidateUtil.parseOrder("타파스-1,타파스-2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수의 총합이 20개 넘어가면 예외 처리")
    @Test
    void checkOver20() {
        assertThatThrownBy(() -> ValidateUtil.parseOrder("타파스-10,양송이수프-10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문 시 예외 처리")
    @Test
    void checkOnlyBeverageOrder() {
        assertThatThrownBy(() -> ValidateUtil.parseOrder("제로콜라-1,레드와인-2"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> ValidateUtil.parseOrder("제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문금액 10000원 미만일 시 이벤트 적용 X <크리스마스 혜택 포함>")
    @Test
    void checkMinPrice() {
        assertSimpleTest(() -> {
            run("23", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음",
                    "[INFO] 10000원 이하의 주문 금액은 이벤트를 받지 못합니다.");

        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}