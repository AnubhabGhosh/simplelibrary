Feature: Access simple library website and insert a new book

Scenario: Check if insertion of book is done

Given I open the site
When I can see the list of books
And I add a new book
And I log in
And I insert an new book with id as "2" , title as "tesing insertion feature", author as "Anubhab ghosh" and price as "700"
Then I can see the new book with id as "2" 

