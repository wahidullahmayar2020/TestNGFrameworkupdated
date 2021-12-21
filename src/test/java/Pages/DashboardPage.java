package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class DashboardPage extends CommonMethods {
    @FindBy (id ="welcome")
    public WebElement welcomemessage;
    @FindBy (id="menu_pim_viewPimModule")
    public WebElement PIMOption;
    @FindBy(id="menu_pim_addEmployee")
    public WebElement AddEmployeebtn;





    public DashboardPage(){

        PageFactory.initElements(driver,this);
    }

}
