package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import po.chrome.AgencyPo;
import po.chrome.LoginPo;
import po.chrome.UsersPo;

import java.util.Map;


public class AgencySteps extends DriverManager {
   LoginPo loginPo = new LoginPo(driver);
    private final String threadName = Thread.currentThread().getName();

    private final AgencyPo agencyPo = new AgencyPo(driver);

    Actions action = new Actions(driver);




    /*
     * Description: Fill basic user information in agency
     * Tags: agency
     * Section: agency
     * Parameters:
     *  agency name
     * company name DBA
     * accounting email
     * producer identifier
     * street
     * phone
     */
    @And("Fill basic user information section in agency page")
    public void fillNewUserSection(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();

        if (map.containsKey("agency name")) agencyPo.fillAgencyName(map.get("agency name"));
        if (map.containsKey("company name DBA")) agencyPo.fillCompanyNameDBA(map.get("company name DBA"));
        if (map.containsKey("accounting email")) agencyPo.fillEmail(map.get("accounting email"));
        if (map.containsKey("producer identifier")) agencyPo.fillProducerIdentifier(map.get("producer identifier"));
        if (map.containsKey("street")) agencyPo.selectStreet(map.get("street"));
        if (map.containsKey("phone")) agencyPo.fillProducerIdentifier(map.get("phone"));

    }


    @And("Fill carriers in agency page")
    public void fillCarriersAgency(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        if (map.containsKey("carriers")) agencyPo.selectCarriers(map.get("carriers"));
        action.sendKeys(Keys.ESCAPE).perform();
    }

    @And("Fill error and omissions section in agency page")
    public void fillError(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        if (map.containsKey("carrier name")) agencyPo.fillCarrierName(map.get("carrier name"));
        if (map.containsKey("policy number")) agencyPo.fillPolicyNumber(map.get("policy number"));
        if (map.containsKey("limit")) agencyPo.fillLimit(map.get("limit"));
        if (map.containsKey("expiration date")) agencyPo.fillExpirationDate(map.get("expiration date"));
    }


    @And("Fill states section in agency page")
    public void fillStatesSection(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
      //TBD till bug
    }

    @And("Fill operations for approval in agency page")
    public void fillOperations(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        if (map.containsKey("operations")) agencyPo.selectOperations(map.get("operations"));
        if (map.containsKey("submit for approval")) agencyPo.selectSubmitForAprovalRadioButton(map.get("submit for approval"));
        if (map.containsKey("require bind physical")) agencyPo.selectRequireBindPhysical(map.get("require bind physical"));
        if (map.containsKey("require bind cargo")) agencyPo.selectRequireBindCargo(map.get("require bind cargo"));
        if (map.containsKey("select block agents")) agencyPo.selectBlockAgents(map.get("select block agents"));
    }

    @And("Fill underwriters in agency page")
    public void fillUnderwritters(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        if (map.containsKey("underwritter")) agencyPo.selectUnderwritter(map.get("underwritter"));
    }

    @And("Fill office in agency page")
    public void fillOffice(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        if (map.containsKey("office")) agencyPo.selectOffice(map.get("office"));
    }


    @Then("Click create button")
    public void clickCreateButton() {
//        agencyPo.clickRegisterBUtton();
    }





}
