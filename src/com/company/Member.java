package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Member extends Person {
    protected List_service serviceProvidedList;

    public Member(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip);
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    public Member() {
        super();
        this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
    }

    public Member update() {
        super.update();
        return this;
    }

    public int writeToFile(File aFile) {
        int result;
        String idString;
        String serviceProvidedFilename;
        File serviceProvidedFile;
        FileWriter aFileWriter;

        result = super.writeToFile(aFile);
        if (result != -1) {
            idString = Integer.toString(this.idNum);
            serviceProvidedFilename = String.join("", "P", idString, ".txt");
            serviceProvidedFile = new File("data_files\\list_of_services\\" + serviceProvidedFilename);
            result += this.serviceProvidedList.write_Text_file(serviceProvidedFile);
            try {
                aFileWriter = new FileWriter(aFile, true);
                aFileWriter.append("\n");
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
            count += this.serviceProvidedList.write_report(aFile);
            return count;
        }
        else
*/        return result;
    }

    public int loadFromFile(Scanner fileInput) {
        int result = 0;
        int count = 0;
        String serviceProvidedFilename;
        File serviceProvidedFile;

        fileInput.useDelimiter(";");
        result = super.loadFromFile(fileInput);
        if (result==1) {
            serviceProvidedFilename = fileInput.next();
            serviceProvidedFile = new File("data_files\\list_of_services\\" + serviceProvidedFilename);
            count = this.serviceProvidedList.load_Services_from_text_file(serviceProvidedFile);

            fileInput.nextLine();

            return count;
        }
        else
            return result;
    }

    public int addServiceProvided(ServiceProvided toAdd) {
        if (this.serviceProvidedList == null)
            this.serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);
        return this.serviceProvidedList.add_service_to_list(toAdd);
    }

    public void display() {
        super.display();
        if (this.serviceProvidedList != null) {
            System.out.println("Services provided:\n");
            this.serviceProvidedList.display_all_list_services_();
        }
    }

}
