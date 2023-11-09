package christmas.view;

public class ErrorMessage {
    private static final String INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static void getInvalidDate() {
        System.out.println(INVALID_DATE);
    }

    public static void getInvalidOrder() {
        System.out.println(INVALID_ORDER);
    }
}
