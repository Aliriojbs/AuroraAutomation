package driver;

import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;

public class Driver extends ThucydidesWebDriverSupport {
    protected WebDriver driver;

    public Driver(WebDriver driver) {
        this.driver = getDriver();
        useDriver(driver);
    }
}
