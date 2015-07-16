package com.apconic.kata.birthday_greetings;


public class Main {

	public static void main(String[] args) throws Exception {
		MessageSenderService messageSenderService= ServiceFactory.getMailMessageSenderService("localhost", 25);
		EmployeeRepository employeeRepository = RepositoryFactory.getFileEmployeeRepository("employee_data.txt");
		
		BirthdayService service = new BirthdayService(messageSenderService, employeeRepository);
		
		service.sendGreetings(new XDate());
	}

}
