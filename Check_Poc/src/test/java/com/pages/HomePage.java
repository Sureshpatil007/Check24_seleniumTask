package com.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;


public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	 
	 
	@FindBy(xpath="//*[text()='Akzeptieren']")

    WebElement Acceptcookies;
	
	@FindBy(xpath="(//div[@class='product-panel__controls__button']//a)[3]")

    WebElement BarclaysVisaButton;
	
	@FindBy(xpath="//input[@id='cl_login']")

    WebElement EmailTextbox;
	
	@FindBy (xpath="//*[text()='Weitere persönliche Angaben']")
	WebElement NoErrorscreen;

	@FindBy(xpath="//button[@id='c24-uli-login-btn']//span[1]")

    WebElement FurtherButton;
	
	@FindBy(xpath="//*[@id=\"cl_pw_register\"]")

    WebElement password;
	
	@FindBy(xpath="//*[@id=\"cl_ul_pw_register_repeat\"]")

    WebElement confirmPassword;
	
	@FindBy(xpath="//*[text()=\"Ich möchte als Gast fortfahren\"]")

    WebElement radioButton;
	
	@FindBy(xpath="//button[@id='c24-uli-register-btn']/span")

    WebElement registerButton;
	
	@FindBy(xpath="//*[@for=\"GENDER_MALE\"]")

    WebElement genderRadioButton;
	
	@FindBy(xpath="//*[@id=\"GIVEN_NAME\"]")

    WebElement givenName;
	
	@FindBy(xpath="//*[@id=\"LAST_NAME\"]")

    WebElement lastName;
	
	@FindBy(xpath="//*[@id=\"DATE_OF_BIRTH\"]")

    WebElement dob;
	
	@FindBy(xpath="//*[@id=\"PHONENUMBER_MOBILE\"]")

    WebElement phoneNumber;
	
	@FindBy(xpath="//*[text()=\"Bitte wählen Sie Ihre Anrede aus.\"]")

    WebElement genderError;
	
	@FindBy(xpath="//*[text()=\"Bitte geben Sie Ihren Vornamen an.\"]")

    WebElement givenNameError;
	
	@FindBy(xpath="//*[text()=\"Bitte geben Sie Ihren Nachnamen an.\"]")

    WebElement lastNameError;
	
	@FindBy(xpath="//*[text()=\"Bitte geben Sie Ihr Geburtsdatum an.\"]")

    WebElement dobError;
	
	@FindBy(xpath="//*[text()=\"Für eventuelle Rückfragen benötigen wir Ihre deutsche Mobilnummer.\"]")

    WebElement phoneNumberError;
	
	@FindBy(xpath="//div[@class='styles__ButtonWrapper-sc-1solng-1 dwSWhP']")

    WebElement nextButton;
    

	public void acceptCookies() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Acceptcookies.click();
	}
	
	@SuppressWarnings("deprecation")
	public void verifycookie() {
		Response response = RestAssured.get("https://finanzen.check24.de/accounts/d/kreditkarte/result.html");
		 String expectedCookie = "kreditkarte";
		 String actualCookie = response.getCookie("ppset");
		 Assert.assertEquals(expectedCookie, actualCookie);
	}
	
	public void clickOnProduct() {
		BarclaysVisaButton.click();
	}
	
	public void enterEmailAddress() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(EmailTextbox));
		EmailTextbox.sendKeys("abc@malinator.com");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", FurtherButton);
	}
	
	
	public void clickOnRadioButton() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(radioButton));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", radioButton);
		executor.executeScript("arguments[0].click();", registerButton);
	}
	
	@SuppressWarnings("deprecation")
	public void verifyErrors() {
		nextButton.click();
		String ExpectedGenderError = "Bitte wählen Sie Ihre Anrede aus.";
		String ExpectedGivenNameError = "Bitte geben Sie Ihren Vornamen an.";
		String ExpectedLastNameError = "Bitte geben Sie Ihren Nachnamen an.";
		String ExpecteddobError = "Bitte geben Sie Ihr Geburtsdatum an.";
		String ExpectedPhoneError = "Bitte wählen Sie Ihre Anrede aus.";
		
		String ActualGenderError = genderError.getText();
		Assert.assertEquals(ExpectedGenderError, ActualGenderError);
		
		String ActualGivenNameError = givenNameError.getText();
		Assert.assertEquals(ExpectedGivenNameError, ActualGivenNameError);
		
		String ActualLastNameError = lastNameError.getText();
		Assert.assertEquals(ExpectedLastNameError, ActualLastNameError);
		
		String ActualdobError = dobError.getText();
		Assert.assertEquals(ExpecteddobError, ActualdobError);
		
		String ActualPhoneError = genderError.getText();
		Assert.assertEquals(ExpectedPhoneError, ActualPhoneError);
		
	}
	
	public void enterDetails() {
		genderRadioButton.click();
		givenName.sendKeys("Suresh");
		lastName.sendKeys("Patil");
		dob.sendKeys("22.05.1990");
		phoneNumber.sendKeys("+491742564250");
		
		nextButton.click();
	}
	
	public void NoErrorscreen()
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(NoErrorscreen));
		NoErrorscreen.isDisplayed();
	}
}
