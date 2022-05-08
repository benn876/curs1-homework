public class Inventory {
    private final String productName;
    private Integer numberOfProducts;

    public Inventory(String productName) {
        this.productName = productName;
        this.numberOfProducts = 0;
    }

    public Inventory(String productName, Integer numberOfProducts) {
        this.productName = productName;
        this.numberOfProducts = numberOfProducts;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void add(Integer productsToBeAdded) {
        this.numberOfProducts = this.numberOfProducts + productsToBeAdded;
    }

    public void remove(Integer productsToBeRemoved) {
        this.numberOfProducts = this.numberOfProducts - productsToBeRemoved;
    }
}
