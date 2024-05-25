package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;


public class WikipediaWatchlistSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marin\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        driver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin");

        driver.findElement(By.id("wpName1")).sendKeys("MN2024");
        driver.findElement(By.id("wpPassword1")).sendKeys("hrhr6446$");
        driver.findElement(By.id("wpLoginAttempt")).click();
    }

    @Given("the user is logged into Wikipedia")
    public void the_user_is_logged_into_wikipedia() {
        //Already logged in during setup
    }

    @When("the user adds {string} to their watchlist")
    public void the_user_adds_to_their_watchlist(String articleTitle) {
        navigateToPage(articleTitle);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement watchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ca-watch")));
        watchButton.click();

    }

    @Then("the user should see {string} and {string} in their watchlist")
    public void the_user_should_see_and_in_their_watchlist(String page1, String page2) {
        driver.get("https://en.wikipedia.org/wiki/Special:Watchlist");
        Assert.assertTrue(driver.getPageSource().contains(page1));
        Assert.assertTrue(driver.getPageSource().contains(page2));
    }

    @Given("the user has {string} and {string} in their watchlist")
    public void the_user_has_and_in_their_watchlist(String page1, String page2) {
        the_user_adds_to_their_watchlist(page1);
        the_user_adds_to_their_watchlist(page2);
    }

    @When("the user removes {string} from their watchlist")
    public void the_user_removes_from_their_watchlist(String articleTitle) {
        navigateToPage(articleTitle);
        WebElement unwatchLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("ca-unwatch")));
        unwatchLink.click();
    }

    @Then("the user should not see {string} in their watchlist")
    public void the_user_should_not_see_in_their_watchlist(String page) {
        driver.get("https://en.wikipedia.org/wiki/Special:Watchlist");
        Assert.assertFalse(driver.getPageSource().contains(page));
    }

    @Then("the user should see {string} in their watchlist")
    public void the_user_should_see_in_their_watchlist(String page) {
        driver.get("https://en.wikipedia.org/wiki/Special:Watchlist");
        Assert.assertTrue(driver.getPageSource().contains(page));
    }

    @Given("the user has removed {string} from their watchlist")
    public void the_user_has_removed_from_their_watchlist(String page) {
        the_user_removes_from_their_watchlist(page);
    }

    @When("the user goes to the {string} article from their watchlist")
    public void the_user_goes_to_the_article_from_their_watchlist(String page) {
        driver.get("https://en.wikipedia.org/wiki/Special:Watchlist");
        driver.findElement(By.linkText(page)).click();
    }

    @Then("the title should be {string}")
    public void the_title_should_be(String title) {
        Assert.assertTrue(driver.getTitle().contains(title));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void navigateToPage(String pageTitle) {
        driver.get("https://en.wikipedia.org/wiki/" + pageTitle);
    }
}