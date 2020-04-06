package com;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;



public class SendEmail{
	
	public static void main(String args[])
	
	{
		String to = "sakshamg30@gmail.com";
		String body= "hi";
		sendMail(to,body,"YOLO");
	}
	/**
	 * 
	 */
	private static String user = "sakshamtest30";
	private static String password = "Saksham1998";

	public static void sendMail(String to, String body, String subject) {    

	      String host = "smtp.gmail.com";
	      String from = user;
          String pass = password;
          //String subject = "This is a Transcript email";
	      Properties props = System.getProperties();

	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.imap.ssl.enable", "true"); // required for Gmail
	        props.put("mail.imap.auth.mechanisms", "XOAUTH2");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        
	        Session session = Session.getDefaultInstance(props);

	      try {
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject(subject);
	         message.setText(body);

	         Transport transport = session.getTransport("smtp");
	         transport.connect(host,from,pass);
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
}
