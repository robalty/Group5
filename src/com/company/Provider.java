package com.company;

import java.io.File;
import java.util.Scanner;

public class Provider extends Person {
    protected List_service serviceList;
    protected List_service serviceProvidedList;

    public Provider(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip, String email, String phone) throws Exception {
        super(idNum, firstName, lastName, streetAddress, city, state, zip, email, phone);
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
        int result = super.writeToFile(aFile);
        if (result != -1) {
            String idString = Integer.toString(this.idNum);
            String serviceFilename = String.join("", "S", idString, ".txt");
            File serviceFile = new File(serviceFilename);
//        result += this.serviceList.write();
            String serviceProvidedFilename = String.join("", "P", idString, ".txt");
            File serviceProvidedFile = new File(serviceProvidedFilename);
//        result += this.serviceProvidedList.write(serviceProvidedFile);
        }
        return result;
    }

    public int writeReport(File aFile) {
        return super.writeReport(aFile);
    }

    public int loadFromFile(Scanner fileInput) {
        int result = super.loadFromFile(fileInput);
//        if (result==1) {
//        int count = this.serviceList.add_all_services(fileInput);
//        count += this.serviceProvidedList.add_all_services(fileInput);
//        return count;
//        }
//        else
        return result;
    }

    public int addService(Service toAdd) {
        if (this.serviceList == null)
            this.serviceList = new List_service(List_Service_Type.all_services_available_provider);
//        int count =
        this.serviceList.add_service_to_list(toAdd);
        return 1; //change to return count once Thong has updated the add method
    }

    public int addServiceProvided(ServiceProvided toAdd) {
        if (this.serviceProvidedList == null)
            this.serviceList = new List_service(List_Service_Type.all_services_provided_provider);
//        int count =
        this.serviceProvidedList.add_service_to_list(toAdd);
        return 1; //change to return count once Thong has updated the add method
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
