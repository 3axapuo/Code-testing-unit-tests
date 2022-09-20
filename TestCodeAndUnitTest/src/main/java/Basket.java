import java.util.Arrays;
import java.util.Scanner;

public class Basket {
    protected static String[] saleProducts3x2 = {null, "Яблоки", null}; // создание массива для продуктов к которым применима скидка.
    protected static String[] products = {"Хлеб", "Яблоки", "Молоко"}; // создание массива продукты.
    protected static int saleCountProduct3x2 = 3; // акция 3 по цене 2
    protected static int[] prices = {100, 200, 300}; // создание массива цен.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] addBasket; // добавить в корзину
        int[] mainBasket = new int[products.length]; // количество продуктов = список продуктов
        int sumProducts = 0; // итоговая суммы чека
        int sumSale = 0; // итогова скидка по акциям.

        System.out.println("Список возможных товаров для покупки: ");
        for (int i = 1; i < products.length + 1; i++) { // Выводим список товаров и их цены.
            System.out.println(i + ". "
                    + products[i - 1]
                    + (products[i - 1].equals(saleProducts3x2[i - 1]) ? " (товар по акции 3 по цене 2) " : " (товар без акции) ") + " "
                    + prices[i - 1] + "руб/шт.");
        }
        while (true) {
            System.out.println("\n☐ Выберите товар и количество или введите `end` ");
            try {
                String input = scanner.nextLine();
                if ("end".equals(input)) break; // выходим в случае набора текста end
                addBasket = input.split(" "); // создаем массив, кладем туда строки раздельно до и после пробела
                int selectProduct = Integer.parseInt(addBasket[0].trim());
                int selectCount = Integer.parseInt(addBasket[1].trim());
                if (selectProduct > (products.length) || (selectProduct == 0) || (selectCount == 0)) { // если выбранный товар больше списка товаров то
                    System.out.println("⊠ ОШИБКА: Такого товара или количества не существует! Ваши вводные данные " + Arrays.toString(addBasket));
                } else if (addBasket.length == 2) {
                    mainBasket[selectProduct - 1] += (selectCount); // записываем в массив выбранный товар
                    System.out.println("☑ Товар '" + products[selectProduct - 1] + "' в количестве " + selectCount + " добавлен в корзину!");
                } else {
                    System.out.println((addBasket.length == 1
                            ? "⊠ ОШИБКА: Вы выбрали товар без количества, Ваши вводные данные "
                            : "⊠ ОШИБКА: Неверный формат выбора! \n Необходимо вводить номер товара и количество. Ваши вводные данные ")
                            + Arrays.toString(addBasket));
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("⊠ ОШИБКА: Вы не выбрали товар и количество!");
            }
        }
        scanner.close();
        System.out.println("\n☑ Ваша корзина составляет: ");
        for (int i = 1; i < products.length + 1; i++) { // Выводим список выбранных товаров и их цены.
            if (mainBasket[i - 1] != 0) {
                System.out.println(i + ". "
                        + products[i - 1] + " "
                        + mainBasket[i - 1] + "шт., по "
                        + prices[i - 1] + "руб/шт., на общую сумму: "
                        + (products[i - 1].equals(saleProducts3x2[i - 1]) && mainBasket[i - 1] >= saleCountProduct3x2
                        ? (FinalSum(mainBasket[i - 1], prices[i - 1])) - Sale3x2(mainBasket[i - 1], products[i - 1], prices[i - 1])
                        + " (товар по акции)" : (FinalSum(mainBasket[i - 1], prices[i - 1])) + " (товар без акции) "));
            }
            sumSale += Sale3x2(mainBasket[i - 1], products[i - 1], prices[i - 1]);
            sumProducts += FinalSum(mainBasket[i - 1], prices[i - 1]) - Sale3x2(mainBasket[i - 1], products[i - 1], prices[i - 1]);
        }
        System.out.println("Итого: "
                + sumProducts
                + "\nСкидка по акции: "
                + sumSale);
    } // Basket

    public static int Sale3x2(int countProducts, String products, int prices) {
        int sale;

        if (Arrays.asList(saleProducts3x2).indexOf(products) == 1) {
            sale = (Integer.valueOf(countProducts) / saleCountProduct3x2) * prices;
        } else {
            sale = 0;
        }
        return sale;
    }

    public static int FinalSum(int countProduct, int pricesProduct) {
        return countProduct * pricesProduct;
    }
} // class Basket
