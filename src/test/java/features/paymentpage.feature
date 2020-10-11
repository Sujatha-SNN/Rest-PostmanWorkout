Feature: Payments on paystack
Background:
Given Set the url for payments page
And Set the authentication
And Set the content type and enable logs

Scenario: Create Payment Page
When Set the body params
And Call the post request 
And Get the payment page id from response
Then Check the status to be 200

Scenario: Get all payments page
When Set the query params
And Call the get method allPaymentsPages
And Verify the created payment page id in response
Then Check the status to be 200

Scenario: Get the created payments page
When Set the path params
And Call the get method getCreatedPage
And Verify the created payment page id in response
Then Check the status to be 200
