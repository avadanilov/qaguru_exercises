package lesson_06_allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class CheckIssues_WebSteps {

    @Step("Открываем главную страницу")
    public CheckIssues_WebSteps openMainPage() {
        open("https://github.com");
        return this;
    }

    @Step("Ищем репозиторий [{repository}]")
    public CheckIssues_WebSteps searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").val(repository).submit();
        return this;
    }

    @Step("Переходим в репозиторий [{repository}]")
    public CheckIssues_WebSteps goToRepository(String repository) {
        $(linkText(repository)).click();
        return this;
    }

    @Step("Переходим в раздел Issues")
    public CheckIssues_WebSteps openIssuesTab() {
        $(partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем что существует Issue с номером {number}")
    public void shouldSeeIssueWithNumber(int number) {
        $(withText("#" + number)).should(exist);
    }
}
