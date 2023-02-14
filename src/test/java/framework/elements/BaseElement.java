package framework.elements;

import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public abstract class BaseElement {
    protected WebElement element;
    protected List<WebElement> elements;
    private By by;
    private String name;
    private WebDriverWait wait;

    public BaseElement(By by) {
        this.by = by;
    }

    public BaseElement(By by, String name) {
        this.by = by;
        this.name = name;
    }

    public WebElement getElement() {
        isElementPresent();
        return element;
    }

    protected abstract String getElementType();

    public boolean isElementPresent() {
        try {
            Browser.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(Browser.getTimeoutForCondition()), TimeUnit.SECONDS);
            element = Browser.getDriver().findElement(by);
            System.out.println(getElementType() + ":" + by + " - is present");
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(getElementType() + ":" + by + " - is not present. Exception: NoSuchElementException");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return false;
    }

    public boolean isDisplayed() {
        isElementPresent();
        return element.isDisplayed();
    }

    public void click() {
        isElementPresent();
        element.click();
    }

    public void clickAndWait() {
        isElementPresent();
        element.click();
        Browser.waitForPageToLoad();
    }

    public void clickViaJS() {
        isElementPresent();
        if (Browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) Browser.getDriver()).executeScript("arguments[0].style.border='3px solid blue'", element);
        }
        ((JavascriptExecutor) Browser.getDriver()).executeScript("arguments[0].click();", element);
    }
}
