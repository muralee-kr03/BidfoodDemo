package bidfood.co.nz.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

import bidfood.co.nz.runner.RunSetup;
import bidfood.co.nz.runner.RunnerClass;
import io.cucumber.java.Scenario;

public class Helper {
	

    static WebDriver driver;

    public static String strGlobalTCID;		// Used to rename the video file with test case id and is used in StopVideoRecording function.  Test case id is obtained from UpdateTestCase function......
    
    public static DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
	public static Date date = new Date();
	public static String dirVideoFilePath = System.getProperty("user.dir");
    public static String dirScreenshotFilePath = System.getProperty("user.dir");
    JavascriptExecutor jse = (JavascriptExecutor)driver;

	public Helper() throws Exception  
	{
			
			
		
	}

	public Helper(WebDriver driver) throws Exception  {
		
		this.driver = driver;
		
	
	}

	
	public static void renameReport() throws Throwable{
	
		 String strOldFileName = System.getProperty("user.dir")+ "//test-output//HTMLReport//Index.html";					// Storing the old file name------------------
		 RunSetup.reportFileName = System.getProperty("user.dir") +"//test-output//HTMLReport//Regression Report-"+Helper.GetConfigValue("Environment").toUpperCase()+"-"+ Helper.GetConfigValue("Browser").toUpperCase().trim() + "-"+ Helper.GetConfigValue("Platform").trim() + "-" +Helper.CheckDateTime()+".html";   // New file name for renaming with Test Case ID----------------
		 System.out.println("Old Report Path :"+strOldFileName );
		 System.out.println("New Report Path :"+RunSetup.reportFileName );
		 
		 File oldFile = new File(strOldFileName);											// Checking if old file exits
		 if(oldFile.exists()){												
			  System.out.println("Report Renamed");
			  File newFile = new File(RunSetup.reportFileName);										// Rename old file with Test case id
			  oldFile.renameTo(newFile);
			
		  }else{
			  System.out.println("File not found!");
		  }
	}
	
	 
	 public static String getscreenshotUpdate(WebDriver driver) throws IOException, InterruptedException 
     {
		  //strGlobalTCID = "TC001";
		  String strCDate, strScreenshotFile = "";
		  ScreenshotFolderAndFilePath();
		  System.out.println(dirScreenshotFilePath);
		  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  strCDate = dateFormat.format(date);
		  FileUtils.copyFile(screenshot, new File(dirScreenshotFilePath+"TestScript-"+strGlobalTCID+strCDate+".png"));
		  strScreenshotFile = dirScreenshotFilePath+"TestScript-"+strGlobalTCID+strCDate+".png";
		  //System.out.println("image parth :" + strScreenshotFile);
		  return strScreenshotFile;
		 
     }
	 
	
	 
	public static void ScreenshotFolderAndFilePath() throws InterruptedException, IOException {                                 
			System.out.println("Screenshot file Path:"+dirVideoFilePath.indexOf("TestExecutionScreenshot"));
			if(dirScreenshotFilePath.indexOf("TestExecutionScreenshot")== -1){
				dirScreenshotFilePath = dirScreenshotFilePath + "//target//TestExecutionScreenshot//";
				
				File ScreenshotFolder = new File(dirScreenshotFilePath);											
				 if(!ScreenshotFolder.exists()){	
					 ScreenshotFolder.mkdir();
				 }
				 
			}
		}
	//----------------------------------------Function delete exiting screenshot folder before starting the execution and creating the same
	
	 public static void clearScreenshotFolder() throws Throwable
	 {
		 
		 
		 String strScreenshotFoldertoDelete = dirScreenshotFilePath+ "//target//TestExecutionScreenshot";
		 File ScreenshotFolder = new File(strScreenshotFoldertoDelete);											
		 if(ScreenshotFolder.exists()){
			 
			 deleteRecursive(strScreenshotFoldertoDelete);
			 ScreenshotFolder.mkdir();
		 }
	 }
	 
 
	 private static void deleteRecursive(String str1) throws Throwable
	 {
		 FileUtils.deleteDirectory(new File(str1));
	        
	 }
	 public String getRandomNumber() 
	 {

		SecureRandom random = new SecureRandom();
		int num = random.nextInt(20000);
		String randomNumber = String.format("%05d", num);
		//System.out.println("Random number"+randomNumber);
		return randomNumber;

	 }
	 public static String CheckDateTime()throws Exception
	 {
		  Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= 
		  new SimpleDateFormat("dd-MM-HH-mm-ss");
		  String dateNow = formatter.format(currentDate.getTime());
//		  System.out.println("Now the date is :=>  " + dateNow);
		  return dateNow;
	}
	 
