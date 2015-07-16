package com.apconic.kata.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.apconic.kata.birthday_greetings.MessageSenderService;

public class MailMessageSenderService implements MessageSenderService {
	private String smtpHost;
	private int smtpPort;
	
	public MailMessageSenderService(String smtpHost, int smtpPort) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}
	
	public void sendMessage(String sender, String subject, String body, String recipient) throws AddressException, MessagingException {
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		Session session = Session.getInstance(props, null);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(sender));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		msg.setSubject(subject);
		msg.setText(body);

		Transport.send(msg);
	}

}
