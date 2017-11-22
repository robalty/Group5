package com.company;
/*
import org.junit.Test;
import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class ServiceTest {
    @Test
    public void display() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void compareByName() throws Exception {
        //Arrange
        Service testService = new Service(123456, "Current", 123.7);
        Service nullReference = null;
        Service nameLesserThanCurrent = new Service(123455, "ACurrent", 123.7);
        Service nameEqualToCurrent = new Service(123456, "Current", 123.7);
        Service nameGreaterThanCurrent = new Service(123457, "DCurrent", 123.7);

        //Act
        int result0 = testService.compare(nullReference);
        int result1 = testService.compare(nameLesserThanCurrent);
        int result2 = testService.compare(nameEqualToCurrent);
        int result3 = testService.compare(nameGreaterThanCurrent);

        //Assert
        assertEquals(result0, -2);
        assertEquals(result1, 1);
        assertEquals(result2, 0);
        assertEquals(result3, -1);
    }

    @Test
    public void setNext() throws Exception {
        //Arrange
        Service service1 = new Service(123456, "Service 1", 123.7);
        Service service2 = new Service(123457, "Service 2", 123.7);

        //Act
        service1.setNext(service2);

        //Assert
        assertEquals(service1.next, service2);
        assertEquals(service2.next, null);
    }

    @Test
    public void goNext() throws Exception {
        //Arrange: set up a list of three services
        Service service1 = new Service(123456, "Service 1", 123.7);
        Service service2 = new Service(123457, "Service 2", 123.7);
        Service service3 = new Service(123458, "Service 3", 123.7);
        service1.setNext(service2);
        service2.setNext(service3);
        Service current = service1;

        //Act: traverse to last Service
        while (current.goNext() != null)
            current = current.goNext();

        //Assert: that current is now at service3
        assertEquals(current, service3);
    }

    @Test
    public void writeToFile() throws Exception {
    }

    @Test
    public void writeReport() throws Exception {
    }

    @Test
    public void loadFromFile() throws Exception
    {
        //Arrange
        Service testService = new Service();
        File file = new File("data_files/serviceDB.txt");
        Scanner fileReader = new Scanner(file);
        int read = 0;
        int notRead = 0;

        //Act: For each line in file, create a new service and read in data
        while (fileReader.hasNext())
        {
            Service newService = new Service();
            try
            {
                int result = newService.loadFromFile(fileReader);
                if (result == 1)
                {
                    ++read;
                    System.out.println("Read " + read);
                    newService.display();
                }

                else if (result == -1)
                {
                    System.out.println("Data not found for all fields");
                    ++notRead;
                }

                else
                    ++notRead;
            }

            catch (NumberFormatException e)
            {
                System.out.println("Unable to parse number");
                ++notRead;
            }
        }

        //If all lines in file have not been read successfully
        if (notRead != 0)
            System.out.println("\n*** Unable to read: " + notRead + " lines ***");

        //Assert
        assertEquals(notRead, 0);
        assertEquals(testService.loadFromFile(null), 0);
    }

    @Test
    public void copy() throws Exception {
        //Arrange
        Service testService = new Service(123456, "Test Service", 123.7);
        Service copyService = new Service();

        //Act
        Service copied = copyService.copy(testService);

        //Assert
        assertEquals(testService.idNum, copyService.idNum);
        assertEquals(testService.name, copyService.name);
        assertEquals(testService.fee, copyService.fee, 0.0);
        assertEquals(copied, copyService);
    }

    @Test
    public void matchID() throws Exception {
        //Arrange
        Service testService = new Service(123456, "Test Service", 123.7);
        Entry resultMatchFound, resultMatchNotFound;

        //Act
        resultMatchFound = testService.matchID(123456);
        resultMatchNotFound = testService.matchID(123444);


        //Assert
        assertEquals(resultMatchFound, testService);
        assertEquals(resultMatchNotFound, null);
    }

    @Test
    public void compareUsingEntry() throws Exception {
        //Arrange
        Service testService = new Service(123456, "Test Service", 123.7);
        Service nullReference = null;
        Service idLesserThanCurrent = new Service(123455, "Lesser", 123.7);
        Service idEqualToCurrent = new Service(123456, "Equal", 123.7);
        Service idGreaterThanCurrent = new Service(123457, "Greater", 123.7);

        //Act
        int result0 = testService.compare(nullReference);
        int result1 = testService.compare(idLesserThanCurrent);
        int result2 = testService.compare(idEqualToCurrent);
        int result3 = testService.compare(idGreaterThanCurrent);

        //Assert
        assertEquals(result0, -2);
        assertEquals(result1, 1);
        assertEquals(result2, 0);
        assertEquals(result3, -1);
    }

    @Test
    public void compareUsingId() throws Exception {
        //Arrange
        Service testService = new Service(123456, "Test Service", 123.7);
        int idLesserThanCurrent = 123455;
        int idEqualToCurrent = 123456;
        int idGreaterThanCurrent = 123457;

        //Act
        int result1 = testService.compare(idLesserThanCurrent);
        int result2 = testService.compare(idEqualToCurrent);
        int result3 = testService.compare(idGreaterThanCurrent);

        //Assert
        assertEquals(result1, 1);
        assertEquals(result2, 0);
        assertEquals(result3, -1);
    }

    @Test
    public void setLeft() throws Exception {
    }

    @Test
    public void setRight() throws Exception {
    }

    @Test
    public void goLeft() throws Exception {
    }

    @Test
    public void goRight() throws Exception {
    }

}
*/