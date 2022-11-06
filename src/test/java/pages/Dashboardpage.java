package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboardpage {
	public WebDriver driver;

	public Dashboardpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Loctaor
	@FindBy(xpath = "//a[text()='John Smith']")
	WebElement UserName;
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement Logout;
	@FindBy(xpath = "//div[@id='ajaxBusy' and @style='display: none;']")
	WebElement WaitImage;
	/*
	//Locator
	By userNameLabel = By.xpath("//a[text()='John Smith']");
	By logoutLink = By.xpath("//a[text()='Logout']");
	*/
	
	//component
	public String getUserName() {
		//return driver.findElement(userNameLabel).getText();
		return UserName.getText();
	}
	
	public void clickLogoutLink() {		
		WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.domAttributeToBe(WaitImage, "style", "display: none;"));
		//driver.findElement(logoutLink).click();		
		Logout.click();
	}
	
}
