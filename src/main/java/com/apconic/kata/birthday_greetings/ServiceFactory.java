package com.apconic.kata.birthday_greetings;

import com.apconic.kata.services.MailMessageSenderService;

public class ServiceFactory {
	
	public static MessageSenderService getMailMessageSenderService(String smtpHost, int smtpPort) {
		return new MailMessageSenderService(smtpHost, smtpPort);
	}
}
