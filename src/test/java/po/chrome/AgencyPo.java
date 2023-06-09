package po.chrome;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgencyPo extends BasePagePo {

    Actions action = new Actions(driver);

    //Basic Information

    @FindBy(css = "input[formcontrolname=agencyName]")
    private WebElement agencyNameInput;

    @FindBy(css = "input[formcontrolname=companyName]")
    private WebElement companyNameInput;

    @FindBy(css = "input[formcontrolname=accountingEmail]")
    private WebElement accountingEmailInput;

    @FindBy(css = "input[formcontrolname=producerIdentifier]")
    private WebElement producerIdentifierInput;

    @FindBy(css = "input[formcontrolname=street]")
    private WebElement streetInput;

    @FindBy(css = "input[formcontrolname=street2]")
    private WebElement street2Input;

    @FindBy(css = "input[formcontrolname=city]")
    private WebElement cityInput;

    @FindBy(css = "select[formcontrolname=state]")
    private WebElement stateDropdown;

    @FindBy(css = "input[formcontrolname=zip]")
    private WebElement zipInput;

    @FindBy(css = "input[formcontrolname=phoneNumber]")
    private WebElement phoneNumberInput;


    //Configurations
    @FindBy(css = "mat-select[formcontrolname=carriers]")
    private WebElement carriersDropdown;



  //ERROR AND OMISSIONS AGENCY


    @FindBy(css = "input[formcontrolname=carrierName]")
    private WebElement carrierNameInput;

    @FindBy(css = "input[formcontrolname=policyNumber]")
    private WebElement policyNumberInput;

    @FindBy(css = "input[formcontrolname=limit]")
    private WebElement limitInput;

    @FindBy(css = "input[formcontrolname=expirationDate]")
    private WebElement expirationDateInput;

    @FindBy(xpath = "//button[contains(text(), ' Add State ')]")
    private WebElement addStateButton;

    //STATES

    //TBD

    //Operations Submit for Approval
    @FindBy(css = "mat-select[formcontrolname=operations]")
    private WebElement operationsDropdown;

    @FindBy(css = "input[id=rd_1]")
    private WebElement submitAppprovalYes;

    @FindBy(css = "input[id=rd_2]")
    private WebElement submitAppprovalNo;

    @FindBy(css = "input[id=rd_3]")
    private WebElement requireBindPhysicalYes;

    @FindBy(css = "input[id=rd_4]")
    private WebElement requireBindPhysicalNo;

    @FindBy(css = "input[id=rd_5]")
    private WebElement requireBindCargoYes;

    @FindBy(css = "input[id=rd_6]")
    private WebElement requireBindCargoNo;

    @FindBy(css = "input[id=rd_7]")
    private WebElement blockAgentsYes;

    @FindBy(css = "input[id=rd_8]")
    private WebElement blockAgentsNo;


    //Assigned Underwriters
    @FindBy(css = "mat-select[formcontrolname=reporter]")
    private WebElement reporterDropdown;

    //OFFICE
    @FindBy(css = "mat-select[formcontrolname=office]")
    private WebElement officeDropdown;


    @FindBy(css = "button[type=submit]")
    private WebElement createButton;




    public AgencyPo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }



    public void fillAgencyName(String option) {
        this.typeText(this.agencyNameInput,option);
    }

    public void fillCompanyNameDBA(String option) {
        this.typeText(this.companyNameInput,option);
    }

    public void fillProducerIdentifier(String option) {
        this.typeText(this.producerIdentifierInput,option);
    }

    public void fillEmail(String option) {
        this.typeText(this.accountingEmailInput,option);
    }

    public void selectStreet(String option) {
        this.typeText(this.streetInput,option);
        this.selectOptionClassicDropDown(this.streetInput,option,"//span[text()= \"Taylors, SC, USA\"]" );
    }

    public void fillStreet2(String option) {
        this.typeText(this.street2Input,option);
    }
    public void fillZipCode(String option) {
        this.typeText(this.zipInput,option);
    }
    public void fillPhone(String option) {
        this.typeText(this.phoneNumberInput,option);
    }


    public void selectCarriers(String option) {
        this.carriersDropdown.click();
        this.selectOptionClassicDropDown(this.carriersDropdown, option,"//span[@class='mat-option-text' and contains(text(), '%s')]");
        action.sendKeys(Keys.ESCAPE).perform();
    }

    public void fillCarrierName(String option) {
        this.typeText(this.carrierNameInput,option);
    }
    public void fillPolicyNumber(String option) {
        this.typeText(this.policyNumberInput,option);
    }
    public void fillLimit(String option) {
        this.typeText(this.limitInput,option);
    }
    public void fillExpirationDate(String option) { this.typeText(this.expirationDateInput,option); }


    //States Fills pending

    public void selectOperations(String option) {
        this.operationsDropdown.click();
        this.selectOptionClassicDropDown(this.operationsDropdown, option,"//span[@class='mat-option-text' and contains(text(), '%s')]");
        action.sendKeys(Keys.ESCAPE).perform();
    }

    public void selectSubmitForAprovalRadioButton(String option) {
        if(option=="yes"){
            this.clickElement(submitAppprovalYes);
        } else if (option=="no") {
            this.clickElement(submitAppprovalNo);
        }else{
            System.out.println("Option Not found");
        }
    }

    public void selectRequireBindPhysical(String option) {
        if(option=="yes"){
            this.clickElement(requireBindPhysicalYes);
        } else if (option=="no") {
            this.clickElement(requireBindPhysicalNo);
        }else{
            System.out.println("Option Not found");
        }
    }

    public void selectRequireBindCargo(String option) {
        if(option=="yes"){
            this.clickElement(requireBindCargoYes);
        } else if (option=="no") {
            this.clickElement(requireBindCargoNo);
        }else{
            System.out.println("Option Not found");
        }
    }

    public void selectBlockAgents(String option) {
        if(option=="yes"){
            this.clickElement(blockAgentsYes);
        } else if (option=="no") {
            this.clickElement(blockAgentsYes);
        }else{
            System.out.println("Option Not found");
        }
    }

    public void selectUnderwritter(String option) {
        this.reporterDropdown.click();
        this.selectOptionClassicDropDown(this.reporterDropdown, option,"//span[@class='mat-option-text' and contains(text(), '%s')]");
        action.sendKeys(Keys.ESCAPE).perform();
    }

    public void selectOffice(String option) {
        this.officeDropdown.click();
        this.selectOptionClassicDropDown(this.officeDropdown, option,"//span[@class='mat-option-text' and contains(text(), '%s')]");
        action.sendKeys(Keys.ESCAPE).perform();
    }





}
