package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class MedicalStaff extends BaseEntity {
    @NotBlank private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public MedicalStaff() {}
    public MedicalStaff(String name, Department department) { this.name = name; this.department = department; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
