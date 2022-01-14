package TestCases;

import Pages.AddEmployeePage;
import Pages.DashboardPage;
import Pages.EmployeeListPage;
import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import Utils.Constants;
import Utils.ExcelReading;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {
    @Test(groups = "smoke1")
    public void AddEmployee() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        DashboardPage dashboardPage = new DashboardPage();
        click(dashboardPage.PIMOption);
        click(dashboardPage.AddEmployeebtn);

        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, "Wahidullah");
        sendText(addEmployeePage.lastName, "Maharajah");
        sendText(addEmployeePage.middleName, "Wardak");
        click(addEmployeePage.CreatechkLoginbox);
        click(addEmployeePage.SaveBtn);

    }

    @Test(groups ="smoke1")
    //login operation
    public void addMultipleEmployees() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        //navigating to add employee page.
        DashboardPage dash = new DashboardPage();
        EmployeeListPage emplist = new EmployeeListPage();
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "EmployeeData");


        SoftAssert softAssert = new SoftAssert();


        Iterator<Map<String, String>> it = newEmployees.iterator();
        while (it.hasNext()) {
            click(dash.PIMOption);
            click(dash.AddEmployeebtn);
            Map<String, String> mapNewEmployee = it.next();
            sendText(addEmployeePage.firstName, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePage.lastName, mapNewEmployee.get("LastName"));
            String employeeIDValue = addEmployeePage.employeeID.getAttribute("Value");
            sendText(addEmployeePage.photograph, mapNewEmployee.get("Photograph"));
            //select checkbox
            if (!addEmployeePage.CreatechkLoginbox.isSelected()) {
                click(addEmployeePage.CreatechkLoginbox);
                //add login credentials for user
            }
            sendText(addEmployeePage.usernamecreate, mapNewEmployee.get("Username"));
            sendText(addEmployeePage.passwordcreate, mapNewEmployee.get("Password"));
            sendText(addEmployeePage.confirmpassword, mapNewEmployee.get("Password"));
            click(addEmployeePage.SaveBtn);

            //Navigate to the employee list
            click(dash.PIMOption);
            click(dash.EmployeeList);

            //enter employee ID
//            waitForClickability(emplist.EmployeeID);
            sendText(emplist.EmployeeID, employeeIDValue);
            click(emplist.SearchBtn);

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop");
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                Thread.sleep(1000);
                String expectedData = employeeIDValue + " " + mapNewEmployee.get("FirstName") + " " + mapNewEmployee.get("MiddleName") + " " + mapNewEmployee.get("LastName");
                softAssert.assertEquals(rowText, expectedData);


            }

        }

        softAssert.assertAll();


    }
}
