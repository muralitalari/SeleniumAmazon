package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AmazonLogin {
	@DataProvider(name = "data-provider")
	public Object[][] dpMethod() {
		return new Object[][] { { "pmuralitalari8@gmail.com" }, { "8341576973" } };
	}

	@Test(dataProvider = "data-provider")
	private void ValidLogin(String val) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(
				"https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_532ejkqrv6_b&adgrpid=90234497208&hvpone=&hvptwo=&hvadid=398778398633&hvpos=&hvnetw=g&hvrand=14535483959700056441&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9040221&hvtargid=kwd-296174151060&hydadcr=24608_1972811&gclid=CjwKCAjwk_WVBhBZEiwAUHQCmZzt1__YpWDBH5_-BPsWBfbJEWxQU61H2ZPJWAWa4HGH8t6-RpXYyhoCc1UQAvD_BwE");
		driver.findElement(By.xpath("//div[@id='nav-signin-tooltip']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(val);
		driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
		driver.quit();
	}

	@DataProvider(name = "data-provider2")
	public Object[][] dpMethod2() {
		return new Object[][] { { "pmuralitalari8@gmail.co", "email address" }, { "8347697", "mobile number" },
				{ "", "" } };
	}

	@Test(dataProvider = "data-provider2")
	private void InValidLogin(String user, String message) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(
				"https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_532ejkqrv6_b&adgrpid=90234497208&hvpone=&hvptwo=&hvadid=398778398633&hvpos=&hvnetw=g&hvrand=14535483959700056441&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9040221&hvtargid=kwd-296174151060&hydadcr=24608_1972811&gclid=CjwKCAjwk_WVBhBZEiwAUHQCmZzt1__YpWDBH5_-BPsWBfbJEWxQU61H2ZPJWAWa4HGH8t6-RpXYyhoCc1UQAvD_BwE");
		driver.findElement(By.xpath("//div[@id='nav-signin-tooltip']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
		if (!(user == "")) {
			driver.findElement(
					By.xpath("//span[normalize-space() ='We cannot find an account with that " + message + "']"));
		} else {
			driver.findElement(By.xpath(
					"//div[@Id='auth-email-missing-alert' and normalize-space()='Enter your email or mobile phone number']"));
		}
		driver.quit();
	}
}