	 public String dateMinus(int daysToAdd)throws Exception
	 {
		  Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= 
		  new SimpleDateFormat("YYYY-MM-dd");
		  currentDate.add(Calendar.DAY_OF_MONTH, daysToAdd);  
		  String dateNow = formatter.format(currentDate.getTime());
		  
		  System.out.println("Minus the date is :=>  " + dateNow);
	
		  return dateNow;
	}
	
	 
	
	
	 public static String getTCIDFromScenario(Scenario scenario) 
	 {

		String TCID = "";

		if (scenario.getName().indexOf(" ") != -1)

		{

			TCID = scenario.getName().substring(0, scenario.getName().indexOf(" "));



		} else

		{

			TCID = "BCHK-000007"; // IF TCID NOT FOUND IN SCENARIO, THEN SETTING TCID TO A DEFAULT ONE

		}

		return TCID;

	 }
	 
	 public static ArrayList<String> getBUGIDFromScenario(Scenario scenario) 
		{

			String BUGID = "";
			BUGID = scenario.getName().substring(scenario.getName().indexOf("[") + 1, scenario.getName().lastIndexOf("]"));
			//System.out.println(BUGID);
			
			ArrayList<String> list=new ArrayList<String>();
			boolean flag = true;
			while(flag)
			{
				
				if(BUGID.indexOf(",") == -1)
				{
					flag = false;
					list.add(BUGID);
				}
				else
				{
					String tempID = BUGID.substring(0, BUGID.indexOf(","));
					list.add(tempID);
					BUGID = BUGID.substring(BUGID.indexOf(",") + 1, BUGID.length());
					
					if(BUGID.indexOf(",") == -1)
					{
						flag = false;
						list.add(BUGID);
					}
					
				}
				
				 
			}
			/*
			for (String lst : list) 
			{ 		      
		           System.out.println(lst); 		
		    }
			*/
			return list;
		

		}
	
	 public static String GetConfigValue(String strConfigLabel) throws Exception
	 {
		 FileInputStream io =new FileInputStream("Config.properties");
		 Properties property = new Properties();
		 property.load(io);
		 Set set = property.entrySet();  
		 String strConfigValue = property.get(strConfigLabel).toString();
		
		 return strConfigValue ;
	 }
	
	
	 
