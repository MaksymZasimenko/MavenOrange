package Tests;

import Pages.BasePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.OrangePage;

public class OrangeTest extends tests.BaseTest {
    private OrangePage orangePage;
    @BeforeMethod
    public void localSetUp(){
        orangePage = new OrangePage(driver);
        orangePage.click(orangePage.logInAsDifferentRole);
        orangePage.click(orangePage.adminBtn);
    }


    @Test(testName = "Open news")
    public void test1(){
        orangePage.click(orangePage.viewAdminModule);
        orangePage.click(orangePage.newsAnnouncements);
        orangePage.click(orangePage.viewNewsList);
        BasePage.sleep(2000);
        orangePage.click(orangePage.addNewsBtn);

        orangePage.switchFrame(orangePage.frame1);
        BasePage.sleep(1000);
        orangePage.titleInput.sendKeys("Test");
        orangePage.switchFrame(orangePage.frame2);
        orangePage.description.sendKeys("Test message");
    }
}
