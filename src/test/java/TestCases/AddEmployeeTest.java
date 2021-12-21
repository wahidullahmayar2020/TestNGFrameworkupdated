package TestCases;

import Pages.AddEmployeePage;
import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import org.testng.annotations.Test;

public class AddEmployeeTest extends CommonMethods {
    @Test
    public void AddEmployee(){
        LoginPage loginPage=new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));
        DashboardPage dashboardPage=new DashboardPage();
 click(dashboardPage.PIMOption);
        click(dashboardPage.AddEmployeebtn);

        AddEmployeePage addEmployeePage=new AddEmployeePage();
        sendText(addEmployeePage.firstName,"Mj2021");
        sendText(addEmployeePage.lastName,"Mayar");
        sendText(addEmployeePage.middleName,"rahban");
        click(addEmployeePage.CreatechkLoginbox);
        click(addEmployeePage.SaveBtn);

    }
}
