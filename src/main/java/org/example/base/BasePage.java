package org.example.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage {
	public static WebDriver driver;
	private String url;
	private Properties prop;

	public BasePage() throws IOException {
//		prop = new Properties();
//		FileInputStream data = new FileInputStream(
//				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
//		prop.load(data);
		
		url = "https://www.automationtesting.co.uk/";
		
	}

	public WebDriver getDriver() throws IOException {
//		if (prop.getProperty("browser").equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe");
//			driver = new ChromeDriver();
//		} else if (prop.getProperty("browser").equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		} else {
//			System.setProperty("webdriver.edge.driver",
//					System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\msedgedriver.exe");
//			driver = new EdgeDriver();
//		}

		System.setProperty("webdriver.chrome.driver",
		System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

		return driver;
	}

	public String getUrl() throws IOException {
		//url = prop.getProperty("url");
		return url;
	}

	public void takeSnapShot(WebDriver webdriver) throws IOException {
		File srcFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\target\\screenshots\\"
				+ timestamp() + ".png");

		FileUtils.copyFile(srcFile, destFile);
	}

	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
}
