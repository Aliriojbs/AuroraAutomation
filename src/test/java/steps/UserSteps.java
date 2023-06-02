package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import po.chrome.LoginPo;
import po.chrome.UsersPo;

import java.util.Map;


public class UserSteps extends DriverManager {
   LoginPo loginPo = new LoginPo(driver);
    private final String threadName = Thread.currentThread().getName();

    private final UsersPo usersPo = new UsersPo(driver);


    /*
     * Description: Fill new user section
     * Tags: login
     * Section: General
     * Parameters:
     *   role
     * first name
     * middle name
     * last name
     * email
     */
    @And("Fill new user section")
    public void fillNewUserSection(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();

        if (map.containsKey("role")) usersPo.selectRole(map.get("role"));
        if (map.containsKey("first name")) usersPo.fillFirstName(map.get("first name"));
        if (map.containsKey("middle name")) usersPo.fillMiddleName(map.get("middle name"));
        if (map.containsKey("last name")) usersPo.fillLastName(map.get("last name"));
        if (map.containsKey("email")) usersPo.fillEmail(map.get("email"));

    }


    @Then("Click register button")
    public void clickRegisterButton() {
        usersPo.clickRegisterBUtton();    }





}
