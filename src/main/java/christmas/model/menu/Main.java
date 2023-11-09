package christmas.model.menu;

public enum Main implements Menu {
    티본스테이크(55000),
    바비큐립(54000),
    해산물파스타(35000),
    크리스마스파스타(25000);

    private int price;

    Main(int _price) {
        this.price = _price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
