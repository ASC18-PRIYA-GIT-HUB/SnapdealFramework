package com.snapdeal.tests;

import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.snapdeal.base.BaseTest;
import com.snapdeal.pages.*;
import com.snapdeal.utilities.ScreenshotUtilities;

public class loginsnapdeal extends BaseTest {

    private ExtentTest test;
    private LoginPage login;
    private HomePage home;
    private SearchResultsPage searchResults;
    private ProductPage product;
    private CartPage cart;

    @BeforeClass
    public void setupPages() {
        login = new LoginPage(driver);
        home = new HomePage(driver);
        searchResults = new SearchResultsPage(driver);
    }

    @Test(priority = 1)
    public void loginSnapdeal() {
        test = extent.createTest("Step 0: Login with Mobile Number");
        try {
            driver.get("https://www.snapdeal.com/login");
            login.loginWithMobile("9360362324"); // Enter your mobile here
            test.pass("Login initiated. OTP should be entered manually.");
        } catch(Exception e) {
            test.fail("Login failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void openHomePage() {
        test = extent.createTest("Step 1: Open Home Page");
        try {
            driver.get("https://www.snapdeal.com");
            String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "HomePage");
            test.pass("Home page opened successfully").addScreenCaptureFromPath(screenshot);
        } catch(Exception e) {
            test.fail("Home page failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void searchProduct() {
        test = extent.createTest("Step 2: Search Product");
        try {
            home.searchProduct("Mobile");
            String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "SearchResults");
            test.pass("Product searched successfully").addScreenCaptureFromPath(screenshot);
        } catch(Exception e) {
            test.fail("Search product failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void sortProducts() {
        test = extent.createTest("Step 3: Sort Products by Price Low to High");
        try {
            searchResults.sortByLowPrice();
            String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "SortedResults");
            test.pass("Products sorted successfully").addScreenCaptureFromPath(screenshot);
        } catch(Exception e) {
            test.fail("Sorting failed: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void selectFirstProduct() {
        test = extent.createTest("Step 4: Select First Product from Results");
        try {
            product = searchResults.selectFirstProduct();
            String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "FirstProductPage");
            test.pass("First product selected successfully").addScreenCaptureFromPath(screenshot);
        } catch(Exception e) {
            test.fail("Selecting first product failed: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void addToCart() {
        test = extent.createTest("Step 5: Add Product to Cart");
        try {
            product.addToCart();
            String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "AddedToCart");
            test.pass("Product added to cart successfully").addScreenCaptureFromPath(screenshot);
        } catch(Exception e) {
            test.fail("Add to cart failed: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void viewCart() {
        test = extent.createTest("Step 6: View Cart");
        try {
            cart = product.goToCart();
            if(cart != null) {
                String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                    System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "ViewCart");
                test.pass("View cart clicked successfully").addScreenCaptureFromPath(screenshot);
            } else {
                test.fail("View cart failed");
            }
        } catch(Exception e) {
            test.fail("View cart failed: " + e.getMessage());
        }
    }

    @Test(priority = 8)
    public void verifyCartPage() {
        test = extent.createTest("Step 7: Verify Cart Page Displayed");
        try {
            if(cart != null && cart.isCartPageDisplayed()) {
                String screenshot = ScreenshotUtilities.captureScreenshot(driver,
                    System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots", "CartPage");
                test.pass("Cart page displayed successfully").addScreenCaptureFromPath(screenshot);
            } else {
                test.fail("Cart page not displayed");
            }
        } catch(Exception e) {
            test.fail("Verify cart page failed: " + e.getMessage());
        }
    }
}
