import org.junit.jupiter.api.*;

@DisplayName("Тестирование класса корзина")
class BasketTest {

    @BeforeAll
    @DisplayName("Проверка на пустые значения массива продукты")
    static void setUpApp() {
        //System.out.println("Запускаюсь до выполнения всех тестов...");
        Assertions.assertNotNull(Basket.products); //массив не должен быть пустой
        for (String product : Basket.products) { // перебираем значения массива
            Assertions.assertNotNull(product);
        }
        System.out.println("Тест пройден: наличие пустых значений в массиве продуктов не найдено");
    }

    /*    @BeforeEach
        void setUp() {
            System.out.println("Вызываюсь до выполнения теста");
        }*/
    @Test
    @DisplayName("проверка акции 3 по цене 2")
    void sale3x2() {
        for (int product = 0; product < Basket.saleProducts3x2.length; product++) {
            if (Basket.saleProducts3x2[product] != null) { // если есть товар по акции то
                Assertions.assertEquals(3, Basket.saleCountProduct3x2); // значение должно быть 3 по правилам акции
                System.out.println("Тест пройден: найден товар по акции " + Basket.saleProducts3x2[product] + " 3 по цене 2. ");
            }
        }
    }

    @Test
    @DisplayName("проверка на положительное число цен в массиве")
    void zeroPrices() {
        for (int product : Basket.prices) { // перебираем значения массива
            Assertions.assertEquals(product > 0, true);
        }
        System.out.println("Тест пройден: все цены положительны");
    }
/*    @AfterEach
    void tearDown() {
        System.out.println("Вызываюсь после вызова теста");
    }*/

    @AfterAll
    @DisplayName("Проверка массивов на равное заполненность по количеству")
    static void tearDownAll() {
        Assertions.assertEquals(Basket.products.length, Basket.products.length);
        Assertions.assertEquals(Basket.products.length, Basket.saleCountProduct3x2);
        Assertions.assertEquals(Basket.products.length, Basket.saleProducts3x2.length);
        Assertions.assertEquals(Basket.products.length, Basket.prices.length);
        System.out.println("Тест пройден: равное заполняемость количества во всех массивах: "
                + Basket.products.length + "х" + Basket.prices.length);
    }
}
