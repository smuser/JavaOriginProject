import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ResizeExample {
	WebDriver driver;

	@Test
	public void testToResizeElement() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://jqueryui.com/resizable/");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
		Thread.sleep(1000);
		WebElement resizeableElement = driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
		resize(resizeableElement, 50, 50);
	}

	public void resize(WebElement elementToResize, int xOffset, int yOffset) {
		try {
			if (elementToResize.isDisplayed()) {
				Actions action = new Actions(driver);
				action.clickAndHold(elementToResize).moveByOffset(xOffset, yOffset).release().build().perform();
			} else {
				System.out.println("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + elementToResize + "is not attached to the page document "	+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to resize" + elementToResize + " - "	+ e.getStackTrace());
		}
	}

}