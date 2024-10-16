package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileReader;


public class FinancialTransactions {

    static Scanner scanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<TransactionRecord> transactions = new ArrayList<TransactionRecord>();


    public static void main(String[] args) {

        // ***** BEGIN Create menu Instructions *****

        // Initialize the command variable (int mainMenuCommand;)

        int mainMenuCommand;
        // Create a do-while loop with a conditional for the command= home screen

        do {
            // display the menu
            System.out.println("1) Would you like to add deposit? ");


            System.out.println("2) Would you like to make payment (Debit)?");
            System.out.println("3) Display the screen");
            System.out.println("0) Exit ");



            try {
                mainMenuCommand = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input, please enter the number.");
                mainMenuCommand = 0;
            }


            switch (mainMenuCommand) {
                case 1:
                    addDeposit();
                    break;
                case 2:
                    makePayment();
                    break;
                case 3:
//                     displayMenu();
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Command not found, please try again");

            }

        } while (mainMenuCommand != 0);


        loadTransactions();
        loadTransactionsFromFile();

    }

    public static void loadTransactions() {

        LocalDate date = LocalDate.parse("2023-04-15");
        LocalTime time = LocalTime.parse("10:13:25");

        TransactionRecord transcation1 = new TransactionRecord("date", "time", "ergonomic keyboard", "Amazon", -89.50);

        transactions.add(transcation1);
    }

    public static void loadTransactionsFromFile() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transaction.csv"));

            String firstline = bufferedReader.readLine();
            String input;


            while ((input = bufferedReader.readLine()) != null) {


                String[] trans = input.split("\\|"); // "2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50" >> ["2023-04-15", "10:13:25", "ergonomic keyboard", "Amazon", "-89.50"]

                LocalDate date = LocalDate.parse(trans[0]);

                LocalTime time = LocalTime.parse(trans[1]);


                String description = trans[2];


                String vendor = trans[3];


                double amount = Double.parseDouble(trans[4]);

                transactions.add(new TransactionRecord(date, time, description, vendor, amount));

            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDeposit() {


        System.out.print("Please enter today's date ");
        String date = inputScanner.nextLine();

        System.out.println("Please enter the time");
        String time = inputScanner.nextLine();

        System.out.println("Please enter the description");
        String description = inputScanner.nextLine();

        System.out.println("Please enter the vendor");
        String vendor = inputScanner.nextLine();

        System.out.println("Please enter the amount");
        double amount = inputScanner.nextInt();

        TransactionRecord transaction = new TransactionRecord(date, time, description, vendor, amount);
        transactions.add(transaction);


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transaction.csv", true));
            bufferedWriter.write(String.format("%s|%s|%s|%s|%.2f",
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount()

            ));
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  makePayment(){
        int subMenuCommand;

        System.out.println("1) What is your debit card number?");
        String cardNumber = inputScanner.nextLine();

        System.out.println("2) What is the name on the debit card?");
        String nameonCard = inputScanner.nextLine();

        System.out.println("3) What is the expiration date of your debit card? (Please enter in MM/YY format)");
        String expirationDate = inputScanner.nextLine();

        System.out.println("4) What is the CVV number on the back of your debit card?");
        String cvvNumber = inputScanner.nextLine();

        System.out.println("5) How much would you like to pay?");
        double payment = inputScanner.nextInt();
        inputScanner.nextLine();

        System.out.println("6) What is the billing address associated with your debit card? (Street, City, State, Zip Code)");
        String address = inputScanner.nextLine();


        System.out.println("7)What is your contact phone number or email address for confirmation?");
        String emailorPhone = inputScanner.nextLine();

        System.out.println("0) Back");


    }

}

