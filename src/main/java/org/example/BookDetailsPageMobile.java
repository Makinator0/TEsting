package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BookDetailsPageMobile {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private String searchTerm;
    private String bookUrl = "https://www.amazon.com/Python-Programming-Fundamentals-Comprehensive-Analysis-ebook/dp/B0D4B3BMG6/ref=mp_s_a_1_2_sspa?crid=20Y0XTEBPMNWY&dib=eyJ2IjoiMSJ9.66xg9I21CkHmcRHcyLwr55_0cqSh51xu5OqeH8aNkVvbw8icagLHsjNCGY_FExmYcxJShaeatNvD5eh1xu83GMhs03q14CKnhjg0RjattrgbUNHF4DqNC6kgiRh91k8Gk2BQ-dISA8LR2JLWHLv65XktU3lLW2qg2NyTxTD5FKYn60flRhub-TIGTU2mABE04fR_4lj0Pi8EjqR3NlA7vg.LK4vciGgzO9OUMYZk520C6acndBh4BaJELseU-5iEuc&dib_tag=se&keywords=python+books&qid=1716723846&sprefix=python+books%2Cbooks%2C209&sr=8-2-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9waG9uZV9zZWFyY2hfYXRm&psc=1";
    public BookDetailsPageMobile(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        searchTerm = System.getProperty("searchTerm", "Java");
    }
    @Step("Navigate to book details page")
    public void navigateToBookDetailsPageMobile() throws InterruptedException {
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/url_bar")));
        inputField.click();
        WebElement sdfsdf = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/url_bar")));
        sdfsdf.sendKeys(bookUrl);
        Thread.sleep(7000); // 7 seconds delay
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        Thread.sleep(7000); // 7 seconds delay
    }
    @Step("Get book title")
    public String getBookTitleMobile() {

        WebElement bookElement = driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View"));
        WebElement titleElement = bookElement.findElement(By.xpath(".//android.widget.TextView[contains(@text, '"+searchTerm+"')]"));
        return titleElement.getText().trim().toLowerCase();
    }
    @Step("Get book author")
    public String getBookAuthorMobile() {
        WebElement bookElement = driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View"));
        WebElement authorElement = bookElement.findElement(By.xpath(".//android.widget.TextView[matches(@text, '^[A-Z][a-z]* [A-Z][a-z]*$')]"));
        return authorElement.getText().trim();
    }

}
