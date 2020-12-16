@Product
Feature: Product Scenarios

  Scenario Outline: Validate product info on Products page
    Given I am logged in using username "<username>" and password "<password>"
    Then The product is listed with title "<title>" and price "<price>"

    Examples:
      |username     |password   | title                   | price |
      |standard_user|secret_sauce| Sauce Labs Backpack | $29.99  |

  Scenario Outline: Validate product info on Product Details page
    Given I am logged in using username "<username>" and password "<password>"
    When I click on product title
    Then I should be on product details page with title "<title>", price "<price>" and description "<description>"

    Examples:
      |username     |password   | title |price | description |
      |standard_user|secret_sauce | Sauce Labs Backpack |$29.99  | carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.|


