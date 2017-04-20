package com.terry.selenium.demo;

import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Created by Xianghe on 2017/4/19.
 */
public class Demo {


    public void test1() {
        String url = "http://www.baidu.com";

        String chromeDriverPath = StandardSystemProperty.USER_DIR.value() +  "/src/main/resources/driver/win32/chromedriver.exe";


        File chromeDriverExecutable = new File(chromeDriverPath);

        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(chromeDriverExecutable).usingAnyFreePort().build();

        try {
            chromeDriverService.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        WebDriver driver = new RemoteWebDriver(chromeDriverService.getUrl(),
                DesiredCapabilities.chrome());

        // 让浏览器访问 Baidu
        driver.get("http://www.baidu.com");
        // 用下面代码也可以实现
        // driver.navigate().to("http://www.baidu.com");

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
                return d.getTitle().toLowerCase().endsWith("ztree");
            }
        });

        // 显示搜索结果页面的 title
        System.out.println("2 Page title is: " + driver.getTitle());

        // 关闭浏览器
        driver.quit();
        // 关闭 ChromeDriver 接口
        chromeDriverService.stop();
    }

    public void test2() {

        String chromeDriverPath = StandardSystemProperty.USER_DIR.value() +  "/src/main/resources/driver/win32/chromedriver.exe";
        System.setProperty(
                "webdriver.chrome.driver",
                chromeDriverPath);
        // 创建一个 ChromeDriver 的接口，用于连接 Chrome
        // 创建一个 Chrome 的浏览器实例

        ChromeOptions chromeOptions = new ChromeOptions();

//        chromeOptions.addArguments("test-type");
//        chromeOptions.setExperimentalOption("excludeSwitches", Lists.newArrayList("ignore-certificate-errors"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        WebDriver driver = new ChromeDriver(capabilities);

        // 让浏览器访问 Baidu
        driver.get("http://www.baidu.com");
//        driver.navigate().to("http://www.baidu.com");
        // 用下面代码也可以实现
        // driver.navigate().to("http://www.baidu.com");

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

    public static void main(String[] args) {
        Demo d = new Demo();
        d.test1();
//        d.test2();
    }

}
