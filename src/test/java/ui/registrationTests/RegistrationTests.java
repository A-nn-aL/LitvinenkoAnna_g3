package ui.registrationTests;

import libs.WebElements;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.MainPage;
import ui.pages.MyAccount;
import ui.pages.Registration2Page;
import ui.pages.RegistrationPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class RegistrationTests {

    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    WebElements webElement;

    public MainPage mainPage;
    public RegistrationPage registrationPage;
    public Registration2Page registration2Page;
    public MyAccount myAccount;

    @Before
    public void setUp() {
        logger = Logger.getLogger(getClass());
        File fileChromeDriver = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        webDriver = new ChromeDriver();
        webElement = new WebElements(webDriver);
        logger.info("Get chromeDriver");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("browser was opened");

        mainPage = new MainPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        registration2Page = new Registration2Page(webDriver);
        myAccount = new MyAccount(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    private final String EMAIL = "domashka5@gmail.com";
    private final String FIRST_NAME = "Anna";
    private final String LAST_NAME = "Litvinenko";
    private final String PASSWORD = "qwerty123";
    private final String STREET = "street New York, 12";
    private final String CITY = "New York";
    private final int STATE = 32;
    private final String POST_CODE = "12345";
    private final String MOBILE_PHONE = "1234567890";

    @Test
    public void registrationTest() {
        mainPage.openUrl("http://automationpractice.com/");
        registrationPage.openSignInPage();
        registrationPage.inputEmailCreate(EMAIL);
        registrationPage.submitButtonCreate();
        registrationPage.inputCustomerFN(FIRST_NAME);
        registrationPage.inputCustomerLN(LAST_NAME);
        registrationPage.inputEmail(EMAIL);
        registrationPage.inputPassword(PASSWORD);
        registrationPage.inputFirstName(FIRST_NAME);
        registrationPage.inputLastName(LAST_NAME);
        registrationPage.inputStreet(STREET);
        registrationPage.inputCity(CITY);
        registrationPage.selectState(STATE);
        registrationPage.inputPostCode(POST_CODE);
        registrationPage.inputMobilePhone(MOBILE_PHONE);
        registrationPage.inputAlias(EMAIL);
        registrationPage.clickSubmitAccount();
        Assert.assertEquals("MY ACCOUNT",
                myAccount.checkTitle());
        myAccount.checkTitle("Title",
                myAccount.checkTitleTextOnPage("My account - My Store"), true);
    }
// домашка 5.2
    @Test
    public void registrationTest2() {
        mainPage.openUrl("http://automationpractice.com/");
        registration2Page
                .openSignInPage()
                .inputEmailCreate(EMAIL)
                .submitButtonCreate()
                .inputCustomerFN(FIRST_NAME)
                .inputCustomerLN(LAST_NAME)
                .inputEmail(EMAIL)
                .inputPassword(PASSWORD)
                .inputFirstName(FIRST_NAME)
                .inputLastName(LAST_NAME)
//                .inputStreet(STREET)
//                .inputCity(CITY)
//                .selectState(STATE)
                .inputPostCode(POST_CODE)
                .inputMobilePhone(MOBILE_PHONE)
                .inputAlias(EMAIL)
                .clickSubmitAccount();
        webElement.isElementPresent("//div[@class='alert alert-danger']");

    }
}