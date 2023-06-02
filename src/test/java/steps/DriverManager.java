package steps;
import com.fasterxml.jackson.databind.ObjectMapper;
import driver.Driver;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;



public class DriverManager extends Driver {
    protected WebDriver driver;
    protected EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
    protected EnvironmentSpecificConfiguration variables;

    protected static List<String> savedStaticClone = null;


    public DriverManager() {
        super(getDriver());
        driver = getDriver();
        variables = new EnvironmentSpecificConfiguration(env);
        if(savedStaticClone == null){
            savedStaticClone = new ArrayList<>();
        }
    }
}