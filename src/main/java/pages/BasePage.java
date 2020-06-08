package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public static final int NUMBER_OF_PAGES_SHOULD_BE_CHECKED = 2;
    private SelenideElement showingItemsNextAfterCurrent = $(By.xpath("//*[contains(@class, 'PageSelector swipeNumberClick')]/following-sibling::*[not(contains(@class, 'swipeNextClick NextLink'))][1]"));

    protected BasePage() {
        Configuration.baseUrl = "https://lv.sportsdirect.com/";
        Configuration.startMaximized = true;
    }

    /**
     * @param expectedText - expected text
     * @param listOfItemsToCheck - list of items text to check
     */
    protected void verifyListTextEqualsExpectedText(String expectedText, List<String> listOfItemsToCheck) {
        if (listOfItemsToCheck.isEmpty())
            throw new SkipException("List for verification is Empty");

        int pageNumber = 1;
        boolean exist = true;
        while (exist && pageNumber < NUMBER_OF_PAGES_SHOULD_BE_CHECKED) {
            checkListExpectedText(expectedText, listOfItemsToCheck);
            exist = clickNextPageResult();
            pageNumber++;
        }

    }

    public static void checkListExpectedText(String expectedText, List<String> list) {
        for (int i=0; i<list.size(); i++) {
            Assert.assertEquals(list.get(i), expectedText);
        }
    }

    protected boolean clickNextPageResult() {
        try {
            scrollToParticularElement(showingItemsNextAfterCurrent);
            showingItemsNextAfterCurrent.click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void scrollToParticularElement(WebElement element) {
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void selectOptionFromDropDownByText(WebElement select, String dropdownOption) {
        Select myDropdown = new Select(select);
        myDropdown.selectByVisibleText(dropdownOption);
    }
}
