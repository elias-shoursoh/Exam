package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class MembershipPopUp extends BasePage {

	@FindBy(css = "button.btn.w100")
	private WebElement acceptBtn; // Accept advertisements and tracking

	public MembershipPopUp(WebDriver driver) {
		super(driver);
	}

	@Step("Accept the advertisements and tracking condition")
	public void clickAccept() {
		click(acceptBtn);
	}
}
