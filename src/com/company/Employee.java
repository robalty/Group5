package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Employee extends Person {
    protected char jobTitleCode;

    public Employee(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip, char jobTitleCode) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip);
        this.jobTitleCode = jobTitleCode;
    }

    public Employee() {
        super();
        this.jobTitleCode = 'O';
    }

    public Employee update() {
        String response;
        String temp;
        char newCode;

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

    public int loadFromFile(Scanner fileInput) {
        String jobTitleString;

        fileInput.useDelimiter(";");
        int result = super.loadFromFile(fileInput);
        if (result==1) {
            jobTitleString = new String(fileInput.next());
            //add error handling
            this.jobTitleCode = jobTitleString.charAt(0);
            fileInput.nextLine();
            return 1;
        }
        else
            return result;
    }

    public void display() {
        super.display();
        System.out.println("Job Title Code: " + this.jobTitleCode);
    }

}
