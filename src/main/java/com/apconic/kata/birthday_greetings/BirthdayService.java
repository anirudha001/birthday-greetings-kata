package com.apconic.kata.birthday_greetings;

import java.util.List;

public class BirthdayService {
	private MessageSenderService messageSenderService;
	private EmployeeRepository employeeRepository;

	public BirthdayService(MessageSenderService messageSenderService, EmployeeRepository employeeRepository) {
		this.messageSenderService = messageSenderService;
		this.employeeRepository = employeeRepository;
	}

	public void sendGreetings(XDate xDate) throws Exception {
		List<Employee> employeeList = employeeRepository.findEmployeesBornOn(xDate);
		for (Employee employee : employeeList) {
			String recipient = employee.getEmail();
			String body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.getFirstName());
			String subject = "Happy Birthday!";
			messageSenderService.sendMessage("anirudhaa@apconic.com", subject, body, recipient);
		}
	}
}
