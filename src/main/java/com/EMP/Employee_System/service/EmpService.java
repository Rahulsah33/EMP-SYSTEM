package com.EMP.Employee_System.service;

import com.EMP.Employee_System.entity.Employee; // Ensure you import the Employee class
import com.EMP.Employee_System.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import the Transactional annotation

import java.util.List; // Import the List class
import java.util.Optional;

@Service // Add this annotation to indicate that this class is a service
public class EmpService {

    @Autowired
    private EmpRepo repo;

    public void addEmp(Employee e) {
        repo.save(e); // Save the employee object to the repository
    }

    public List<Employee> getAllEmp() {
        return repo.findAll(); // Return all employees from the repository
    }

    public Employee getEmpById(int id) {
        Optional<Employee> e = repo.findById(id);
        if (e.isPresent()) {
            return e.get();
        }
        throw new RuntimeException("Employee not found with id: " + id); // Better error handling
    }

    @Transactional // Ensure this method is transactional
    public void updateEmp(Employee employee) {
        if (repo.existsById(employee.getId())) { // Check if the employee exists
            repo.save(employee); // Save the updated employee object
        } else {
            throw new RuntimeException("Employee not found with id: " + employee.getId());
        }
    }

    public void deleteEmp(int id){
        repo.deleteById(id);
    }

}
