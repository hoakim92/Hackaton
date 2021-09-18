Feature: Test

  @FirstThread
  Scenario: One
    Given Open Store
    Then Click on '//img[@class="js-responsive-image" and @title="Men"]'
    Then Click on text 'Jackson Tee SS mint leather M'
    Then Click on '//form[@id="addToCartForm"]//button'
    Then Click on '//div[@id="addToCartLayer"]/a[@class="btn btn-primary btn-block add-to-cart-button"]'
    Then Click on '//div[@class="cart__actions"]//button[@class="btn btn-primary btn-block btn--continue-checkout js-continue-checkout-button"]'
    Then Type to '//input[@id="j_username"]' text 'username'
    Then Type to '//input[@id="j_password"]' text 'password'
