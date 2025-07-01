package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	  WebDriver driver;

	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void fillDetailsAndContinue(String first, String last, String zip) {
	        driver.findElement(By.id("first-name")).sendKeys(first);
	        driver.findElement(By.id("last-name")).sendKeys(last);
	        driver.findElement(By.id("postal-code")).sendKeys(zip);
	        driver.findElement(By.id("continue")).click();
	        driver.findElement(By.id("finish")).click();
	    }

	    public String getConfirmationMessage() {
	        return driver.findElement(By.className("complete-header")).getText();
	    }
}
