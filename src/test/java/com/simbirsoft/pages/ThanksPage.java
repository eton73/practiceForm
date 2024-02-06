package com.simbirsoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThanksPage {

  public WebDriver driver;

  public ThanksPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  @FindBy(xpath = "//*[@id=\"example-modal-sizes-title-lg\"]")
  private WebElement title;

  public String getTitle() {
    return title.getText();
  }

  public WebElement getTable() {
    return driver.findElement(
        By.xpath("//*[@class=\"table table-dark table-striped table-bordered table-hover\"]/tbody")
    );
  }

  public String getTextFromRow(WebElement table, Integer numberRow) {
    WebElement element = table.findElement(
        By.xpath(String.format("tr[%s]/td[2]", numberRow))
    );
    return element.getText();
  }

}
