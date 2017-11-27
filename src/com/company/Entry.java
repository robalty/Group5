//Author: Meera Murali
package com.company;

import java.io.File;
import java.util.Scanner;

public abstract class Entry extends Util{

    protected int idNum;    //ID number or service code
    protected Entry left;   //left child
    protected Entry right;  //right child

    //Default constructor
    //Initializes idNum to 0, left and right to null
    public Entry()
    {
        this.idNum = 0;
        this.left = this.right = null;
    }

    //Constructor with arguments
    //Sets idNum to argument value
    //Sets left and right to null
    public Entry(int id)
    {
        this.idNum = id;
        this.left = this.right = null;
    }

    //Copy Constructor
    //Sets current object's idNum to that of argument object
    //Initializes left and right to null
    public Entry(Entry toCopy)
    {
        copy(toCopy);
        this.left = this.right = null;
    }

    //Compares argument with idNum field
    //Returns current object reference if a match and null otherwise
    public Entry matchID(int id)
    {
        if (id == this.idNum)
            return this;

        return null;
    }

    //Compares current object's idNum with that of argument Entry object
    //Returns -2 if argument is null.
    //Returns -1 if idNum is lesser than,
    //         0 if equal to, and
    //         1 if greater than that of argument
    public int compare(Entry toCompare)
    {
        //Null argument
        if (toCompare == null)
            return -2;

        //Current object's id lesser than that of argument
        if (this.idNum < toCompare.idNum)
            return -1;

        //Current object's id matches that of argument
        if (this.idNum == toCompare.idNum)
            return 0;

        //Current object's id greater than that of argument
        return 1;
    }

    public int compare(String toCompare)
    {
        //Null argument
        if (toCompare == null)
            return -2;

        //Current object's id lesser than that of argument
        if (this.idNum < Integer.getInteger(toCompare))
            return -1;

        //Current object's id matches that of argument
        if (this.idNum == Integer.getInteger(toCompare))
            return 0;

        //Current object's id greater than that of argument
        return 1;
    }

    //Compares current object's idNum with argument int value
    //Returns -1 if idNum is lesser than,
    //         0 if equal to, and
    //         1 if greater than argument
    public int compare(int idtoCompare)
    {
        //Current object's id lesser than argument
        if (this.idNum < idtoCompare)
            return -1;

        //Current object's id matches argument
        if (this.idNum == idtoCompare)
            return 0;

        //Current object's id greater than argument
        return 1;
    }

    //Sets current object’s left to argument value
    //No return value
    public void setLeft(Entry toAdd)
    {
        this.left = toAdd;
    }

    //Sets current object’s right to argument value
    //No return value
    public void setRight(Entry toAdd)
    {
        this.right = toAdd;
    }

    //Returns current object’s left
    public Entry goLeft() {
        return this.left;
    }

    //Returns current object’s right
    public Entry goRight() {
        return this.right;
    }

    //ABSTRACT method to be implemented in derived classes
    //Prompts user and reads in new data for fields (depending upon derived class type)
    //Returns current object reference (update is successful),
    //        null (update failed)
    abstract public Entry update();

    //ABSTRACT method to be implemented in derived classes
    //Appends data to argument File in format (depending upon derived class type)
    //Returns 0 (Failure; Null argument)
    //       -1 (Failure; IO Exception)
    //        1 (Success)
    abstract public int writeToFile(File writeFile);

    //ABSTRACT method to be implemented in derived classes
    //Appends data to argument File in report format (depending upon derived class type)
    //Returns 0 (Failure; Null argument)
    //       -1 (Failure; File not found)
    //        1 (Success)
    abstract public int writeReport(File writeFile);

    //ABSTRACT method to be implemented in derived classes
    //Reads a single line from file using argument Scanner object, and copies
    //data into current object's fields
    //Returns 0 (Failure; Null argument)
    //       -1 (Failure; Unable to read in data for all fields)
    //        1 (Success)
    abstract public int loadFromFile(Scanner fileReader);

    //ABSTRACT method to be implemented in derived classes
    //Displays current object's data in format depending upon derived class type
    //No return value
    abstract public void display();

    //Copies argument object's idNum
    //Does not change left, right values
    //Returns null(Copy failure, argument is null), or
    //        current object reference (Copy success)
    protected Entry copy(Entry toCopy)
    {
        //If argument is not null, copy idNum value
        if (toCopy != null) {
            this.idNum = toCopy.idNum;
            return this;
        }

        return null;
    }

    abstract public int getNumberOfServices();

    abstract public double getTotalFee();
}