@tag
Feature: Erroe Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Negative Test of Incorrect login
    Given I landed on Ecommerce Page
    When Logged in with username <userName> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | userName            | password   |
      | johndoe12@gmail.com | Johndoe@12 | 
      | anshika@gmail.com   | Iamking@00 | 
