package lesson_06_allure;

import org.junit.jupiter.api.Test;

public class CheckIssue_AnnotatedTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    private CheckIssues_WebSteps steps = new CheckIssues_WebSteps();

    @Test
    public void shouldSeeIssueInRepository() {
        steps.openMainPage()
                .searchForRepository(REPOSITORY)
                .goToRepository(REPOSITORY)
                .openIssuesTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
