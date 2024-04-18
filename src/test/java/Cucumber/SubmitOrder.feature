
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
		Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test for submitting the order
    Given Logged in with username <userName> and password <password>
    When Select the product <product> and add to cart
    And Checkout <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | userName            | password    | product         |
      | johndoe12@gmail.com | Johndoe@123 | ZARA COAT 3     |
      | anshika@gmail.com   | Iamking@000 | ADIDAS ORIGINAL |
      
