package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.annotations.events.AfterScenario;
import org.openqa.selenium.TimeoutException;
import po.chrome.LoginPo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoginSteps extends DriverManager {
   LoginPo loginPo = new LoginPo(driver);
    private final String threadName = Thread.currentThread().getName();




    /*
     * Description: Login into the Aurora application
     * Tags: login
     * Section: General
     * Parameters:
     *   N/A
     */
    @Then("Login into the AURORA application")
    public void iTypeMyUserName() throws InterruptedException {
        loginPo.fillUserName();
    }


}
