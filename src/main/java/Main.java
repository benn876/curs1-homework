import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static model.Category.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Product> products = new ArrayList<>();
        List<Category> categories = new ArrayList<>(List.of(CLOTHES, ELECTRONICS, DYI));

        populateWithProjects(products, categories);

        categories.add(FOOD);
        System.out.println(categories);
        System.out.println(products);

        Shop shop = new Shop(products);
        System.out.println(shop.getProducts());
        shop.addNewProductType(new Product("newProduct", 199, List.of(ELECTRONICS), "Pc"));
        System.out.println(shop.getProducts());
        shop.addNewProductsToInventory("newProduct", 4);
        System.out.println(shop.buyProducts("newProduct", 2));

    }

    private static void populateWithProjects(List<Product> products, List<Category> categories) {
        IntStream.range(0, 10)
                .forEach(i -> products.add(new Product("Project" + i, i, categories, "description" + i)));
    }
}
