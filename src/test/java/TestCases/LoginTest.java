package TestCases;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import Utils.Constants;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends CommonMethods {

    @Test
    public void AdminLogin(){

        //login to HRMS application
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordbox,ConfigReader.getPropertyValue("password"));
        click(loginPage.loginbtn);

        //validation
        //assertion

        DashboardPage dashboard=new DashboardPage();
        Assert.assertTrue(dashboard.welcomemessage.isDisplayed(),"welcome message is not displayed");



    }

@Test(dataProvider ="invalidData")
    public void invalidLoginErrorMessageValidation(String username,String password, String message){
        LoginPage loginPage=new LoginPage();
        sendText(loginPage.usernamebox,username);
        sendText(loginPage.passwordbox,password);
        click(loginPage.loginbtn);
//    WebDriverWait wait=new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        String actualText=loginPage.errormessage.getText();
        Assert.assertEquals(actualText,message,"Error message is not matched");



}
@DataProvider
public Object[][] invalidData(){
        Object [][] data= {
                {"Admin", "123", "Invalid credentials"},
                {"Admin1", "Syntax123!", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Syntax123!", "Username cannot be empty"},
        };
        return data;
}
}
