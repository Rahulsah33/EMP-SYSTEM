package com.EMP.Employee_System.repository;

import com.EMP.Employee_System.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository; // Ensure you have this import
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {
    // You can define custom query methods here if needed
}
