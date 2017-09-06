package com.coderslab.dao;

import com.coderslab.model.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> getAllEmployee() {
        String sql = "select * from employee";
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    private static class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getDouble("salary"));
            return employee;
        }
    }

    public boolean saveEmployee(Employee employee) {
        String sql = "insert into employee (name, salary) values (?, ?)";
        int value = jdbcTemplate.update(sql, new Object[]{employee.getName(), employee.getSalary()});
        if (value > 0) {
            return true;
        }
        return false;
    }

    public boolean updateEmployee(Employee employee) {
        String sql = "update employee set name=?, salary=? where id=?";
        int value = jdbcTemplate.update(sql, new Object[]{employee.getName(), employee.getSalary(), employee.getId()});
        if (value > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(Employee employee) {
        String sql = "delete from employee where id=" + employee.getId();
        int value = jdbcTemplate.update(sql);
        if (value > 0) {
            return true;
        }
        return false;
    }

}
