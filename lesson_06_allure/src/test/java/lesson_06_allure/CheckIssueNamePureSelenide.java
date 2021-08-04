package lesson_06_allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class CheckIssueNamePureSelenide {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    @Test
    public void testRepositoryIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-input").val(REPOSITORY).submit();
        $(linkText(REPOSITORY)).click();
        $(partialLinkText("Issues")).click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
    }
}
