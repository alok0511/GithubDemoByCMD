package com.capgemini.repo;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capgemini.bean.Customer;

public interface WalletRepo {
	public boolean save(Customer customer) ;
	public Customer findOne(String mobileNumber) ;
	void update(String mobileNumber, BigDecimal amount);
	
}
