public enum WhitespaceValues {

    USERNAME(30),
    DEFAULT(20),
    PRICE(8),
    PRODUCT_NAME(20),
    CATEGORY(20),
    PASSWORD(20),
    PRODUCT_STRING(50),
    DATE_TIME(20);

    private final int value;

    WhitespaceValues(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
