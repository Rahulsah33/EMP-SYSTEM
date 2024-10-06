package com.EMP.Employee_System.controller;

import com.EMP.Employee_System.entity.Employee; // Ensure you import your Employee entity
import com.EMP.Employee_System.service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping("/")
    public String index(Model m, HttpSession session) {
        // Get the list of employees
        List<Employee> emp = service.getAllEmp();
        m.addAttribute("emp", emp); // Correctly add the employee list to the model

        // Check for message in session and add it to the model
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
            m.addAttribute("msg", msg);
            session.removeAttribute("msg"); // Remove the message after displaying
        }

        return "index"; // Return the index view
    }

    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp"; // This should return your add_emp.html
    }

    @PostMapping("/register") // Add the mapping for registration
    public String register(@ModelAttribute Employee employee, HttpSession session) {
        service.addEmp(employee);
        session.setAttribute("msg", "Employee Added Successfully");

        return "redirect:/"; // Redirects to the index page after submission
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Employee e = service.getEmpById(id);
        m.addAttribute("emp", e); // Correctly add the Employee object to the model
        return "edit"; // Return the edit view
    }

    @PostMapping("/update") // Method to handle the update of employee
    public String update(@ModelAttribute Employee employee, HttpSession session) {
        service.updateEmp(employee); // Ensure you have a method to update the employee
        session.setAttribute("msg", "Employee Updated Successfully");
        return "redirect:/"; // Redirects to the index page after submission
    }

    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id , HttpSession session){

        service.deleteEmp(id);
        session.setAttribute("msg","Employee Deleted Successfully");
        return "redirect:/";

    }

}
