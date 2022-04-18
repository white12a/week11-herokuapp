package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        WebElement emailFiled=driver.findElement(By.id("username"));// email field locator
        emailFiled.sendKeys("tomsmith");// send keys to email field
        WebElement passwordField= driver.findElement(By.id("password"));// password field locator
        passwordField.sendKeys("SuperSecretPassword!");// send keys to password field
        WebElement loginButton=driver.findElement(By.xpath("//button[@class='radius']/i"));// login button
        loginButton.click();//click login button
        WebElement secureMsg=driver.findElement(By.xpath("//div[@id='content']/div/h2"));
        String actualText=secureMsg.getText();
        String expectedText="Secure Area";
        Assert.assertEquals("Secure area text not visible",expectedText,actualText);

    }
    @Test
    public void verifyTheUsernameErrorMessage() {
        WebElement emailFiled=driver.findElement(By.id("username"));// email field locator
        emailFiled.sendKeys("tomsmith1");// send keys to email field
        WebElement passwordField= driver.findElement(By.id("password"));// password field locator
        passwordField.sendKeys("SuperSecretPassword!");// send keys to password field
        WebElement loginButton=driver.findElement(By.xpath("//button[@class='radius']/i"));// login button
        loginButton.click();//click login button
        WebElement text=driver.findElement(By.xpath("//div[@id='flash-messages']/div[1]"));//
        String actualMsg=text.getText();// get text
        String actual1Msg=actualMsg.substring(0,25);// split the text through substring method
        String expectedMsg="Your username is invalid!";//expected message
        Assert.assertEquals("Error message not visible",expectedMsg,actual1Msg);// validation
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        WebElement emailFiled=driver.findElement(By.id("username"));// email field locator
        emailFiled.sendKeys("tomsmith");// send keys to email field
        WebElement passwordField= driver.findElement(By.id("password"));// password field locator
        passwordField.sendKeys("SuperSecretPassword");// send keys to password field
        WebElement loginButton=driver.findElement(By.xpath("//button[@class='radius']/i"));// login button
        loginButton.click();//click login button
        WebElement text=driver.findElement(By.xpath("//div[@id='flash-messages']/div[1]"));
        String actualMsg=text.getText();
        String actual1Msg=actualMsg.substring(0,25);// split the text through substring method
        String expectedMsg="Your password is invalid!";
        Assert.assertEquals("Error message not visible",expectedMsg,actual1Msg);
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}
