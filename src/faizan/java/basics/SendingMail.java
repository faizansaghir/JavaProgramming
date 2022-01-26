package faizan.java.basics;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingMail {

	public static void sendMail(String recepient) throws MessagingException {
		System.out.println("Preparing to send Email");
		Properties prop=new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		String username="websitepragmatic@gmail.com";
		String password="PragmaticPass@1121";
		
		PasswordAuthentication passAuth=new PasswordAuthentication(username, password);
		
		Authenticator auth=new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return passAuth;
			}
		};
		
		Session session= Session.getInstance(prop,auth);
		
		Message msg=prepareMessage(session,username,recepient);
		Transport.send(msg);
		System.out.println("Email sent successfully");
	}
	
	private static Message prepareMessage(Session session,String username,String recepient) {
		Message msg=new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(username));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			msg.setSubject("First email from JAVA");
			msg.setText("Success has been achieved");
			return msg;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			sendMail("gk9157@myamu.ac.in");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
