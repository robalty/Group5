package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/* *********************************************************************************************************************
Hannah Femling

The Person class (derived from the Entry class) is an abstract base class for the Employee, Member, and Provider
classes.  It contains the fields and methods that will be used by all three classes.
********************************************************************************************************************* */

abstract public class Person extends Entry {
    protected String firstName;
    protected String lastName;
    protected String streetAddress;
    protected String city;
    protected String state;
    protected int zip;

    // Constructor with arguments
    public Person(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip) throws Exception {
        super(idNum);
        this.firstName = new String(firstName);
        this.lastName = new String(lastName);
        this.streetAddress = new String(streetAddress);
        this.city = new String(city);
        this.state = new String(state);
        this.zip = zip;
    }

    // Default constructor
    public Person() {
        super();
        this.firstName = null;
        this.lastName = null;
        this.streetAddress = null;
        this.city = null;
        this.state = null;
        this.zip = 0;
    }

    // Displays all the personal information
    public void display() {
        if (this.firstName==null || this.lastName==null || this.streetAddress==null || this.city==null || this.state==null || this.zip==0) {
            System.out.println("Missing info for person");
            return;
        }
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("Address: " + this.streetAddress + ", " + this.city + ", " + this.state + ", " + this.zip);
    }

    // Allows the user to update the name or address of the person (employee, member, or provider)
    // OUTPUT: returns a reference to the current object
    public Person update() {
        String response; // Whether the user wants to change a field
        String temp; // Value the user wants to change a field to

        System.out.println("Here is the current information: ");
        display();
        System.out.print("Do you want to change the first name? Yes or No:");
        response = new String(this.input.next());
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new first name: ");
            this.input.nextLine();
            temp = this.input.nextLine();
            System.out.print("Do you want to change the first name to: " + temp + "? Yes or No:");
            response = this.input.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.firstName = temp;
        }
        System.out.print("Do you want to change the last name? Yes or No:");
        response = this.input.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new last name: ");
            this.input.nextLine();
            temp = this.input.nextLine();
            System.out.print("Do you want to change the last name to: " + temp + "? Yes or No:");
            response = this.input.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.lastName = temp;
        }
        System.out.print("Do you want to change the street address? Yes or No:");
        response = this.input.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new street address: ");
            this.input.nextLine();
            temp = this.input.nextLine();
            System.out.print("Do you want to change the street address to: " + temp + "? Yes or No:");
            response = this.input.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.streetAddress = temp;
        }
        System.out.print("Do you want to change the city? Yes or No:");
        response = this.input.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new city: ");
            this.input.nextLine();
            temp = this.input.nextLine();
            System.out.print("Do you want to change the city to: " + temp + "? Yes or No:");
            response = this.input.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.city = temp;
        }
        System.out.print("Do you want to change the state? Yes or No:");
        response = this.input.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new state: ");
            this.input.nextLine();
            temp = this.input.nextLine();
            System.out.print("Do you want to change the state to: " + temp + "? Yes or No:");
            response = this.input.next();
            if (0==response.compareToIgnoreCase("Yes"))
                this.state = temp;
        }
        System.out.print("Do you want to change the zip? Yes or No:");
        response = this.input.next();
        if (0==response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new zip: ");
            this.input.nextLine();
            if (this.input.hasNextInt()) {
                int tempZip = this.input.nextInt();
                System.out.print("Do you want to change the zip to: " + tempZip + "? Yes or No:");
                response = this.input.next();
                if (0 == response.compareToIgnoreCase("Yes"))
                    this.zip = tempZip;
            }
            else
                System.out.println("Invalid input.  Unable to change zip code.");
        }
        return this;

    }

    // Writes all the personal information to an external file
    // INPUT: File object for database file
    // OUTPUT: -returns -1 if an error occurs while writing to file
    //         -returns 1 if writing was successful
    public int writeToFile(File aFile) {
        FileWriter aFileWriter;
        String zipString; // Temp string to convert zip code to before writing to file

        try {
            aFileWriter = new FileWriter(aFile, true);

            aFileWriter.append(Integer.toString(this.idNum));
            aFileWriter.append(";");

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

            aFileWriter.close();
        } catch (IOException e) {
            return -1;
        }
        return 1;
    }

    // Adds personal info the the report
    // INPUT: File object for the report
    // OUTPUT: -returns -1 if an error occurred while writing
    //         -returns 1 if writing was successful
    public int writeReport(File aFile) {
        FileWriter aFileWriter;

        try {
            aFileWriter = new FileWriter(aFile, true);
            aFileWriter.append(String.join("","Name: ", this.firstName, " ", this.lastName, "\n"));
            aFileWriter.append(String.join("", "ID Number: ", Integer.toString(this.idNum), "\n"));
            aFileWriter.append(String.join("", "Street Address: ", this.streetAddress, "\n"));
            aFileWriter.append(String.join("", "City: ", this.city, "\n"));
            aFileWriter.append(String.join("", "State: ", this.state, "\n"));
            aFileWriter.append(String.join("", "Zip Code: ", Integer.toString(this.zip), "\n"));
            aFileWriter.close();
        }
        catch (IOException e) {
            return -1;
        }
        return 1;
    }

    // Loads personal information from an external file
    // INPUT: Scanner object for the external file
    // OUTPUT: -returns -1 if an error occurred
    //         -returns 1 if loading was successful
    public int loadFromFile(Scanner fileInput) {
        try {
            this.idNum = fileInput.nextInt();
            this.firstName = new String(fileInput.next());
            this.lastName = new String(fileInput.next());
            this.streetAddress = new String(fileInput.next());
            this.city = new String(fileInput.next());
            this.state = new String(fileInput.next());
            this.zip = fileInput.nextInt();
        }
        catch (Exception e) {
            return -1;
        }
        return 1;
    }

    protected int clearFileContents(File aFile) {
        FileWriter aFileWriter;

        try {
            aFileWriter = new FileWriter(aFile, false);
            aFileWriter.write("");
            aFileWriter.close();
        }
        catch (Exception e) {
            return -1;
        }
        return 1;
    }

}
