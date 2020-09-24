# citiconnect
This is a spring boot service which calculates if there is a connection between two cities.
The data(pair of cities) are loaded while starting of the application and service responds 'Yes' or 'No' based on the input.

The exposed end point is as follows:
http://localhost:8080/connected?origin=city1&destination=city2

Api documentaion exposed throuugh swagger endpoint.
http://localhost:8080/swagger-ui.html

Unit test case code coverage is exposed through jococo report:
../target/site/jacoco/index.html
