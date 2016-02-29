package step_definitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

public class PageVerificationSteps {
	
	public WebDriver driver;

	public PageVerificationSteps() {
		driver = Hooks.driver;
	}

	@Then("^I can see the name, title, author and price of each book$")
	public void i_can_see_the_name_title_author_and_price_of_each_book() throws Throwable {
		assertTrue(driver.findElements( By.xpath("//table[@id='books']/tbody/tr")).size() > 1);
	}

}