	public static void SendMail() throws Exception
	{
		System.out.println("Inside send email function");
		String strHostName, strPortNumber,  strFromEmailId, strToEmailId, strSubjectLine, strEmailBody, strReportPathName;
		final String strMailUserName, strMailPassword;
		//---------------------------------------------Getting SMPT and Email details from Properties file-----------------------------------------
		strHostName = GetConfigValue("HostName").trim();
		strPortNumber = GetConfigValue("PortNumber").trim();
		strMailUserName = GetConfigValue("MailUserName").trim();
		strMailPassword = GetConfigValue("MailPassword").trim();
		strFromEmailId = GetConfigValue("FromEmailId").trim();
		strToEmailId = GetConfigValue("ToEmailId").trim();
		strSubjectLine = GetConfigValue("SubjectLine").trim()+" ---- ";
		strEmailBody = GetConfigValue("EmailBody").trim();
		strReportPathName = System.getProperty("user.dir");
		strReportPathName = strReportPathName+GetConfigValue("ReportPathName").trim();
		//------------------------------------------------------------------------------------------------------------------------------------------
		Properties props = new Properties();
 		props.put("mail.smtp.host", strHostName);												// SMPT Host name
 		props.put("mail.smtp.socketFactory.port", strPortNumber);								// SMPT Port number
 		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", strPortNumber);											    // SMPT Port number
		Session session = Session.getDefaultInstance(props,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 
					return new PasswordAuthentication(strMailUserName, strMailPassword);   //-- User name and password for mail server
 
					}
 
				});
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(strFromEmailId));   					//--------------------------------From email id
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(strToEmailId));   //-----------------To Email id
			message.setSubject(strSubjectLine);											//--------------------------------Subject line
 			BodyPart messageBodyPart1 = new MimeBodyPart();
 			messageBodyPart1.setText(strEmailBody);								//--------------------------------Email body Text
 			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 			String filename = strReportPathName;    							//----Report HTML path and name 
 			DataSource source = new FileDataSource(filename);
 			messageBodyPart2.setDataHandler(new DataHandler(source));
 			messageBodyPart2.setFileName(filename);
 			Multipart multipart = new MimeMultipart();
 			multipart.addBodyPart(messageBodyPart2);
 			multipart.addBodyPart(messageBodyPart1);
 			message.setContent(multipart);
 			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
 
	}
	
	public static void sendTestResultOnSlack() throws Exception
	{
		String urlSlackWebHook = System.getenv("SlackWebHook");
		String channelName = System.getenv("SlackChannelName");
		String userOAuthToken= System.getenv("SlackOAuthToken");
	
		String titleMessage = "<-----------------------Test Execution Summary--------------------";
		
		
		try {
			StringBuilder messageBuider = new StringBuilder();
		
			messageBuider.append("-----------------------Test Execution Summary--------------------" + System.lineSeparator());
			messageBuider.append("Enviornment : " + Helper.GetConfigValue("Environment") + "  Build : " + Helper.GetConfigValue("Build").trim() +  System.lineSeparator());
			messageBuider.append("Platform : " + Helper.GetConfigValue("Platform").toUpperCase().trim() + "  Browser : " + Helper.GetConfigValue("Browser").toUpperCase().trim() +  System.lineSeparator());
			messageBuider.append("No of Test Executed : " + RunnerClass.noOfTotalTest + "  No of Test Passed : " + RunnerClass.noOfPassedTest + "  No of Test Failed : " + RunnerClass.noOfFailedTest);
			
			Payload payload = Payload.builder().channel(channelName).text(messageBuider.toString()).build();
			WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	 

	 public static ArrayList<String> getUserNameAndPassword(String userType) throws Exception
	 {	
		 ArrayList<String> userNamePassword = new ArrayList<String>();
		 
		 if(userType.toUpperCase().trim().equals(UserType.STANDARD.toString()) && GetConfigValue("Environment").toUpperCase().trim().equals("UAT"))
		 {
			 userNamePassword.add(System.getenv("UATStandardUserName").trim());
			 userNamePassword.add(System.getenv("UATStandardPassword").trim());
		 }
		 else if(userType.toUpperCase().trim().equals(UserType.ADMIN.toString()) && GetConfigValue("Environment").toUpperCase().trim().equals("UAT"))
		 {
			 
		 }
		 
	
		 return userNamePassword ;
	 }

	 public static void wait(int waitTime)
	 {
		 driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	 }
	 
	 public boolean waitForElementToDisplayed(By element)
	 {
		 boolean flag = true;
		 int icounter = 1;
			while(flag) {
				
				if(icounter == 2) {   // Wait for maximum of 2 mins
					break;
				}
				
				System.out.println("Waiting for Element to loaded in the page ---------------");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				 try {
					if(driver.findElement(element).isDisplayed())
					{
						System.out.println("Element loaded in the page ---------------");
						flag = false;
						return true;
					}
				 }catch (Exception ex) {
					    System.out.println("Element is not loaded in the page ---------------");
					    
				  }
				 icounter =  icounter + 1;
			}
		 return false;
	 }
	 

	public boolean isDisplayed(By element) {
		 boolean flag = false;
		 try {
				if(driver.findElement(element).isDisplayed())
				{
					flag = true;
					return true;
				}
			 }catch (Exception ex) {
				    System.out.println("Element is not loaded in the page ---------------*********");
				    return false;
				    
			  }
		 return flag;
	}
	

	public void sendKeys(By element, String inputText) 
	{
		driver.findElement(element).sendKeys(inputText.trim());
				
	}
	public void clear(By element)
	{
		driver.findElement(element).clear();
				
	}
	
	public void click(By element) 
	{
		driver.findElement(element).click();
				
	}	
	
	public void scrollDown(String position) 
	{

		jse.executeScript("window.scrollBy(0,"+ position + ")");
				
	}
	
	public void scrollDownTillElementIsVisible(WebElement elementToFind) 
	{

		jse.executeScript("arguments[0].scrollIntoView();", elementToFind);
				
	}
	
	
	
	public void waitAndClick(By element) 
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elementToClick = wait.until(ExpectedConditions.elementToBeClickable(element)); 
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", elementToClick);
				
				
	}
	
	
	public String getText(By element) 
	{
		return driver.findElement(element).getText();
				
	}
	
	
	
	
	
	
}

	


