package com.terry.selenium.demo.lib;

import java.util.Optional;

/**
 * Created by Xianghe on 2017/4/19.
 */
public class WebDriverConfig {
    private WebBrowser browser;
    private Optional<String> browerBinPath;
    private Optional<String> webDriverPath;

    public WebBrowser getBrowser() {
        return browser;
    }

    public void setBrowser(WebBrowser browser) {
        this.browser = browser;
    }

    public Optional<String> getBrowerBinPath() {
        return browerBinPath;
    }

    public void setBrowerBinPath(String browerBinPath) {
        this.browerBinPath = Optional.ofNullable(browerBinPath);
    }

    public Optional<String> getWebDriverPath() {
        return webDriverPath;
    }

    public void setWebDriverPath(String webDriverPath) {
        this.webDriverPath = Optional.ofNullable(webDriverPath);
    }
}
