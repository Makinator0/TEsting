package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

import java.util.List;

public class AmazonSearchPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private String searchTerm;
    public AmazonSearchPage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = (AndroidDriver) driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        searchTerm = System.getProperty("searchTerm", "Python");
    }


    public List<Book> extractBooks() {
        List<Book> books = new ArrayList<>();
        List<WebElement> bookElements = driver.findElements(By.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View"));
        for (WebElement bookElement : bookElements) {
            try {
                books.add(extractBookData(bookElement));
            } catch (Exception e) {
                System.err.println("Failed to extract book data: " + e.getMessage());
            }
        }
        return books;
    }



    private Book extractBookData(WebElement bookElement) {
        String title = "Unavailable";
        String author = "Unknown";
        String price = "N/A";
        boolean isBestSeller = false;

            title = safeGetText(bookElement, ".//android.widget.TextView[contains(@text, '"+searchTerm+"')]"); // Adjust the XPath to match your structure
            author = safeGetText(bookElement, ".//android.widget.TextView[contains(@text, 'by')]/following-sibling::android.widget.TextView[1]");
            // Adjust the XPath to match your structure
            price = safeGetText(bookElement, ".//android.widget.TextView[contains(@text, '$')]"); // Adjust the XPath to match your structure
            WebElement bestSellerBadge = bookElement.findElement(By.xpath(".//android.view.View[contains(@text, 'Best Seller')]"));
            isBestSeller = bestSellerBadge != null && bestSellerBadge.getText().contains("Best Seller");


        return new Book(title, author, price, isBestSeller);
    }

    private String safeGetText(WebElement parent, String xpath) {
        try {
            return parent.findElement(By.xpath(xpath)).getText();
        } catch (NoSuchElementException e) {
            return "Data not found";
        }
    }
}


