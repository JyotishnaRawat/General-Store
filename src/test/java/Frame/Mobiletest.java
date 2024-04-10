package Frame;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
public class Mobiletest extends Capabilities {
	
	AndroidDriver<AndroidElement> driver;
	
	
	@BeforeTest
	public  void bt() throws IOException, InterruptedException {
	
		service = startServer();
		 driver = cap(deviceName, platformName, appPackage, appActivity);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	@Test(enabled = true)
	public void shoes() throws InterruptedException {
		
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		service.stop();
		
		
	}	
}