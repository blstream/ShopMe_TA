package Steps;

import Base.BaseUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hook extends BaseUtil {

    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }


    @Before
    public void InitializeTest() {
        System.out.println(" Opening the browser : Chrome ");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.mac");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
        }

        base.Driver = new ChromeDriver();
        base.Driver.manage().window().maximize();
        base.Driver.navigate().to
                ("http://automationpractice.com/index.php?controller=my-account");
    }

    @After
    public void TearDownTest() {
        base.Driver.manage().deleteAllCookies();
        base.Driver.close();

    }
}

