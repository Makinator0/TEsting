package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AmazonHomePageMobile {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private String searchTerm;

    public AmazonHomePageMobile(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;  // Cast to AndroidDriver
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void navigateToHomePage() throws InterruptedException {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/signin_fre_continue_button")));
        continueButton.click();
        Thread.sleep(7000); // 7 seconds delay

        WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/button_secondary")));
        skipButton.click();
        Thread.sleep(7000); // 7 seconds delay

        WebElement allowButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/more_button")));
        allowButton.click();
        Thread.sleep(7000); // 7 seconds delay

        WebElement urlField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/ack_button")));
        urlField.click();
        Thread.sleep(7000); // 7 seconds delay

        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/search_box_text")));
        inputField.click();

        WebElement sdfsdf = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/url_bar")));
        sdfsdf.sendKeys("https://www.amazon.com");
        Thread.sleep(7000); // 7 seconds delay

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));  // Use AndroidDriver's pressKey method
        Thread.sleep(7000); // 7 seconds delay
    }

    public void enterSearchTerm() throws InterruptedException {

        WebElement clearSearchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc='Clear search keywords']")));
        clearSearchButton.click();
        Thread.sleep(7000); // 7 seconds delay
        searchTerm = System.getProperty("searchTerm", "Python");
        for (char ch : searchTerm.toCharArray()) {
            // Convert the character to its uppercase version as AndroidKey uses uppercase
            driver.pressKey(new KeyEvent(AndroidKey.valueOf(Character.toString(ch).toUpperCase())));
        }
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void goToBooksCategory() throws InterruptedException {
        WebElement booksElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='Books']")));
        booksElement.click();
        Thread.sleep(7000); // 7 seconds delay
    }
}
