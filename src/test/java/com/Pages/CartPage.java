package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver=driver;
	}
public int getItemCount() {
		List<WebElement> items=(List<WebElement>) driver.findElements(By.className("cart_item"));
		return items.size();
	}
public double getTotalPrice() {
	List<WebElement> prices=(List<WebElement>) driver.findElements(By.className("inventory_item_price"));
	return prices.stream().mapToDouble(p->Double.parseDouble(p.getText().replace("$", ""))).sum();
}

public void checkout() {
    driver.findElement(By.id("checkout")).click();
}

}
