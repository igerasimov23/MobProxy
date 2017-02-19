import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

/**
 * Created by Ilya on 2/19/2017.
 */
public class MobProxyTest {

    public Capabilities capabilities(){
        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);

        proxy.addRequestFilter((request, content, info) -> {
            String s = info.getOriginalUrl();
            if(s.endsWith("money-market")){
                System.out.println(s);
                System.out.println(content.getTextContents());
            }
            return null;
        });

        proxy.addResponseFilter((response, content, info) -> {
            String s = info.getOriginalUrl();
            if(s.endsWith("money-market")){
                System.out.println(s);
                System.out.println(content.getTextContents());
            }
        });

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        return capabilities;
    }

    @Test
    public void testProxy() throws Exception {
        // start the browser up
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver driver = new ChromeDriver(capabilities());

        // open yahoo.com
        driver.get("https://qq1cardappdacq.americanexpress.com/us/next-step-loans/apply");

        DumasLocators actions = new DumasLocators(driver);
        actions.login();
        actions.setLoan();

        driver.quit();
    }
}
