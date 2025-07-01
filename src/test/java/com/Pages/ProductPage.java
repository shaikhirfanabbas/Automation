package com.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void selectSortOptions(String option) {
		Select sort=new Select(driver.findElement(By.className("product_sort_container")));
		sort.selectByVisibleText(option);
	}
	
	public List<Double> addTwoMostExpensiveItems(){
		 List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
		    List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));

		    Map<Double, WebElement> priceButtonMap = new HashMap<>();
		    for (int i = 0; i < prices.size(); i++) {
		        String priceText = prices.get(i).getText().replace("$", "").trim(); // 👈 Fix here
		        double price = Double.parseDouble(priceText);
		        priceButtonMap.put(price, addButtons.get(i));
		    }

		    List<Double> sortedPrices = new ArrayList<>(priceButtonMap.keySet());
		    sortedPrices.sort(Collections.reverseOrder());

		    List<Double> selected = sortedPrices.subList(0, 2);
		    for (Double price : selected) {
		        priceButtonMap.get(price).click();
		    }

		    return selected;
	}
	
	public CartPage goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        return new CartPage(driver);
    }
}
