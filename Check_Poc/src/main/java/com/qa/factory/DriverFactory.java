package com.qa.factory;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the theadLocal Driver on the basis of given browser.
	 * @param browser
	 * @return this will return the tlDriver
	 */
	public WebDriver init_driver(String browser) {
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		}

		else {
			System.out.println("Please pass the correct browser value:"+browser);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}

	/**
	 * This method is used to launch the URL on the basis of given url.
	 * @param url
	 * @return this will return the tlDriver
	 */
	public WebDriver launchUrl(String url) {
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(url);
		waitForPageToLoad();
		return getDriver();
	}

	/**
	 * This method is used to get the driver of threadLocal 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();

	}

	/**
	 * This method is wait for page to load completely. 
	 */
	public static void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) getDriver()).executeScript(
						"return document.readyState"
						).equals("complete");
			}
		});		
	}

	/**
	 * This method is wait for element to present in DOM. 
	 */
	public static void waitForElementToPresent(WebElement element){	
		FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver())
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200))
				.ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.visibilityOf(element));	
	}

	/**
	 * This method is wait for element to be clickable  in DOM. 
	 */
	public static void waitForElementToBeClickable(WebElement element){	
		FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver())
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200))
				.ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(element));	
	}
}
