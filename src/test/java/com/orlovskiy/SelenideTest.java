package com.orlovskiy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    private static final SelenideElement HEADER_INPUT = $(".header-search-input");
    private static final String REPOSITORY_NAME = "eroshenkoam/allure-example";
    private static final String ISSUE = "#80";
    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.browserSize = "1932x1160";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        HEADER_INPUT.click();
        HEADER_INPUT.sendKeys(REPOSITORY_NAME);
        HEADER_INPUT.submit();

        $(linkText(REPOSITORY_NAME)).click();
        $("#issues-tab").click();
        $(withText(ISSUE)).should(exist);
    }
}
