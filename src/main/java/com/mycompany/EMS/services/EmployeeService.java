package com.mycompany.EMS.services;

import com.mycompany.EMS.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeService extends CrudRepository<Employee, Integer> {

}
