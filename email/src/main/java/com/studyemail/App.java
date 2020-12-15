package com.studyemail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) {

		System.out.println("Preparing to send message......");

		String message = "hello dear this is message for security check.";
		String subject = "CodersArea : Confirmation";
		String to = "gouravmahor60@gmail.com";
		String from = "gauravmahor49@gmail.com";

		// sendEmail(message, subject, to, from);

		// For Attachment
		sendAttach(message, subject, to, from);

	}

	// this is responsible to send email...
	private static void sendEmail(String message, String subject, String to, String from) {

		// variable for gmail host
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		System.out.println("Smtp is now starting...");

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			String username = "gourav.srishti@gmail.com";
			String password = "rock@on1";

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);
			}

		});

		session.setDebug(true);

		// step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {
			// form
			m.setFrom(from);

			// adding recipient to message (to)
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// send

			// step 3 : send message using transport class
			Transport.send(m);

			System.out.println("Sent successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// this is responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) {

		// variable for gmail host
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		System.out.println("Smtp is now starting...");

		// setting important information to properties object

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			String username = "gourav.srishti@gmail.com";
			String password = "rock@on1";

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);
			}

		});

		session.setDebug(true);

		// step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);

		try {
			// form
			m.setFrom(from);

			// adding recipient to message (to)
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// adding text to message
			m.setText(message);

			// attchment.....

			// file path

			String path = "C:\\Users\\Gourav\\Desktop\\img";

			MimeMultipart mimeMultipart = new MimeMultipart();
			// MimeMultipart have two thinks
			// text
			// file

			MimeBodyPart textMime = new MimeBodyPart();
			MimeBodyPart fileMime = new MimeBodyPart();

			try {

				textMime.setText(message);

				File file = new File(path);
				fileMime.attachFile(file);

				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);

			} catch (Exception e) {
				e.printStackTrace();
			}

			m.setContent(mimeMultipart);

			// send

			// step 3 : send message using transport class
			Transport.send(m);

			System.out.println("Sent successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
