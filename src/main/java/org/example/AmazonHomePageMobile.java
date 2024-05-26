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
import io.qameta.allure.Step;
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
    @Step("Navigate to Amazon home page")
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
        Thread.sleep(23000);
    }
    @Step("Enter search term")
    public void enterSearchTerm() throws InterruptedException {

        WebElement clearSearchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc='Clear search keywords']")));
        clearSearchButton.click();
        Thread.sleep(7000); // 7 seconds delay
        searchTerm = System.getProperty("searchTerm", "Java");
        for (char ch : searchTerm.toCharArray()) {
            driver.pressKey(new KeyEvent(AndroidKey.valueOf(Character.toString(ch).toUpperCase())));
        }
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(23000); // 7 seconds delay
    }
    @Step("Go to books category")
    public void goToBooksCategory() throws InterruptedException {
        WebElement booksElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Clear search keywords\"]")));
        booksElement.click();
        Thread.sleep(2000); // 7 seconds delay
        driver.pressKey(new KeyEvent(AndroidKey.SPACE));
        driver.pressKey(new KeyEvent(AndroidKey.B));
        driver.pressKey(new KeyEvent(AndroidKey.O));
        driver.pressKey(new KeyEvent(AndroidKey.O));
        driver.pressKey(new KeyEvent(AndroidKey.K));
        driver.pressKey(new KeyEvent(AndroidKey.S));
        Thread.sleep(7000); // 7 seconds delay
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(7000); // 7 seconds delay
    }
}
