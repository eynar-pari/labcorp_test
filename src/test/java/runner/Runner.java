package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import session.Session;
import utils.GetProperties;

import java.util.ArrayList;

@RunWith(Cucumber.class)
public class Runner {
    @Before
    public void before(Scenario scenario){
        GetProperties.getInstance().getUrlWeb();
    }

    @After("@ui")
    public void after(Scenario scenario){
        ArrayList<String> tabs = new ArrayList<String>(Session.getInstance().getDriver().getWindowHandles());
        Session.getInstance().getDriver().switchTo().window(tabs.get(0));

        if (scenario.isFailed()) {
            final byte[] screenShot= ((TakesScreenshot) Session.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot,"image/png","ScreenShot when the test was failed");
        }
        Session.getInstance().closeSession();
    }
}
