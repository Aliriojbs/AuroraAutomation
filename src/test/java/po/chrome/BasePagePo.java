package po.chrome;

import driver.Driver;
import lombok.SneakyThrows;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasePagePo extends Driver {
    protected WebDriverWait wait;
    Actions actions;
    protected String options = "option[value=%s]";
    protected String options2 = "//div[@class = 'option-container' and text()= \" %s \"]";
    protected String options1 = "//span[@class = 'mat-option-text' and text()= ' MC # 420047 - DOT # 1 - Dup Do Not Use - MIDWEST COAST LOGISTICS INC ']";
    protected EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
    protected EnvironmentSpecificConfiguration variables;

    protected JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//span[@class = 'mat-option-text']")
    protected List<WebElement> optionList;

    @FindBy(xpath = "//mat-error")
    private WebElement error;

    @FindBy(xpath = "//span[text() = 'ADD FACILITY']/ancestor::a")
    private WebElement addFacilityButton;

    @FindBy(xpath = "//tms-shipment-event-wrapper//mat-icon[contains(@class, 'facilitity-clear-icon')]")
    private WebElement facilityClearIcon;

    @FindBy(xpath = "//mat-spinner")
    private List<WebElement> allSpinners;

    String hoverMessage = "//div[contains(@class, 'mat-tooltip') and text() = '%s']";

    public BasePagePo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        variables = new EnvironmentSpecificConfiguration(env);
    }

    public void checkVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } finally {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    public void checkDropdownPopulated(WebElement dropdown) {
        try {
            wait.until(ExpectedConditions.visibilityOf(dropdown));
        } finally {
            String actualText = dropdown.getAttribute("childElementCount");
            Assert.assertTrue(Integer.parseInt(actualText) > 2);
        }
    }

    public void checkListElements(WebElement List, int elements) {
        try {
            wait.until(ExpectedConditions.visibilityOf(List));
        } finally {
            String actualText = List.getAttribute("childElementCount");
            Assert.assertEquals(elements, Integer.parseInt(actualText));
        }
    }

    public void checkDropdownIsNotPopulated(WebElement dropdown) {
        try {
            wait.until(ExpectedConditions.visibilityOf(dropdown));
        } finally {
            String actualText = dropdown.getAttribute("childElementCount");
            Assert.assertTrue(Integer.parseInt(actualText) <= 2);
        }
    }

    public void checkIfElementDoesntExistByCss(String css) {
        List<WebElement> listReleaseShipmentButton = driver.findElements(By.cssSelector(css));
        if (listReleaseShipmentButton.isEmpty()) {
            Assert.assertTrue("Element is not existent in DOM", true);
        }
    }

    public boolean checkIfElementExistsByXpath(String xpath) {
        List<WebElement> expectedElementList = driver.findElements(By.xpath(xpath));
        return !expectedElementList.isEmpty();   // Element exists = Return True
    }

    public void typeText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public void selectOptionInTypeAhead(WebElement element, String option) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);

        String xpath = String.format(this.options, option);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement selectedOption = element.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        selectedOption.click();
    }

    public void selectOptionInTypeAhead(WebElement element, String option, String optionXpath) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);
        String xpath = String.format(optionXpath, option);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement selectedOption = element.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        clickElement(selectedOption);
    }

    public void selectOptionInTypeAheadWithJavascript(WebElement element, String option, String optionXpath) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);
        String xpath = String.format(optionXpath, option);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement selectedOption = element.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", selectedOption);
    }

    public void displayOptionInTypeAhead(WebElement element, String option, String optionXpath) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);
    }


    public void selectOptionInTypeAheadForDropdown(WebElement element, String option, String optionForDropdown) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);

        String xpath = String.format(this.options, optionForDropdown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement selectedOption = element.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        selectedOption.click();
    }

    public void selectOptionInTypeAheadForDropdownNeedingEnterPress(WebElement element, String option, String optionForDropdown, boolean vendor) {
        Actions action = new Actions(driver);
        String xpath;
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);
        action.sendKeys(Keys.ENTER).perform();
        if (vendor) {
            xpath = String.format("//span[contains(text(), '%s')]/parent::mat-option", optionForDropdown);
        } else {
            xpath = String.format("//span[contains(text(), 'MC # %s')]/parent::mat-option", optionForDropdown);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement selectedOption = element.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        selectedOption.click();
    }

    public void selectOptionInTypeAheadForDropdownNeedingEnterPressVendor(WebElement element, String option, String optionForDropdown) {
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(option);
        action.sendKeys(Keys.ENTER).perform();
        String xpath = String.format(this.options, optionForDropdown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement selectedOption = element.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        selectedOption.click();
    }


    public void selectOptionInTypeAheadForDropdownCheckbox(String option) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//input[contains(@class, 'mat-input')]")));

        wait.until(ExpectedConditions.visibilityOf(inputElement));
        inputElement.sendKeys(option);

        String optionXpath = String.format("//span[text() = ' %s ']/preceding-sibling::mat-pseudo-checkbox", option);
        WebElement optionCheckBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath)));
        optionCheckBox.click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
    }

    protected void waitUntilOptionIsVisible(WebElement element, String optionXpath) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        wait.until(
                driver -> {
                    try {
                        element.click();
                    } catch (ElementClickInterceptedException e) {
                        e.getMessage();
                    }
                    return driver.findElement(By.xpath(optionXpath));
                }
        );
    }

    public void hoverOptionClassicDropDown(WebElement element, String option, String xpathForAllOptions) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String xpath = String.format(xpathForAllOptions, option);

        waitUntilOptionIsVisible(element, xpath);
        WebElement selectedOption = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        this.hoverAnElement(selectedOption);
    }

    public void selectOptionClassicDropDown(WebElement element, String option) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String css = String.format(this.options, option);
        System.out.println(css);
