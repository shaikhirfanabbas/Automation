
Feature: login and purchase flow on saucedemo

  Scenario: Complete purchase flow with cart price validation
  
  Given I launch the saucedemo website
  When I log in with valid credentials "standard_user" and "secret_sauce"
  And I apply "Price (low to high)" filter
  And I add two most expensive items in cart 
  Then I should see 2 items in the cart
  And I validate total price equals the sum of individual item prices
  When I proceed to checkout with "John", "Doe", and "226001"
  Then I should see the message "THANK YOU FOR YOUR ORDER"
  And I take a screenshot named "purchase_sucess.png"
  
   
