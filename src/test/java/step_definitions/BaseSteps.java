package step_definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class BaseSteps {
	
	public WebDriver driver;
	private static final String SERVER_URL = "http://localhost:8080";

	public BaseSteps() {
		driver = Hooks.driver;
	}

	@Given("^I open the site$")
	public void i_open_the_site() throws Throwable {
		driver.get(SERVER_URL + "/simplelibrary");
	}

	@When("^I can see the list of books$")
	public void i_can_see_the_list_of_books() throws Throwable {
		driver.findElement(By.id("books")).isDisplayed();
	}

}