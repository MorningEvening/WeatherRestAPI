package com.rest.testcases;

/*
 * @Project WeatherRestAPI
 * @author  pritipradhan
 */

import com.aventstack.extentreports.Status;
import com.rest.base.BaseClass;
import com.rest.client.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class WeatherAPITest extends BaseClass {

    BaseClass baseClass;

    @BeforeMethod (alwaysRun = true)
    public void setup(ITestResult result) {
        baseClass = new BaseClass();
        extentTest = reports.createTest(result.getMethod().getMethodName(),"Starting test...");
    }

    /* Description : Testcase to verify the correct api value returned corresponding to city name */
    @Test (groups = { "CityName" })
    public void verifyCityName() throws IOException {

        String city_name = prop.getProperty("city_name");
        extentTest.log(Status.INFO, "Running API test for city : " +city_name);
        String url=api_url+"q="+city_name+"&appid="+api_key;

        RestClient restClient = new RestClient();
        restClient.get(url);
        CloseableHttpResponse closeableHttpResponse = restClient.get(url);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String name = responseJson.getString("name");

        if(city_name.replaceAll("%20"," ").contains(name)){
            extentTest.pass("Verified the city Name response And it is returned as expected!!");
            Assert.assertTrue(true);
        }
        else {
            extentTest.fail("City Response isn't same as expected!!");
            Assert.fail();
        }
    }

    /* Description : Testcase to verify the longitude and latitude values returned corresponding to city name */
    @Test (groups = { "Coordinate" })
    public void verifyCoordinates() throws IOException {

        String latitude = prop.getProperty("latitude");
        String longitude = prop.getProperty("longitude");

        extentTest.log(Status.INFO, "Running API test for latitude " +latitude +"and logitude : " +longitude);
        String url=api_url+"lat="+latitude+"&lon="+longitude+"&appid="+api_key;

        RestClient restClient = new RestClient();
        restClient.get(url);
        CloseableHttpResponse closeableHttpResponse = restClient.get(url);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        JSONObject responseJson = new JSONObject(responseString);

        JSONObject coordJson = responseJson.getJSONObject("coord");
        Double lat_json = (Double) coordJson.get("lat");
        Double lon_json = (Double) coordJson.get("lon");

        extentTest.log(Status.INFO, " Verifying Latitude!!");
        if(lat_json.toString().equals(latitude)){
            Assert.assertTrue(true);
            extentTest.pass("Verified the Latitude And it is returned as expected!!");
        }
        else {
            extentTest.fail("Latitude isn't same as expected!!");
            Assert.fail();
        }

        extentTest.log(Status.INFO, " Verifying Longitude!!");
        if(lon_json.toString().equals(longitude)){
            Assert.assertTrue(true);
            extentTest.pass("Verified the Longitude And it is returned as expected!!");
        }
        else {
            extentTest.fail("Longitude isn't same as expected!!");
            Assert.fail();
        }
    }

    /* Description : Testcase to verify the City name corresponding to Zipcode */
    @Test (dataProvider = "getCityZipCodeData",groups = { "Zipcode"})
    public void verifyNameByZipCode(int zipCode, String cityName) throws IOException {

        extentTest.log(Status.INFO, "Running API test for Zipcode " +zipCode );
        String url=api_url+"zip="+zipCode+",us&appid="+api_key;

        RestClient restClient = new RestClient();
        restClient.get(url);
        CloseableHttpResponse closeableHttpResponse = restClient.get(url);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        String name = responseJson.getString("name");

        if(name.equals(cityName)){
            extentTest.pass("Verified the city Name " +cityName+ " corresponding to Zipcode And it is returned as expected!!");
            Assert.assertTrue(true);
        }
        else {
            extentTest.fail("City Name " +cityName+ " Response isn't same as expected!!");
            Assert.fail();
        }


    }
    /* TestData for Zipcode to City name mapping */
    @DataProvider
    public Object[][] getCityZipCodeData(){
        Object [][] data = new Object[2][2];

        data[0][0] = 10007;
        data[0][1] = "New York";

        data[1][0] = 94040;
        data[1][1] = "Mountain View";

        return  data;
    }

    @AfterMethod (alwaysRun = true)
    public void exit(){
        reports.flush();
    }
}
