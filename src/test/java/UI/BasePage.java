package UI;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import utils.BrowserHelper;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class BasePage{


    String originalWindow;
    JavascriptExecutor je;
    Alert alert;

    BrowserHelper browserHelper = new BrowserHelper();
    protected int timeout = 30;
    protected WebDriver driver;

    public BasePage() {
        this.driver = browserHelper.getChromeDriver();
        this.originalWindow = driver.getWindowHandle();
    }
    public void goTo(String link) {

        driver.get(link);
        waitForPageLoadComplete();
    }

    public void getControlOfOriginalTab() {
        driver.switchTo().window(originalWindow);
    }

    public void switchTabs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(numberOfWindowsToBe(2));
        for(String windowHandle : driver.getWindowHandles()) {
            if(!this.originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void clickCheckBoxes(By CssSelector, List<String> answers) {
        List<WebElement> checkboxes = findElemenstByCssSelector(CssSelector);
        for(String answer : answers) {
            for(WebElement checkbox : checkboxes) {
                if (Objects.equals(checkbox.getText(), answer))
                    click(checkbox);
            }
        }
    }

    public WebElement findElementById(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return driver.findElement(By.id(id));
    }

    public WebElement findElementByClassName(String className) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
        return driver.findElement(By.className(className));
    }

    public WebElement findElementByCssSelector(String CSS_Selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_Selector)));
        return driver.findElement(By.cssSelector(CSS_Selector));
    }

    public WebElement findElementByCssSelector(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element);
    }

    public WebElement findElementByXpath(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        return driver.findElement(By.xpath(xPath));
    }

    public void fill(WebElement textbox, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(textbox));
        je = (JavascriptExecutor) driver;

        je.executeScript("arguments[0].scrollIntoView(true);",textbox);
        textbox.clear();
        textbox.sendKeys(text);
    }

    public void scrollTo(WebElement element) {
        je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrolElement(String cssSelector) {
        je = (JavascriptExecutor) driver;
        je.executeScript("elem = document.querySelector(" + cssSelector + "); elem.scrollTop = elem.scrollIntoView()");
    }

    public void fill(String cssSelector, String text) {
        findElementByCssSelector(cssSelector);
        empty(cssSelector);
        findElementByCssSelector(cssSelector).sendKeys(text);
    }

    public void fill(By element, String text) {
        findElementByCssSelector(element);
        empty(element);
        findElementByCssSelector(element).sendKeys(text);
    }
    public void fillWOEmpty(By element, String text) {
        findElementByCssSelector(element);
        findElementByCssSelector(element).sendKeys(text);
    }

    public void empty(String cssSelector) {
        findElementByCssSelector(cssSelector).clear();
    }

    public void empty(By element) {
        findElementByCssSelector(element).clear();
    }

    public void pressKeyInTextbox(WebElement textbox, Keys keys) {
        je =(JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",textbox);
        textbox.sendKeys(keys);
    }

    public List<WebElement> findElemenstByCssSelector(By CSS_Template) {
        return driver.findElements(CSS_Template);
    }

    public void waitUntilURLContains(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(text)));
    }

    public void waitForRefresh(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public void waitUntilItIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilItIsVisible(String element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(element))));
    }

    public void waitUntilItIsVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public void waitUntilItIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilItIsClickable(String element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(element))));
    }

    public void waitUntilItIsClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
    }

    public void waitUntilItsGone(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilItsGone(String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(cssSelector))));
    }

    public void waitUntilItsGone(By cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(cssSelector)));
    }

    public void waitUntilAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void click(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void click(String cssLocator) {
        findElementByCssSelector(cssLocator);
        getElement(cssLocator).click();
    }

    public void click(By element) {
//        fluentyWaitForElementToBeVisible(element);
//        softWaitUntilItsVisible(element);
        hardWait(3);
        waitUntilItIsClickable(element);
        getElement(element).click();
    }

    public void hover(By element) {
        Actions actions = new Actions(driver);
        findElementByCssSelector(element);
        actions.moveToElement(getElement(element)).perform();
    }

    public void doubleClick(String cssLocator) {
        Actions act = new Actions(driver);
        findElementByCssSelector(cssLocator);
        act.doubleClick(getElement(cssLocator));
    }

    public void doubleClick(By element) {
        Actions act = new Actions(driver);
        findElementByCssSelector(element);
        act.doubleClick(getElement(element));
    }

    public void justClick(String cssLocator) {
        getElement(cssLocator).click();
    }

    public void switchToAlert() {
        alert = driver.switchTo().alert();
    }

    public String getAlertText() {
        return alert.getText();
    }

    public void dismissAlert() {
        alert.dismiss();
    }

    public void acceptAlert() {
        alert.accept();
    }

    public WebElement getElementFromCombobox(WebElement combobox, String text) {
        List<WebElement> comboboxs = combobox.findElements(By.cssSelector(" "));
        for(WebElement element :comboboxs) {
            if(Objects.equals(element.getText(), text)) {
                return element;
            }
        }
        return null;
    }

    public void selectCheckbox(By CssSelector, String text) {
        List<WebElement> checkboxes = driver.findElements(CssSelector);
        for(WebElement element :checkboxes) {
            if(Objects.equals(element.getText(), text)) {
                click(element);
                break;
            }
        }
    }

    public WebElement getElement(By element) {
        return driver.findElement(element);
    }
    public WebElement getElement(String element) {
        return driver.findElement(By.cssSelector(element));
    }

    public void selectFromDropDrown(WebElement dropdown, String option) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }
    public void waitForPageLoadComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(driver1 -> String.valueOf(((JavascriptExecutor) driver1).
                executeScript("return document.readyState")).equals("complete"));
    }

    public void scrollToElement(WebElement webElement) {
        je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",webElement);
    }

    public void scrollElement(String cssSelector) {
        je = (JavascriptExecutor) driver;
        je.executeScript("elem = document.querySelector(" + cssSelector +"); elem.scrollTop = elem.scrollIntoView()");
    }
    public void hardWait(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));

        } catch (Exception e) {

            // sa identific ultimul element de pe pagina si dupa sa inceapa executia
        }
    }
}
