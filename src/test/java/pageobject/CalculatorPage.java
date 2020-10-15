package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CalculatorPage extends BasePage {

	@FindBy(css = "[name='input']")
	private WebElement formulaField;
	@FindBy(css = ".options #hist")
	private WebElement historyFrameBtn;
	@FindBy(css = "a#clearhistory")
	private WebElement clearHistoryBtn;
	@FindBy(css = "#histframe div button")
	private WebElement closeHistoryBtn;
	@FindBy(css = "button#BtnCalc")
	private WebElement calculateResultBtn; // the "=" sign

	@FindBy(css = "#histframe li .l")
	private List<WebElement> formulasHistoryList; // formulas side
	@FindBy(css = "#histframe li .r")
	private List<WebElement> resultsHistoryList; // results side

	// initial list size will be used to monitor results list size in history frame
	// box
	private static int initialListSize = 0;

	// Constructor
	public CalculatorPage(WebDriver driver) {
		super(driver);
	}

	@Step("Open History frame")
	public void openHistoryFrame() {
		click(historyFrameBtn);
	}

	@Step("Click Clear History button")
	public void clickClearHistoryBtn() {
		click(clearHistoryBtn);
		acceptAlert();
	}

	@Step("Close History frame")
	public void closeHistoryFrame() {
		click(closeHistoryBtn);
	}

	@Step("Clear all previous history")
	public void clearHistory() {
		openHistoryFrame();
		clickClearHistoryBtn();
		closeHistoryFrame();
	}

	@Step("Calculate {formula}")
	public void calculate(String formula) {
		fillText(formulaField, formula);
		click(calculateResultBtn);
	}

	@Step("Get result")
	public String getResult() {
		List<WebElement> results = resultsHistoryList;
		while (results.size() == initialListSize) {
			results = resultsHistoryList;
		}
		initialListSize = results.size();
		return results.get(0).getAttribute("title");
	}

	@Step("Get number of displayed formulas in list")
	public int getHistoryListSize() {
		openHistoryFrame();
		sleep(1000);
		List<WebElement> formulas = formulasHistoryList;
		return formulas.size();
	}
}
