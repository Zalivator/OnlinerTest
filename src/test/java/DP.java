import framework.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DP {
    @DataProvider (name = "login")
    public Object[][] dpMethod() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"", "secret_sauce"},
                {"standard_user", ""}
        };
    }

    WebDriver driver;

    @Test (dataProvider = "login")
    public void loginDataTest (String login, String pass) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(new PropertyReader("config.properties").getIntProperty("timeout.for.page.load"), TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.click();
        userName.sendKeys(login);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(pass);

        driver.findElement(By.id("login-button")).click();

        if (driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed()) {
            System.out.println("Успешный вход");
        } else {
            System.out.println("Что-то пошло не так...");
        }
        driver.quit();
    }

}
