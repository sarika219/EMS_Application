package com.mycompany.EMS.controllers;

import com.mycompany.EMS.model.Employee;
import com.mycompany.EMS.services.EmployeeService;
import com.mycompany.EMS.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/addemployee")
    public Employee addEmployee( @Valid @RequestBody Employee employee) throws ValidationException{
        if (employee.getEmpID() == 0 )
			return employeeService.save(employee);
		else
			throw new ValidationException("ID will generated by the system only!!");

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class )
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e ){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages =fieldErrors.stream().map(fieldError ->
                        new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return fieldErrorMessages;

    }
    @GetMapping("/listemployee")
    public Iterable<Employee> listEmployee() {
        return employeeService.findAll();
    }
    @PutMapping("/updateemployee")
    public ResponseEntity<Employee> update(@RequestBody Employee employee)
    {
        if(employeeService.findById(employee.getEmpID()).isPresent())
            return new ResponseEntity(employeeService.save(employee), HttpStatus.OK);
        else
            return new ResponseEntity(employee,HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteemployee/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
    }

    public @GetMapping("/getemployeebyid/{id}")
    Optional<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }
    public @GetMapping("/wrong")
    Employee enteredWrongUrl() {
        throw new javax.validation.ValidationException("Something went wrong----Incorrect URL");
    }

}
