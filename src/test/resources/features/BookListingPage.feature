Feature: Access simple library website and check Book Listing Page

Scenario: Check if list of books are shown

Given I open the site
When I can see the list of books
Then I can see the name, title, author and price of each book