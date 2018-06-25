package com.qainfotech.training.basic;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class demo_two {
	
	WebDriver driver=null;

	@BeforeClass
	public void intializeDriver(){
		System.out.println("Inside Before class");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
	}

	@BeforeMethod
	public void BeforeMethod(){
		System.out.println("Starting test method");
	}
    @Test(priority=1)
	public void clickBasicCourse()
	{
		driver.findElement(By.linkText("Basic Course")).click();
	}
	@Test(priority=2)
	public void greenBox()
	{
		driver.findElement(By.className("greenbox")).click();
	}
	@Test(priority=3)
	public void frameDungeon() {
		driver.switchTo().frame(driver.findElement(By.id("main")));


		String Box1 = driver.findElement(By.id("answer")).getAttribute("class");
		String Box2 = null;
		do
		{
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#child")));
			Box2=driver.findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().parentFrame();
			if(Box1.equals(Box2)) {

				driver.findElement(By.linkText("Proceed")).click();

				break;
			}
			else

				driver.findElement(By.linkText("Repaint Box 2")).click();
		}

		while(!(Box1.equals(Box2)));
	}
	@Test (priority=4)
	public void dragDrop() {
		WebElement From = driver.findElement(By.id("dragbox"));
		WebElement To =driver.findElement(By.id("dropbox"));

		Actions act=new Actions(driver);					


		act.dragAndDrop(From, To).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
	}
	@Test (priority=5)
	public void popUp() {

		driver.findElement(By.linkText("Launch Popup Window")).click();
		String winHandleBefore = driver.getWindowHandle();
		for(String windowHandle  : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}

		driver.findElement(By.id("name")).sendKeys("aditi");
		driver.findElement(By.id("submit")).click();
		driver.switchTo().window(winHandleBefore);
        driver.findElement(By.linkText("Proceed")).click();
	}
	@Test(priority=6)
	public void  generateToken(){
		driver.findElement(By.linkText("Generate Token")).click();
		String token1 = driver.findElement(By.id("token")).getText();
		String Token2 = token1.substring(token1.indexOf(":")+2);
		Cookie cookie = new Cookie("Token",Token2);
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();
	}


}









