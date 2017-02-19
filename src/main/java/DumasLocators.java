import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Ilya on 2/19/2017.
 */
public class DumasLocators {

    public WebDriver driver = null;
    public DumasLocators(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@placeholder='User ID']")
    public WebElement userId;

    @FindBy(xpath="//input[@placeholder='Password']")
    public WebElement password;

    @FindBy(xpath="//button[@class='btn btn-default']")
    public WebElement loginBtn;

    @FindBy(xpath = "//*[@value='Any Purpose Loan']/../label")
    public WebElement PersonalLoanLabel;

    @FindBy(xpath = "//*[@value='Debt Consolidation']/../label")
    public WebElement CCDebtConsolRadioBtn;

    @FindBy(xpath = "//*[@value='Any Purpose Loan']")
    public WebElement personalLoanRadio;

    @FindBy(xpath = "//*[@value='Debt Consolidation']")
    public WebElement CCDebtRadio;

    @FindBy(xpath = "//input[contains(@id,'personal-loan-amount')]")
    public WebElement LoanAmountText;

    @FindBy(xpath = "//label[contains(@id,'personal-loan-amount')]")
    public WebElement loanAmountLabel;

    public  void login(){
        userId.click();
        userId.sendKeys("duma23073");
        password.click();
        password.sendKeys("flower1");
        loginBtn.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setLoan(){
        PersonalLoanLabel.click();
        loanAmountLabel.click();
        LoanAmountText.sendKeys("5555");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
