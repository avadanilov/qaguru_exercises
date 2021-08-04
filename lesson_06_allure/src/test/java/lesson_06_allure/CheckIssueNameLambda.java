package lesson_06_allure;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class CheckIssueNameLambda {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    @Test
    public void testRepositoryIssue() {
        step("Открываем главную страницу", () -> open("https://github.com"));
        step("Ищем репозиторий " + REPOSITORY, () -> $(".header-search-input").val(REPOSITORY).submit());
        step("Переходим в репозиторий", () -> $(linkText(REPOSITORY)).click());
        step("Переходим в раздел Issues", () -> $(partialLinkText("Issues")).click());
        step("Проверяем что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(exist);
        });
    }
}

