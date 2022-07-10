package com.mycompany.EMS.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;
    @NotBlank
    @NonNull
    private String empName;
    @NotBlank
    @NonNull
    private String empContact;
    @NotBlank
    @NonNull
    private String empDepartment;
    @NotBlank
    @NonNull
    private String empEmail;
    @NotBlank
    @NonNull
    private String empAddress;
    @NotBlank
    @NonNull
    private String empRole;

    public Employee() {
    }

    public Employee(String empName,String empContact,String empDepartment,String empEmail,String empAddress,String empRole)
    {
        this.empName=empName;
        this.empContact=empContact;
        this.empDepartment=empDepartment;
        this.empEmail=empEmail;
        this.empAddress=empAddress;
        this.empRole=empRole;
    }
}
