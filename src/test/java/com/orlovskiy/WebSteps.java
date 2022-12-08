package com.orlovskiy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    private static final SelenideElement HEADER_INPUT = $(".header-search-input");
    private static final String REPOSITORY_NAME = "eroshenkoam/allure-example";
    private static final String ISSUE = "#80";

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
        Configuration.browserSize = "1932x1160";
        Configuration.holdBrowserOpen = true;
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем  репозиторий {repo}")
    public void searchForRepository(String repo) {
        HEADER_INPUT.click();
        HEADER_INPUT.sendKeys(repo);
        HEADER_INPUT.submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void shouldSeeIssueWithNumber(String issue) {
        $(withText(issue)).should(exist);
    }
}
