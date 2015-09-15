package com.manish.javadev.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.manish.javadev.dao.CustomerDAO;
import com.manish.javadev.helper.CustomerRowMapper;
import com.manish.javadev.to.CustomerTO;

public class CustomerDaoImplUsingSimpleJdbcTemplate implements CustomerDAO {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	public void addCustomer(CustomerTO cust) {
		String sql = "insert into Customer values(?,?,?,?,?)";
		Object args[] = {cust.getCustId(), cust.getFirstName(), cust.getLastName(),
				cust.getAge(),cust.getCity() };
		jdbcTemplate.update(sql, args);
		
	}

	public void updateCustomer(CustomerTO cust) {
		String sql = "Update Customer set firstName=?,lastName=? where custId=?";
		Object args[] = { cust.getFirstName(), cust.getLastName(),
				cust.getCustId() };
		jdbcTemplate.update(sql, args);
		System.out.println("Update Operation Done");

	}

	public void deleteCustomer(int custId) {
		String sql = "delete from customer where custid=?";
		Object args[] = { custId };
		jdbcTemplate.update(sql, args);
		System.out.println("Delete Operation Done");
	}

	public List<CustomerTO> getAllCustomer() {
		String quiry = "Select * from Customer";
		return jdbcTemplate.query(quiry, new CustomerRowMapper());
	}

	public CustomerTO getCustomerById(int custId) {
		String sql = "Select * from Customer where custId=?";
		Object args[] = { custId };
		return (CustomerTO) jdbcTemplate.queryForObject(sql, args,
				new CustomerRowMapper());

	}

}
