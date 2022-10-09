package tests;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	WebDriver driver;
	
	@Test(dataProvider="getLoginData")
	
	public void login(String email,String password,String expectedResult) throws IOException, InterruptedException {
		
	   
		LandingPage landingPage= new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		landingPage.loginOption().click();
		
		Thread.sleep(3000);
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.emailAddressField().sendKeys(email);
		loginPage.passwordField().sendKeys(password);
		loginPage.loginButton().click();
	
		AccountPage accountPage= new AccountPage(driver);
		
		String actualResult =null;
		
	try {	
     if(accountPage.editAccountInformationOption().isDisplayed());{
	 actualResult= "Successful";
	}}
	catch(Exception e) {
		actualResult = "Failure";
	}
	Assert.assertEquals(actualResult,expectedResult);
	
	}
	
	@BeforeMethod
	public void openApplication() throws IOException {
		driver = intializeDriver();
		driver.get(prop.getProperty("url"));
		
	}
	@AfterMethod
	public void closure() {
		driver.close();
	}
	@DataProvider 
public Object[][] getLoginData() {
	Object[][] data = {{"Shreyaskarsingh10@gmail.com","Second@123","Successful"},{"dummy@test.com","dummy","Failure"}};

return data;
}
}
