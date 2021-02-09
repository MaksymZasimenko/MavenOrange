package Tests;

import Pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.OrangePage;

public class OrangeTest extends BaseTest {
    private OrangePage orangePage;

    @BeforeMethod
    public void localSetUp() {
        orangePage = new OrangePage(driver);

    }


    @Test(testName = "Open news")
    public void test1() {

        orangePage.click(orangePage.logInAsDifferentRole);
        orangePage.click(orangePage.adminBtn);

        orangePage.click(orangePage.viewAdminModule);
        orangePage.click(orangePage.newsAnnouncements);
        orangePage.click(orangePage.viewNewsList);
        BasePage.sleep(2000);

        orangePage.switchFrame(orangePage.frame1);
        BasePage.sleep(1000);

        orangePage.click(orangePage.addNewsBtn);
        orangePage.titleInput.sendKeys("Test");
        orangePage.switchFrame(orangePage.frame2);
        orangePage.description.sendKeys("Test message");
    }

    @Test(testName = "Tim - Steps 10 - 12", description = "Log in as 1st Level Supervisor -> Go to News section -> Verify Topic and Description values")
    public void test3() {
        orangePage.logInAsDifferentRole.click();
        orangePage.firstlvlSupervisorBtn.click();
        orangePage.firstLvlMenuAnnouncement.click();
        orangePage.firstLvlNews.click();
        orangePage.firstLvlHeader.click();

        // need to modify 'Employee Insurance Renewal'
        Assert.assertEquals(orangePage.firstLvlTopic.getText(), "Employee Insurance Renewal");
        // need to modify 'Hi All,'
        Assert.assertEquals(orangePage.firstLvlDescription.getText().substring(0, 7), "Hi All,");

    }
}
