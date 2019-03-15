package com.capgemini.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capgemini.bean.Customer;
import com.capgemini.exception.DuplicateMobileNumberException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InvalidInputException;

public interface WalletService {
	
	public Customer createAccount(String cName ,String mobileNumber, BigDecimal balance) throws DuplicateMobileNumberException;
	public Customer showBalance (String mobileNumber) throws InvalidInputException;
	public Customer fundTransfer (String sourceMobileNumber,String targetMobileNumber, BigDecimal balance) throws InvalidInputException;
	public Customer deposit (String mobileNumber,BigDecimal balance ) throws InvalidInputException;
	public Customer withdraw(String mobileNumber, BigDecimal balance) throws InsufficientBalanceException, InvalidInputException;


	public boolean ValidateName(String cName) throws InvalidInputException;
	public boolean ValidateMobNo(String mobileNumber) throws InvalidInputException;
	public boolean ValidateAmount(BigDecimal balance) throws InvalidInputException;

}
