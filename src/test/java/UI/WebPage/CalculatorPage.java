package UI.WebPage;

import UI.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage extends BasePage {

    public CalculatorPage() {
        super();
    }

    @FindBy(css = "div [class='hh'] [href='/math-calculator.html']") public WebElement mathBtn;
    @FindBy(css = "[class='sectionlists'] [href='/percent-calculator.html']") public WebElement perBtn;
    @FindBy(css = "[id=cpar1]") public WebElement firstValue;
    @FindBy(css = "[id=cpar2]") public WebElement secondValue;
    @FindBy(css = "[value = 'Calculate']") public WebElement cltBtn;
    @FindBy(css = "div [id='content'] [class='h2result']") public WebElement result;

    public final By MATH_BTN = By.cssSelector("div [class='hh'] [href='/math-calculator.html']");
    public final By PER_BTN = By.cssSelector("[class='sectionlists'] [href='/percent-calculator.html']");
    public final By FIRST_VALUE = By.cssSelector("[id=cpar1]");
    public final By SECOND_VALUE = By.cssSelector( "[id=cpar2]");
    public final By CLT_BTN = By.cssSelector("[value = 'Calculate']");
    public final By RESULT = By.cssSelector("div [id='content'] [class='h2result']");
    public final By LOGO = By.cssSelector("[id='logo']");



    public void goToCalculatorPage() {
        goTo("http://www.calculator.net/");

    }
    public void calculateSimpleTest()   {



//        findElementByCssSelector("div [class='hh'] [href='/math-calculator.html']").click();
//        findElementByCssSelector("[class='sectionlists'] [href='/percent-calculator.html']").click();
//        fill("[id=cpar1]","40");
//        fill("[id=cpar2]","40");
//        findElementByCssSelector("[value = 'Calculate']").click();
//        getElement("div [id='content'] [class='h2result']").getText();

        findElementByCssSelector(MATH_BTN).click();
        findElementByCssSelector(PER_BTN).click();
        fill(findElementByCssSelector(FIRST_VALUE),"40");
        fill(findElementByCssSelector(SECOND_VALUE),"5");
        findElementByCssSelector(CLT_BTN).click();
        System.out.println(getElement(RESULT).getText());
    }

    public boolean getTitle() {
        findElementByCssSelector(LOGO).getText();
        return true;
    }

}
