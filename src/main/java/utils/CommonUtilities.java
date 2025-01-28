package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class CommonUtilities {

	public static Properties loadPropertiesFile() {
		Properties prop = new Properties();
		String filePath = "D:\\Automation\\Fonada\\resources\\projectdata\\config.properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			prop.load(fis);
			System.out.println("Properties file loaded successfully from: {}");
		} catch (IOException e) {
			System.out.println("Error loading properties file from: {}");
		}

		return prop;
	}

	public static void setProperties(String propertyKey, String propertyValue, Properties prop) {
		prop.setProperty(propertyKey, propertyValue);
		FileWriter fr = null;
		try {
			fr = new FileWriter(System.getProperty("user.dir") + "\\resources\\projectdata\\config.properties");
			prop.store(fr, "Data Updated Successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String generateBrandNewEmail() {

		Date date = new Date();
		String dateString = date.toString();
		String dateStringWithoutSpaces = dateString.replaceAll("\\s", "");
		String dateStringWithoutSpacesAndColons = dateStringWithoutSpaces.replaceAll("\\:", "");
		String brandNewEmail = dateStringWithoutSpacesAndColons + "@gmail.com";
		return brandNewEmail;

	}
//	public static String getTextFromMessage(Message message) throws Exception {
//		String result = "";
//		if (message.isMimeType("text/plain")) {
//			result = message.getContent().toString();
//		} else if (message.isMimeType("text/html")) {
//			result = message.getContent().toString();
//		} else if (message.isMimeType("multipart/*")) {
//			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//			result = getTextFromMimeMultipart(mimeMultipart);
//		}
//		return result;
//	}

//	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
//		StringBuilder result = new StringBuilder();
//		int count = mimeMultipart.getCount();
//		for (int i = 0; i < count; i++) {
//			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//			if (bodyPart.isMimeType("text/plain")) {
//				result.append(bodyPart.getContent());
//			} else if (bodyPart.isMimeType("text/html")) {
//				result.append(bodyPart.getContent());
//			} else if (bodyPart.getContent() instanceof MimeMultipart) {
//				result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
//			}
//		}
//		return result.toString();
//	}
	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
		BufferedImage ActualImage = null;
		BufferedImage ExpectedImage = null;
		try {
			ActualImage = ImageIO.read(new File(actualImagePath));
			ExpectedImage = ImageIO.read(new File(expectedImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(ExpectedImage, ActualImage);
		return imgDifference.hasDiff();
	}

	public static void takeScreenshot(WebDriver driver, String screenshotPath) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(srcScreenshot, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String validEmailRandomizeGenerator() {
		String[] validEmails = { "birajup01@gmail.com", "birajup02@gmail.com", "birajup01@gmail.com",
				"birajup01@gmail.com" };
		Random random = new Random();
		int index = random.nextInt(validEmails.length);
		return validEmails[index];
	}

	public static ExtentReports getExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		ExtentSparkReporterConfig sparkConfig = sparkReporter.config();
		sparkConfig.setReportName("Tutorials Ninja Test Automation Results");
		sparkConfig.setDocumentTitle("TNER Results");

		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReport.setSystemInfo("username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Selenium WebDriver Version", "4.27.0");
		return extentReport;
	}

	public static String takeScreenshotAndReturnPath(WebDriver driver, String pathToBeCopied) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
        String destScreenShotPath = System.getProperty("user.dir")+pathToBeCopied;
		try {
			FileHandler.copy(srcScreenshot, new File(destScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenShotPath;
}}
