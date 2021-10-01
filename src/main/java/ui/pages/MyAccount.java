package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends MainPage {
    public MyAccount(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id='center_column']/h1")
    public WebElement titleRegisteredAccount;

    public String checkTitle() {
        return titleRegisteredAccount.getText();
    }

    public boolean checkTitleTextOnPage(String title) {
        webElements.checkTitle(title);
        return true;
    }

    public void checkTitle(String message, boolean actual, boolean expected) {
        webElements.checkAC(message, actual, expected);
    }
}