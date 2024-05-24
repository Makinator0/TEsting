package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BookDetailsPageMobile {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private String searchTerm;
    private String bookUrl = "https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997/ref=sr_1_1?crid=LFH2JLGS3V8X&dib=eyJ2IjoiMSJ9.95M_fzc7E-PFO4Y-2pVeJjiZBHRYf4rUlqL_xYgfJrKezE0EaUQG9MWrVXI8HkHer1bLZ9uNbR4sHH63GEZ8-aKlmHJLnxfgj7527rQaVJt0FnO3VwXsiW6GcZZmTP3CMwxXfEfohN70gKLcjmEo8kJI9_fONAKII4R8y6-ERPBcN7VQ7PNbm0cWc7pL_AKyRRkusu3cDWVkDq1BHefWZp4r_msYRx9gmE7wrzdP6Hs.qL5appUVJPupeZMD8JZ3YnLBK4OYvfG9sEo4A-TKAB4&dib_tag=se&keywords=java+books&qid=1716561462&s=books&sprefix=java+books%2Cstripbooks-intl-ship%2C323&sr=1-1";
    public BookDetailsPageMobile(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        searchTerm = System.getProperty("searchTerm", "Java");
    }
    public void navigateToBookDetailsPageMobile() throws InterruptedException {
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/url_bar")));
        inputField.click();
        WebElement sdfsdf = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/url_bar")));
        sdfsdf.sendKeys(bookUrl);
        Thread.sleep(7000); // 7 seconds delay
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(7000); // 7 seconds delay
    }
    public String getBookTitleMobile() {

        WebElement bookElement = driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View"));
        WebElement titleElement = bookElement.findElement(By.xpath(".//android.widget.TextView[contains(@text, '"+searchTerm+"')]"));
        return titleElement.getText().trim().toLowerCase();
    }

    public String getBookAuthorMobile() {
        WebElement bookElement = driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View"));
        WebElement authorElement = bookElement.findElement(By.xpath(".//android.widget.TextView[matches(@text, '^[A-Z][a-z]* [A-Z][a-z]*$')]"));
        return authorElement.getText().trim();
    }

}
