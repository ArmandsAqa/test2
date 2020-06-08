package pages.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {

    private SelenideElement closeButton = $(By.xpath("//*[@id='advertPopup']//div[1]/button"));
    private SelenideElement popupWindow = $(By.cssSelector("#advertPopup > div > div"));
    private SelenideElement mensMenuTab = $(By.xpath("//*[@id='topMenu']/ul/li[1]/a"));
    private SelenideElement footBallBootsMenuTab = $(By.xpath("//*[@class='MensCenter Center']//li[@class='mmHasChild level1 sdmColHeaderHidden gradBorder']/ul/li[8]/a"));
    private SelenideElement searchByInput = $(By.id("txtSearch"));

    public HomePage() {
        super();
        open("");
        closePopup();
    }

    @Step("Search by Items tab menu")
    public HomePage searchByItemsTabMenu() {
        actions().moveToElement(mensMenuTab).perform();
        footBallBootsMenuTab.waitUntil(Condition.visible, 1000L);
        actions().moveToElement(footBallBootsMenuTab).click().perform();
        return page(this);
    }

    @Step("Search by Input text")
    public HomePage searchByInputText(String brandType) {
        searchByInput.clear();
        searchByInput.sendKeys(brandType);
        searchByInput.pressEnter();
        return page(this);
    }

    @Step("Close Popup")
    protected void closePopup(){
        try{
            Thread.sleep(2000);
            if (popupWindow.isDisplayed()){
                closeButton.click();
            }
        } catch (NoAlertPresentException | InterruptedException noe){
        }
    }
}
