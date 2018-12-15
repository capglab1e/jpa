package com.cg.pwa.ui;


import java.util.List;
import java.util.Scanner;

import com.cg.pwa.beans.Account;
import com.cg.pwa.beans.PrintTransactions;
import com.cg.pwa.exception.MyException;
import com.cg.pwa.exception.InvalidAmount;
import com.cg.pwa.exception.InvalidPhoneNumber;
import com.cg.pwa.exception.NameException;
import com.cg.pwa.service.AccountService;
import com.cg.pwa.service.AccountServiceImpl;

public class MainUI {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		AccountService service = new AccountServiceImpl();

		int option;
		do {

			System.out.println("1. Create new customer account");
			System.out.println("2. Show user's balance");
			System.out.println("3. Fund Transfer");
			System.out.println("4. Deposit amount into your account");
			System.out.println("5. Withdraw amount from your account");
			System.out.println("6. Print transcations");
			System.out.println("7. Exit");
			option = sc.nextInt();

			switch (option) {
			case 1:

				String name;
				String mobNo;
				double amt;

				do {
					System.out.println("Enter Customer Name: ");
					name = sc.next();
					try {
						if (service.validateUserName(name))
							break;
					} catch (NameException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter Mobile Number: ");
					mobNo = sc.next();
					try {
						if (service.validatePhoneNumber(mobNo))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter Initial Amount: ");
					amt = sc.nextDouble();
					try {
						if (service.validateAmount(amt))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);

				Account c = new Account(name, mobNo, amt);
				Account c1 = null;
				try {
					if (service.validateAll(c))
						c1 = service.createAccount(c);
					System.out.println("Successfully created new account for "
							+ c1.getCustomerName() + " with "
							+ "Mobile Number " + c1.getMobileNumber());
				} catch (MyException | NameException | InvalidAmount
						| InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;

			case 2:

				System.out.println("Enter an existing mobile number: ");
				String mobNoShow = sc.next();

				double bal = 0;
				try {
					if (service.validatePhoneNumber(mobNoShow))
						bal = service.showBalance(mobNoShow);
					System.out
							.println("Available balance for the mobile number "
									+ mobNoShow + " is " + bal);
				} catch (InvalidPhoneNumber | MyException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}

				break;

			case 3:

				String sourceMobileNo;
				String targetMobileNo;
				double amount;
				Account funds = null;

				do {
					System.out.println("Enter your mobile number: ");
					sourceMobileNo = sc.next();
					try {
						if (service.validatePhoneNumber(sourceMobileNo))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter recipient's mobile number: ");
					targetMobileNo = sc.next();
					try {
						if (service.validatePhoneNumber(targetMobileNo))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out
							.println("Enter the amount that to be transfered: ");
					amount = sc.nextDouble();
					try {
						if (service.validateAmount(amount))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);

				try {
					if (service.validatePhoneNumber(sourceMobileNo)
							&& service.validateTargetMobNo(targetMobileNo)
							&& service.validateAmount(amount))
						funds = service.fundTransfer(sourceMobileNo,
								targetMobileNo, amount);

					System.out.println("Successfully transfered Rs." + amount
							+ " to " + targetMobileNo + ".\n"
							+ "Available balance is Rs. " + funds.getAccountBalance());

				} catch (InvalidPhoneNumber | InvalidAmount | MyException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}

				break;

			case 4:

				String mobNoDep;
				double amtDep;
				Account cDep = null;

				do {
					System.out.println("Enter your mobile number: ");
					mobNoDep = sc.next();
					try {
						if (service.validatePhoneNumber(mobNoDep))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter amount that to be deposited: ");
					amtDep = sc.nextDouble();
					try {
						if (service.validateAmount(amtDep))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);

				try {
					if (service.validatePhoneNumber(mobNoDep)
							&& service.validateAmount(amtDep))
						cDep = service.depositAmount(mobNoDep, amtDep);
					System.out.println("Your current balance is Rs."
							+ cDep.getAccountBalance());
				} catch (InvalidAmount | InvalidPhoneNumber | MyException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;

			case 5:

				String mobNoWiDraw;
				double amtWiDraw;

				do {
					System.out.println("Enter your mobile number: ");
					mobNoWiDraw = sc.next();
					try {
						if (service.validatePhoneNumber(mobNoWiDraw))
							break;
					} catch (InvalidPhoneNumber e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} while (true);
				do {
					System.out.println("Enter amount that to be withdrawn: ");
					amtWiDraw = sc.nextDouble();
					try {
						if (service.validateAmount(amtWiDraw))
							break;
					} catch (InvalidAmount e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (true);

				Account cWD = null;
				try {
					if (service.validatePhoneNumber(mobNoWiDraw)
							&& service.validateAmount(amtWiDraw))
						cWD = service.withdrawAmount(mobNoWiDraw, amtWiDraw);
					System.out.println("Your current balance is Rs. "
							+ cWD.getAccountBalance());
				} catch (InvalidAmount | MyException | InvalidPhoneNumber e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				break;

			case 6:

				System.out
						.println("Enter mobile number to print transactions:");
				String mob = sc.next();
				List<PrintTransactions> list = null;

				try {
					if (service.validatePhoneNumber(mob))
						list = service.getTransactions(mob);
					for (PrintTransactions pt : list)
						System.out.println(pt);
				} catch (MyException | InvalidPhoneNumber e) {
					System.err.println(e.getMessage());
				}

				break;
			default:
			case 7:
				break;
			}

		} while (option != 7);

		sc.close();
	}

}
