import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ТЕСТИРОВАНИЕ класса корзина")
class BasketTest {

    @Test //@BeforeAll
    @DisplayName("ТЕСТ, проверка на пустые значения массива продукты")
    void setUpApp() { //static
        Assertions.assertNotNull(Basket.products); //массив не должен быть пустой
        for (String product : Basket.products) { // перебираем значения массива
            Assertions.assertNotNull(product);
        }
        System.out.println("Тест пройден: наличие пустых значений в массиве продуктов не найдено");
    }

    @Test
    @DisplayName("ТЕСТ, проверка акции 3 по цене 2")
    void sale3x2() {
        for (int product = 0; product < Basket.saleProducts3x2.length; product++) {
            if (Basket.saleProducts3x2[product] != null) { // если есть товар по акции то
                Assertions.assertEquals(3, Basket.saleCountProduct3x2); // значение должно быть 3 по правилам акции
                System.out.println("Тест пройден: найден товар по акции " + Basket.saleProducts3x2[product] + " 3 по цене 2. ");
            }
        }
    }

    @Test
    @DisplayName("ТЕСТ, проверка на положительное число цен в массиве")
    void zeroPrices() {
        for (int product : Basket.prices) { // перебираем значения массива
            Assertions.assertEquals(product > 0, true);
        }
        System.out.println("Тест пройден: все цены положительны");
    }

    @Test //@AfterAll
    @DisplayName("ТЕСТ, проверка массивов на равное заполнение по количеству")
    void tearDownAll() { // static
        Assertions.assertEquals(Basket.products.length, Basket.products.length);
        Assertions.assertEquals(Basket.products.length, Basket.saleCountProduct3x2);
        Assertions.assertEquals(Basket.products.length, Basket.saleProducts3x2.length);
        Assertions.assertEquals(Basket.products.length, Basket.prices.length);
        System.out.println("Тест пройден: равное заполнение количества во всех массивах: "
                + Basket.products.length + "х" + Basket.prices.length);
    }
}
