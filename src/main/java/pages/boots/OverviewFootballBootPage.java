package pages.boots;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class OverviewFootballBootPage extends BasePage {

    private SelenideElement addToBag = $(By.id("aAddToBag"));
    private SelenideElement selectSizeOfBoot = $(By.xpath("//*[not(contains(@class, 'tooltip sizeButtonli greyOut')) and (contains(@class,'tooltip sizeButtonli '))][2]"));
    private SelenideElement dropDownColour = $(By.id("pTypeLISTTHC"));
    private SelenideElement lettersText = $(By.id("pTypeLETNUM"));
    private SelenideElement dropDownFlag = $(By.id("ddlFlag"));
    private SelenideElement addToBagAfterFillFields = $(By.id("aPersAddToBag"));
    private SelenideElement checkoutButton = $(By.xpath("//*[@class='HeaderCheckoutLink']"));

    public OverviewFootballBootPage() {
    }

    @Step("Add boot to cart")
    public OverviewFootballBootPage addToCart(String bootColour, String letterText,
                                              String flag) {
        selectSizeOfBoot.waitUntil(Condition.visible, 2000L);
        selectSizeOfBoot.doubleClick();
        addToBag.click();
        selectOptionFromDropDownByText(dropDownColour, bootColour);
        lettersText.sendKeys(letterText);
        selectOptionFromDropDownByText(dropDownFlag, flag);
        addToBagAfterFillFields.click();
        checkoutButton.click();
        return page(this);
    }
}
