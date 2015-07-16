package com.apconic.kata.birthday_greetings;

import com.apconic.kata.services.FileEmployeeRepository;

public class RepositoryFactory {

	public static EmployeeRepository getFileEmployeeRepository(String fileName) {
		return new FileEmployeeRepository(fileName);
	}

}
