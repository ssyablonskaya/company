package com.solvd.company.service.impl;

import com.solvd.company.domain.Employee;
import com.solvd.company.domain.Position;
import com.solvd.company.persistence.EmployeeRepository;
import com.solvd.company.persistence.PayrollAccountRepository;
import com.solvd.company.persistence.jdbcImpl.EmployeeRepositoryImpl;
import com.solvd.company.persistence.jdbcImpl.PayrollAccountRepositoryImpl;
import com.solvd.company.persistence.mybatisImpl.EmployeeMyBatisRepository;
import com.solvd.company.persistence.mybatisImpl.PayrollAccountMyBatisRepository;
import com.solvd.company.service.EmployeeService;
import com.solvd.company.service.PositionService;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PayrollAccountRepository payrollAccountRepository;
    private final PositionService positionService;

    public EmployeeServiceImpl() {
        //this.employeeRepository = new EmployeeRepositoryImpl();
        this.employeeRepository = new EmployeeMyBatisRepository();
        //this.payrollAccountRepository = new PayrollAccountRepositoryImpl();
        this.payrollAccountRepository = new PayrollAccountMyBatisRepository();
        this.positionService = new PositionServiceImpl();
    }

    @Override
    public Employee create(Long departmentId, Employee employee) {
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


        return employee;
    }
}
