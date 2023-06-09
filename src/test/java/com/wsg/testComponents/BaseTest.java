
package com.wsg.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
      // WebDriverManger.setup().chrome();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//com//wsg//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
	//	System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			
			ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.addArguments("--start-maximized");
		    chromeOptions.addArguments("--remote-allow-origins=*");
		    driver = new ChromeDriver(chromeOptions);

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		return driver;

	}

	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

	public List<Map<String, Map<String, Object>>> getJsonDataToMap(String filePath) throws IOException {

		// Read JSON to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		// String to Map - Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Map<String, Object>>> data = mapper.readValue(jsonContent,new TypeReference<List<Map<String, Map<String, Object>>>>() {});

		return data;
	}

	@BeforeMethod(alwaysRun=true)
	public void setUp() throws IOException {
		
		 initializeDriver();
		
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {

		 driver.quit();
	}

}
