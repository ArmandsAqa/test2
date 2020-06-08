package pages.boots;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class MensFootballBootsPage extends BasePage {

    private SelenideElement nikeBrandCheckBox = $(By.xpath("//*[@data-productname='Nike']/a/span"));
    private ElementsCollection brandTitleText = $$(By.xpath("//*[@class='productdescriptionbrand']"));

    public MensFootballBootsPage() {
    }

    @Step("Search by Brand 'Nike'")
    public MensFootballBootsPage searchByBrandNike() {
        nikeBrandCheckBox.waitUntil(Condition.visible, 2000L);
        nikeBrandCheckBox.click();
        return page(this);
    }

    @Step("Verify Brand title text")
    public MensFootballBootsPage validateBrand(String brand) {
        brandTitleText.get(0).waitUntil(Condition.text(brand), 3000L);
        verifyListTextEqualsExpectedText(brand, brandTitleText.texts());
        return page(this);
    }

    @Step("Add to Cart")
    public MensFootballBootsPage addToCart(String bootColour, String letterText,
                                           String flag) {
        brandTitleText.get(0).waitUntil(Condition.visible, 2000L);
        brandTitleText.get(0).doubleClick();
        new OverviewFootballBootPage().
                addToCart(bootColour, letterText, flag);
        return page(this);
    }
}
