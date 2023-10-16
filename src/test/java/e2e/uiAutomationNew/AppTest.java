package e2e.uiAutomationNew;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {
	WebDriver driver;

	  @Test
	    public void verifyUiLaunch() throws InterruptedException {
	        // Setup ChromeOptions to run in incognito mode and disable notifications
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--incognito");
	        options.addArguments("--disable-infobars");
	        options.addArguments("--use-fake-ui-for-media-stream"); // Enable microphone access
	        // Initialize the ChromeDriver with the configured options
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver(options);

	        driver.get("https://online-voice-recorder.com/");
	        driver.manage().window().maximize();
	        Thread.sleep(2000);
	        // Use JavaScriptExecutor to click the element
	        WebElement recordButton = driver.findElement(By.xpath("//div[@class='btn-record']"));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", recordButton);
	    }
}
