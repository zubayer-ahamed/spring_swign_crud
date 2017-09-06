
package com.coderslab.dao;

import com.coderslab.model.Employee;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
    
    public boolean saveEmployee(Employee employee){
        String sql = "insert into employee (name, salary) values (?, ?)";
        int value = jdbcTemplate.update(sql, new Object[]{employee.getName(), employee.getSalary()});
        if(value > 0){
            return true;
        }
        return false;
    }
    
    
    
}
