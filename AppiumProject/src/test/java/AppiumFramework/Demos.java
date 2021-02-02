package AppiumFramework;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class Demos extends Capability {

static AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void bt() throws IOException, InterruptedException
	{
		//driver = capabilities(appPackage,appActivity,deviceName,platformName,chromeExecutable);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	@Test
	public void testcase1() throws InterruptedException
	{
	//Sign in with google ID and sign out
			Thread.sleep(8000);
			driver.findElement(By.xpath("//*[@text='Dismiss']")).click();
			driver.findElement(By.xpath("//*[@text='Sign in']")).click();
			driver.findElement(By.xpath("//*[@text='Continue with Google']")).click();
			driver.findElement(By.id("com.google.android.gms:id/container")).click();
			Thread.sleep(3000);
			driver.findElement(By.className("android.widget.ImageView")).click();
			driver.findElement(By.xpath("//*[@text='Sign out']")).click();
			driver.findElement(By.id("android:id/button1")).click();
			
	}
	@Test
	public void testcase2() throws InterruptedException
	{
		//Navigating to play store for downloading app
		driver.findElement(By.className("android.widget.ImageView")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"org.khanacademy.android:id/action_bar_root\").instance(0)).scrollIntoView(new UiSelector().textMatches(\"Download now\").instance(0))").click();
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); 		    
		}
		Thread.sleep(5000);
		driver.context("WEBVIEW_org.khanacademy.android");
		Thread.sleep(5000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");

	}
	@Test
	//select language and region
	public void testcase3() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@text='Language & region']")).click();
		driver.findElement(By.xpath("//*[@text='English • United States']")).click();
		driver.findElement(By.className("android.widget.ImageView")).click();
	}
	
	@Test
	public void testcase4() throws InterruptedException
	{
	//verify privacy policy
		driver.findElement(By.xpath("//*[@text='Privacy policy']")).click();
		driver.findElement(By.id("android:id/button_once")).click();
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
		}
		Thread.sleep(5000);
		driver.context("WEBVIEW_org.khanacademy.android");
		Thread.sleep(5000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		
	}


}

