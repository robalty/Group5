package com.company;

import java.io.File;
import java.util.Scanner;

public class Service extends Entry {

    protected String name;  //name of service
    protected double fee;   //service fee
    protected Service next; //reference to next service in list



    //Default constructor
    public Service()
    {
        //Initializes idNum to 0, left and right to null
        super();

        //Initializes name and next to null, fee to 0.0
        this.name = null;
        this.fee = 0.0;
        this.next = null;
    }



    //Constructor with arguments
    public Service(int id, String aName, double aFee)
    {
        //Copy idNum,
        //set left and right to null
        super(id);

        //Copy name and fee
        this.name = aName;
        this.fee = aFee;

        //Set next to null
        this.next = null;
    }



    //Copy Constructor
    //Sets current object's idNum, name and fee to those of argument Service
    //Initializes left, right and next fields to null
    public Service (Service toCopy)
    {
        //Copy idNum,
        //set left and right to null
        super(toCopy);

        //Copy name and fee
        this.name = toCopy.name;
        this.fee = toCopy.fee;

        //Set next to null
        this.next = null;
    }



    //Displays service code, name and fee (each on a new line, indented)
    //No return value
    public void display()
    {
        System.out.println("\tService code: " + idNum);
        System.out.println("\tService name: " + name);
        System.out.println("\tService fee: " + fee);
    }



    //Updates service
    //      Prompts user and reads in new fee for service
    //      Echos back new data to user for confirmation
    //      If user confirms, updates current object with new data
    //      Otherwise allows user to re enter data
    //Returns current object reference if update is successful, null otherwise
    public Service update()
    {
        boolean reEnterData;    //Flag if user wants to re-enter data
        boolean confirmData;    //Flag if user confirms that new data is correct

        //Display current data
        System.out.println("\nCurrent Entry: ");
        display();

        //Temporary object to read data and confirm with user
        //before updating current object
        Service temp = new Service();
        temp.copy(this);

        do {
            //Reset flags
            reEnterData = false;
            confirmData = true;

            //Read in new fee
            System.out.print("\nEnter new fee: ");
            temp.fee = input.nextDouble();
            input.nextLine();

            //Echo back to user to confirm
            System.out.println("\nUpdated Entry: ");
            temp.display();

            //If user does not confirm, allow them to re-enter data
            if (!confirm())
            {
                confirmData = false;

                if (tryAgain())
                    reEnterData = true;
            }
        } while (reEnterData);

        //If user has confirmed data, copy new data into current object
        //and return current object reference
        if (confirmData && copy(temp) != null)
            return this;

        //Otherwise return null
        return null;
    }


    //Compares current service's name with that of argument
    //Returns -2 if argument is null.
    //Returns -1 if current object's name is lesser than,
    //         0 if equal to, and
    //         1 if greater than that of argument
    public int compareByName(Service toCompare)
    {
        //Null argument
        if (toCompare == null)
            return -2;

        int compareResult = this.name.compareTo(toCompare.name);

        //Current object's name lesser than that of argument
        if (compareResult < 0)
            return -1;

        //Current object's name matches that of argument
        if (compareResult == 0)
            return 0;

        //Current object's name greater than that of argument
        return 1;
    }



    //Sets current object’s next to argument value
    //No return value
    public void setNext(Service toAdd)
    {
        this.next = toAdd;
    }



    //Returns current object’s next
    public Entry goNext() {
        return this.next;
    }



    public int writeToFile(File writeFile)
    {
        int success = 0;

        return success;
    }



    public int writeReport(File writeFile)
    {
        int success = 0;

        return success;
    }



    public int loadFromFile(Scanner fStream)
    {
        int success = 0;

        return success;
    }



    //Copies argument object's idNum, name and fee
    //Does not change left, right, next values
    //Returns null(Copy failure, argument is null), or
    //        current object reference (Copy success)
    protected Service copy (Service toCopy)
    {
        //copy idNum
        //(if toCopy is null super's copy method returns null)
        if (super.copy(toCopy) != null)
        {
            //copy name and fee
            this.name = toCopy.name;
            this.fee = toCopy.fee;

            return this;
        }

        return null;
    }



    public static void main(String[] args) {

        //Testing update()
        System.out.println("Testing ");
        Service test = new Service(123456, "Test Service", 123.7);
        if (test.update() != null) {
            System.out.println("\nUpdate successful!");
        }
        test.display();
    }
}
