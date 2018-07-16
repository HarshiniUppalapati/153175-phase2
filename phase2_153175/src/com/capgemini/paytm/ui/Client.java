package com.capgemini.paytm.ui;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.service.WalletService;
import com.capgemini.paytm.service.WalletServiceImpl;

public class Client {

	private WalletService walletService;
	public Client()
	{
		walletService=new WalletServiceImpl();
	}
	public void menu() {
		String ans="";	
		Scanner sc=new Scanner(System.in);
		System.out.println("****Paytm Application****");
		Customer customer=new Customer();
		do
		{
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance");
			System.out.println("3. Fund Transfer");
			System.out.println("4. Deposit amount");
			System.out.println("5. Withdraw amount");
			System.out.println("6. Exit");			
			System.out.println("\nPlease Select an option:");
			int choice=sc.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("Enter Name: ");
				String name=sc.next();
				System.out.println("Enter Mobile Number: ");
				String mobileNo=sc.next();
				System.out.println("Enter Balance");
				BigDecimal amount=sc.nextBigDecimal();				
				customer=walletService.createAccount(name, mobileNo, amount);
				if(customer!=null){
				System.out.println("Your account is successfully created!");
				System.out.println(customer);
				}
				else
					System.out.println("Account Already Exist,Plz Change mobileno");
				break;				
			case 2:				
				System.out.println("Enter the mobile number to view balance: ");
				String mobileNo2=sc.next();
				customer=walletService.showBalance(mobileNo2);
				System.out.println("Your balance for mobile number: "+mobileNo2+" is "+customer.getWallet());
				break;
			case 3:
				System.out.println("Enter Source Mobile Number: ");
				String sourceMobileNo=sc.next();
				System.out.println("Enter Target Mobile Number: ");
				String targetMobileNo=sc.next();
				System.out.println("Enter amount to transfer");
				BigDecimal amount1=sc.nextBigDecimal();				
				customer=walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount1);
                System.out.println("Transferred successfully! Your account details after tranfer are:");
				System.out.println(customer);
				break;
			case 4:
				System.out.println("Enter Mobile Number: ");
				String mobileNo3=sc.next();
				System.out.println("Enter amount to deposit");
				BigDecimal amount3=sc.nextBigDecimal();				
				customer=walletService.depositAmount(mobileNo3, amount3);
				System.out.println(customer);
				break;
			case 5:
				System.out.println("Enter Mobile Number: ");
				String mobileNo4=sc.next();
				System.out.println("Enter amount to withdraw");
				BigDecimal amount4=sc.nextBigDecimal();				
				customer=walletService.withdrawAmount(mobileNo4, amount4);
				System.out.println(customer);
				break;
			case 6:
				System.out.println("Thank You");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid options");
				break;
			}			
			System.out.println("\nDo you want to continue: Y/N ");
			ans=sc.next();
		}while(ans.equalsIgnoreCase("y")|| ans.equalsIgnoreCase("yes"));
		System.out.println("\nThank you for using this service!");	
	}
	public static void main(String[] args) {
		Client client=new Client();
		client.menu();

	}

}
