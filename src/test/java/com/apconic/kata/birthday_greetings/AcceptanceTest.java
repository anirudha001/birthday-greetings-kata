package com.apconic.kata.birthday_greetings;

import static org.junit.Assert.*;

import org.junit.*;

import com.apconic.kata.birthday_greetings.BirthdayService;
import com.apconic.kata.birthday_greetings.XDate;
import com.apconic.kata.services.FileEmployeeRepository;
import com.apconic.kata.services.MailMessageSenderService;
import com.dumbster.smtp.*;


public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private BirthdayService birthdayService;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		birthdayService = new BirthdayService(new MailMessageSenderService("localhost", NONSTANDARD_PORT), new FileEmployeeRepository("employee_data.txt"));
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {
		birthdayService.sendGreetings(new XDate("1992/03/09"));

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear Anirudha", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("anirudha001@gmail.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/01/01"));

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}
