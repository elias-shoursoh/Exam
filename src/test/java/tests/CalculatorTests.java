package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.CalculatorPage;
import pageobject.MembershipPopUp;

@Severity(SeverityLevel.NORMAL)
@Epic("Calculator feature's Functionality")
public class CalculatorTests extends BaseTest {

	private String additionFormula = "2+3";
	private String substractionFormula = "10-2";
	private String multiOperationFormula = "(10-2)*2";
	private String sineFormula = "sin(30)";

	@Test(priority = 1, alwaysRun = true, description = "Clear initial history list")
	@Story("When opening history frame and clicking on clear history button, history should be cleared")
	@Description("Clear history test")
	public void clearHistoryTest() {
		CalculatorPage cp = new CalculatorPage(driver);
		cp.clearHistory();
		try {
			MembershipPopUp mpp = new MembershipPopUp(driver);
			mpp.clickAccept();
		} catch (Exception e) {
			// pass
		}
		Assert.assertTrue(cp.getHistoryListSize() == 0);
	}

	@Test(priority = 2, dependsOnMethods = { "clearHistoryTest" }, description = "Addition operation correctness test")
	@Story("When calculating an addition operation, a correct mathematical result should be displayed")
	@Description("Addition operation test")
	public void additionTest() {
		CalculatorPage cp = new CalculatorPage(driver);
		cp.calculate(additionFormula);
		Assert.assertEquals(cp.getResult(), "5");
	}

	@Test(priority = 3, description = "Substraction operation correctness test")
	@Story("When calculating a substraction operation, a correct mathematical result should be displayed")
	@Description("Substraction operation test")
	public void substractionTest() {
		CalculatorPage cp = new CalculatorPage(driver);
		cp.calculate(substractionFormula);
		Assert.assertEquals(cp.getResult(), "8");
	}

	@Test(priority = 4, description = "Multiple operations correctness test")
	@Story("When calculating multiple operations in one single formula, a correct mathematical result should be displayed")
	@Description("Multiple operations test")
	public void multiOperationCalcTest() {
		CalculatorPage cp = new CalculatorPage(driver);
		cp.calculate(multiOperationFormula);
		Assert.assertNotEquals(cp.getResult(), "20");
	}

	@Test(priority = 5, description = "Sine operation correctness test")
	@Story("When calculating a Sine operation, a correct mathematical result should be displayed")
	@Description("Sine operation test")
	public void sinosTest() {
		CalculatorPage cp = new CalculatorPage(driver);
		cp.calculate(sineFormula);
		Assert.assertEquals(cp.getResult(), "0.5");
	}

	@Test(priority = 6, description = "History frame box display feature test")
	@Story("When opening the history frame box, all previous executed formulas should be displayed")
	@Description("History frame box display test")
	public void historyFrameTest() {
		CalculatorPage cp = new CalculatorPage(driver);
		int expectedHistoryListNum = 4;
		int actualHistoryListNum = cp.getHistoryListSize();
		Assert.assertTrue(expectedHistoryListNum == actualHistoryListNum);
	}
}
