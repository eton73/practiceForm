package com.simbirsoft.pages;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeFormPage {

  public WebDriver driver;

  public PracticeFormPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  @FindBy(xpath = "//*[@id=\"firstName\"]")
  private WebElement firstNameField;

  @FindBy(xpath = "//*[@id=\"lastName\"]")
  private WebElement lastNameField;

  @FindBy(xpath = "//*[@id=\"userEmail\"]")
  private WebElement userEmailField;

  @FindBy(xpath = "//label[@for='gender-radio-1']")
  private WebElement genderMaleRadio;

  @FindBy(xpath = "//label[@for='gender-radio-2']")
  private WebElement genderFemaleRadio;

  @FindBy(xpath = "//label[@for='gender-radio-3']")
  private WebElement genderOtherRadio;

  @FindBy(xpath = "//*[@id=\"userNumber\"]")
  private WebElement userNumberField;

  @FindBy(xpath = "//*[@id=\"dateOfBirthInput\"]")
  private WebElement dateOfBirthInputField;

  @FindBy(xpath = "//*[@id=\"subjectsInput\"]")
  private WebElement subjectsInputField;

  @FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
  private WebElement sportsCheckbox;

  @FindBy(xpath = "//label[@for='hobbies-checkbox-2']")
  private WebElement readingCheckbox;

  @FindBy(xpath = "//label[@for='hobbies-checkbox-3']")
  private WebElement musicCheckbox;

  @FindBy(css = "input#uploadPicture")
  private WebElement uploadPictureFile;

  @FindBy(css = "textarea#currentAddress")
  private WebElement currentAddressTextArea;

  @FindBy(id = "react-select-3-input")
  private WebElement reactSelectStateInput;

  @FindBy(id = "react-select-4-input")
  private WebElement reactSelectCityInput;

  @FindBy(id = "submit")
  private WebElement submitButton;

  public void inputFirstNameField(String firstName) {
    firstNameField.sendKeys(firstName);
  }

  public void inputLastNameField(String lastName) {
    lastNameField.sendKeys(lastName);
  }

  public void inputUserEmailField(String userEmail) {
    userEmailField.sendKeys(userEmail);
  }

  public void clickGenderMaleRadio() {
    genderMaleRadio.click();
  }

  public void clickGenderFemaleRadio() {
    genderFemaleRadio.click();
  }

  public void clickGenderOtherRadio() {
    genderOtherRadio.click();
  }

  public void inputGender(String gender) {
    if (gender == null) {
      return;
    }
    switch(gender.toUpperCase(Locale.ROOT)) {
      case "MALE":
        clickGenderMaleRadio();
        break;
      case "FEMALE":
        clickGenderFemaleRadio();
        break;
      case "OTHER":
        clickGenderOtherRadio();
        break;
      default:
        break;
    }

  }

  public void inputUserNumberField(String userNumber) {
    userNumberField.sendKeys(userNumber);
  }

  public void inputDateOfBirthInputField(String dateOfBirthInput) {
    dateOfBirthInputField.clear();
    dateOfBirthInputField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    dateOfBirthInputField.sendKeys(dateOfBirthInput);
  }

  public void inputSubjectsInputField(List<String> subjectsInput) {
    subjectsInput.forEach(s -> {
      subjectsInputField.sendKeys(s);
      subjectsInputField.sendKeys(Keys.TAB);
    });
  }

  public void inputHobbiesInputField(List<String> hobbiesInput) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    hobbiesInput.forEach(h -> {
      switch(h.toUpperCase(Locale.ROOT)) {
        case "SPORTS":
          sportsCheckbox.click();
          break;
        case "READING":
          readingCheckbox.click();
          break;
        case "MUSIC":
          musicCheckbox.click();
          break;
        default:
          break;
      }
    });
  }

  public void inputUploadPictureFile(File uploadPicture) {
    if (uploadPicture == null) {
      return;
    }
    uploadPictureFile.sendKeys(uploadPicture.getAbsolutePath());
  }

  public void inputCurrentAddressTextArea(String currentAddress) {
    currentAddressTextArea.sendKeys(currentAddress);
  }

  public void inputReactSelectStateInput(String stateOption) {
    reactSelectStateInput.sendKeys(stateOption);
    reactSelectStateInput.sendKeys(Keys.TAB);
  }

  public void inputReactSelectCityInput(String cityOption) {
    reactSelectCityInput.sendKeys(cityOption);
    reactSelectCityInput.sendKeys(Keys.TAB);
  }

  public void submit() {
    //firstNameField.sendKeys(Keys.ENTER);
    submitButton.submit();
  }
}
