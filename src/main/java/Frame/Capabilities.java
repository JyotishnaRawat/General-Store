
package Frame;
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

public class Capabilities {
	static String deviceName;
	static String platformName;
	static String appPackage;
	static String appActivity;
	
	public AppiumDriverLocalService service;
	 //for starting the server
	 public AppiumDriverLocalService startServer()
	 {
	 boolean flag = checkifserverisRunning(4723);
	 if(!flag)
	 {
	 service = AppiumDriverLocalService.buildDefaultService();
	 service.start();
	 }
	 return service;
	 }
	 public static boolean checkifserverisRunning(int port)
	 {
	 boolean isServerRunning=false;
	 ServerSocket serversocket;
	 try{
	 serversocket = new ServerSocket(port);
	 serversocket.close();
	 }
	 catch(IOException e)
	 {
	 isServerRunning = true;
	 }
	 finally {
	 serversocket=null;
	 }
	 return isServerRunning;
	 }
	
	public static void startEmulator() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\extension.bat");
		Thread.sleep(10000);
		
	}
	
public static AndroidDriver<AndroidElement> cap(String deviceName, String platformName, String appPackage, String appActivity) throws IOException, InterruptedException {
	
	//There are two ways in which we can fetch our Global Properties:
	//1. FileIO
	//2. FileReader
	
		FileReader gp = new FileReader(System.getProperty("user.dir")+"/src/main/java/Global.properties");
		Properties pro = new Properties();
		pro.load(gp);
		 deviceName = pro.getProperty("deviceName");
		 platformName = pro.getProperty("platformName");
		 appPackage = pro.getProperty("appPackage");
		 appActivity = pro.getProperty("appActivity");
		
		if(deviceName.equals("Gauri")) {
			startEmulator();
		}
	
		DesiredCapabilities dc = new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		return driver;
		
		
	}
}

