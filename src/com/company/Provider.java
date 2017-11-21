package com.company;

import java.io.File;
import java.util.Scanner;

public class Provider extends Person {
    protected List_service serviceList = new List_service(List_Service_Type.all_services_available_provider);
    protected List_service serviceProvidedList = new List_service(List_Service_Type.all_services_provided_provider);

    public Provider(int idNum, String firstName, String lastName, String streetAddress, String city, String state, int zip, String email, int [] phone) {
        super(idNum, firstName, lastName, streetAddress, city, state, zip, email, phone);
        this.serviceList = null;
        this.serviceProvidedList = null;
    }

    public Provider() {
        super();
        this.serviceList = null;
        this.serviceProvidedList = null;
    }

    public Provider update() {
        super.update();
        return this;
    }

    public int writeToFile(File aFile) {
        int result = super.writeToFile(aFile);
        //write Service and ServiceProvided lists
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

    public boolean addService(Service toAdd) {
        this.serviceList.add_service_to_list(toAdd);
        return true;
    }

    public boolean addServiceProvided(ServiceProvided toAdd) {
        this.serviceProvidedList.add_service_to_list(toAdd);
        return true;
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
