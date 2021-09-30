package CurrencyExchange4;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CurrencyExchangeTest {

    WebDriver webDriver;
    Logger logger;

    @Before
    public void setUp() {
        logger = Logger.getLogger(getClass());
        File fileChromeDriver = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Opening the link");
        webDriver.get("https://finance.i.ua/converter/");
    }

    @After
    public void tearDown() {
        logger.info("Closing browser");
        //webDriver.quit();
    }

    @Test
    public void checkMonobank() {
        logger.info("Check UAN");
        webDriver.findElement(By.xpath("//select[@id='converter_bank']")).click();
        WebElement monobank = webDriver.findElement(By.xpath("//select/option[text()='Monobank']"));
        Assert.assertNotNull(monobank);
    }

    @Test
    public void checkConverter() {
        logger.info("Check currency amount");
        String box = webDriver.findElement(By.xpath("//input[@id='currency_amount']")).getText();
        if (box.equals("")) {
            Assert.assertEquals((webDriver.findElement(By.xpath("(//input[@id='currency_exchange'])[1]")).getText()), "");
            Assert.assertEquals((webDriver.findElement(By.xpath("(//input[@id='currency_exchange'])[2]")).getText()), "");
            Assert.assertEquals((webDriver.findElement(By.xpath("(//input[@id='currency_exchange'])[3]")).getText()), "");
            Assert.assertEquals((webDriver.findElement(By.xpath("(//input[@id='currency_exchange'])[4]")).getText()), "");
            Assert.assertEquals((webDriver.findElement(By.xpath("(//input[@id='currency_exchange'])[5]")).getText()), "");
        } else {
            System.out.println("element was not found");
        }
    }
    @Test
    public void checkReturnLink()  {
        logger.info("Check Return Link");
        webDriver.findElement(By.xpath("//a[@class='ho_logo']")).click();
        webDriver.findElement(By.xpath("//a[text()='Конвертер валют']")).click();
        String x = webDriver.findElement(By.xpath("(//h2[contains(text(),'Курсы валют')])[1]")).getText();
        Assert.assertEquals(x,"Курсы валют");
    }

}
