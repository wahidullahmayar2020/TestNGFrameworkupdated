package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {
    @FindBy(id = "firstName")
    public WebElement firstName;
    @FindBy(id = "middleName")
    public WebElement middleName;
    @FindBy(id = "lastName")
    public WebElement lastName;
    @FindBy(id = "chkLogin")
    public WebElement CreatechkLoginbox;
    @FindBy(id = "btnSave")
    public WebElement SaveBtn;
    @FindBy(id = "employeeId")
    public WebElement employeeID;
    @FindBy(id = "photofile")
    public WebElement photograph;
    @FindBy(id = "user_name")
    public WebElement usernamecreate;

    @FindBy(id = "user_password")
    public WebElement passwordcreate;

    @FindBy(id = "re_password")
    public WebElement confirmpassword;

    public AddEmployeePage() {
        PageFactory.initElements(driver, this);
    }
}
