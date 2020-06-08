package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage {

    private SelenideElement productDetailText = $(By.xpath("//*[@class='personalisation']"));
    private SelenideElement deleteItemFromCart = $(By.xpath("//*[@class='deleteItemText']"));

    public CartPage() {
    }

    @Step("verify product detail text")
    public CartPage verifyProductDetailText(String bootColour, String letterText,
                                              String flag) {
        productDetailText.waitUntil(Condition.visible, 2000L);
        assertThat(productDetailText.getText()).contains(bootColour);
        assertThat(productDetailText.getText()).contains(letterText);
        assertThat(productDetailText.getText()).contains(flag);
        deleteItemFromCart.click();
        return page(this);
    }
}
