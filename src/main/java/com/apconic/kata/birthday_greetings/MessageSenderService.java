package com.apconic.kata.birthday_greetings;


public interface MessageSenderService {

	public void sendMessage(String sender, String subject, String body, String recipient) throws Exception;
}
