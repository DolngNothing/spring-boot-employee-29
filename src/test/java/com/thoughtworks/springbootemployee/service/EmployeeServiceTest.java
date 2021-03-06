package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

    @Test
    void should_get_all_employees_when_get_given() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employees = Arrays.asList(new Employee(1, 18, "female", "eva", 10000),
                new Employee(3, 24, "male", "gradle", 12000));
        given(employeeRepository.findAll()).willReturn(employees);
        //when
        List<Employee> foundEmployees = employeeService.findAll();

        //then
        assertEquals(employees, foundEmployees);
    }

    @Test
    void should_get_page_employees_when_get_by_page_given_page_pageSize() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        //todo
//        List<Employee> employees = Arrays.asList(new Employee(1, 18, "female", "eva", 1000),
//                new Employee(1, 19, "male", "eva", 1000));
//        given(employeeRepository.findAll(PageRequest.of(1,2))).willReturn((Page<Employee>) employees);

        //when
        employeeService.findEmployeesByPageAndPageSize(1, 2);

        //then
        //assertEquals(employees, employeesByPageAndPageSize);
        verify(employeeRepository).findAll(PageRequest.of(1,2));
    }

    @Test
    void should_return_gender_employees_when_find_by_gender_given_gender() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employees = Arrays.asList(new Employee(1, 18, "female", "eva", 1000),
                new Employee(2, 19, "female", "eva", 1000));
        given(employeeRepository.findAllByGender("female")).willReturn(employees);
        //when
        List<Employee> employeesByGender = employeeService.findEmployeesByGender("female");
        //then
        assertEquals(employees, employeesByGender);
    }

    @Test
    void should_return_specific_employee_when_findById_given_id() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee(2, 19, "female", "eva", 1000);
        given(employeeRepository.findById(2)).willReturn(Optional.of(employee));
        //when
        Employee employeeByID = employeeService.findEmployeeByID(2);
        //then
        assertEquals(employee, employeeByID);
    }

    @Test
    void should_return_created_employee_when_add_given_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee(2, 19, "female", "eva", 1000);
        given(employeeRepository.save(employee)).willReturn(employee);

        //when
        Employee createdEmployee = employeeService.addEmployee(employee);

        //then
        assertEquals(employee, createdEmployee);
    }

    @Test
    void should_update_employee_when_update_given_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        given(employeeRepository.findById(1)).willReturn(Optional.of(new Employee(2, 18, "female", "chris", 9999)));

        //when
        Employee updatedEmployee = new Employee(2, 18, "female", "eva", 1000);
        Employee employee = employeeService.update(1, updatedEmployee);

        //then
        assertEquals(2, employee.getId());
        assertEquals("eva", employee.getName());
        assertEquals("female", employee.getGender());
        assertEquals(1000, employee.getSalary());
    }

    @Test
    void should_return_void_when_delete_given_employee_id() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        //when
        employeeService.deleteEmployeeByID(1);

        //then
        Mockito.verify(employeeRepository).deleteById(1);
    }
}
