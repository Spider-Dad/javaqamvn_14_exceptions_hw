import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.AlreadyExistsException;
import ru.netology.javaqa.NotFoundException;
import ru.netology.javaqa.Product;
import ru.netology.javaqa.ShopRepository;

public class ShopRepositoryTest {
    private ShopRepository shopRepository;

    @BeforeEach
    public void setup() {
        shopRepository = new ShopRepository();
        shopRepository.add(new Product(1, "Хлеб", 100));
        shopRepository.add(new Product(2, "Булка", 200));
        shopRepository.add(new Product(3, "Масло", 300));
    }

    @Test
    public void testRemoveExistingProduct() {
        int idToRemove = 2;

        shopRepository.removeById(idToRemove);
        Product[] products = shopRepository.findAll();

        Assertions.assertEquals(2, products.length);
        Product[] expectedProducts = {new Product(1, "Хлеб", 100), new Product(3, "Масло", 300)};
        Assertions.assertArrayEquals(expectedProducts, products);
    }

    @Test
    public void testRemoveNonExistingProduct() {
        int nonExistingProductId = 10;

        Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(nonExistingProductId));
    }

    @Test
    public void shouldAddProductTest() {
        Product product = new Product(4, "Колбаса", 400);
        shopRepository.add(product);

        Product[] products = shopRepository.findAll();
        Assertions.assertEquals(4, products.length);
        Product[] expectedProducts = {
                new Product(1, "Хлеб", 100),
                new Product(2, "Булка", 200),
                new Product(3, "Масло", 300),
                new Product(4, "Колбаса", 400)
        };
        Assertions.assertArrayEquals(expectedProducts, products);
    }

    @Test
    public void shouldNotAddAlreadyExistsProductTest() {
        Product duplicateProduct = new Product(1, "Хлеб c существующим  ID", 100);
        Assertions.assertThrows(AlreadyExistsException.class, () -> shopRepository.add(duplicateProduct));
    }
}
