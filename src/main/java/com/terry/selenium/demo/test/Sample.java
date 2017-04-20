package com.terry.selenium.demo.test;

import com.google.common.base.StandardSystemProperty;
import com.terry.selenium.demo.lib.WebBrowser;
import com.terry.selenium.demo.lib.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Xianghe on 2017/4/19.
 */
public class Sample {
    private void runStep(WebDriver driver) {
        driver.get("http://www.baidu.com");
//        driver.navigate().to("http://www.baidu.com");



        // 获取 网页的 title
        System.out.println("1 Page title is: " + driver.getTitle());

        // 通过 id 找到 input 的 DOM
        WebElement element = driver.findElement(By.id("kw"));

        // 输入关键字
        element.sendKeys("zTree");

        // 提交 input 所在的 form
        element.submit();

        // 通过判断 title 内容等待搜索页面加载完毕，Timeout 设置10秒
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                System.out.println(d.getTitle());
                return d.getTitle().toLowerCase().startsWith("ztree");
            }
        });

        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());


        // 关闭浏览器
        driver.quit();
    }

    private void chromeSample() {
        WebDriverConfig config = new WebDriverConfig();
        config.setBrowser(WebBrowser.CHROME);
        String chromeDriverPath = StandardSystemProperty.USER_DIR.value() +  "/src/main/resources/driver/win32/chromedriver.exe";

        config.setWebDriverPath(chromeDriverPath);

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        WebDriver webDriver = new ChromeDriver(capabilities);

        runStep(webDriver);
    }

    private void ieSample() {
        WebDriverConfig config = new WebDriverConfig();
        config.setBrowser(WebBrowser.IE);

        String ieDriverPath = StandardSystemProperty.USER_DIR.value() + "/src/main/resources/driver/win64/IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", ieDriverPath);

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("nativeEvents", false);

        WebDriver webDriver = new InternetExplorerDriver(capabilities);
        runStep(webDriver);
    }

    private void firefoxSample() {

    }

    public static void main(String[] args) {
        Sample sample = new Sample();
//        sample.chromeSample();
        sample.ieSample();
    }

}
