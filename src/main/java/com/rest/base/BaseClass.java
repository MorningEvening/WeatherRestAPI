package com.rest.base;

/*
 * @Project WeatherRestAPI
 * @author  pritipradhan
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.rest.reports.ExtentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public Properties prop;
    public ExtentReports reports;
    public ExtentTest extentTest;
    public String api_url,api_key;

    /* Class to perform initial setup before running any tests */
    public BaseClass () {

        prop = new Properties();
        try {
            FileInputStream fp = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/rest/config/config.properties");
            prop.load(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        api_url = prop.getProperty("weather_url");
        api_key = prop.getProperty("api_key");
        reports = ExtentManager.getReport(System.getProperty("user.dir")+"/target/Report/");
    }
}
