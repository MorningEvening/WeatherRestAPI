package com.rest.reports;

/*
 * @Project RestAPITest
 * @author  pritipradhan
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentManager {

    static ExtentReports reports;

    public static ExtentReports getReport( String reportPath){
        if(reports == null){


            Date d = new Date();
            String reportFolder = d.toString().replaceAll(" ","-");
            String path = reportPath + reportFolder;
            File file = new File(path);
            file.mkdirs();

            ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath+reportFolder);
            sparkReport.config().setReportName("** Weather RestAPI Testing **");
            sparkReport.config().setDocumentTitle("Automation Reports");
            sparkReport.config().setTheme(Theme.DARK);

            reports = new ExtentReports();
            reports.attachReporter(sparkReport);
        }
        return reports;
    }
}
