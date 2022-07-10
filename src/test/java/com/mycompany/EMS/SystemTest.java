package com.mycompany.EMS;


import com.mycompany.EMS.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class SystemTest {


    @Test public void testCreateReadDelete() {
        RestTemplate restTemplate = new
            RestTemplate();

        String url = "http://localhost:8080/addemployee";
        String employeelisturl = "http://localhost:8080/listemployee";
        String deleteemployeeurl = "http://localhost:8080/deleteemployee";
        Employee employee = new Employee("Abhi","1234","HR","abc@gmail.com","Delhi","Employee");
        ResponseEntity<Employee> entity
                = restTemplate.postForEntity(url, employee, Employee.class);

        Employee[] employeelist = restTemplate.getForObject(employeelisturl, Employee[].class);
        Assertions.assertThat(employeelist).extracting(Employee::getEmpName).containsOnly(
                "Abhi");

        restTemplate.delete(deleteemployeeurl + "/" + entity.getBody().getEmpID());
        Assertions.assertThat(restTemplate.getForObject(employeelisturl,
                Employee[].class)).isEmpty();
    }

    @Test
    public void testErrorHandlingReturnsBadRequest() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/wrong";

        try {
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }

}