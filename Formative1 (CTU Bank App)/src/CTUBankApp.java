/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * @author nelani maluk
 */
public class CTUBankApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
  
        try { //Checks if the file containing the amount exists and that it has text to extract
            BufferedReader reader = new BufferedReader(new FileReader("amount.txt"));
            String a;
            a = reader.readLine(); 
            a = a.substring(10);
             
        } catch(IOException e){ //If the file doesn't exist then it creates it and sets default amounts and time values
         
            File file = new File("amount.txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd ");
            String strDate = dateFormat.format(date);
         
            pw.println("Balance: R" + 0);
            pw.println("Savings: R" + 0);
            pw.println(strDate);
            pw.close();    
        } finally {
        
            int balance, savings;
            String a, b, strDate ;
        
            //Specifies to read the file containing the amounts 
            BufferedReader reader = new BufferedReader(new FileReader("amount.txt")); 
        
            //Reads the lines
            a = reader.readLine(); 
            b = reader.readLine();
            strDate = reader.readLine();
            reader.close();
        
            //Filters out the text
            a = a.substring(10);
            b = b.substring( 10);
       
            //Converts the text to integers to be used in the code
            balance = Integer.parseInt(a);
            savings = Integer.parseInt(b);
        
            updateAmount(balance, savings, strDate);//Runs the update method to create the file if it doesnt exist
            home(balance, savings, strDate); //starts the program
        }
    }; 
      
    public static void home(int balance, int savings, String strDate) throws IOException {
        System.out.println("\n============================================");
        System.out.println("**** Welcome to the CTU Banking App **** \n");
        System.out.println("1) Display Account Details");
        System.out.println("2) Deposit");
        System.out.println("3) Save");
        System.out.println("4) Withdraw");
        System.out.println("5) Exit");
        System.out.println("\nN.B. Select a number (e.g 1) ");
        System.out.print("What would you like to do : ");
        
        int selectedOption; 
        Scanner scan = new Scanner(System.in);
        selectedOption = scan.nextInt();
        
        switch (selectedOption) {
            case 1 -> display_Acc_Det(balance, savings, strDate);
            case 2 -> deposit(balance, savings, strDate);
            case 3 -> save(balance, savings, strDate);
            case 4 -> withdraw(balance, savings, strDate);
            case 5 -> exit();
            default -> {
                System.out.println("\nError Wrong input \n");
                home(balance, savings, strDate);
            }
        } 
    };
    
    public static void display_Acc_Det(int balance, int savings, String strDate) throws IOException {
        System.out.println("\n============================================");
        System.out.println("**** Account Details **** \n");
        System.out.println("Account Number: 123456789");
        System.out.println("Account Holder: Nelani Maluka");
        System.out.println("Account type: Local");
        System.out.println("Balance: R" + balance);
        System.out.println("Savings: R" + savings);
        
        // Gives the user options to return to the previous window or exit
        System.out.println("\n1) Back");
        System.out.print("2) end");
        System.out.println("\nN.B. Select a number (e.g 1) ");
        System.out.print("What would you like to do: ");
        
        int selectedOption;
        Scanner scan = new Scanner(System.in);
        selectedOption = scan.nextInt();
        
        switch (selectedOption) {
            case 1 -> home(balance, savings, strDate);
            case 2 -> exit();
            default -> {
                System.out.println("\nError Wrong input \n");
                home(balance, savings, strDate);
            }
        }
    };
    
    public static void deposit(int balance, int savings, String strDate) throws IOException {
        System.out.println("\n============================================");
        System.out.println("**** Deposit **** \n");
        System.out.println("Balance: R" + balance);
        
        
        System.out.print("\nAmount to deposit: ");
        
        int deposit;
        Scanner scan = new Scanner(System.in);
        deposit = scan.nextInt();
        
        if (deposit > 0){ //Checks if the entered amount is a number and adds the deposit,updates the amount,goes to the home page
            balance += deposit;
            updateAmount(balance, savings, strDate);
            System.out.print("\nR" + deposit + " was deposited into your account.");
        } else {
            System.out.println("\nError Wrong format \n");    
        }
        
        home(balance, savings, strDate);
    };
    
    public static void withdraw(int balance, int savings, String strDate) throws IOException {
        System.out.println("\n============================================");
        System.out.println("**** Withdraw **** \n");
        System.out.println("Balance: R" + balance);
        
        System.out.print("\nAmount to withdraw: ");
        
        int withdraw;
        Scanner scan = new Scanner(System.in);
        withdraw = scan.nextInt();
        
        if (withdraw > 0 && withdraw <= balance){ //Checks if the entered amount is a number and removes the withdrawall,updates the amount,goes to the home page
            balance -= withdraw;
            updateAmount(balance, savings, strDate);
            System.out.print("\nR" + withdraw + " was withdrawn from your account.");
        } else {
            System.out.println("\nError Wrong input \n");
        }
        
        home(balance, savings, strDate);
    };
    
    public static void save(int balance, int savings, String strDate) throws IOException {
        System.out.println("\n============================================");
        System.out.println("**** Savings **** \n");
        System.out.println("Balance: R" + balance);
        System.out.println("Savings: R" + savings);
        
        System.out.println("1) Transfer from balance to savings");
        System.out.println("2) Transfer from savings to balance");
        System.out.println("3) Intrest information");
        System.out.println("4) Exit");
        
        System.out.println("\nN.B. Select a number (e.g 1) ");
        System.out.print("What would you like to do: ");
        
        int selectedOption;
        Scanner scan = new Scanner(System.in);
        selectedOption = scan.nextInt();
        
        switch (selectedOption) {
            case 1 ->                 {
                    System.out.println("\nBalance: R" + balance);
                    System.out.println("Savings: R" + savings);
                    System.out.print("\nAmount to save: ");
                    int amount = scan.nextInt();
                    //Checks if the entered amount isn't larger than the accounts amount then adds and subtracts from savings and balance
                    if (amount <= balance) {
                        balance -= amount;
                        savings += amount;
                        updateAmount(balance, savings, strDate);
                    } else {
                        System.out.println("\nError Wrong input \n");
                    }     home(balance, savings, strDate);
                }
            case 2 ->                 {
                    System.out.println("\nSavings: R" + savings);
                    System.out.println("Balance: R" + balance);
                    System.out.print("\nAmount to transfer: ");
                    int amount = scan.nextInt();
                    //Checks if the entered amount isn't larger than the accounts amount then adds and subtracts from savings and balance
                    if (amount <= savings) {
                        savings -= amount;
                        balance += amount;
                        updateAmount(balance, savings, strDate);
                    } else {
                        System.out.println("\nError Wrong input \n");
                    }     home(balance, savings, strDate);
                }
            case 3 -> {
                System.out.println("\n============================================");
                System.out.println("Intrest is earned on a monthly basis in the savings account.");
                System.out.println("Intrest amount changes with the account balance.\n");
                System.out.println("for R100 - R599 intrest is 0.5%");
                System.out.println("for R600 - R1000 intrest is 2%");
                System.out.println("for over R1000 intrest is 5%\n");
                System.out.println("1) Back");
                System.out.println("2) Exit");
                System.out.print("What would you like to do: ");
                selectedOption = scan.nextInt();
                
                switch (selectedOption) {
                    case 1 -> save(balance, savings, strDate);
                    case 2 -> exit();
                    default -> {
                        System.out.println("\nError Wrong input \n");
                        home(balance, savings, strDate);
                }
                }
            }
            case 4 -> exit();
            default -> {
                System.out.println("\nError Wrong input \n");
                home(balance, savings, strDate);
            }
        }  
    };
    
    public static void exit() {
       System.out.println("\nThank you for using the CTU Banking App \n");
    };
    
    public static void updateAmount(int balance, int savings, String strDate) throws IOException {
        
        intrest(balance, savings, strDate); //Checks if intrest needs to be added before updating the balance
        
        File file = new File("amount.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        
        if (balance >= 0 && savings >= 0) { //Checks if the a amounts are in the right format and then updates the amounts
            pw.println("Balance: R" + balance);
            pw.println("savings: R" + savings);
            pw.println(strDate);
            pw.close();
        } 
    };
    
    public static void intrest(int balance, int savings, String strDate) throws IOException{
          
          Date date = Calendar.getInstance().getTime();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd ");
          String currentDate = dateFormat.format(date);
          
          while (currentDate.compareTo(strDate) >= 0){ //Checks if the current date is ahead of the intreast date 
              if (savings >= 100 && savings <= 599){ //Checks the current savings amount to add intreat accordingly
                  savings *= 1.005;
              } else if (savings >= 600 && savings <= 1000){
                  savings *= 1.02; 
              } else if (savings >= 1000){
                  savings *= 1.05; 
              }
 
              //updates the intreast date to the next intreast date then updates the txt file
              Calendar cal = Calendar.getInstance();
              cal.add(Calendar.MONTH, 1);
              cal.getTime(); 
              strDate = dateFormat.format(cal);
 
              updateAmount(balance, savings, strDate);
          }
    }; 
};
