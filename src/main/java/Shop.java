import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Shop {
    private final List<Product> products;
    private final List<Inventory> inventories = new ArrayList<>();

    public Shop(List<Product> products) throws Exception {
        long distinctProducts = products.stream().map(Product::getName).distinct().count();
        if (distinctProducts != products.size()) {
            throw new Exception("Please make sure that there are no duplicates!");
        }

        this.products = new ArrayList<>(products);
        products.forEach(product -> inventories.add(new Inventory(product.getName())));
    }

    public Product addNewProductType(Product newProduct) throws Exception {
        if (products.stream()
                .anyMatch(product -> product.getName().equals(newProduct.getName()))) {
            throw new Exception("There is already a product with this name: " + newProduct.getName());
        }
        products.add(newProduct);
        inventories.add(new Inventory(newProduct.getName()));
        return newProduct;
    }

    public void addNewProductsToInventory(String productName, Integer numberOfProducts) {
        inventories.stream()
                .filter(inventory -> inventory.getProductName().equals(productName))
                .findFirst()
                .ifPresentOrElse(inventory -> inventory.add(numberOfProducts),
                        () -> System.out.println("\"The products with name: \" + productName + \" was not found in the shop\"")
                );
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> buyProducts(String productName, Integer pieces) throws Exception {
        Optional<Inventory> inventoryFound = inventories.stream()
                .filter(inventory -> inventory.getProductName().equals(productName))
                .findFirst();
        if (inventoryFound.isPresent()) {
            getProductsFromInventory(inventoryFound.get(), pieces);
        } else {
            throw new Exception("\"The products with name: \" + productName + \" was not found in the shop\"");
        }

        List<Product> cart = new ArrayList<>();
        products.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .ifPresent(product -> {
                    IntStream.range(0, pieces)
                            .forEach((index) -> cart.add(product));
                });
        return cart;
    }

    private void getProductsFromInventory(Inventory inventory, Integer piecesNeeded) {
        Integer currentlyAvailable = inventory.getNumberOfProducts();
        if (currentlyAvailable < piecesNeeded) {
            System.out.println("We are sorry but we don't have the required number of the pieces.");
        } else {
            inventory.remove(piecesNeeded);
        }
    }


}
