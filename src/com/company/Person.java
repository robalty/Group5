package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

abstract public class Person extends Entry {
    protected String firstName;
    protected String lastName;
    protected String streetAddress;
    protected String city;
    protected String state;
    protected int zip;
    protected int []phone;
    protected Scanner userInput;

    public Person(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip, String phone) throws Exception {
        //add exception handling
        super(idNum);
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
        this.streetAddress = new String(streetAddress);
        this.city = new String(city);
        this.state = new String(state);
        this.zip = zip;
        this.phone = new int[10];
        String temp = new String(phone);
        char [] tempArray = temp.toCharArray();
        int length = temp.length();
        int j = 0;
        for (int i = 0; i < length; ++i) {
            int num = Character.getNumericValue(tempArray[i]);
            if (num!=-1 && j<10) {
                this.phone[j] = num;
                ++j;
            }
        }
        if (j < 10)
            throw new Exception();
        this.userInput = new Scanner(System.in);
    }

    public Person() {
        super();
        this.firstName = null;
        this.lastName = null;
        this.streetAddress = null;
        this.city = null;
        this.state = null;
        this.zip = 0;
        this.phone = null;
        this.userInput = new Scanner(System.in);
    }

    public void display() {
        if (this.firstName==null || this.lastName==null || this.streetAddress==null || this.city==null || this.state==null || this.zip==0 || this.phone==null) {
            System.out.println("Missing info for person");
            return;
        }
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("Address: " + this.streetAddress + ", " + this.city + ", " + this.state + ", " + this.zip);
        System.out.print("Phone: ");
        for (int i = 0; i < 10; ++i) {
            System.out.print(this.phone[i]);
            if (i==2 || i==5)
                System.out.print("-");
        }
        System.out.println();
    }

    public Person update() {
        String response;
        String temp;

        //add error handling/exceptions
        System.out.println("Here is the current information: ");
        display();
        System.out.print("Do you want to change the first name? Yes or No:");
        response = new String(this.userInput.next());
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new first name: ");
            userInput.nextLine();
            temp = this.userInput.nextLine();
            System.out.print("Do you want to change the first name to: " + temp + "? Yes or No:");
            response = this.userInput.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.firstName = temp;
        }
        System.out.print("Do you want to change the last name? Yes or No:");
        response = this.userInput.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new last name: ");
            userInput.nextLine();
            temp = this.userInput.nextLine();
            System.out.print("Do you want to change the last name to: " + temp + "? Yes or No:");
            response = this.userInput.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.lastName = temp;
        }
        System.out.print("Do you want to change the street address? Yes or No:");
        response = this.userInput.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new street address: ");
            userInput.nextLine();
            temp = this.userInput.nextLine();
            System.out.print("Do you want to change the street address to: " + temp + "? Yes or No:");
            response = this.userInput.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.streetAddress = temp;
        }
        System.out.print("Do you want to change the city? Yes or No:");
        response = this.userInput.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new city: ");
            userInput.nextLine();
            temp = this.userInput.nextLine();
            System.out.print("Do you want to change the city to: " + temp + "? Yes or No:");
            response = this.userInput.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.city = temp;
        }
        System.out.print("Do you want to change the state? Yes or No:");
        response = this.userInput.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new state: ");
            userInput.nextLine();
            temp = this.userInput.nextLine();
            System.out.print("Do you want to change the state to: " + temp + "? Yes or No:");
            response = this.userInput.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.state = temp;
        }
        System.out.print("Do you want to change the zip? Yes or No:");
        response = this.userInput.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new zip: ");
            userInput.nextLine();
            int tempZip = this.userInput.nextInt();
            System.out.print("Do you want to change the zip to: " + tempZip + "? Yes or No:");
            response = this.userInput.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.zip = tempZip;
        }
        System.out.print("Do you want to change the phone number? Yes or No:");
        response = this.userInput.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new phone number: ");
            userInput.nextLine();
            temp = this.userInput.nextLine();
            System.out.print("Do you want to change the phone number to: " + temp + "? Yes or No:");
            response = userInput.next();
            if (0==response.compareToIgnoreCase("Yes")) {
                char [] tempArray = temp.toCharArray();
                for (int i = 0; i < 10; ++i)
                    this.phone[i] = Character.getNumericValue(tempArray[i]);
            }
        }
        return this;

    }

    public int writeToFile(File aFile) {
        FileWriter aFileWriter;
        String zipString;
        String phoneString;

        try {
            aFileWriter = new FileWriter(aFile, true);

            aFileWriter.append(this.firstName);
            aFileWriter.append(";");

            aFileWriter.append(this.lastName);
            aFileWriter.append(";");

            aFileWriter.append(this.streetAddress);
            aFileWriter.append(";");

            aFileWriter.append(this.city);
            aFileWriter.append(";");

            aFileWriter.append(this.state);
            aFileWriter.append(";");

            zipString = Integer.toString(this.zip);
            aFileWriter.append(zipString);
            aFileWriter.append(";");

            phoneString = "";
            for (int i = 0; i < 10; ++i)
                phoneString = String.join("", phoneString, Integer.toString(this.phone[i]));
            aFileWriter.append(phoneString);
            aFileWriter.append(";");

            aFileWriter.close();
        } catch (IOException e) {
            return -1;
        }
        return 1;
    }

    public int writeReport(File aFile) {

        return 1;
    }

    public int loadFromFile(Scanner fileInput) {
        //add error handling/exceptions
        this.idNum = fileInput.nextInt();
        this.firstName = new String(fileInput.next());
        this.lastName = new String(fileInput.next());
        this.streetAddress = new String(fileInput.next());
        this.city = new String(fileInput.next());
        this.state = new String(fileInput.next());
        this.zip = fileInput.nextInt();
        this.phone = new int[10];
        String temp = new String(fileInput.next());
        char [] tempArray = temp.toCharArray();
        for (int i = 0; i < 10; ++i) {
            phone[i] = Character.getNumericValue(tempArray[i]);
        }
        return 1;
    }


}