//        waitUntilOptionIsVisible(element, css);
        WebElement selectedOption = driver.findElement(By.cssSelector(css));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOption));
        this.clickElement(selectedOption);
    }

    public void selectOptionClassicDropDown(WebElement element, String selectedOption, String xpathForAllOptions) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String xpath = String.format(xpathForAllOptions, selectedOption);
        WebElement selectedOptionElement = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(selectedOptionElement));
        selectedOptionElement.click();
    }

    public void selectOptionClassicDropDown2(WebElement element, String option) {
        this.selectOptionClassicDropDown(element, option, this.options2);
    }

    public void clickElement(WebElement element) {
        scrollToElement(element);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitElementToBeInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static String formattedDateTodayBase() {
        //creates a utc date and transforms it to Pacific time
        //  return moment.utc().tz("America/Los_Angeles").format(this._formatForms);
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        ZoneId losAngelesTimeZone = ZoneId.of("America/Los_Angeles");
        // Converting Current timezone time to Log Angeles time
        ZonedDateTime losAngelesDateTime = currentDateTime.withZoneSameInstant(losAngelesTimeZone);
        // Datetime formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyyHHmm");
        return formatter.format(losAngelesDateTime);
    }

    public void validateOptionsInDropDown(List<String> options) {
        boolean first = true;
        for (String option : options) {
            String xpath = String.format(this.options, option);
            if (first) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                first = false;
            }
            Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
        }
    }

    public void validateOptionsInDropDown(List<String> options, String xpathOption) {
        boolean first = true;
        for (String option : options) {
            String xpath = String.format(xpathOption, option);
            if (first) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                first = false;
            }
            Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
        }
    }

    @SneakyThrows
    public void checkPermanenceInput(WebElement element, String expectedText, Boolean isinputField) {
        if (isEmptyString(expectedText))
            checkPermanenceInputIsEmpty(element, isinputField);
        else {
            this.scrollToElement(element);
            this.checkVisibility(element);
            String actualText;
            if (isinputField) {
                actualText = element.getAttribute("value").trim();
            } else {
                actualText = element.getAttribute("textContent").trim();
            }
            Assert.assertEquals(expectedText, actualText);
        }

    }

    public boolean isEmptyString(String expectedText) {
        if (expectedText.equals("empty_string")) {
            return true;
        }
        return false;
    }

    public void checkPermanenceInputIsEmpty(WebElement element, Boolean isinputField) throws UnsupportedEncodingException {
        this.checkVisibility(element);
        String actualText;
        if (isinputField) {
            actualText = element.getAttribute("value").replaceAll("[\\n\\r\\xA0]", " ").trim();
        } else {
            actualText = element.getAttribute("textContent").replaceAll("[\\n\\r\\xA0]", " ").trim();
        }

        Assert.assertTrue(actualText.isBlank());
    }

    public void checkPermanenceInputDate(WebElement element, String TextProvided) {
//        wait.until(ExpectedConditions.textToBePresentInElement(element,option));
        this.checkVisibility(element);

        String actualText, finalDate;

        actualText = element.getAttribute("value").trim();
        /*
         *  converting date from ("01/01/2020 21:00")  to 010120202100
         */
        String[] DateNoSpaces = actualText.split(" ");
        String[] DatePart1 = DateNoSpaces[0].split("/");
        String[] DatePart2 = DateNoSpaces[1].split(":");

        finalDate = DatePart1[0] + DatePart1[1] + DatePart1[2] + DatePart2[0] + DatePart2[1];
        Assert.assertEquals(finalDate, TextProvided);


    }


    @SneakyThrows
    public void checkPermanenceDynamicInputDate(WebElement element, String expectedDate) {
        if (isEmptyString(expectedDate)) {
            checkPermanenceInputIsEmpty(element, true);
        } else {
            String actualText, finalDate;
            expectedDate = setFormatDates(expectedDate);
            actualText = element.getAttribute("value").trim();
            /*
             *  converting date from ("01/01/2020 21:00")  to 010120202100
             */
            String[] DateNoSpaces = actualText.split(" ");
            String[] DatePart1 = DateNoSpaces[0].split("/");
            String[] DatePart2 = DateNoSpaces[1].split(":");

            finalDate = DatePart1[0] + DatePart1[1] + DatePart1[2] + DatePart2[0] + DatePart2[1];
            Assert.assertEquals(expectedDate,finalDate);
        }
    }
    @SneakyThrows
    public void checkPermanenceDynamicAndLiteralInputDate(WebElement element, String expectedDate) {
        if (isEmptyString(expectedDate)) {
            checkPermanenceInputIsEmpty(element, true);
        } else if (expectedDate.contains("today")) {
            checkPermanenceDynamicInputDate(element, expectedDate);
        } else {
            this.checkPermanenceInputDate(element, expectedDate);
        }
    }

    @SneakyThrows
    public void checkPermanenceInputDateWithoutTime(WebElement element, String expectedDate) {
        if (isEmptyString(expectedDate)) {
            checkPermanenceInputIsEmpty(element, true);
        } else {
            this.checkVisibility(element);
            String actualText, finalDate;
            expectedDate = setFormatOnlyDates(expectedDate);
            actualText = element.getAttribute("value").trim();
            /*
             *  converting date from ("01/01/2020")  to 01012020
             */
            String[] DateNoSpaces = actualText.split(" ");
            String[] DatePart1 = DateNoSpaces[0].split("/");

            finalDate = DatePart1[0] + DatePart1[1] + DatePart1[2];
            Assert.assertEquals(expectedDate, finalDate);
        }
    }

    public void checkEmptyInput(WebElement element) {
        this.checkVisibility(element);
        String actualText;

        actualText = element.getAttribute("value").trim();
        Assert.assertTrue(actualText.isEmpty());
    }

    public void checkEmptyElement(WebElement element) {
        this.checkVisibility(element);
        String actualText;

        actualText = element.getAttribute("textContent");
        Assert.assertTrue(actualText.length() <= 1);
    }

    public void checkPermanenceInputCurrency(WebElement element, String TextProvided) {
//        wait.until(ExpectedConditions.textToBePresentInElement(element,option));
        this.checkVisibility(element);
        String actualText, finalDate;

        actualText = element.getAttribute("value").trim();
        /*
         *  converting currency from ("$ 17.00") to 17.00 COMMAS STANDS, MAKE SURE USE COMMAS IN THE GHERKINS FILE
         */
        String[] DateNoSpaces = actualText.split(" ");
        finalDate = DateNoSpaces[1];
        Assert.assertEquals(finalDate, TextProvided);
    }

    public void navigateTo(String link) {
        switch (link) {
            case "Dartboards": {
                driver.navigate().to("https://brokerage-uat-tms.azurewebsites.net/dartboards");
                WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
                break;
            }
            case "CreateShipment": {
                driver.navigate().to("https://brokerage-uat-tms.azurewebsites.net/createshipment");
                break;
            }
            default: {
                System.out.println("Option undefined ⚠");
            }
        }
    }

    public void detectTab(int tabNumber) {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(tabNumber));
    }

    public void closeTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }

    public void closeOtherTabAndReturnToOriginalTab(int indexTabToClose) {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        int originalIndex = tabs2.indexOf(driver.getWindowHandle());
        driver.switchTo().window(tabs2.get(indexTabToClose));
        driver.close();
        driver.switchTo().window(tabs2.get(originalIndex));
    }

    public void waitForNewTabAndSwitch() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        wait.until(
                driver -> {
                    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    if (tabs.size() > 1) {
                        driver.switchTo().window(tabs.get(tabs.size() - 1));
                        return true;
                    } else {
                        return false;
                    }
                }
        );
    }

    public void waitForNewTabAndSwitch(int actualSize) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        wait.until(
                driver -> {
                    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    if (tabs.size() > actualSize) {
                        driver.switchTo().window(tabs.get(tabs.size() - 1));
                        return true;
                    } else {
                        return false;
                    }
                }
        );
    }

    public void waitForTextNotEmpty(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        wait.until(
                driver -> {
                    System.out.println(element.getText());
                    return !element.getText().isEmpty();
                }
        );
    }

    public void deleteFieldContendByKeySequence(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "A"));
        element.sendKeys(Keys.DELETE);
    }

    public boolean checkDisabledElement(WebElement element) {
        return element.isEnabled();
    }

    public void validateIsDisabled(WebElement element, Boolean isInput) {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (!isInput) {
            Assert.assertEquals("true", element.getAttribute("aria-disabled"));
        } else {
            Assert.assertEquals("true", element.getAttribute("disabled"));
        }
    }
    public void validateIsEnabled(WebElement element, Boolean isInput) {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (isInput == false) {
            Assert.assertEquals("false", element.getAttribute("aria-disabled"));
        } else {

            Assert.assertEquals("false", element.getAttribute("disabled"));
        }
    }

    public void scrollToElement(WebElement Element) {
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }


    public WebElement cssSelectorBuilder(String builder, String variable) {
        String css = String.format(builder, variable);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }

    public void validateOptionsInDropDownAlphabeticalOrder() {
        List<String> optionValues = new ArrayList<>();
        for (WebElement option : this.optionList) {
            optionValues.add(option.getText());
        }
        List<String> optionValuesSorted = new ArrayList<>(optionValues);
        Collections.sort(optionValuesSorted);
        Assert.assertArrayEquals(optionValues.toArray(), optionValuesSorted.toArray());
    }

    public void checkTextLabel(WebElement element, String textProvided) {
        this.checkVisibility(element);
        String actualText;
        actualText = element.getAttribute("textContent").trim();
        Assert.assertEquals(textProvided, actualText);
    }

    public void validateNoErrorIsDisplayed() {
        wait.until(ExpectedConditions.invisibilityOfAllElements(this.error));
    }

    public void hoverAnElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void validaTeHoverMessageForAddFacilityButton(String expectedHoverMessage) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(this.addFacilityButton));
        scrollToElement(addFacilityButton);
        hoverAnElement(this.addFacilityButton);

        String xpath = String.format(hoverMessage, expectedHoverMessage);
        WebElement hoverMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        Assert.assertEquals(expectedHoverMessage, hoverMessageElement.getText().trim());
    }

    public void clearFacility() {
        this.clickElement(facilityClearIcon);
    }

    public String setFormatDates(String stringDate) {
        int dayDifference = 0;
        boolean flag = true;
        if (stringDate.contains("today")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy1200");
            LocalDateTime now = LocalDateTime.now();
            if (stringDate.contains("minus")) {
                dayDifference = Integer.parseInt(stringDate.split("minus ")[1]);
                stringDate = dtf.format(now.minusDays(dayDifference));
                flag = false;
            }
            if (stringDate.contains("plus")) {
                dayDifference = Integer.parseInt(stringDate.split("plus ")[1]);
                stringDate = dtf.format(now.plusDays(dayDifference));
                flag = false;
            }
            if (flag) {
                stringDate = dtf.format(now);
            }
        }
        return stringDate;
    }

    public String setFormatDates(String stringDate, String hours) {
        int dayDifference = 0;
        boolean flag = true;
        if (stringDate.contains("today")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy" + hours);
            LocalDateTime now = LocalDateTime.now();
            if (stringDate.contains("minus")) {
                dayDifference = Integer.parseInt(stringDate.split("minus ")[1]);
                stringDate = dtf.format(now.minusDays(dayDifference));
                flag = false;
            }
            if (stringDate.contains("plus")) {
                dayDifference = Integer.parseInt(stringDate.split("plus ")[1]);
                stringDate = dtf.format(now.plusDays(dayDifference));
                flag = false;
            }
            if (flag) {
                stringDate = dtf.format(now);
            }
        }
        return stringDate;
    }

    public String setFormatOnlyDates(String stringDate) {
        int dayDifference = 0;
        boolean flag = true;
        if (stringDate.contains("today")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
            LocalDateTime now = LocalDateTime.now();
            if (stringDate.contains("minus")) {
                dayDifference = Integer.parseInt(stringDate.split("minus ")[1]);
                stringDate = dtf.format(now.minusDays(dayDifference));
                flag = false;
            }
            if (stringDate.contains("plus")) {
                dayDifference = Integer.parseInt(stringDate.split("plus ")[1]);
                stringDate = dtf.format(now.plusDays(dayDifference));
                flag = false;
            }
            if (flag) {
                stringDate = dtf.format(now);
            }
        }
        return stringDate;
    }

    public void waitUntilAllSpinnersDisappear() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(NoSuchElementException.class);

        wait.until(
                driver -> allSpinners.size() == 0
        );
    }

    public void selectOptionClassicDropDownJavaScript(WebElement element, String selectedOption, String xpathForAllOptions) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String xpath = String.format(xpathForAllOptions, selectedOption);

        waitUntilOptionIsVisible(element, xpath);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", driver.findElement(By.xpath(xpath)));
    }

    public void selectOptionInTypeAheadForDropdownCheckbox(String option, String optionForDropdown) {
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option//input[contains(@class, 'mat-input')]")));
        inputElement.sendKeys(option);

        String optionXpath = String.format(optionForDropdown, option);
        WebElement optionCheckBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXpath)));
        optionCheckBox.click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
    }


}
