Feature: Test

  @FirstThread
  Scenario: One
    Given Open Store
    Then Click on '/html/body/main/div[3]/div[2]/a/img'
    Then Validate title 'Streetwear | Collections | Apparel Site UK'

  @SecondThread
  Scenario: Two
    Given Open Store
    Then Click on '/html/body/main/div[3]/div[2]/a/img'
    Then Validate title 'Streetwear | Collections | Apparel Site UK'
