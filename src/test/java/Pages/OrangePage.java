package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class OrangePage extends BasePage {
    WebDriver driver;

    public OrangePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "loginAsButtonGroup")
    public WebElement logInAsDifferentRole;

    @FindBy(xpath = "//a[contains(text(), 'System Administrator')]")
    public WebElement systemAdminBtn;

    @FindBy(xpath = "//a[@data-username='admin']")
    public WebElement adminBtn;

    @FindBy(xpath = "//a[contains(text(), 'ESS')]")
    public WebElement essUserBtn;

    @FindBy(xpath = "//a[contains(text(), '1st')]")
    public WebElement firstlvlSupervisorBtn;

    @FindBy(id = "menu_admin_viewAdminModule")
    public WebElement viewAdminModule;

    @FindBy(id = "menu_news_Announcements")
    public WebElement newsAnnouncements;

    @FindBy(id = "menu_news_viewNewsList")
    public WebElement viewNewsList;

    @FindBy(id = "noncoreIframe")
    public WebElement frame1;

    @FindBy(id = "news_description_ifr")
    public WebElement frame2;

    @FindBy(xpath = "//tr[@class='dataRaw']/td[2]")
    public List<WebElement> announcements;
    @FindBy(xpath = "//tr[@class='dataRaw']//td[3]")
    public List<WebElement> publishDate;
    @FindBy(xpath = "//tr[@class='dataRaw']//td[6]")
    public List<WebElement> publishUserRole;
    @FindBy(xpath = "//tr[@class='dataRaw']//td[7]/i")
    public List<WebElement> attachment;

    @FindBy(xpath = "//i[@class = 'large material-icons']")
    public WebElement addNewsBtn;
    @FindBy(id = "news_topic")
    public WebElement titleInput;
    @FindBy(id = "tinymce")
    public WebElement description;

    //---------------------- Tim start---------------------
    @FindBy(xpath = "//span[@id='account-job']")
    public WebElement accountJob;

    @FindBy(xpath = "//a[@id='logoutLink']")
    public WebElement logOut;

    @FindBy(id = "menu_news_viewAnnouncementModule")
    public WebElement firstLvlMenuAnnouncement;

    @FindBy(id = "menu_news_viewNewsArticles")
    public WebElement firstLvlNews;

    @FindBy(id = "header")
    public WebElement firstLvlHeader;

    // need to modify 'Employee Insurance Renewal'
    @FindBy(xpath = "//div[contains(text(), 'Employee Insurance Renewal')]")
    public WebElement firstLvlTopic;

    // need to modify 'Hi All,'
    @FindBy(xpath = "//div[@class='html-content']//p[contains(text(), 'Hi All,')]")
    public WebElement firstLvlDescription;

    //---------------------- Tim end---------------------

    public void switchFrame(WebElement element){
        driver.switchTo().frame(element);
    }



}