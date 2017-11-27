package com.company;

import java.util.Scanner;

public class OperatorProfile extends Profile {
    Scanner user = new Scanner(System.in);
    public OperatorProfile(Tree[] newData) {
        data = newData;
    }

    public boolean menu(){
        System.out.println("You're a ChocAn operator");
        System.out.println("Select an option:");
        System.out.println("1. Display all services");
        System.out.println("2. Edit services");
        System.out.println("0. Exit");
        int selection = user.nextInt();
        switch(selection){
            case 0:
                System.out.println("Thank you for using the CDPS.");
                return false;
            case 1:
                System.out.println(data[0].displayEntries());
                break;
            case 2:
                editTree(0);
                break;
        }
        return true;
    }


    private void editTree(int whichTree){
        System.out.println("Please select the operation to perform.");
        System.out.println("1. Add an entry");
        System.out.println("2. Remove an entry");
        System.out.println("0. Return");
        int selection = user.nextInt();
        switch(selection){
            case 0:
                break;
            case 1:
                System.out.println("This isn't implemented yet.");
                break;
            case 2:
                System.out.println("Please enter the ID of the entry to remove.");
                int id = user.nextInt();
                data[whichTree].removeEntry(id);
                break;

        }
    }
}
