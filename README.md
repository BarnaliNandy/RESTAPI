# RESTAPI
APITesting using base URI -  https://petstore.swagger.io
Created a Maven project in IntelliJIDEA IDE using Java, Rest Assured and TestNG. I used Maven since I am more familiar with it. 
Created dependencies in the pom.xml for Rest Assured and TestNG. Since I was unable to install Docker, I used Rest Assured (Java DSL) to check the API endpoints. 
Created test cases to check the HTTP methods GET, POST and PUT and created assertions to verify the status code (per the public API docs) and a value from the JSON response body. 
Added incorrect data to the above test to generate failures (I have commented the .java file where I added the below inconsistencies)
  PUT request -  added incorrect string to compare a value from the response body 
  POST request - changed the endpoint from the original in API documentation
  PUT request - changed the expected status code form the original
Used TestNG to run the tests from the IDE. 
