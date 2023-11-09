package christmas.model;

public enum Badge {
    STAR(5000),
    TREE(10000),
    SANTA(20000);

    private final int minPrice;

    Badge(int _minPrice) {
        this.minPrice = _minPrice;
    }
}
