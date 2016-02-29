package step_definitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BookInsertionStepDefs {
	
	public WebDriver driver;

	public BookInsertionStepDefs() {
		driver = Hooks.driver;
	}

	@When("^I add a new book$")
	public void addBook() throws Throwable {
		driver.findElement(By.id("addBook")).click();
	}

	@When("^I log in$")
	public void logIn() throws Throwable {
		driver.findElement(By.id("userName")).sendKeys("admin");
		driver.findElement(By.id("pwd")).sendKeys("admin");
		driver.findElement(By.id("login")).click();
	}

	@When("^I insert an new book with id as \"(.*?)\" , title as \"(.*?)\", author as \"(.*?)\" and price as \"(.*?)\"$")
	public void insertNewBook(String id, String title, String author, String price) throws Throwable {
		driver.findElement(By.id("bookId")).sendKeys(id);
		driver.findElement(By.id("title")).sendKeys(title);
		driver.findElement(By.id("author")).sendKeys(author);
		driver.findElement(By.id("price")).sendKeys(price);
		driver.findElement(By.id("insertBook")).click();
	}

	@Then("^I can see the new book with id as \"(.*?)\"$")
	public void checkIfBookAdded(String id) throws Throwable {
		List<WebElement> books=driver.findElements(By.xpath("//table[@id='books']/tbody/tr"));
		assertTrue(books.size() == 3);
		assertEquals(id, driver.findElement(By.xpath("//table[@id='books']/tbody/tr[3]/td[1]")).getText());
	}

}