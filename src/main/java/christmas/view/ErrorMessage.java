package christmas.view;

public class ErrorMessage {
    private static final String INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String INVALID_COUNT_ORDER = "[ERROR] 메뉴 개수의 총합이 20개를 넘었습니다. 다시 입력해 주세요.";
    private static final String INVALID_MENU_ORDER = "[ERROR] 음료만 주문 하였습니다. 다시 입력해 주세요.";
    private static final String INVALID_PRICE_ORDER = "[ERROR] 총 주문금액은 10000원 이상이어야 합니다. 다시 입력해 주세요.";


    public static void getInvalidDate() {
        System.out.println(INVALID_DATE);
    }

    public static void getInvalidOrder() {
        System.out.println(INVALID_ORDER);
    }

    public static void getInvalidCountOrder() {
        System.out.println(INVALID_COUNT_ORDER);
    }

    public static void getInvalidMenuOrder() {
        System.out.println(INVALID_MENU_ORDER);
    }

    public static void getInvalidPriceOrder() {
        System.out.println(INVALID_PRICE_ORDER);
    }
}
