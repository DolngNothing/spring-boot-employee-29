package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee update(Integer employeeID, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeID).orElse(null);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        employee.setSalary(updatedEmployee.getSalary());
        this.employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    public Page<Employee> findEmployeesByPageAndPageSize(int page, int pageSize) {
        return this.employeeRepository.findAll(PageRequest.of(page,pageSize));
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return this.employeeRepository.findAllByGender(gender);
    }

    public Employee findEmployeeByID(Integer employeeID) {
        return this.employeeRepository.findById(employeeID).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public void deleteEmployeeByID(Integer employeeID) {
        this.employeeRepository.deleteById(employeeID);
    }
}
