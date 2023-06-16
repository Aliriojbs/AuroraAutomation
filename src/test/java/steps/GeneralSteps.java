package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import po.chrome.ChromeHomePo;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class GeneralSteps {
    private final ChromeHomePo chromeHomePo = new ChromeHomePo();

    @Given("I open the google webpage")
    public void iOpenTheGoogleWebpage() {
        getDriver().get("https://google.com");
    }
    @Given("I open the AURORA webpage")
    public void iOpenTheAuroraWebpage() {
        getDriver().get("https://aurora-web-testingv2.azurewebsites.net/");
    }

    @Given("I open the AURORA add users page")
    public void iOpenTheAuroraAddUserWebpage() {
        getDriver().get("https://aurora-web-testingv2.azurewebsites.net/users/create-user");
    }

    @Given("I open the AURORA edit users page")
    public void iOpenTheAuroraEditUserWebpage() {
        getDriver().get("https://aurora-web-testingv2.azurewebsites.net/users/edit-profile");
    }
    @Given("I open the AURORA add agency page")
    public void iOpenTheAuroraAgencyWebpage() {
        getDriver().get("https://aurora-web-testingv2.azurewebsites.net/home/agencies-catalog/create-agency");
    }

    @Then("I send a text to the search bar")
    public void iSendATextToTheSearchBar() {
        chromeHomePo.fillSearchBar();
    }
}
