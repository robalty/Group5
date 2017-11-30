package com.company;

import java.util.Scanner;

public class ProviderProfile extends Profile {
    Scanner user = new Scanner(System.in);
    Provider You = null;

    ProviderProfile(Database[] newData, Provider login){
        data = newData;
        You = login;
    }

    public boolean menu(){
        System.out.println("Please select an option:");
        System.out.println("1. Member lookup");
        System.out.println("2. Display available services");
        System.out.println("0. Exit");
        int temp = user.nextInt();
        switch(temp){
            case 0:
                System.out.println("Thank you for using the CDPS.");
                return false;
            case 1:
                Member member = null;
                do {
                    System.out.println("Please enter the 9 digit Member ID#, or enter 0 to return to previous menu");
                    temp = user.nextInt();
                    if(temp == 0)
                        break;
                    member = searchMembers(temp);
                }while(member == null);
                memberMenu(member);
                break;
            case 2:
                You.displayServices();
                break;
        }
    return true;
    }

    public void memberMenu(Member patient){
        patient.display();
        System.out.println("Please select an option from the list below:");
        System.out.println("1. Add a new service record");
        System.out.println("0. Return");


    }
}