package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Provider extends Person {
    protected List_service serviceList;
    protected List_service serviceProvidedList;

    public Provider(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip);
        this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    public Provider() {
        super();
        this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    public Provider update() {
        super.update();
        return this;
    }

    public int writeToFile(File aFile) {
        int result = 0;
        String idString;
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

    public double getTotalFee() {
        double total = this.serviceProvidedList.total_amount_fees_of_the_list();
        if (total==-1)
            return 0;
        else
            return total;
    }

    public int getNumberOfServicesProvided() {
        int count = this.serviceProvidedList.number_of_services_in_the_list();
        if (count==-1)
            return 0;
        else
            return count;
    }

    public int loadFromFile(Scanner fileInput) {
        int result = 0;
        int count = 0;
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

    public int addService(Service toAdd) {
        if (this.serviceList == null)
            this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
        return this.serviceList.add_service_to_list(toAdd);
    }

    public int addServiceProvided(ServiceProvided toAdd) {
        if (this.serviceProvidedList == null)
            this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
        return this.serviceProvidedList.add_service_to_list(toAdd);
    }

    public void display() {
        super.display();
        if (this.serviceList != null) {
            System.out.println("Services offered:\n");
            this.serviceList.display_all_list_services_();
        }
        if (this.serviceProvidedList != null) {
            System.out.println("Services provided:\n");
            this.serviceProvidedList.display_all_list_services_();
        }
    }

}
