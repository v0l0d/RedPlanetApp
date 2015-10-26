package com.redplanet.utils; 



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ctaf.support.ConfiguratorSupport;
import com.ctaf.utilities.Zip;

public class MailUtility {

	public static ConfiguratorSupport configProps = new ConfiguratorSupport(
			"config.properties");

	public static  void sendNotification() {

		// Recipient's email ID needs to be mentioned.
		String[] toMailerList = configProps.getProperty("To").split(",");
		System.out.println(configProps.getProperty("To"));
		String[] ccMailerList = configProps.getProperty("CC").split(",");
		System.out.println(configProps.getProperty("CC"));
		final String username = configProps.getProperty("UserName");
		final String password = configProps.getProperty("Password");
		String from = configProps.getProperty("From");

		Properties props = new Properties();

		props.put("mail.smtp.host", "mail.quickflix.com.au");
		props.put("mail.smtp.port", "25");
		
		Session session = Session.getInstance(props);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[toMailerList.length];

			for (int i = 0; i < toMailerList.length; i++) {
				addressTo[i] = new javax.mail.internet.InternetAddress(
						toMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO,
					addressTo);
			
			javax.mail.internet.InternetAddress[] addressCC = new javax.mail.internet.InternetAddress[ccMailerList.length];
			for (int i = 0; i < ccMailerList.length; i++) {
				addressCC[i] = new javax.mail.internet.InternetAddress(
						ccMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.CC,
					addressCC);
			// Set Subject: header field
			message.setSubject("Test Data Expiry Alert");//("Quickflix Titles are Exceeded  .... ");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart
					.setContent(
									"Hi, <br><br>"
									+configProps.getProperty("body")
									
								
									+ "<br><br>Thanks,<br>Offshore Team<br><br>",
							"text/html; charset=utf-8");
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
public static  void sendReportAsMailBody(String filePath, String subject,String AttachmentSource) throws Throwable {
		
		File fis = new File("C:\\SVN\\12 QuickflixProject\\QuickflixProject\\Results\\Chrome\\"
				+ "Chrome_2015-03-25_15_54_34_292\\SummaryResults_2015-03-25_15_54_34_292.html");
		//File fis = new File(filePath);
		FileReader fileReader = new FileReader(fis);
		//jsoup code 
		Document doc = Jsoup.parse(fis, "UTF-8", "http://example.com/");
		Element newsHeadlines = doc.getElementById("footer");
		Elements header = doc.select("head");
		String jsonStringBuffer = "<html>"+header.html()+"<body> <table id='footer'>"+newsHeadlines.html()+"</table></body></html>";
        Document newFooter = Jsoup.parse(jsonStringBuffer);
        Element remLogos = doc.getElementById("Logos");
		remLogos.remove();
        Element remFooter = doc.getElementById("footer");
		remFooter.remove();
		//**********
        BufferedReader reader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = reader.readLine()) != null) {
				//String newLine[] = line.split("(?<=footer).*?(?=</tfoot>)");
				String newLine[] = line.split("(?i)(<table id='footer'.*?>)(.+?)(</tfoot>)");
				//System.out.println(newLine[0]);
				/*String logoLess[]=newLine[0].split("(?i)(<table id='Logos'.*?>)(.+?)(main)");
				System.out.println(logoLess[0]);*/
				stringBuffer.append(newLine[0]);
		}
		//stringBuffer.append(stbfooter.toString());
		/*System.out.println("Contents of file:");
		System.out.println(stringBuffer.toString());*/
		fileReader.close();
	
		// Recipient's email ID needs to be mentioned.
		String[] toMailerList = configProps.getProperty("To").split(",");
		System.out.println(configProps.getProperty("To"));
		String[] ccMailerList = configProps.getProperty("CC").split(",");
		System.out.println(configProps.getProperty("CC"));
		final String username = configProps.getProperty("UserName");
		final String password = configProps.getProperty("Password");
		String from = configProps.getProperty("From");

		/*Properties props = new Properties();
		props.put("mail.smtp.host", "mail.quickflix.com.au");
		props.put("mail.smtp.port", "25");*/
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");
		/*props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");*/
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "995");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		//Session session = Session.getInstance(props);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			
			javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[toMailerList.length];

			for (int i = 0; i < toMailerList.length; i++) {
				addressTo[i] = new javax.mail.internet.InternetAddress(
						toMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.TO,
					addressTo);
			
			javax.mail.internet.InternetAddress[] addressCC = new javax.mail.internet.InternetAddress[ccMailerList.length];
			for (int i = 0; i < ccMailerList.length; i++) {
				addressCC[i] = new javax.mail.internet.InternetAddress(
						ccMailerList[i]);
			}
			message.setRecipients(javax.mail.Message.RecipientType.CC,
					addressCC);
			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			//Create the attachement part
			BodyPart AttachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(AttachmentSource);
			AttachmentBodyPart.setDataHandler(new DataHandler(source));
			AttachmentBodyPart.setFileName("QuickflixReports.zip");
			// Fill the message
			messageBodyPart
					.setContent(
							newFooter.html()+stringBuffer.toString(),
							"text/html; charset=utf-8");
			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			//Set Attachment part
			multipart.addBodyPart(AttachmentBodyPart);
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	public static void main(String a[]) throws Throwable{
		
		//sendNotification();
		String att = "C:\\SVN\\12 QuickflixProject\\QuickflixProject\\Results\\Chrome\\"
						+ "Chrome_2015-03-25_15_54_34_292.zip";
		Zip.zipFolder("C:\\SVN\\12 QuickflixProject\\QuickflixProject\\Results\\Chrome\\"
				+ "Chrome_2015-03-25_15_54_34_292", "C:\\SVN\\12 QuickflixProject\\QuickflixProject\\Results\\Chrome\\"
						+ "Chrome_2015-03-25_15_54_34_292.zip");
		sendReportAsMailBody("filePath", "testReports", att);
	}

}