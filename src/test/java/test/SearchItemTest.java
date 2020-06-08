package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.boots.MensFootballBootsPage;
import pages.home.HomePage;

public class SearchItemTest extends BaseTest {

    @DataProvider(name = "brand-types")
    private Object[][] brandTypes() {
        return new Object[][]{
                {"Adidas Boots", "ADIDAS"},
                {"Nike Boots", "NIKE"}
        };
    }

    @DataProvider(name = "brand-types-add-to-cart")
    private Object[][] brandTypesAddToCart() {
        return new Object[][]{
                {"Adidas Boots", "Black", "TEST", "Spain"},
                {"Nike Boots", "Silver", "TEST2", "Brazil"}
        };
    }

    @Test(dataProvider = "brand-types", description = "Open Home page and search by Input text")
    @Parameters({"brand of boots", "type of brand"})
    public void searchByInputText(String brandBoot, String brandType) {
        new HomePage()
                .searchByInputText(brandBoot);
        new MensFootballBootsPage()
                .validateBrand(brandType);
    }

    @Test(description = "Open Home page and search by Item Tab menu 'Mens' -> 'Football Boots'")
    public void searchByTabMenu() {
        new HomePage()
                .searchByItemsTabMenu();
        new MensFootballBootsPage()
                .searchByBrandNike()
                .validateBrand("NIKE");
    }

    @Test(dataProvider = "brand-types-add-to-cart", description = "Open Home page and search by Input text and add to Cart")
    @Parameters({"brand of boots", "boot colour", " letter text", "flag"})
    public void searchByInputTextAddToCart(String brandBoot, String bootColour, String letterText, String flag) {
        new HomePage()
                .searchByInputText(brandBoot);
        new MensFootballBootsPage()
                .addToCart(bootColour, letterText, flag);
        new CartPage()
                .verifyProductDetailText(bootColour, letterText, flag);
    }

    @Test(description = "Open Home page and search by Item Tab menu 'Mens' -> 'Football Boots' and add to Cart")
    public void searchByTabMenuAddToCart() {
        new HomePage()
                .searchByItemsTabMenu();
        new MensFootballBootsPage()
                .searchByBrandNike()
                .addToCart("Gold", "TEST3", "England");
        new CartPage()
                .verifyProductDetailText("Gold", "TEST3", "England");
    }
}
