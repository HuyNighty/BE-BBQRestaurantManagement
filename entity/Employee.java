package com.huynguyen.bbqrestaurantmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.huynguyen.bbqrestaurantmanagement.enums.RoleEmployee;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "employee_name", nullable = false)
    String employeeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    RoleEmployee role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("employee-to-supplier")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<EmployeeSupplier> employeeSuppliers;
}
