package org.example;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import java.util.List;


public class SampleTest {
    private AndroidDriver driver;
    private WebDriverWait wait;




    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "POCOPHONE F1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        URL url = URI.create("http://10.8.0.15:4723/").toURL();
        driver = new AndroidDriver(url, capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // 30 seconds timeout
    }



    @Test
    public void testScrapeBooksMobile() throws InterruptedException {
        AmazonHomePageMobile homePage = new AmazonHomePageMobile(driver, wait);
        homePage.navigateToHomePage();
        homePage.enterSearchTerm();
        homePage.goToBooksCategory();

        AmazonSearchPage searchPage = new AmazonSearchPage(driver, wait);

        // Extract the first 7 books
        List<Book> books = searchPage.extractBooks();
        BookDetailsPageMobile bookDetailsPage = new BookDetailsPageMobile(driver, wait);
        bookDetailsPage.navigateToBookDetailsPageMobile();
        String extractedTitle = bookDetailsPage.getBookTitleMobile();
        String extractedAuthor = bookDetailsPage.getBookAuthorMobile();

        Book foundBook = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(extractedTitle) && book.getAuthor().equals(extractedAuthor))
                .findFirst()
                .orElse(null);

        Assert.assertTrue(books.contains(foundBook), "Expected book should be in the list of books");
        if (foundBook != null) {
            System.out.println("Found book: " + foundBook.getTitle() + " by " + foundBook.getAuthor() + ", Price: " + foundBook.getPrice() + ", Bestseller: " + foundBook.isBestSeller());
        }
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
