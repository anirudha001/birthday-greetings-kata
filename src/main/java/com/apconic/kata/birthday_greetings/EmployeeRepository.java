package com.apconic.kata.birthday_greetings;

import java.util.List;

public interface EmployeeRepository {
	
	List<Employee> findEmployeesBornOn(XDate xDate);
}
