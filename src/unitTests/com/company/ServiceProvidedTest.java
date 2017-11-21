package com.company;
/*
import org.junit.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;

public class ServiceProvidedTest {
    @Test
    public void display() throws Exception {
        //Arrange
        Service testService = new Service(123456, "Current", 123.7);
        try {
            ServiceProvided testProvided = new ServiceProvided(testService, "09/1/2017",
                    987654321, "Member name",
                    987654321, "Provider name",
                    null);


            //Act
            System.out.println("New Service Provided: ");
            testProvided.display();
        }
        catch (ParseException e)
        {
            System.out.println("Incorrect date format");
        }
    }


    @Test
    public void goNext() throws Exception {
        //Arrange: set up a list of three ServiceProvided objects
        //Arrange
        Service testService = new Service(123456, "Current", 123.7);
        Service testService2 = new Service(123457, "Service 2", 123.7);
        Service testService3 = new Service(123458, "Service 3", 123.7);

        ServiceProvided service1 = new ServiceProvided(testService, "09/1/2017",
                    987654321, "Member name",
                    987654321, "Provider name",
                    "Some comments....");
        ServiceProvided service2 = new ServiceProvided(testService2, "09/1/2017",
                987654321, "Member name",
                987654321, "Provider name",
                "Some comments....");
        ServiceProvided service3 = new ServiceProvided(testService3, "09/1/2017",
                987654321, "Member name",
                987654321, "Provider name",
                "Some comments....");

        service1.setNext(service2);
        service2.setNext(service3);
        Service current = service1;

        //Act: traverse to last Service
        while (current.goNext() != null)
            current = current.goNext();

        //Assert: that current is now at service3
        assertEquals(current, service3);
        current.display();
    }

    @Test
    public void writeToFile() throws Exception {
    }

    @Test
    public void writeReport() throws Exception {
    }

    @Test
    public void loadFromFile() throws Exception {
    }

}
