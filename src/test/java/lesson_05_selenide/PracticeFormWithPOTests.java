package lesson_05_selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import lesson_05_selenide.pages.RegistrationPage;
import com.github.javafaker.Faker;

public class PracticeFormWithPOTests {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            eMail = faker.internet().emailAddress(),
            gender = "Male",
            number = faker.numerify("8#########"),
            subject = "maths",
            hobby = "Reading",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi",
            picturePath = "./img/",
            pictureFileName = "1.png";

    String date = "28", //date of birth
            month = "July",
            year = "2005";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {

        registrationPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(eMail)
                .selectGender(gender)
                .typeUserNumber(number)
                .setDateOfBirth(date, month, year)
                .setSubjects(subject)
                .setHobby(hobby)
                .uploadPicture(picturePath + pictureFileName)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submit();


        registrationPage.checkStudentName(firstName, lastName)
                .checkStudentEmail(eMail)
                .checkMobile(number)
                .checkDateOfBirth(date, month, year)
                .checkSubjects(subject)
                .checkHobbies(hobby)
                .checkPicture(pictureFileName)
                .checkAddress(address)
                .checkStateAndCity(state, city);
    }
}
