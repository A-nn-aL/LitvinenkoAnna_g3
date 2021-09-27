package SimpleTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SimpleTest {
    WebDriver webDriver;
    Logger logger;

    @Before
    public void setUp() {
        logger = logger.getLogger(getClass());
        File fileChromeDriver = new File("./drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Opening the link");
        webDriver.get("https://coderoad.ru");
    }

    @After
    public void tearDown() {
        logger.info("Closing browser");
        webDriver.quit();
    }

    @Test
    public void checkMainPageLink() {
        logger.info("Checking link to the main page");
        String firstPageText = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/h1")).getText();
        webDriver.findElement(By.xpath("/html/body/nav/div/div[1]/a")).click();
        String secondPageText = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/h1")).getText();
        Assert.assertEquals(firstPageText, secondPageText);
    }

    @Test
    public void checkSearch() {
        logger.info("Chek the search box");
        webDriver.findElement(By.xpath("//*[@id=\"search_text\"]")).sendKeys("Популярные браузеры на Mac");
        webDriver.findElement(By.xpath("//*[@id=\"search_text\"]")).sendKeys(Keys.ENTER);
        String link = webDriver.findElement(By.xpath("//*[@id=\"content\"]/h4[1]/a")).getText();
        Assert.assertEquals("Популярные браузеры на Mac", link);
    }

    @Test
    public void checkTags() {
        logger.info("Check link to the tags");
        webDriver.findElement(By.xpath("//*[@id=\"navbarToggleExternalContent\"]/nav/div/div[1]/a[3]")).click();
        String tag = webDriver.findElement(By.xpath("//*[@id='content']/h2")).getText();
        Assert.assertEquals("Теги", tag);
    }
}
