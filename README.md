# WeatherRestAPI
RestAPI Testing


To run All the test cases - please run testng.xml

Test cases written are  - verifyCityName, verifyCoordinates, verifyNameByZipCode

To run selected test cases - <include> tag can be commented for the specific group in testNG.xml

Input data for city_name, latitude and longitude can be updated in config.properties. 

Temporarily 2 dimensional object array is used as  data provider for verifyNameByZipCode test case.

(Code can be enhanced to access data provider data from excel sheet.)

For now the object array can be updated to run few more cases for the zip code. 

Reports are generated under target folder.
