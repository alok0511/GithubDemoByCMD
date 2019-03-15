package com.capgemini.view;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.exception.DuplicateMobileNumberException;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InvalidInputException;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImplementation;

public class ClientSide {
	static Scanner sc = new Scanner(System.in);;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		WalletService wService = new WalletServiceImplementation();

		int choice = 0;

		while (true) {
			System.out.println("	Mobile Payment Wallet		");
			System.out.println("____________________________________");
			System.out.println(" Press 1 to Create Account");
			System.out.println(" Press 2 to Show Balance");
			System.out.println(" Press 3 to Deposit");
			System.out.println(" Press 4 to Withdraw");
			System.out.println(" Press 5 to Fund Transfer");
			System.out.println(" Press 6 to Exit");
			System.out.println("\nEnter Your Choice:");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Please Enter Your Mobile Number");
				String customerMobileNUmber = sc.next();
				System.out.println("Please Enter Your Name");
				String customerName = sc.next();
				System.out.println("Please Enter Money you wanted to add");
				BigDecimal balance = sc.nextBigDecimal();

				try {
					if (wService.ValidateMobNo(customerMobileNUmber) && wService.ValidateName(customerName)
							&& wService.ValidateAmount(balance)) {
						Customer customer = null;
						try {
							customer = wService.createAccount(customerName, customerMobileNUmber, balance);
						} catch (DuplicateMobileNumberException e) {
							System.out.println(e.getMessage());
						}
						if (customer != null)
							System.out.println(customer);
						else
							System.out.println("Account is not added in database");

					} else
						try {
							throw new InvalidInputException("Enter Details of Required Format");
						} catch (InvalidInputException e) {
							System.out.println(e.getMessage());
						}
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:
				System.out.println("Please Enter Your Mobile Number");
				String custMobNo1 = sc.next();
				try {
					if (wService.ValidateMobNo(custMobNo1)) {
						Customer customer = wService.showBalance(custMobNo1);

						Wallet wallet = customer.getWallet();
						System.out.println(customer);
						System.out.println(customer.getcName() + ", your balance is " + wallet.getBalance());

					} else
						throw new InvalidInputException("Enter Ten Digit Number starting with 6/7/8/9  ");
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 3:
				System.out.println("Please Enter Your Mobile Number");
				custMobNo1 = sc.next();
				try {
					if (wService.ValidateMobNo(custMobNo1)) {
						Customer customer = wService.showBalance(custMobNo1);
						Wallet wallet = customer.getWallet();
						System.out.println(customer.getcName() + ", your current balance is " + wallet.getBalance());
						System.out.println("Please Enter the amount you want to deposit ");
						BigDecimal deposit = sc.nextBigDecimal();
						customer = wService.deposit(custMobNo1, deposit);
						wallet = customer.getWallet();
						System.out.println(customer.getcName() + ", your updated balance is " + wallet.getBalance());
					} else
						throw new InvalidInputException("Enter  Ten Digit Mobile Number  ");
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 4:
				System.out.println("Please Enter Your Mobile Number");
				custMobNo1 = sc.next();
				try {
					if (wService.ValidateMobNo(custMobNo1)) {
						Customer customer = wService.showBalance(custMobNo1);
						Wallet wallet = customer.getWallet();
						System.out.println(customer.getcName() + ", your current balance is " + wallet.getBalance());
						System.out.println("Please Enter the amount you want to withdraw ");
						BigDecimal withdraw = sc.nextBigDecimal();
						try {
							customer = wService.withdraw(custMobNo1, withdraw);
						} catch (InsufficientBalanceException e) {
							System.out.println(e.getMessage());
						}
						wallet = customer.getWallet();
						System.out.println(customer.getcName() + ", your updated balance is " + wallet.getBalance());
					} else
						throw new InvalidInputException("Enter Ten Digit Mobile Number  ");
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println("Please Enter Your Mobile Number");
				String custMobNoS = sc.next();
				try {
					if (wService.ValidateMobNo(custMobNoS)) {
						Customer customer = wService.showBalance(custMobNoS);
						Wallet w = customer.getWallet();
						System.out.println(customer.getcName() + ", your current balance is " + w.getBalance());
						System.out.println("Please Enter the mobile number  you want to transfer money ");
						String custMobNoT = sc.next();
						if (wService.ValidateMobNo(custMobNoT)) {
							System.out.println("Please Enter amount to be transferred ");

							BigDecimal ft = sc.nextBigDecimal();

							customer = wService.fundTransfer(custMobNoS, custMobNoT, ft);
							w = customer.getWallet();
							System.out.println(customer.getcName() + ", your updated balance is " + w.getBalance());
						}
					} else
						try {
							throw new InvalidInputException("Enter Ten Digit Mobile Number  ");
						} catch (InvalidInputException e) {
							System.out.println(e.getMessage());
						}
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 6:
				System.out.println("Successfully Teminated");
				System.exit(0);
				break;

			default:
				System.out.println("Wrong Input");

			}
		}

	}

}
