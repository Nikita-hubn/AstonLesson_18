import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ReplenishmentPage {

    protected WebDriver driver;

    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement title;

    @FindBy(xpath = "//div[@class='pay__partners']/ul/li")
    private List<WebElement> logos;

    @FindBy(xpath = "//button[@class='btn btn_black cookie__ok']")
    private WebElement acceptButton;

    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement linkMoreAboutService;

    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement servicesDropdownList;

    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement servicesDropDownList;

    @FindBy(xpath = "//p[contains(text(), 'Услуги связи')]")
    private WebElement communicationServices;

    @FindBy(xpath = "//p[contains(text(), 'Домашний интернет')]")
    private WebElement homeInternet;

    @FindBy(xpath = "//p[contains(text(), 'Рассрочка')]")
    private WebElement installment;

    @FindBy(xpath = "//p[contains(text(), 'Задолженность')]")
    private WebElement arrears;

    @FindBy(xpath = "//input[@id='connection-phone']")
    private WebElement accountNumberForServices;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountMoneyForServices;

    @FindBy(xpath = "//div/input[@id='connection-email']")
    private WebElement emailForServices;

    @FindBy(xpath = "//input[@placeholder='Номер абонента']")
    private WebElement accountNumberForInternet;

    @FindBy(xpath = "//input[@id='internet-sum']")
    private WebElement amountMoneyForInternet;

    @FindBy(xpath = "//input[@id='internet-email']")
    private WebElement emailForInternet;

    @FindBy(xpath = "//input[@id='score-instalment']")
    private WebElement accountNumberForInstallment;

    @FindBy(xpath = "//input[@id='instalment-sum']")
    private WebElement amountMoneyForInstallment;

    @FindBy(xpath = "//input[@id='instalment-email']")
    private WebElement emailForInstallment;

    @FindBy(xpath = "//input[@id='score-arrears']")
    private WebElement accountNumberForArrears;

    @FindBy(xpath = "//input[@id='arrears-sum']")
    private WebElement amountMoneyForArrears;

    @FindBy(xpath = "//input[@id='arrears-email']")
    private WebElement emailForArrears;

    private final String testNumber = "297777777";
    private final String testMoney = "100.00 BYN";

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-payment-container/section/div/div/div[1]")
    private WebElement cost;

    @FindBy(xpath = "//button[@class='colored disabled']")
    private WebElement payCostButton;

    @FindBy(xpath = "//span[contains(text(), 'Номер')]")
    private WebElement number;

    @FindBy(xpath = "//label[contains(text(), 'Номер карты')]")
    private WebElement cardNumber;

    @FindBy(xpath = "//label[contains(text(), 'Срок действия')]")
    private WebElement validityPeriod;

    @FindBy(xpath = "//label[contains(text(), 'CVC')]")
    private WebElement cvc;

    @FindBy(xpath = "//label[contains(text(), 'Имя держателя (как на карте)')]")
    private WebElement cardHolderName;

    @FindBy(xpath = "//img[@class='ng-tns-c61-0 ng-star-inserted']")
    private List<WebElement> cardLogos;



    public ReplenishmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookie() {
        if (acceptButton.isDisplayed()) {
            acceptButton.click();
        }
    }

    public void checkTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        assertEquals(expectedTitle, title.getText(), "Некорректное название блока.");
    }

    public void checkLogos() {
        assertTrue(!logos.isEmpty(), "Логотипы отсутвуют");
    }

    public void checkLink() {
        linkMoreAboutService.click();
    }

    public void clickButtonContinue() {
        accountNumberForServices.sendKeys(testNumber);
        amountMoneyForServices.sendKeys(testMoney);
        continueButton.click();
    }
    public void timeOut() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='bepaid-iframe']")));
    }

    public void waitAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void checkEmptyFields() {
        waitAndClick(servicesDropDownList);
        waitAndClick(communicationServices);
        assertEquals("Номер телефона", accountNumberForServices.getAttribute("placeholder"));
        assertEquals("Сумма", amountMoneyForServices.getAttribute("placeholder"));
        assertEquals("E-mail для отправки чека", emailForServices.getAttribute("placeholder"));
        waitAndClick(servicesDropDownList);
        waitAndClick(homeInternet);
        assertEquals("Номер абонента", accountNumberForInternet.getAttribute("placeholder"));
        assertEquals("Сумма", amountMoneyForInternet.getAttribute("placeholder"));
        assertEquals("E-mail для отправки чека", emailForInternet.getAttribute("placeholder"));
        waitAndClick(servicesDropDownList);
        waitAndClick(installment);
        assertEquals("Номер счета на 44", accountNumberForInstallment.getAttribute("placeholder"));
        assertEquals("Сумма", amountMoneyForInstallment.getAttribute("placeholder"));
        assertEquals("E-mail для отправки чека", emailForInstallment.getAttribute("placeholder"));
        waitAndClick(servicesDropDownList);
        waitAndClick(arrears);
        assertEquals("Номер счета на 2073", accountNumberForArrears.getAttribute("placeholder"));
        assertEquals("Сумма", amountMoneyForArrears.getAttribute("placeholder"));
        assertEquals("E-mail для отправки чека", emailForArrears.getAttribute("placeholder"));
    }

    public void checkCommunicationServicesSum() {
        clickButtonContinue();
        timeOut();
        assertEquals(testMoney, cost.getAttribute("innerText"));
        assertEquals("Оплатить 100.00 BYN", payCostButton.getAttribute("innerText"));
    }

    public void checkCommunicationServicesNumber() {
        clickButtonContinue();
        timeOut();
        assertEquals("Оплата: Услуги связи Номер:375297777777", number.getAttribute("innerText"));
    }

    public void checkCommunicationServicesLogos() {
        clickButtonContinue();
        timeOut();
        assertFalse(cardLogos.isEmpty(), "Логотипы отсутвуют.");
    }

    public void checkCommunicationServicesCardFields() {
        clickButtonContinue();
        timeOut();
        assertEquals("Номер карты", cardNumber.getAttribute("innerText"));
        assertEquals("Срок действия", validityPeriod.getAttribute("innerText"));
        assertEquals("CVC", cvc.getAttribute("innerText"));
        assertEquals("Имя держателя (как на карте)", cardHolderName.getAttribute("innerText"));
    }
}
