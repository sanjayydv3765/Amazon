package amazon;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Strings;

public class Amazon1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000000, TimeUnit.SECONDS);
		WebElement SearchBar = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		SearchBar.sendKeys("samsung");

		WebElement Go = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		Go.click();

		List<WebElement> ProductList = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		System.out.println("No. of items " + ProductList.size());

		List<WebElement> Prices = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
	     System.out.println("Prices are" + Prices);

		for (int i = 0; i < ProductList.size(); i++) {
			System.out.println(ProductList.get(i).getText() + "   " + Prices.get(i).getText());
		}
		String ParentWin = driver.getWindowHandle();
		String ExpectedValue = ProductList.get(0).getText();
		System.out.println("Product Value at 0" + ExpectedValue);

		ProductList.get(0).click();

		Set<String> AllWindows = driver.getWindowHandles();
		for (String win : AllWindows) {
			System.out.println(win);
			if (!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
		WebElement title = driver.findElement(By.xpath("//span[@id='productTitle']"));
		String Str = title.getText();
		if (Str.equals(ExpectedValue)) {
			System.out.println("Title is matching");
		} else {
			System.out.println("Title is not matching");
		}
		driver.quit();
	}
}
