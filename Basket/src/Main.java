import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main implements Basket{
    ArrayList<String> products = new ArrayList<>();
    HashMap<String, Integer> hashMap = new HashMap<>();

    @Override
    public void addProduct(String product, int quantity) {
        products.add(product);
        hashMap.put(product, quantity);
    }

    @Override
    public void removeProduct(String product) {
        products.remove(products.indexOf(product));
        hashMap.remove(product);
    }

    @Override
    public void updateProductQuantity(String product, int quantity) {
        hashMap.replace(product, quantity);
    }

    @Override
    public void clear() {
        products.clear();
        hashMap.clear();
    }

    @Override
    public List<String> getProducts() {
        return products;
    }

    @Override
    public int getProductQuantity(String product) {
        return hashMap.get(product);
    }

    public static void main(String[] args) {
        Main prod = new Main();
        prod.addProduct("Системный блок", 1);
        prod.addProduct("Монитор", 2);
        prod.addProduct("Мышь", 2);
        prod.addProduct("Клавиатура", 2);
        prod.addProduct("Колонки", 2);
        System.out.println(prod.getProducts());
        System.out.println(prod.getProductQuantity("Клавиатура"));
        prod.updateProductQuantity("Клавиатура", 1);
        prod.removeProduct("Мышь");
        System.out.println(prod.getProducts());
        System.out.println(prod.getProductQuantity("Клавиатура"));
        prod.clear();
        System.out.println(prod.getProducts());
    }
}
