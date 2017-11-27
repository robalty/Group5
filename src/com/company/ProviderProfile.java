package com.company;

import java.util.Scanner;

public class ProviderProfile extends Profile {
    Scanner user = new Scanner(System.in);
    public ProviderProfile(Tree[] newData) {
        data = newData;
    }
    ProviderProfile(){

    }

    public boolean menu(){
        System.out.println("You're a provider");
        System.out.println("Select an option:");
        System.out.println("1. Display all services");
        System.out.println("0. Exit");
        int temp = user.nextInt();
        switch(temp){
            case 0:
                System.out.println("Thank you for using the CDPS.");
                return false;
            case 1:
                System.out.println(data[0].displayEntries());
                break;

        }
    return true;
    }
}
