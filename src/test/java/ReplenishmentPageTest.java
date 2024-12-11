import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ReplenishmentPageTest {

    private WebDriver driver;
    private ReplenishmentPage replenishmentPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //таймаут

        driver.get("https://www.mts.by/");

        replenishmentPage = new ReplenishmentPage(driver);

        replenishmentPage.acceptCookie();
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkTitleTest() {
        replenishmentPage.checkTitle();
    }

    @Test
    public void checkLogosTest() {
        replenishmentPage.checkLogos();
    }

    @Test
    public void checkLinkTest() {
        replenishmentPage.checkLink();
    }

    @Test
    public void checkButtonContinueTest() {
        replenishmentPage.clickButtonContinue();
    }

    @Test
    public void checkEmptyFieldsTest() {
        replenishmentPage.checkEmptyFields();
    }

    @Test
    public void checkCommunicationServicesSumTest() {
        replenishmentPage.checkCommunicationServicesSum();
    }

    @Test
    public void checkCommunicationServicesNumberTest() {
        replenishmentPage.checkCommunicationServicesNumber();
    }

    @Test
    public void checkCommunicationServicesLogosTest() {
        replenishmentPage.checkCommunicationServicesLogos();
    }

    @Test
    public void checkCommunicationServicesCardFieldTest() {
        replenishmentPage.checkCommunicationServicesCardFields();
    }
}