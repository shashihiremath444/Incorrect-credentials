package confirmtktIC;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.confirmtkt.com/rbooking-d/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void login(String mobileNumber, String otp) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='LOGIN']")).click();
        driver.findElement(By.id("mobileNumber")).sendKeys(mobileNumber);
        // Wait for OTP entry
        Thread.sleep(30000);
        driver.findElement(By.id("otp")).sendKeys(otp);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        // Check for login success
        if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='LOGIN']"))) != null) {
            System.out.println("Login failed with incorrect credentials");
        } else {
            System.out.println("Login successful");
        }
    }
}
