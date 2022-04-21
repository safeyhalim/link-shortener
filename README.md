## Building and Running Instructions

-	Build and run tests with the following command:

	 ./gradlew clean test --info

- 	Launch the service by running the command:

	 ./gradlew bootRun
	
- 	Note when you run the gradlew bootRun, the progress bar will stop at less that 100%. That's a perfectly normal Gradle behaviour. The application is already running and ready to receive requests

-	The server is running on: localhost:8081. If you want to change the port, edit application.yml

-	For full API documentation, and also for trying the service out, launch the service as explained above, and load Swagger-UI by opening the following endpoint in your browser:

	 http://localhost:8081/swagger-ui/#
-	Use the "Try it out" button to run the api endpoints

-	Note: a valid url, must start with http:// or https://. Therefore: www.example.com is NOT considered a valid URL! http(s)://example.com, or http(s)://www.example.com are