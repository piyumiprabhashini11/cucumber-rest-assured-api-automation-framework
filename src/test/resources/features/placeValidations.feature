Feature: Validating Place APIs

Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
     Given Add Place Payload "<name>" "<language>" "<address>"
     When User calls "AddPlaceAPI" with POST http request
     Then The API call is success with the status code 200
     And "status" inside the response body is "OK"
     And "scope" inside the response body is "APP"

     Examples:
          |name    |language |address           |
          |AAhouse |English  |World cross center|
          |BBhouse |German   |Flower road       |
     
