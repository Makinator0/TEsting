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
    private String bookUrl = "https://www.amazon.com/Python-Programming-Fundamentals-Comprehensive-Analysis-ebook/dp/B0D4B3BMG6/ref=mp_s_a_1_1_sspa?dib=eyJ2IjoiMSJ9.66xg9I21CkHmcRHcyLwr55_0cqSh51xu5OqeH8aNkVvcf2aK3bAP5aQGELQ9lbYGFsrJEkVN0Vt9iLixNjL-ZikJAoGoVnegzEoaAMJvZkDYXl8IXZsX_efry_X-Iq9MKxR5Er7JDBxCjabo6Qw-HcEuH7-dh1ZfV9IKBxwzMexvEUlAM63EEpW7XSO-6rMVKp7KYJWwFkbNOtc11fTFIw.-RXIlIINrjIOb2rpTlWAygqFMi32LYg6GGe-yXDJaZg&dib_tag=se&keywords=python+books&qid=1716544531&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9waG9uZV9zZWFyY2hfYXRm&psc=1";
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
