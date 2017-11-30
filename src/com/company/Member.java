package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/* *********************************************************************************************************************
The Member class (derived from the Person class) is a node in the tree of ChocAn members, and contains the fields
and methods specific to an member.
********************************************************************************************************************* */

public class Member extends Person {
    protected List_service serviceProvidedList;

    // Constructor with arguments
    public Member(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip);
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    // Default constructor
    public Member() {
        super();
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    // Allows user to change the name or address of the member
    // OUTPUT: returns reference to the current Member object
    public Member update() {
        super.update();
        return this;
    }

    // Writes the personal information of the member to the File object passed in and
    // writes the serviceProvidedList to a file name P + the member's id number (ex: P123456789.txt)
    // INPUT: File object for the member database
    // OUTPUT: - returns -1 if an error occurs while writing the files
    //         - returns the number of items written to file if writing was successful (1 for personal info plus 1 for each
    //           serviceProvided written to file)
    public int writeToFile(File aFile) {
        int result;
        String idString; // temporary string of provider's id to create serviceFilename and serviceProvidedFilename
        String serviceProvidedFilename;
        File serviceProvidedFile;
        FileWriter aFileWriter;

        result = super.writeToFile(aFile);
        if (result != -1) {
            idString = Integer.toString(this.idNum);
            serviceProvidedFilename = String.join("", "P", idString, ".txt");
            serviceProvidedFile = new File(".\\data_files\\list_of_services\\member\\" + serviceProvidedFilename);
            if (serviceProvidedFile.exists())
                clearFileContents(serviceProvidedFile);
            result += this.serviceProvidedList.write_Text_file(serviceProvidedFile);
            try {
                aFileWriter = new FileWriter(aFile, true);

                aFileWriter.append(serviceProvidedFilename);
                aFileWriter.append(";\n");

                aFileWriter.close();
            }
            catch (IOException e) {
                return -1;
            }
        }
        return result;
    }

    // Writes a report for the member to an external file
    // INPUT: File object to write report to
    public int writeReport(File aFile) {
        int result = 0;

        result = super.writeReport(aFile);
        if (result==1)
            try {
                result += this.serviceProvidedList.writeMemberReport(aFile);
            }catch(Exception e){
            return 0;
            }
        return result;
    }

    // Loads a member's information from external files
    // INPUT: Scanner object for the member database file
    // OUTPUT: -returns -1 if an error occurs while loading from the file
    //         -returns the number of items loaded (1 for personal info plus 1 for each service and service provided)
    public int loadFromFile(Scanner fileInput) {
        int result = 0;
        String serviceProvidedFilename;
        File serviceProvidedFile;

        fileInput.useDelimiter(";");
        result = super.loadFromFile(fileInput);
        if (result==1) {
            try {
                serviceProvidedFilename = fileInput.next();
                serviceProvidedFile = new File(".\\data_files\\list_of_services\\member\\" + serviceProvidedFilename);
                if (serviceProvidedFile.length() > 0)
                    result += this.serviceProvidedList.load_Services_from_text_file(serviceProvidedFile);
            }
            catch (Exception e) {
                result = -1;
            }

            if (fileInput.hasNextLine())
                fileInput.nextLine();
        }
        return result;
    }

    // Adds a service that the member has been provided to the serviceProvidedList
    // INPUT: ServiceProvided object to add
    // OUTPUT: -returns -2 if the ServiceProvided object argument is null
    //         -returns -1 if the ServiceProvided was not added successfully
    //         -returns 1 if the ServiceProvided was added successfully
    public int addServiceProvided(ServiceProvided toAdd) {
        if (this.serviceProvidedList == null)
            this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
        return this.serviceProvidedList.add_service_to_list(toAdd);
    }

    // Displays all the information about the provider, including the services provided in the serviceProvidedList
    public void display() {
        super.display();
        if (this.serviceProvidedList != null) {
            System.out.println("Services received:");
            this.serviceProvidedList.display_all_list_services_();
        }
    }

    public int getNumberOfServices() {
        int count = this.serviceProvidedList.number_of_services_in_the_list();
        if (count==-1)
            return 0;
        else
            return count;
    }

    public double getTotalFee() {
        double total = this.serviceProvidedList.total_amount_fees_of_the_list();
        if (total==-1)
            return 0;
        else
            return total;
    }

    public boolean isCurrent(){
        return true;
    }

    public void toggleStatus(){
        return;
    }

}
