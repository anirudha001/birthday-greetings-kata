package com.apconic.kata.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.apconic.kata.birthday_greetings.Employee;
import com.apconic.kata.birthday_greetings.EmployeeRepository;
import com.apconic.kata.birthday_greetings.XDate;

public class FileEmployeeRepository implements EmployeeRepository {

	private String fileName;

	public FileEmployeeRepository(String fileName) {
		this.fileName = fileName;
	}

	public List<Employee> findEmployeesBornOn(XDate xDate) {
		try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
			List<Employee> employeeList = new ArrayList<Employee>();
			String str = "";
			str = in.readLine(); 
			while ((str = in.readLine()) != null) {
				String[] employeeData = str.split(", ");
				Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
				if (employee.isBirthday(xDate)) {
					employeeList.add(employee);
				}
			}
			return employeeList;

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
