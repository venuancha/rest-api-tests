$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("RESTAPI.feature");
formatter.feature({
  "line": 1,
  "name": "This is a sample feature for JMS Queue scenarios",
  "description": "",
  "id": "this-is-a-sample-feature-for-jms-queue-scenarios",
  "keyword": "Feature"
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "# Assumptions:"
    },
    {
      "line": 4,
      "value": "# This scanrio runs locally. And it is a template scenario"
    },
    {
      "line": 5,
      "value": "# We assume there is an example rest api running"
    },
    {
      "line": 6,
      "value": "# And we make a rest api call"
    },
    {
      "line": 7,
      "value": "# Then we receive the response"
    },
    {
      "line": 8,
      "value": "# Two scenarios are implemented. One with RestTemplate(Spring) and"
    },
    {
      "line": 9,
      "value": "# another with HTTP client. Both scenarios make a rest call and"
    },
    {
      "line": 10,
      "value": "# verify the response message for success."
    }
  ],
  "line": 12,
  "name": "Invoke REST API call with RestTemplate",
  "description": "",
  "id": "this-is-a-sample-feature-for-jms-queue-scenarios;invoke-rest-api-call-with-resttemplate",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 13,
  "name": "I Configure REST API",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "I send the GET API Request",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "I Should receive the Response",
  "keyword": "Then "
});
formatter.match({
  "location": "RESTAPISteps.configureSpringRestTemplate()"
});
formatter.result({
  "duration": 875405353,
  "status": "passed"
});
formatter.match({
  "location": "RESTAPISteps.sendXMLMessage()"
});
formatter.result({
  "duration": 468778601,
  "status": "passed"
});
formatter.match({
  "location": "RESTAPISteps.receiveAndAssertJsonResponse()"
});
formatter.result({
  "duration": 1786299,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Invoke REST API call with HTTP client",
  "description": "",
  "id": "this-is-a-sample-feature-for-jms-queue-scenarios;invoke-rest-api-call-with-http-client",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 18,
  "name": "I configure REST API with HTTP client",
  "keyword": "Given "
});
formatter.step({
  "line": 19,
  "name": "I send the GET API Request with HTTP client",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "I should recieve the response with success",
  "keyword": "Then "
});
formatter.match({
  "location": "RESTAPISteps.createRestClient()"
});
formatter.result({
  "duration": 5638241,
  "status": "passed"
});
formatter.match({
  "location": "RESTAPISteps.callAPIWithHTTPClient()"
});
formatter.result({
  "duration": 31747293,
  "status": "passed"
});
formatter.match({
  "location": "RESTAPISteps.receiveRestAPIResponse()"
});
formatter.result({
  "duration": 6938585,
  "status": "passed"
});
});