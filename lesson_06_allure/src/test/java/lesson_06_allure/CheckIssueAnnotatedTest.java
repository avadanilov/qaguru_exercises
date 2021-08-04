package lesson_06_allure;

import org.junit.jupiter.api.Test;

public class CheckIssueAnnotatedTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    private final CheckIssuesWebSteps steps = new CheckIssuesWebSteps();

    @Test
    public void shouldSeeIssueInRepository() {
        steps.openMainPage()
                .searchForRepository(REPOSITORY)
                .goToRepository(REPOSITORY)
                .openIssuesTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
