#Author: your.email@your.domain.com

Feature: Register a customer
  I want to use this template for my feature file

  @Register
  Scenario: verify the customer has been successfully registered to the portal
#Given Open [Home page](http://automationpractice.com/index.php)
Then Click Sign in button
And Fill "Email" address to create an account
And Click Create an account
Then Fill all fields with correct data "<Password>","<Title>","<FName>","<LName>","<DOB>","<Address>","<City>","<State>","<PostCode>","<Country>","<MobileNumber>"
And Click Register button
#### Assertions
#* My account page(?controller=my-account) is opened
#* Proper username is shown in the header
#* Log out action is available