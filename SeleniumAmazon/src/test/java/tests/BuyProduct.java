package tests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BuyProduct {
	@DataProvider(name = "data-provider")
	public Object[][] dpMethod() {
		return new Object[][] { { "Search", "buynow" }, { "menu", "buynow" }, { "Search", "addtocart" },
				{ "menu", "addtocart" } };
	}

	@Test(dataProvider = "data-provider")
	private void BuyProduct(String val, String action) throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(
				"https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_532ejkqrv6_b&adgrpid=90234497208&hvpone=&hvptwo=&hvadid=398778398633&hvpos=&hvnetw=g&hvrand=14535483959700056441&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9040221&hvtargid=kwd-296174151060&hydadcr=24608_1972811&gclid=CjwKCAjwk_WVBhBZEiwAUHQCmZzt1__YpWDBH5_-BPsWBfbJEWxQU61H2ZPJWAWa4HGH8t6-RpXYyhoCc1UQAvD_BwE");
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(30));
		if (val.equalsIgnoreCase("search")) {
			driver.findElement(By.id("twotabsearchtextbox")).click();
			driver.findElement(By.id("twotabsearchtextbox"))
					.sendKeys("Echo Dot (3rd Gen) – New and improved smart speaker with Alexa (Black)");
			wb.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
			wb.until(ExpectedConditions.elementToBeClickable(By
					.xpath("//span[text()='Echo Dot (3rd Gen) – New and improved smart speaker with Alexa (Black)']")));
			driver.findElement(
					By.xpath("//span[text()='Echo Dot (3rd Gen) – New and improved smart speaker with Alexa (Black)']"))
					.click();
			Thread.sleep(3000);
			String parent = driver.getWindowHandle();
			String winHandleBefore = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

		} else if (val.equalsIgnoreCase("menu")) {
			wb.until(ExpectedConditions.elementToBeClickable(By.id("nav-hamburger-menu")));
			driver.findElement(By.id("nav-hamburger-menu")).click();
			driver.findElement(By.xpath("//*[text()='Echo & Alexa']/parent::a")).click();
			driver.findElement(By.xpath("//*[text()='Echo Dot (3rd Gen)']")).click();
		}
		Actions act = new Actions(driver);
		if (action.equalsIgnoreCase("buynow")) {
			wb.until(ExpectedConditions.elementToBeClickable(By.id("buy-now-button")));
			act.moveToElement(driver.findElement(By.id("buy-now-button")))
					.click(driver.findElement(By.id("buy-now-button"))).build().perform();
		} else if (action.equalsIgnoreCase("addtocart")) {
			Thread.sleep(2000);
			wb.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
			act.moveToElement(driver.findElement(By.id("add-to-cart-button")))
					.click(driver.findElement(By.id("add-to-cart-button"))).build().perform();
			try {
				driver.findElement(By.xpath("//*[normalize-space()='No thanks']")).click();
				act.moveToElement(driver.findElement(By.id("add-to-cart-button")))
						.click(driver.findElement(By.id("add-to-cart-button"))).build().perform();
				Thread.sleep(2000);
			} catch (Exception e) {

			}
			wb.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='proceedToRetailCheckout']")));
			act.moveToElement(driver.findElement(By.xpath("//*[@name='proceedToRetailCheckout']")))
					.click(driver.findElement(By.xpath("//*[@name='proceedToRetailCheckout']"))).build().perform();
		}
		wb.until(ExpectedConditions.elementToBeClickable(By.id("ap_email")));
		driver.quit();
	}
}
