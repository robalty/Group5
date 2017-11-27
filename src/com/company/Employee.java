package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Employee extends Person {
    protected char jobTitleCode;

    // Constructor with arguments
    public Employee(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip, char jobTitleCode) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip);
        this.jobTitleCode = jobTitleCode;
    }

    // Default constructor
    public Employee() {
        super();
        this.jobTitleCode = 'O';
    }

    // Allows the user to update the name, address, or job title code of the employee
    // OUTPUT: returns reference to the current Employee object
    public Employee update() {
        String response; // whether the user wants to change the job title code
        String temp; // temporary value to read in from user
        char newCode; // char for new job title code

        super.update();
        System.out.print("Do you want to change the job title code? Yes or No:");
        response = new String(this.input.next());
        if (0 == response.compareToIgnoreCase("Yes")) {
            System.out.print("Enter the new job title code: ");
            input.nextLine();
            temp = this.input.nextLine();
            newCode = temp.charAt(0);
            if (newCode=='M' || newCode=='O') {
                System.out.print("Do you want to change the job title code to: " + newCode + "? Yes or No:");
                response = this.input.next();
                if (0 == response.compareToIgnoreCase("Yes"))
                    this.jobTitleCode = newCode;
            }
            else
                System.out.println("Invalid input.  Unable to change job title code.");
        }
        return this;
    }

    // Writes the personal information of the employee to the File object passed in
    // INPUT: File object for the employee database
    // OUTPUT: - returns -1 if an error occurs while writing the files
    //         - returns 1 if writing was successful
    public int writeToFile(File aFile) {
        int result;
        FileWriter aFileWriter;

        result = super.writeToFile(aFile);
        if (result==1) {
            try {
                aFileWriter = new FileWriter(aFile, true);
                aFileWriter.append(String.join("",Character.toString(this.jobTitleCode), ";\n"));
                aFileWriter.close();
            }
            catch (IOException e) {
                return -1;
            }
        }
        return result;
    }

    // Loads a employee's information from external files
    // INPUT: Scanner object for the employee database file
    // OUTPUT: -returns -1 if an error occurs while loading from the file
    //         -returns 1 if loading was successful
    public int loadFromFile(Scanner fileInput) {
        String jobTitleString; // String to read in job title from the file
        char tempJobTitleCode; // Temp char to check if the value is valid ('M' or 'O')

        fileInput.useDelimiter(";");
        int result = super.loadFromFile(fileInput);
        if (result==1) {
            jobTitleString = new String(fileInput.next());
            tempJobTitleCode = jobTitleString.charAt(0);
            if (tempJobTitleCode == 'M' || tempJobTitleCode == 'O')
                this.jobTitleCode = tempJobTitleCode;
            else
                result = -1;
        }
        if (fileInput.hasNextLine())
            fileInput.nextLine();
        return result;
    }

    // Displays all the information for the employee
    public void display() {
        super.display();
        System.out.println("Job Title Code: " + this.jobTitleCode);
    }

}
