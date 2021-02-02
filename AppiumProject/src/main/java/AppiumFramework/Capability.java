package AppiumFramework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Capability {
	protected static String appPackage;
	protected static String appActivity;
	protected static String deviceName;
	protected static String platformName;
	protected static String chromeExecutable;
	
	public AppiumDriverLocalService service;
	public AppiumDriverLocalService startServer() {
		boolean flag = checkifserverisRunning(4723);
		if(flag) {
		service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				//this is node js path
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				//this is appium path 
				.withAppiumJS(new File("C:\\Users\\VikasGundewar\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("0.0.0.0").usingPort(4723));
		service.start();
		}
		return service;
	}
	//This is the socket program for checking the port is in use
	public static boolean checkifserverisRunning(int port)
	{
		boolean isServerRunning=false;
        ServerSocket Serversocket;
        try {
            Serversocket = new ServerSocket(port);
            Serversocket.close();
        } catch (IOException e) {
             isServerRunning=true;
        }
        finally
        {
            Serversocket = null;
        }
        return isServerRunning;
	}
	
	
	public static void startEmulator() throws IOException, InterruptedException{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"//src//main//java//emulator.bat");
		Thread.sleep(8000);
		
	}

	public static AndroidDriver<AndroidElement> capabilities(String appPackage,String appActivity,String deviceName,String platformName,String chromeExecutable) throws IOException, InterruptedException {
		FileReader fis= new FileReader(System.getProperty("user.dir")+"//src//main//java//global.properties");
		Properties pro= new Properties();
		pro.load(fis);
		
		appPackage=pro.getProperty("appPackage");
		appActivity=pro.getProperty("appActivity");
		deviceName=pro.getProperty("deviceName");
		platformName=pro.getProperty("platformName");
		chromeExecutable=pro.getProperty("chromeExecutable");
		
		if (deviceName.contains("vikas")) {
			startEmulator();
		}
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "deviceName");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "appPackage");
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "appActivity");
		cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE,"chromeExecutable" );
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;
	}

}
