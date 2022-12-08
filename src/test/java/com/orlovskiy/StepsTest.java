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

public class StepsTest {

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
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем  репозиторий " + REPOSITORY_NAME, () -> {
            HEADER_INPUT.click();
            HEADER_INPUT.sendKeys(REPOSITORY_NAME);
            HEADER_INPUT.submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY_NAME, () -> {
            $(linkText(REPOSITORY_NAME)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText(ISSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY_NAME);
        steps.clickOnRepositoryLink(REPOSITORY_NAME);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
