package com.solvd.company.service.impl;

import com.solvd.company.domain.Employee;
import com.solvd.company.domain.Position;
import com.solvd.company.persistence.EmployeeRepository;
import com.solvd.company.persistence.PayrollAccountRepository;
import com.solvd.company.persistence.impl.EmployeeRepositoryImpl;
import com.solvd.company.persistence.impl.PayrollAccountRepositoryImpl;
import com.solvd.company.service.EmployeeService;
import com.solvd.company.service.PositionService;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final PayrollAccountRepository payrollAccountRepository = new PayrollAccountRepositoryImpl();
    private final PositionService positionService = new PositionServiceImpl();

    @Override
    public void create(Long departmentId, Employee employee) {
        if (employee.getPayrollAccount() != null) {
            payrollAccountRepository.create(employee.getPayrollAccount());
        }
        employee.setId(null);
        Position employeePosition = employee.getPosition();
        if (employeePosition != null) {
            Position position = positionService.findOrCreate(employeePosition);
            if (position != null) {
                employeePosition.setId(position.getId());
            }
            employeeRepository.create(departmentId, employeePosition.getId(), employee.getPayrollAccount().getId(), employee);
        }


    }
}