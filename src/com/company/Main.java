package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Roland Ballinger
//Main.java

public class Main {
    protected static Scanner user = new Scanner(System.in);
    private static File[] dirLoad = new File[4];
    private static Database[] data = null;
    public static void main(String[] args) {
        dirLoad[0] = new File(".\\data_files\\serviceDB.txt");
        dirLoad[1] = new File(".\\data_files\\providerDB.txt");
        dirLoad[2] = new File(".\\data_files\\employeeDB.txt");
        dirLoad[3] = new File(".\\data_files\\memberDB.txt");
        data = populate(dirLoad);
        System.out.println("Welcome to the CDPS!\n");
        Profile curUser = null;
        int input = 0;
        while(curUser == null) {
            System.out.println("Enter 1 to login as a ChocAn network provider");
            System.out.println("Enter 2 to login as a ChocAn data center operator or administrator");
            System.out.println("Enter 0 to exit");
            input = user.nextInt();
            Entry temp = null;
            switch (input) {
                case (1):
                    System.out.println("Please enter your 9 digit Provider ID number.");
                    input = user.nextInt();
                    temp = data[1].searchEntry(input);
                    if (temp != null)
                        curUser = new ProviderProfile(data, (Provider) temp);
                    else
                        System.out.println("Invalid login ID.\n");
                    break;
                case (2):
                    System.out.println("Please enter your 9 digit ChocAn ID number.");
                    input = user.nextInt();
                    temp = data[2].searchEntry(input);
                    if (temp != null)
                        curUser = new OperatorProfile(data, (Employee) temp);
                    else
                        System.out.println("Invalid login ID.\n");
                    break;
                case(0):
                    return;
                default:
                    System.out.println("Invalid selection!\n");
            }
        }
        System.out.printf("Thank you, user #%d! You are now logged in.\n", input);
        while(curUser.menu());
        //saveData(data, dirLoad);
    }

    public static Database[] populate(File loadFrom[]){
        Database[] toCreate = new Database[4];
        for(int i = 0; i < 4; ++i)
            toCreate[i] = new Database();
        Entry temp = new Service();
        Scanner reader = null;
        for(int i = 0; i < loadFrom.length; ++i) {
            try {
                reader = new Scanner(loadFrom[i]);
            } catch (FileNotFoundException e) {
                System.out.println("Disk read error!");
            }
            switch(i) {
                case(0):
                    while (temp.loadFromFile(reader) > 0) {
                       toCreate[0].insertEntry(temp);
                       temp = new Service();
                    }
                    break;
                case(1):
                    temp = new Provider();
                    while(temp.loadFromFile(reader) > 0){
                        toCreate[1].insertEntry(temp);
                        temp = new Provider();
                    }
                    break;
                case(2):
                    temp = new Employee();
                    while(temp.loadFromFile(reader) > 0){
                        toCreate[2].insertEntry(temp);
                        temp = new Employee();
                    }
                    break;
                case(3):
                    temp = new Member();
                    while(temp.loadFromFile(reader) > 0){
                        toCreate[3].insertEntry(temp);
                        temp = new Member();
                    }
                    break;
            }
        }
        return toCreate;
    }

    public static void saveData(Database[] toSave, File[] toWrite){
        for(int i = 0; i < 4; ++i) {
            toSave[i].writeEntries(toWrite[i]);
        }
    }
}
