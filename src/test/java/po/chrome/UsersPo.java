package po.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UsersPo extends BasePagePo {
    @FindBy(css = "select[formcontrolname=rol]")
    private WebElement roleDropdown;

    @FindBy(css = "input[formcontrolname=firstName]")
    private WebElement firstNameInput;

    @FindBy(css = "input[formcontrolname=middleName]")
    private WebElement middleNameInput;

    @FindBy(css = "input[formcontrolname=lastName]")
    private WebElement lastNameInput;

    @FindBy(css = "input[formcontrolname=email]")
    private WebElement emailInput;


    @FindBy(css = "button[type=submit]")
    private WebElement registerButton;


    //EDIT

    @FindBy(css = "input[formcontrolname=name]")
    private WebElement firstNameEditInput;




    public UsersPo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void selectRole(String option) {
        this.selectOptionClassicDropDown(this.roleDropdown, option);
    }

    public void fillFirstName(String option) {
        this.typeText(this.firstNameInput,option);
    }

    public void fillFirstNameEdit(String option) {
        this.deleteFieldContendByKeySequence(this.firstNameEditInput);
        this.typeText(this.firstNameEditInput,option);
    }

    public void fillFirstMiddleEdit(String option) {
        this.deleteFieldContendByKeySequence(this.middleNameInput);
        this.typeText(this.middleNameInput,option);
    }

    public void fillFirstLastEdit(String option) {
        this.deleteFieldContendByKeySequence(this.lastNameInput);
        this.typeText(this.lastNameInput,option);
    }

    public void fillMiddleName(String option) {
        this.typeText(this.middleNameInput,option);
    }

    public void fillLastName(String option) {
        this.typeText(this.lastNameInput,option);
    }

    public void fillEmail(String option) {
        this.typeText(this.emailInput,option);
    }

    public void clickRegisterBUtton() {
        this.clickElement(this.registerButton);
    }



}
