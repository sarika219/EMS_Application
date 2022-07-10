package com.mycompany.EMS;

import com.mycompany.EMS.controllers.EmployeeController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class EmsApplicationTests {

	@Autowired
	EmployeeController employeeController;

	@Test
	public void contextLoads() {

		Assert.assertNotNull(employeeController);
	}
}
