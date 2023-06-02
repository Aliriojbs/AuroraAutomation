package po.chrome;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import po.chrome.BasePagePo;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class LoginPo extends BasePagePo {
    @FindBy(css = "input[formcontrolname=userName]")
    private WebElement emailField;


    @FindBy(css = "input[formcontrolname=password]")
    private WebElement passwordField;

    @FindBy(css = "button[type=submit]")
    private WebElement logInButton;


    public LoginPo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void fillUserName() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String password = "Admin123!";
        String email = "sergioc@advancio.com";
        wait.until(ExpectedConditions.visibilityOf(this.emailField));
        this.emailField.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(this.passwordField));
        this.passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(this.logInButton));
        this.logInButton.click();
    }
}
