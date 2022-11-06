package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver	= driver;
		PageFactory.initElements(driver,this);  //only for PageFactory
	}
	
	//-----------------------------Locator
	@FindBy(xpath = "//button[text()='Log in']")
	WebElement Loginbutton;  // WebElement Login = driver.findElemet(By.xpath("//button[text()='Log in']")));
	
	@FindBy(id = "Email")
	WebElement Emailfield;
	
	@FindBy(id = "Password")
	WebElement Passwordfield;
	
	//Locator  --using By
	//By loginButton = By.xpath("//button[text()='Log in']");	
	
	//----------------------------component
	public void clickLoginButton() {
		Loginbutton.click();   //driver.findElemet(locator)
		//driver.findElement(loginButton).click();
	}
	
	public void enterUsername(String uname) {
		Emailfield.clear();
		Emailfield.sendKeys(uname);
	}
	
	public void enterPassword(String pass) {
		Passwordfield.clear();
		Passwordfield.sendKeys(pass);
	}
	
	
	//-------------------flow
	public void LoginAppliation(String uname, String pass) {
		enterUsername(uname);
		enterPassword(pass);
		clickLoginButton();
	}


}
