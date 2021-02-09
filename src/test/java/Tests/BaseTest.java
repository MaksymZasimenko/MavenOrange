package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private String filePath = "src/main/resources/configuration.properties";
    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extentReports;
    protected ExtentTest extentTest;
    protected Test test;

    @BeforeTest
    public void startReport(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/target/testReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        extentReports.setSystemInfo("Author", ConfigReader.readProperty(filePath, "author"));
        extentReports.setSystemInfo("Browser", ConfigReader.readProperty(filePath, "browser"));
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setDocumentTitle("Orange Test");
        htmlReporter.config().setReportName(ConfigReader.readProperty(filePath, "reportName"));
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @BeforeMethod
    public void setUp(Method method){
        test = method.getAnnotation(Test.class);
        extentTest = extentReports.createTest(test.testName());
        extentTest.info(test.description());
        initializeDriver();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
        driver.quit();
    }

    @AfterTest
    public void endReport(){
        extentReports.flush();
    }



    private void initializeDriver(){
        switch (ConfigReader.readProperty(filePath, "browser").toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser type/name");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.readProperty(filePath, "url"));
    }
}
