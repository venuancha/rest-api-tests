Feature: This is a sample feature for JMS Queue scenarios

# Assumptions:
# This scanrio runs locally. And it is a template scenario
# We assume there is an example rest api running 
# And we make a rest api call
# Then we receive the response
# Two scenarios are implemented. One with RestTemplate(Spring) and
# another with HTTP client. Both scenarios make a rest call and 
# verify the response message for success.

Scenario: Invoke REST API call with RestTemplate
Given I Configure REST API
When I send the GET API Request
Then I Should receive the Response

Scenario: Invoke REST API call with HTTP client
Given I configure REST API with HTTP client
When I send the GET API Request with HTTP client
Then I should recieve the response with success