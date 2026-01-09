Feature: Validating Place APIss

Scenario: Verify if Place is being successfully added using AddPlaceAPI 
     Given Add Place Payload
     When User calls AddPlaceAPI with POST http request
     Then The API call is success with the status code 200
     And Status inside the response body is OK
