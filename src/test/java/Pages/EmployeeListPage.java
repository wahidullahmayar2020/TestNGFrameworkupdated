package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPage extends CommonMethods {
    @FindBy(id="empsearch_id")
    public WebElement EmployeeID;
    @FindBy(id="searchBtn")
    public WebElement SearchBtn;

    public EmployeeListPage(){
        PageFactory.initElements(driver, this);

    }

}
