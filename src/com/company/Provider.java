package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Provider extends Person {
    protected List_service serviceList;  //List of services the provider offers
    protected List_service serviceProvidedList;  //List of services the provider has provided to members

    // Constructor with arguments
    public Provider(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip);
        this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    // Default constructor
    public Provider() {
        super();
        this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    // Allows the user to update the name or address of the provider
    // OUTPUT: returns reference to the current Provider object
    public Provider update() {
        super.update();
        return this;
    }

    // Writes the personal information of the provider to the File object passed in,
    // writes the serviceList to a file named S + the provider's id number (ex: S123456789.txt), and
    // writes the serviceProvidedList to a file name P + the provider's id number (ex: P123456789.txt)
    // INPUT: File object for the provider database
    // OUTPUT: - returns -1 if an error occurs while writing the files
    //         - returns the number of items written to file if writing was successful (1 for personal info plus 1 for each
    //           service and serviceProvided written to file)
    public int writeToFile(File aFile) {
        int result; // result of writing to files
        String idString; // temporary string of provider's id to create serviceFilename and serviceProvidedFilename
        String serviceFilename;
        File serviceFile;
        String serviceProvidedFilename;
        File serviceProvidedFile;
        FileWriter aFileWriter;

        result = super.writeToFile(aFile);
        if (result != -1) {
            idString = Integer.toString(this.idNum);

            serviceFilename = String.join("", "S", idString, ".txt");
            serviceFile = new File("data_files\\list_of_services\\" + serviceFilename);
            result += this.serviceList.write_Text_file(serviceFile);

            serviceProvidedFilename = String.join("", "P", idString, ".txt");
            serviceProvidedFile = new File("data_files\\list_of_services\\" + serviceProvidedFilename);
            result += this.serviceProvidedList.write_Text_file(serviceProvidedFile);

            try {
                aFileWriter = new FileWriter(aFile, true);

                aFileWriter.append(serviceFilename);
                aFileWriter.append(";");

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

    // Writes a report for the provider to an external file
    // INPUT: File object to write report to
    public int writeReport(File aFile) {
        int result = 0;
        int count = 0;

        result = super.writeReport(aFile);
// uncomment once Thong has implemented the method
/*        if (result==1) {
            count = this.serviceProvidedList.write_report(aFile);
            return count;
        }
        else
*/        return result;
    }

    // Gets the total amount owed to the provider by ChocAn
    // OUTPUT: double for total amount
    public double getTotalFee() {
        double total = this.serviceProvidedList.total_amount_fees_of_the_list();
        if (total==-1)
            return 0;
        else
            return total;
    }

    // Gets the number of services the provider has provided
    // OUTPUT: int for number of services
    public int getNumberOfServicesProvided() {
        int count = this.serviceProvidedList.number_of_services_in_the_list();
        if (count==-1)
            return 0;
        else
            return count;
    }

    // Loads a provider's information from external files
    // INPUT: Scanner object for the provider database file
    // OUTPUT: -returns -1 if an error occurs while loading from the file
    //         -returns the number of items loaded if loading was successful
    public int loadFromFile(Scanner fileInput) {
        int result = 0; // result of loading personal info from the external file
        int count = 0; // number of services and servicesProvided loaded from the file
        String serviceFilename;
        File serviceFile;
        String serviceProvidedFilename;
        File serviceProvidedFile;

        fileInput.useDelimiter(";");
        result = super.loadFromFile(fileInput);
        if (result==1) {

            try {
                serviceFilename = fileInput.next();
                serviceFile = new File("data_files\\list_of_services\\" + serviceFilename);
                if (serviceFile.length() > 0)
                    count += this.serviceList.load_Services_from_text_file(serviceFile);

                serviceProvidedFilename = fileInput.next();
                serviceProvidedFile = new File("data_files\\list_of_services\\" + serviceProvidedFilename);
                if (serviceProvidedFile.length() > 0)
                    count += this.serviceProvidedList.load_Services_from_text_file(serviceProvidedFile);
            }
            catch(Exception e) {
                count = -1;
            }

            if (fileInput.hasNextLine())
                fileInput.nextLine();

            return count;
        }
        else
            return result;
    }

    // Adds a service that the provider offers to the serviceList
    // INPUT: Service object to add
    // OUTPUT: -returns -2 if the Service object argument is null
    //         -returns -1 if the Service was not added successfully
    //         -returns 1 if the Service was added successfully
    public int addService(Service toAdd) {
        if (this.serviceList == null)
            this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
        return this.serviceList.add_service_to_list(toAdd);
    }

    // Adds a service that the provider has provided to a member to the serviceProvidedList
    // INPUT: ServiceProvided object to add
    // OUTPUT: -returns -2 if the ServiceProvided object argument is null
    //         -returns -1 if the ServiceProvided was not added successfully
    //         -returns 1 if the ServiceProvided was added successfully
    public int addServiceProvided(ServiceProvided toAdd) {
        if (this.serviceProvidedList == null)
            this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
        return this.serviceProvidedList.add_service_to_list(toAdd);
    }

    // Displays all the information about the provider, including the services in the serviceList and the
    // services provided in the serviceProvidedList
    public void display() {
        super.display();
        if (this.serviceList != null) {
            System.out.println("Services offered:");
            this.serviceList.display_all_list_services_();
        }
        if (this.serviceProvidedList != null) {
            System.out.println("Services provided:");
            this.serviceProvidedList.display_all_list_services_();
        }
    }

}
