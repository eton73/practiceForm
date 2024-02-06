package com.simbirsoft;

import com.simbirsoft.pages.PracticeFormPage;
import com.simbirsoft.pages.ThanksPage;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoTest {

  private static final String THANKS_EXPECTED_PAGE = "Thanks for submitting the form";

  public static PracticeFormPage practiceFormPage;
  public static ThanksPage thanksPage;
  public static WebDriver driver;

  @BeforeClass
  public static void setup() {
    System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    driver.get(ConfProperties.getProperty("startPage"));

    practiceFormPage = new PracticeFormPage(driver);
    thanksPage = new ThanksPage(driver);
  }

  @Test
  public void practiceTest() {
    String firstName = ConfProperties.getProperty("firstName");
    String lastName = ConfProperties.getProperty("lastName");
    String userEmail = ConfProperties.getProperty("userEmail");
    String gender = ConfProperties.getProperty("gender");
    String userNumber = ConfProperties.getProperty("userNumber");
    String dateOfBirthInput = ConfProperties.getProperty("dateOfBirthInput");
    String subjects = ConfProperties.getProperty("subjectsInput");
    String hobbies = ConfProperties.getProperty("hobbies");
    File uploadFile = new File(ConfProperties.getProperty("uploadPictureFilePath"));
    String currentAddressTextArea = ConfProperties.getProperty("currentAddressTextArea");
    String reactSelectStateInput = ConfProperties.getProperty("reactSelectStateInput");
    String reactSelectCityInput = ConfProperties.getProperty("reactSelectCityInput");

    practiceFormPage.inputFirstNameField(firstName);
    practiceFormPage.inputLastNameField(lastName);
    practiceFormPage.inputUserEmailField(userEmail);
    practiceFormPage.inputGender(gender);
    practiceFormPage.inputUserNumberField(userNumber);
    practiceFormPage.inputDateOfBirthInputField(dateOfBirthInput);

    if (subjects != null) {
      List<String> subjectsList = Arrays.stream(subjects.split(",")).collect(Collectors.toList());
      practiceFormPage.inputSubjectsInputField(subjectsList);
    }

    if (hobbies != null) {
      List<String> hobbiesList = Arrays.stream(hobbies.split(",")).collect(Collectors.toList());
      practiceFormPage.inputHobbiesInputField(hobbiesList);
    }

    practiceFormPage.inputUploadPictureFile(uploadFile);
    practiceFormPage.inputCurrentAddressTextArea(currentAddressTextArea);
    practiceFormPage.inputReactSelectStateInput(reactSelectStateInput);
    practiceFormPage.inputReactSelectCityInput(reactSelectCityInput);

    practiceFormPage.submit();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    Assert.assertEquals(THANKS_EXPECTED_PAGE, thanksPage.getTitle());

    WebElement actualTable = thanksPage.getTable();
    Assert.assertEquals(String.format("%s %s", firstName, lastName), thanksPage.getTextFromRow(actualTable, 1));
    Assert.assertEquals(userEmail, thanksPage.getTextFromRow(actualTable, 2));
    Assert.assertTrue(gender.equalsIgnoreCase(thanksPage.getTextFromRow(actualTable, 3)));
    Assert.assertEquals(userNumber, thanksPage.getTextFromRow(actualTable, 4));
    Assert.assertTrue(subjects.equalsIgnoreCase(thanksPage.getTextFromRow(actualTable, 6)));
    Assert.assertTrue(hobbies.equalsIgnoreCase(thanksPage.getTextFromRow(actualTable, 7)));
    Assert.assertEquals(uploadFile.getName(), thanksPage.getTextFromRow(actualTable, 8));
    Assert.assertEquals(currentAddressTextArea, thanksPage.getTextFromRow(actualTable, 9));
    Assert.assertEquals(String.format("%s %s", reactSelectStateInput, reactSelectCityInput), thanksPage.getTextFromRow(actualTable, 10));
  }

  @AfterClass
  public static void quit() {
    driver.quit();
  }

}
