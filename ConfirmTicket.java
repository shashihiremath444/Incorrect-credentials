package confirmtktIC;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

public class ConfirmTicket extends BaseClass {

    public void bookTicket() throws InterruptedException {
        // Assume the user is already logged in
        // Select destination
        driver.findElement(By.xpath("//label[text()='From']")).click();
        driver.findElement(By.xpath("//a[text()='SBC  - Bangalore City Junction']")).click();

        driver.findElement(By.xpath("//label[text()='To']")).click();
        driver.findElement(By.xpath("//a[text()='MMCT  - Mumbai Central']")).click();

        driver.findElement(By.xpath("//span[text()='SEARCH']")).click();
        Thread.sleep(2000);

        // Scroll down for element visibility
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000);");

        driver.findElement(By.xpath("//div[contains(@class, 'train-block')][.//div[contains(text(), '11302 -') and contains(text(), 'udyan exp')]]/following-sibling::div[@style='padding-bottom: 50px;']//div[@class='availability-cache']//div[@class='avail-block']//div[@class='avail-cls' ]//span[text()='3A']")).click();

        driver.findElement(By.xpath("//*[@id='3A']/div[1]/div/div[3]/div")).click();
        Thread.sleep(10000);

        // Enter IRCTC ID manually

        driver.findElement(By.xpath("//span[text()='Male']")).click();

        // Add the name and age of the passenger
        driver.findElement(By.id("passengerName")).sendKeys("Shashidhar");
        driver.findElement(By.id("passengerAge")).sendKeys("24");

        // Select the text from the dropdown using select()
        Select ddown = new Select(driver.findElement(By.id("berth-pref")));
        ddown.selectByVisibleText("Lower Berth");

        driver.findElement(By.xpath("//span[text()='Save']")).click();

        // Once you enter the email ID, it will be saved for the particular mobile number
        driver.findElement(By.id("email")).sendKeys("shashihiremath444@gmail.com");
        driver.findElement(By.xpath("//div[text()='Pay no charges when ticket is cancelled']")).click();
        driver.findElement(By.xpath("//div[text()='PROCEED']")).click();
    }

    public static void main(String[] args) throws InterruptedException {
        ConfirmTicket confirmTicket = new ConfirmTicket();
        confirmTicket.setUp();
        try {
            confirmTicket.login("1234567890", "123456");  // Enter incorrect credentials for testing
            confirmTicket.bookTicket();
        } catch (Exception e) {
			System.out.println("You have entered Incorrect credential");
		}
        
        finally {
            confirmTicket.tearDown();
        }
    }
}
