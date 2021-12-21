package Pages;

import Utils.CommonMethods;
import Utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {
@FindBy (id = "txtUsername")
public WebElement usernamebox;
@FindBy(id="txtPassword")
public WebElement passwordbox;

@FindBy(id="btnLogin")
public WebElement loginbtn;
@FindBy(id="spanMessage")
public WebElement errormessage;

public LoginPage(){
    PageFactory.initElements(driver,this);

}
public void login(String username, String password){
    sendText(usernamebox,username);
    sendText(passwordbox,password);
    click(loginbtn);
}}