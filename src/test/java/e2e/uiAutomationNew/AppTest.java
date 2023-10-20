package e2e.uiAutomationNew;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
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
		//options.addArguments("--incognito");
		options.addArguments("--disable-infobars");
		options.addArguments("--use-fake-ui-for-media-stream"); // Enable microphone access
		// Configure Chrome to// download files without a prompt
        String downloadPath = "C:\\Users\\rajesh.pathak\\Desktop\\HCL docs"; // Set your desired download directory
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-extensions");
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("--download.default_directory=" + downloadPath);
        options.addArguments("--no-pings"); // Disable pinging the server for downloads
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

		Thread.sleep(2000);
		WebElement pause = driver.findElement(By.xpath("//div[@class='btn-record active']"));
		WebElement save = driver.findElement(By.xpath("//div[@class='save-processing']"));
		js.executeScript("arguments[0].click();", pause);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", save);
		Thread.sleep(2000);
		/*
		 * // Schedule a task to keep checking recording status for one hour
		 * ScheduledExecutorService executorService =
		 * Executors.newSingleThreadScheduledExecutor();
		 * executorService.scheduleAtFixedRate(() -> { // Check if recording is still in
		 * progress WebElement stopButton =
		 * driver.findElement(By.xpath("//div[@class='btn-stop']")); if
		 * (stopButton.isDisplayed()) {
		 * System.out.println("Recording is still in progress."); } else {
		 * System.out.println("Recording has finished."); // Optionally, you can perform
		 * additional checks or assertions here Assert.assertTrue(true,
		 * "Recording finished successfully."); executorService.shutdown(); // Stop the
		 * task } }, 0, 1, TimeUnit.MINUTES); // Check every minute
		 * 
		 * // Wait for one hour before completing the test Thread.sleep(60 * 60 * 1000);
		 * // 1 hour in milliseconds
		 */
	}
}
