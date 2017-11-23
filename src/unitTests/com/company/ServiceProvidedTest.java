//Author: Meera Murali
package com.company;

import org.junit.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;

public class ServiceProvidedTest {
    @Test
    public void compareByServiceDate_usingArgumentObject() throws Exception
    {
        //Arrange
        Service testService = new Service(123456, "Current", 123.7);
        String earlierDate = "09/1/2016";
        String laterDate = "09/01/2018";
        String sameDate = "09/01/2017";
        String nullString = null;
        try {
            ServiceProvided testProvided = new ServiceProvided(testService, "09/1/2017",
                    987654321, "Member name",
                    987654321, "Provider name",
                    null);
            ServiceProvided earlier = new ServiceProvided(testService, earlierDate,
                    987654321, "Member name",
                    987654321, "Provider name",
                    null);
            ServiceProvided later = new ServiceProvided(testService, laterDate,
                    987654321, "Member name",
                    987654321, "Provider name",
                    null);
            ServiceProvided same = new ServiceProvided(testService, sameDate,
                    987654321, "Member name",
                    987654321, "Provider name",
                    null);

            //Act
            int result1 = testProvided.compareByServiceDate(earlier);
            int result2 = testProvided.compareByServiceDate(later);
            int result3 = testProvided.compareByServiceDate(same);
            int result4 = testProvided.compareByServiceDate(nullString);

            //Assert
            assertEquals(result1, 1);
            assertEquals(result2, -1);
            assertEquals(result3, 0);
            assertEquals(result4, -2);
        }

        catch (ParseException e)
        {
            System.out.println("Incorrect date format");
        }
    }

    @Test
    public void compareByServiceDate_usingArgumentString() throws Exception
    {
        //Arrange
        Service testService = new Service(123456, "Current", 123.7);
        String earlierDate = "08/1/2017";
        String laterDate = "10/1/2017";
        String sameDate = "09/01/2017";
        String nullString = null;
        try {
            ServiceProvided testProvided = new ServiceProvided(testService, "09/1/2017",
                    987654321, "Member name",
                    987654321, "Provider name",
                    null);

            //Act
            int result1 = testProvided.compareByServiceDate(earlierDate);
            int result2 = testProvided.compareByServiceDate(laterDate);
            int result3 = testProvided.compareByServiceDate("09/1/2017");
            int result4 = testProvided.compareByServiceDate(nullString);

            //Assert
            assertEquals(result1, 1);
            assertEquals(result2, -1);
            assertEquals(result3, 0);
            assertEquals(result4, -2);
        }

        catch (ParseException e)
        {
            System.out.println("Incorrect date format");
        }
    }

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
