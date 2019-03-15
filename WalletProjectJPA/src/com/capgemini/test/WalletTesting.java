package com.capgemini.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.exception.DuplicateMobileNumberException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InvalidInputException;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImplementation;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImplementation;

import org.junit.Before;
import org.junit.Test;

public class WalletTesting {
	WalletRepo wrepo;
	WalletService wService;

	@Before
	public void setUp() throws Exception {
	wrepo = new WalletRepoImplementation();
	wService = new WalletServiceImplementation(wrepo);
		
	}
	
	
	@Test(expected = com.capgemini.exception.DuplicateMobileNumberException.class)
	public void whenMobileNumberIsAlreadyExistForAnotherCustomerThenSystemShouldThrowAnException()
			throws DuplicateMobileNumberException {
		wService.createAccount("Deepak", "8808965596", new BigDecimal("2000"));
		wService.createAccount("Deepak", "8808965596", new BigDecimal("2000"));
	}
	
	@Test(expected = com.capgemini.exception.InsufficientBalanceException.class)
	public void whenThereIsLessAmountInTheAccountForWithdrawThenSystemShouldThrowAnException()
			throws InvalidInputException, InsufficientBalanceException, DuplicateMobileNumberException {
		wService.createAccount("Deepak", "8808959892", new BigDecimal("1000"));
		wService.withdraw("8808959892", new BigDecimal("2000"));
	}
	
	
	
	@Test(expected = com.capgemini.exception.InvalidInputException.class)
	public void whenMobileNumberIsUsedForWithdrawIsNotInDatabaseThenSystemShouldThrowAnException()
			throws InvalidInputException, InsufficientBalanceException, DuplicateMobileNumberException {
		wService.createAccount("Kapil", "9876541223", new BigDecimal("1000"));
		wService.withdraw("9450766173", new BigDecimal("100"));
	}
	
	@Test
	public void whenAllTheDetailsAreValid() throws DuplicateMobileNumberException {
		Wallet wallet = new Wallet(BigDecimal.valueOf(1100));
		BigDecimal balance = wallet.getBalance();
		WalletService wService = new WalletServiceImplementation();
		Customer customer = wService.createAccount("Rashi", "9889469526", balance);
		assertEquals(customer.getMobileNumber(),"9889469526" );
		}
	
	
	

	
}
	

